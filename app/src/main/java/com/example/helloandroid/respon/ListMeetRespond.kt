package com.example.helloandroid.respon

import com.google.gson.annotations.SerializedName

class ListMeetRespond {
    @SerializedName("ïd")
    var id: Int? = null

    @SerializedName("attributes")
    var attributes: ListMeetAttributes? = null
}

class ListMeetAttributes{
    @SerializedName("nameState")
    val nameState : String? = null

    @SerializedName("eventState")
    val eventState : String? = null

    @SerializedName("emailState")
    val emailState : String? = null

    @SerializedName("zoomState")
    val zoomState : String? = null

    @SerializedName("dateState")
    val dateState : String? = null
}
