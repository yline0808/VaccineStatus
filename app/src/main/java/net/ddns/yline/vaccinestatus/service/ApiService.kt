package net.ddns.yline.vaccinestatus.service

import net.ddns.yline.vaccinestatus.BuildConfig
import net.ddns.yline.vaccinestatus.dto.VaccineCentersBody
import net.ddns.yline.vaccinestatus.dto.VaccineStatusBody
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("{endPoint}")
    fun getVaccineStatus(
        @Path("endPoint", encoded = true)endPoint:String,
        @Query("perPage")perPage:Int = 10,
        @Query("page")page:Int = 1,
        @Query("cond[baseDate::EQ]")findDate:String? = null,
        @Query("serviceKey")serviceKey:String = BuildConfig.API_KEY
    ):Call<VaccineStatusBody>

    @GET("{endPoint")
    fun getVaccineCenters(
        @Path("endPoint", encoded = true)endPoint: String,
        @Query("perPage")PrePage:Int = 10,
        @Query("page")page:Int = 1,
        @Query("serviceKey")serviceKey: String = BuildConfig.API_KEY
    ):Call<VaccineCentersBody>
}
