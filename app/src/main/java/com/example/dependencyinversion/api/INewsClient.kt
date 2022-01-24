package com.example.dependencyinversion.api

import com.example.dependencyinversion.callbacks.INewsCallback


interface INewsClient {
    fun get(callback: INewsCallback)
}



//https://img.staging.medscape.com/pi/iphone/testassets/sampleData.json