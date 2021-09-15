package net.ddns.yline.vaccinestatus

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query


interface ApiService {
    @GET("api/15077756/v1/vaccine-stat")
    fun getInfo(
        @Query("perPage")PerPage:Int,
        @Query("page")Page:Int,
        @Query("cond[baseDate::EQ]")FindDate:String,
        @Query("serviceKey")ServiceKey:String = BuildConfig.API_KEY
    ):Call<VaccineBody>
}