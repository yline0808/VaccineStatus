package net.ddns.yline.vaccinestatus

import com.google.gson.annotations.SerializedName

data class VaccineBody(
    @SerializedName("currentCount") val currentCount:Int,
    @SerializedName("data") val data:List<Vaccine>,
    @SerializedName("matchCount") val matchCount:Int,
    @SerializedName("page") val page:Int,
    @SerializedName("perPage") val perPage:Int,
    @SerializedName("totalCount") val totalCount:Int
)

data class Vaccine(
    @SerializedName("accumulatedFirstCnt") val accumulatedFirstCnt:Int,     // 전일까지의 누적 통계 1차
    @SerializedName("accumulatedSecondCnt") val accumulatedSecondCnt:Int,   // 전일까지의 누적 통계 2차
    @SerializedName("baseDate") val baseDate:String,                        // 통계 기준일자
    @SerializedName("firstCnt") val firstCnt:Int,                           // 당일 통계 1차
    @SerializedName("secondCnt") val secondCnt:Int,                         // 당일 통계 2차
    @SerializedName("sido") val area:String,                                // 지역명칭
    @SerializedName("totalFirstCnt") val totalFirstCnt:Int,                 // 전체 누적 통계 1차
    @SerializedName("totalSecondCnt") val totalSecondCnt:Int                // 전체 누적 통계 2차
){
    override fun toString(): String {
        return "Vaccine : [\n" +
                "    accumulatedFirstCnt : ${accumulatedFirstCnt}\n" +
                "    accumulatedSecondCnt : ${accumulatedSecondCnt}\n" +
                "    baseDate : ${baseDate}\n" +
                "    firstCnt : ${firstCnt}\n" +
                "    secondCnt : ${secondCnt}\n" +
                "    area : ${area}\n" +
                "    totalFirstCnt : ${totalFirstCnt}\n" +
                "    totalSecondCnt : ${totalSecondCnt}]\n\n"
    }
}