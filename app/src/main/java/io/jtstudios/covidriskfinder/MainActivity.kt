package io.jtstudios.covidriskfinder

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val riskButton = findViewById<Button>(R.id.riskButton)
        riskButton.setOnClickListener {
            val intent = Intent(this, PatientActivity::class.java)
            startActivity(intent)
        }
        val symptomsButton = findViewById<Button>(R.id.symptomsButton)
        symptomsButton.setOnClickListener {
            val url = "https://www.cdc.gov/coronavirus/2019-ncov/symptoms-testing/symptoms.html"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        val locationsButton = findViewById<Button>(R.id.locationsButton)
        locationsButton.setOnClickListener {
            val url = "https://www.vaccines.gov/get-vaccinated/where"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        val servicesButton = findViewById<Button>(R.id.servicesButton)
        servicesButton.setOnClickListener {
            val url = "https://www.google.com/search?rlz=1C1CHBF_enUS879US879&tbm=lcl&sxsrf=ALeKk01" +
                    "2beU25ut_IBG6jJh-SWTGZZgASw%3A1614581578989&ei=So88YJzhO9Lv9AOewYvoAw&q=emerge" +
                    "ncy+hospital&oq=emergency+hospital&gs_l=psy-ab.3..0i433i67k1j0i263i20k1j0j0i7i" +
                    "30k1j0j0i7i30k1l5.107913.107913.0.108216.1.1.0.0.0.0.143.143.0j1.1.0....0...1c" +
                    ".1.64.psy-ab..0.1.142....0.-2-zRa9CK1Q#rlfi=hd:;si:;mv:[[34.1485917,-117.87065" +
                    "44],[33.8972014,-118.2888207]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u16!2m2!16m1!" +
                    "1e1!1m4!1u16!2m2!16m1!1e2!2m1!1e16!2m1!1e3,lf:1,lf_ui:3"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }
}