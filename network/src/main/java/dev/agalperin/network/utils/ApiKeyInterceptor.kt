package dev.agalperin.network.utils

import okhttp3.Interceptor
import okhttp3.Response

internal class ApiKeyInterceptor(private val apiKey: String): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val httpUrl = chain.request().url.newBuilder()
            .addQueryParameter("apiKey", apiKey)
            .build()

        val request = chain.request()
            .newBuilder()
            .url(httpUrl)
            .build()


        val response = chain.proceed(request)

        return response
    }
}