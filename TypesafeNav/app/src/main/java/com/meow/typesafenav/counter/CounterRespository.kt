package com.meow.typesafenav.counter

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CounterRepository @Inject constructor(): CounterRepositoryImpl {
    override suspend fun increaseCounter(counter: Int):Int{
        return counter+1
    }
}