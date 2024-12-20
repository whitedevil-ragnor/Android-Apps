package com.meow.typesafenav.counter

interface CounterRepositoryImpl {
    suspend fun increaseCounter(counter: Int):Int
}