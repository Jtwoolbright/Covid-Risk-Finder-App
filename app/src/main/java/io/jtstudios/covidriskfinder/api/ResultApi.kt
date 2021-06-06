package io.jtstudios.covidriskfinder.api

import io.jtstudios.covidriskfinder.model.PatientData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ResultApi {

    @POST("/result")
    fun postPatientData(@Body patient: PatientData) : Call<Int>

}