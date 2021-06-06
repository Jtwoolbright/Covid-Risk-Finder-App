package io.jtstudios.covidriskfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import io.jtstudios.covidriskfinder.api.ResultApi
import io.jtstudios.covidriskfinder.model.PatientData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ResultActivity : AppCompatActivity() {

    private lateinit var resultText: TextView
    private lateinit var resultImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var patientData = this.intent.getParcelableExtra<PatientData>("patientData")

        if (patientData == null) {
            patientData = PatientData()
        }
        resultImage = findViewById(R.id.riskImage)
        resultText = findViewById(R.id.resultText)
        resultText.text = getString(R.string.calulating)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://covid-risk-app-api.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ResultApi::class.java)

        val retrofitData = retrofitBuilder.postPatientData(patientData)

        retrofitData.enqueue(object : Callback<Int?> {
            override fun onResponse(call: Call<Int?>, response: Response<Int?>) {
                val responseBody = response.body()
                Log.d("Response", responseBody.toString())
                when(responseBody.toString()) {
                    "2" -> {
                        resultImage.setImageResource(R.drawable.high_risk)
                        resultText.text = getString(R.string.high_risk_result)
                    }
                    "1"-> {
                        resultImage.setImageResource(R.drawable.medium_risk)
                        resultText.text = getString(R.string.medium_risk_result)
                    }
                    "0" -> {
                        resultImage.setImageResource(R.drawable.low_risk)
                        resultText.text = getString(R.string.low_risk_result)
                    }
                }
            }

            override fun onFailure(call: Call<Int?>, t: Throwable) {
                Log.d("Error", t.toString())
                resultText.text = getString(R.string.four_o_four_error)
            }
        })

    }
}