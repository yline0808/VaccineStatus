package net.ddns.yline.vaccinestatus

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import net.ddns.yline.vaccinestatus.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var findDate = SimpleDateFormat("yyyy-MM-dd 00:00:00", Locale.getDefault()).format(Date(System.currentTimeMillis()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListener()
        getRetrofit()
        binding.textviewDate.text = findDate
    }

    private fun setListener(){
        with(binding){
            ClickListener().also {
                buttonGet.setOnClickListener(it)
                buttonDateDialog.setOnClickListener(it)
            }
        }
    }

    private fun getRetrofit(){
        Log.d("getretrofit","|${findDate}|")
        RetrofitObject.getApiService().getInfo(20, 1, findDate).enqueue(object : Callback<VaccineBody>{
            override fun onResponse(call: Call<VaccineBody>, response: Response<VaccineBody>) {
                binding.textviewResponse.text = when(response.code()){
                    200 -> {
                        if(response.body()?.data.toString() == "[]"){
                            "api호출은 성공했으나 해당 날짜에 데이터가 없습니다."
                        }else{
                            response.body()?.data.toString()
                        }
                    }
                    400 -> "API 키가 만료됬거나 쿼리 파라미터가 잘못 됬습니다."
                    401 -> "인증 정보가 정확하지 않습니다."
                    500 -> "API 서버에 문제가 발생하였습니다."
                    else -> "기타 문제발생..."
                }
            }

            override fun onFailure(call: Call<VaccineBody>, t: Throwable) {
                Log.e("retrofit2 error", "${t.printStackTrace()}")
            }
        })
    }

    private fun dateDialog(){
        val cal = Calendar.getInstance()    //캘린더뷰 만들기
        Log.d("test getinstance", "${cal.get(Calendar.DAY_OF_MONTH)}")
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, i, i2, i3 ->
            findDate = String.format("%d-%02d-%02d 00:00:00",i, i2+1, i3)
            binding.textviewDate.text = findDate
        }
        DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
    }

    inner class ClickListener:View.OnClickListener{
        override fun onClick(v: View?) {
            binding.run {
                when(v?.id){
                    buttonGet.id -> getRetrofit()
                    buttonDateDialog.id -> dateDialog()
                    else -> Log.e("click listener null", "onClick view is null")
                }
            }
        }
    }
}