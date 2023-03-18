/*
 * Created by masaki on 2023/01/16
 * Last modified 2023/01/16 11:35
 * Copyright © 2023 Cognivision Inc. All rights reserved.
 */

package com.ihridoydas.simpleapp.util.converter

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import java.lang.reflect.Type;

class NullOnEmptyConverterFactory : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        val delegate: Converter<ResponseBody, *> =
            retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
        return Converter<ResponseBody, Any?> { body ->
            if (body.contentLength() == -1L) {
                null
            } else  {
                delegate.convert(body)
            }
        }
    }
}
