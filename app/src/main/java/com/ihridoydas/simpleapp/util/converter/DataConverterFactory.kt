/*
 * Created by masaki on 2022/09/26
 * Last modified 2022/07/19 18:23
 * Copyright © 2022 Cognivision Inc. All rights reserved.
 */

package com.ihridoydas.simpleapp.util.converter

import com.google.gson.reflect.TypeToken
import com.ihridoydas.simpleapp.data.entities.Data
import com.ihridoydas.simpleapp.data.remote.APIVersion
import com.ihridoydas.simpleapp.data.remote.APIVersions
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class DataConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {

        if (!canHandle(annotations)) {
            return null
        }

        return try {
            val dataType = TypeToken.getParameterized(Data::class.java, type).type
            val converter: Converter<ResponseBody, Data<Any>>? = retrofit.nextResponseBodyConverter(
                this,
                dataType,
                annotations
            )
            DataConverter(converter)
        } catch (e: Exception) {
            null
        }
    }

    private fun canHandle(annotations: Array<Annotation>): Boolean {
        for (annotation in annotations) {
            if (APIVersion::class == annotation.annotationClass) {
                if ((annotation as APIVersion).version < APIVersions.VERSION_DOT1) {
                    return false
                }
                break
            }
        }
        return true
    }
}
