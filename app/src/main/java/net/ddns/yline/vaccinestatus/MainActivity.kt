package net.ddns.yline.vaccinestatus

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import net.ddns.yline.vaccinestatus.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    // 오늘 날짜 설정
    private var findDate = SimpleDateFormat("yyyy-MM-dd 00:00:00", Locale.getDefault()).format(Date(System.currentTimeMillis()))

    // === 생명주기 ===
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 리스너 등록
        setListener()
        // 액티비티 생성시 오늘 날짜로 백신정보 불러옴
        getVaccineStatus()
        binding.textviewDate.text = findDate
    }

    // === 기능 ===
    // 리스너 등록
    private fun setListener(){
        with(binding){
            ClickListener().also {
                buttonGet.setOnClickListener(it)
                buttonDateDialog.setOnClickListener(it)
            }
        }
    }

    // 백신 정보 불러오기
    private fun getVaccineStatus(){
        Log.d("print findDate",findDate)
        RetrofitObject.getApiService().getInfo(20, 1, findDate).enqueue(object : Callback<VaccineBody>{
            // api 호출 성공시
            override fun onResponse(call: Call<VaccineBody>, response: Response<VaccineBody>) {
                setResponseText(response.code(), response.body())
                Toast.makeText(applicationContext, "success", Toast.LENGTH_SHORT).show()
            }

            // api 호출 실패시
            override fun onFailure(call: Call<VaccineBody>, t: Throwable) {
                Log.e("retrofit2 error", "${t.printStackTrace()}")
                Toast.makeText(applicationContext, "fail", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setResponseText(resCode:Int, body:VaccineBody?){
        // 상태별 text 지정
        binding.textviewResponse.text = when(resCode){
            200 -> {
                if(body == null){
                    "api body가 비어있습니다."
                }else{
                    if(body.data.toString() == "[]"){
                        "api호출은 성공했으나 해당 날짜에 데이터가 없습니다."
                    }else{
                        body.toString()
                    }
                }
            }
            400 -> {
                "API 키가 만료됬거나 쿼리 파라미터가 잘못 됬습니다."
            }
            401 -> {
                "인증 정보가 정확하지 않습니다."
            }
            500 -> {
                "API 서버에 문제가 발생하였습니다."
            }
            else -> {
                "기타 문제발생..."
            }
        }
    }

    // 날짜 선택 다이얼로그 생성
    private fun dateDialog(){
        val setDate = findDate
            .substring(0, findDate.indexOf(" "))
            .split("-")
            .map { it.toInt() }.toIntArray()
        DatePickerDialog(this, DateSetListener(), setDate[0], setDate[1]-1, setDate[2]).show()
    }

    // === 이밴트 클래스 ===
    // 버튼 클릭 리스너
    inner class ClickListener:View.OnClickListener{
        override fun onClick(v: View?) {
            binding.run {
                when(v?.id){
                    buttonGet.id -> getVaccineStatus()
                    buttonDateDialog.id -> dateDialog()
                    else -> Log.e("click listener null", "onClick view is null")
                }
            }
        }
    }

    // 날자 선택 리스너
    inner class DateSetListener:DatePickerDialog.OnDateSetListener{
        override fun onDateSet(p0: DatePicker, p1: Int, p2: Int, p3: Int) {
            findDate = String.format("%d-%02d-%02d 00:00:00",p1, p2+1, p3)
            binding.textviewDate.text = findDate
            getVaccineStatus()
        }
    }
}