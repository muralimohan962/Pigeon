package com.code.pegion.concurrency

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

object AppExeUtil {

    fun getScheduledExecutorService(): ScheduledExecutorService {
        return Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors())
    }
}