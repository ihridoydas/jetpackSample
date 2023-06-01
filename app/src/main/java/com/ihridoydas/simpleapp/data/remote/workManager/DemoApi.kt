package com.ihridoydas.simpleapp.data.remote.workManager

import com.ihridoydas.simpleapp.data.models.workManger.Post
import retrofit2.Response
import retrofit2.http.GET

interface DemoApi{

    @GET("posts/1")
    suspend fun getPost():Response<Post>
}