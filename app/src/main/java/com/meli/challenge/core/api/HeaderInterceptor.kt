package com.meli.challenge.core.api

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        /**
         * Do that you want with response
         * Example :
         *
         * val res = chain.proceed(chain.request().newBuilder().build())
         * if (res.code == 401){
         *  clear Token
         * }
         * return res
         **/
        return chain.proceed(chain.request().newBuilder().build())
    }
}