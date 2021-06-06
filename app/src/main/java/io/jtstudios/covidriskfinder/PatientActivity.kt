package io.jtstudios.covidriskfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.jtstudios.covidriskfinder.model.PatientData
import io.jtstudios.covidriskfinder.model.SpinnerItem

class PatientActivity : AppCompatActivity() {
    private lateinit var patientRecyclerView: RecyclerView
    private lateinit var submitButton: FloatingActionButton

    private val patientData: PatientData = PatientData()
    private val questions = listOf("Please answer the following:", "Age Range:",
        "Biological Sex:", "Tobacco Use:", "Vaccinated:",
        "Is the patient known to have any of the following conditions?", "Immune Suppression:",
        "COPD:", "Diabetes:", "Kidney Failure:", "Obesity:", "Pregnant:", "Hypertension:",
        "Asthma:", "Cardiovascular Disease:", "Pneumonia:", "Other:", "space")

    private var items = mutableListOf<SpinnerItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_form)

        initializeItems()
        patientRecyclerView = findViewById(R.id.recyclerView)
        patientRecyclerView.layoutManager = LinearLayoutManager(this)
        patientRecyclerView.adapter = PatientFormAdapter(this, patientData, items)

        submitButton = findViewById(R.id.submitButton)

        submitButton.setOnClickListener {

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("patientData", patientData)
            startActivity(intent)
            finish()
        }
    }

    private fun initializeItems() {
        for (element in questions) {
            items.add(SpinnerItem(element))
        }
    }
}