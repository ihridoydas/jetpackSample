package com.ihridoydas.simpleapp.util.converter

import com.ihridoydas.simpleapp.data.entities.Data
import okhttp3.ResponseBody
import retrofit2.Converter

class DataConverter<Any>(
    private val delegate: Converter<ResponseBody, Data<Any>>?
) : Converter<ResponseBody, Any> {
    override fun convert(value: ResponseBody): Any? {
        val graphQLDataModel = delegate?.convert(value)
        return graphQLDataModel?.data?.response
    }
}
