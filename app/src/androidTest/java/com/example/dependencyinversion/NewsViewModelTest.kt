package com.example.dependencyinversion

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import junit.framework.TestCase
import org.junit.Rule
import java.util.concurrent.CountDownLatch

class NewsViewModelTest : TestCase() {

    private lateinit var signal: CountDownLatch
    private lateinit var viewModel: NewsViewModel
    private lateinit var scenario: ActivityScenario<MainActivity>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    public override fun setUp() {
        super.setUp()

        this.signal = CountDownLatch(1)
        this.viewModel = NewsViewModel()
        this.scenario = launchActivity()
    }

    public override fun tearDown() {

    }

    fun testFetchData_makesNewsApiGetRequest_postValueToNewsItems() {

        this.scenario.onActivity { activity ->
            activity.runOnUiThread {
                this.viewModel.newsItems.observeForever() { items ->
                    assertEquals(3, items.size)
                    assertEquals("Title One", items[0])
                    assertEquals("Title Two", items[1])
                    assertEquals("Title Three", items[2])

                    this.signal.countDown()
                }

                this.viewModel.fetchData()
            }
        }

        this.signal.await()
    }
}