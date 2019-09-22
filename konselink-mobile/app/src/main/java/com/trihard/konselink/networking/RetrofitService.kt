package com.trihard.konselink.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    companion object {
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://konselink.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun <S> createService(serviceClass: Class<S>): S {
            return retrofit.create(serviceClass)
        }
    }
}