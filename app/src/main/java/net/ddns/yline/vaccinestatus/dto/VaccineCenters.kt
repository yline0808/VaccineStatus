package net.ddns.yline.vaccinestatus.dto

import com.google.gson.annotations.SerializedName

data class VaccineCentersBody(
    @SerializedName("currentCount") val currentCount:Int,
    @SerializedName("data") val data:List<VaccineCenters>,
    @SerializedName("matchCount") val matchCount:Int,
    @SerializedName("page") val page:Int,
    @SerializedName("perPage") val perPage:Int,
    @SerializedName("totalCount") val totalCount:Int
)

data class VaccineCenters(
    @SerializedName("id") val id:Int,
    @SerializedName("address") val address:String,
    @SerializedName("centerName") val centerName:String,
    @SerializedName("centerType") val centerType:String,
    @SerializedName("facilityName") val facilityName:String,
    @SerializedName("lat") val lat:String,
    @SerializedName("lng") val lng:String,
    @SerializedName("phoneNumber") val phoneNumber:String
)

