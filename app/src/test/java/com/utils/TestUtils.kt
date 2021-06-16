package com.utils

import com.google.gson.Gson

object TestUtils {

    inline fun <reified T>getObjectFromJsonResource(filename: String) : T =
         Gson().fromJson(getStringResource("responses/${filename}.json"),T::class.java)

    fun getStringResource(filePath: String) =
        javaClass.classLoader?.getResourceAsStream(filePath)?.bufferedReader()?.readText() ?: ""

}