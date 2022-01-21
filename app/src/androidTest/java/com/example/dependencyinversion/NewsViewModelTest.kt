package com.example.dependencyinversion

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.launchActivity
import junit.framework.TestCase
import org.junit.Rule
import java.util.concurrent.CountDownLatch

class NewsViewModelTest : TestCase() {

    private val signal = CountDownLatch(1)
    private lateinit var viewModel: NewsViewModel;

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    public override fun setUp() {
        super.setUp()
        this.viewModel = NewsViewModel();
    }

    public override fun tearDown() {

    }

    fun testFetchData_makesNewsApiGetRequest_postValueToNewsItems() {

        val scenario = launchActivity<MainActivity>()

        scenario.onActivity { activity ->
            activity.runOnUiThread {
                this.viewModel.newsItems.observeForever() { items ->
                    assertEquals(3, items.size)
                    signal.countDown()
                }

                this.viewModel.fetchData()
            }
        }

        signal.await()
    }
}