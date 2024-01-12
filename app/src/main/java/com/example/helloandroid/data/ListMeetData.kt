package com.example.helloandroid.data

import com.google.gson.annotations.SerializedName

class ListMeetDataWrapper(
    @SerializedName("data")
    val data: ListMeetData? = null
)
class ListMeetData (
    @SerializedName("nameState")
    val nameState : String? = null,

    @SerializedName("eventState")
    val eventState : String? = null,

    @SerializedName("emailState")
    val emailState : String? = null,

    @SerializedName("zoomState")
    val zoomState : String? = null,

    @SerializedName("dateState")
    val dateState : String? = null,
)