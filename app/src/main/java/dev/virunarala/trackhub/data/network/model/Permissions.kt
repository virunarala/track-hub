package dev.virunarala.trackhub.data.network.model

import com.squareup.moshi.Json

data class Permissions(

    @Json(name = "admin")
    val admin: Boolean,

    @Json(name = "pull")
    val pull: Boolean,

    @Json(name = "triage")
    val triage: Boolean?,

    @Json(name = "push")
    val push: Boolean,

    @Json(name = "maintain")
    val maintain: Boolean?
)
