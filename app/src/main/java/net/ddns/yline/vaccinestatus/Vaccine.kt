package net.ddns.yline.vaccinestatus

data class Vaccine(
    val accumulatedFirstCnt:Int,    // 전일까지의 누적 통계 1차
    val accumulatedSecondCnt:Int,   // 전일까지의 누적 통계 2차
    val baseDate:String,            // 통계 기준일자
    val firstCnt:Int,               // 당일 통계 1차
    val secondCnt:Int,              // 당일 통계 2차
    val sido:String,                // 지역명칭
    val totalFirstCnt:Int,          // 전체 누적 통계 1차
    val totalSecondCnt:Int          // 전체 누적 통계 2차
)