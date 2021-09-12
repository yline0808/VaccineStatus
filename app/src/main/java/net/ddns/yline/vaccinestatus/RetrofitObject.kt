package net.ddns.yline.vaccinestatus

import retrofit2.Retrofit

object RetrofitObject{
    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.URL_VACCINE)
            .build()
    }

    fun getApiService():ApiService{
        return getRetrofit().create(ApiService::class.java)
    }
}