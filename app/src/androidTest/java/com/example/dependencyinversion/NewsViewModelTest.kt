package com.example.dependencyinversion

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import com.example.dependencyinversion.mocks.MockErrorNewsClient
import com.example.dependencyinversion.mocks.MockNewsClient
import junit.framework.TestCase
import org.junit.Rule
import org.mockito.MockitoAnnotations
import java.util.concurrent.CountDownLatch

class NewsViewModelTest : TestCase() {

    private lateinit var signal: CountDownLatch
    private lateinit var viewModel: NewsViewModel
    private lateinit var scenario: ActivityScenario<MainActivity>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    public override fun setUp() {
        super.setUp()
        MockitoAnnotations.openMocks(this)
        this.signal = CountDownLatch(1)
        this.viewModel = NewsViewModel(MockNewsClient())
        this.scenario = launchActivity()
    }

    public override fun tearDown() {

    }

    fun testFetchData_makesNewsApiGetRequest_postValueToNewsItems() {

        this.scenario.onActivity { activity ->
            activity.runOnUiThread {
                this.viewModel.newsItems.observeForever() { items ->

                    assertEquals(3, items.size)

                    assertEquals("Title One", items[0].title)
                    assertEquals("describe 1", items[0].newsMetaData.description)
                    assertEquals("http://www.image.com/1", items[0].newsMetaData.imageUrl)
                    assertEquals("http://test.com/article1", items[0].newsMetaData.url)

                    assertEquals("Title Two", items[1].title)
                    assertEquals("describe 2", items[1].newsMetaData.description)
                    assertEquals("http://www.image.com/2", items[1].newsMetaData.imageUrl)
                    assertEquals("http://test.com/article2", items[1].newsMetaData.url)

                    assertEquals("Title Three", items[2].title)
                    assertEquals("describe 3", items[2].newsMetaData.description)
                    assertEquals("http://www.image.com/3", items[2].newsMetaData.imageUrl)
                    assertEquals("http://test.com/article3", items[2].newsMetaData.url)

                    assertEquals("", viewModel.errorMessage.value)

                    this.signal.countDown()
                }

                this.viewModel.fetchData()
            }
        }

        this.signal.await()
    }

    fun testFetchData_apiError_postValueToErrorMessage() {

        val vm = NewsViewModel(MockErrorNewsClient())

        this.scenario.onActivity { activity ->
            activity.runOnUiThread {
                vm.errorMessage.observeForever() { message ->

                    assertEquals("Not Found", vm.errorMessage.value)

                    this.signal.countDown()
                }

                vm.fetchData()
            }
        }

        this.signal.await()

    }
}