package com.ihridoydas.simpleapp.data.entities

class Data<T> {
    var data: Response<T>? = null
}

class Response<T> {
    var response: T? = null
}
