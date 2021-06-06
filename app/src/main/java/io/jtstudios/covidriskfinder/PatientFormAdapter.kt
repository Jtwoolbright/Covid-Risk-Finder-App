package io.jtstudios.covidriskfinder

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import io.jtstudios.covidriskfinder.model.PatientData
import io.jtstudios.covidriskfinder.model.SpinnerItem


private const val TAG = "ADAPTER"

class PatientFormAdapter(private val context: Context, val patientData: PatientData,
                         private val items: List<SpinnerItem>):
    RecyclerView.Adapter<PatientFormAdapter.PatientHolder>() {

    private val genderAdapter = ArrayAdapter.createFromResource(context, R.array.Gender, android.R.layout.simple_spinner_item)
    private val yesNoAdapter = ArrayAdapter.createFromResource(context, R.array.YN, android.R.layout.simple_spinner_item)
    private val ageAdapter = ArrayAdapter.createFromResource(context, R.array.Age, android.R.layout.simple_spinner_item)

    inner class PatientHolder(itemView: View) : RecyclerView.ViewHolder(itemView), AdapterView.OnItemSelectedListener {

        val questionText: TextView = itemView.findViewById(R.id.questionText)
        val genderSpinner: Spinner? = itemView.findViewById(R.id.gender_spinner)
        val yesNoSpinners: Spinner? = itemView.findViewById(R.id.spinner)
        val ageSpinner: Spinner? = itemView.findViewById(R.id.age_spinner)

        init {
            genderAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            yesNoAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            ageAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            genderSpinner?.adapter = genderAdapter
            genderSpinner?.gravity = Gravity.END
            yesNoSpinners?.adapter = yesNoAdapter
            yesNoSpinners?.gravity = Gravity.END
            ageSpinner?.adapter = ageAdapter
            ageSpinner?.gravity = Gravity.END
            genderSpinner?.onItemSelectedListener = this
            yesNoSpinners?.onItemSelectedListener = this
            ageSpinner?.onItemSelectedListener = this
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val answer = parent?.getItemAtPosition(position) as String
            when (adapterPosition) {
                // 0 -> answer the following
                1 -> patientData.age = when (answer) {
                    "65 or Older" -> 65
                    "50 to 64" -> 55
                    "40 to 49" -> 45
                    "30 to 39" -> 35
                    "19 to 29" -> 25
                    else -> 15
                }
                2 -> patientData.sex = if (answer == "Male") 1 else 0
                3 -> patientData.tobacco = if (answer == "Yes") 1 else 0
                4 -> patientData.vaccinated = if (answer == "Yes") 1 else 0
                // 5 -> any conditions?
                6 -> patientData.imSupress = if (answer == "Yes") 1 else 0
                7 -> patientData.copd = if (answer == "Yes") 1 else 0
                8 -> patientData.diabetes = if (answer == "Yes") 1 else 0
                9 -> patientData.kidneyFail = if (answer == "Yes") 1 else 0
                10 -> patientData.obesity = if (answer == "Yes") 1 else 0
                11 -> patientData.pregnancy = if (answer == "Yes") 1 else 0
                12 -> patientData.hypertension = if (answer == "Yes") 1 else 0
                13 -> patientData.asthma = if (answer == "Yes") 1 else 0
                14 -> patientData.cardiovascular = if (answer == "Yes") 1 else 0
                15 -> patientData.pneumonia = if (answer == "Yes") 1 else 0
                16 -> patientData.other = if (answer == "Yes") 1 else 0
            }
            items[adapterPosition].position = position
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            // Intentionally left blank
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            1 -> layoutInflater.inflate(R.layout.age_row_item, parent, false)
            2 -> layoutInflater.inflate(R.layout.gender_row_item, parent, false)
            3 -> layoutInflater.inflate(R.layout.helper_row_item, parent, false)
            4 -> layoutInflater.inflate(R.layout.preconditions_row_item, parent, false)
            5 -> layoutInflater.inflate(R.layout.spacer_item, parent, false)
            else  -> layoutInflater.inflate(R.layout.row_item, parent, false)
        }
        return PatientHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position].question) {
            "Age Range:" -> 1
            "Biological Sex:" -> 2
            "Please answer the following:" -> 3
            "Is the patient known to have any of the following conditions?" -> 4
            "space" -> 5
            else -> 0
        }
    }

    override fun onBindViewHolder(holder: PatientHolder, position: Int) {
        val item = items[position]
        holder.questionText.text = item.question
        if (position == 1) {
            holder.ageSpinner?.setSelection(item.position)
        }
        else if (position == 2) {
            holder.genderSpinner?.setSelection(item.position)
        }
        else if (position > 2 && position != 5 && position !=17) {
            holder.yesNoSpinners?.setSelection(item.position)
        }
    }
}