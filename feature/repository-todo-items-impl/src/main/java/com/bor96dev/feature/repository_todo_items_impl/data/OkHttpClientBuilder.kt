package com.bor96dev.feature.repository_todo_items_impl.data

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

object OkHttpClientBuilder {

    fun get(): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()

        val interceptor = object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()

                val request = original.newBuilder()
                    .header("Authorization", "Bearer carpinus")
                    .method(original.method, original.body)
                    .build()

                return chain.proceed(request)
            }
        }

        httpClientBuilder.addInterceptor(interceptor)

        return httpClientBuilder.build()
    }
}