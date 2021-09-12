package net.ddns.yline.vaccinestatus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import net.ddns.yline.vaccinestatus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Log.d("test", "${BuildConfig.URL_VACCINE}")
    }
}