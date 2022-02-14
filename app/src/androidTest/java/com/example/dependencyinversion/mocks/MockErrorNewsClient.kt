package com.example.dependencyinversion.mocks

import com.example.dependencyinversion.api.INewsClient
import com.example.dependencyinversion.callbacks.INewsCallback

class MockErrorNewsClient : INewsClient {
    override fun get(callback: INewsCallback) {
        callback.onError("Not Found")
    }
}