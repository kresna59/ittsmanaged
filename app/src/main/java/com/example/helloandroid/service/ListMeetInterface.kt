package com.example.helloandroid.service

import com.example.helloandroid.data.ListMeetDataWrapper
import com.example.helloandroid.respon.ApiRespon
import com.example.helloandroid.respon.ListMeetRespond
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ListMeetInterface {
    @POST("listmeets")
    fun addList(
        @Body body: ListMeetDataWrapper
    ): Call<ApiRespon<ListMeetRespond>>
}