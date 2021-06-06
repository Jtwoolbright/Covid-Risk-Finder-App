package io.jtstudios.covidriskfinder.model

import android.os.Parcel
import android.os.Parcelable

data class PatientData(var sex:Int = 0,
                       var pneumonia: Int = 0,
                       var age: Int = 0,
                       var pregnancy: Int = 0,
                       var diabetes: Int = 0,
                       var copd: Int = 0,
                       var asthma: Int = 0,
                       var imSupress: Int = 0,
                       var hypertension: Int = 0,
                       var other: Int = 0,
                       var cardiovascular: Int = 0,
                       var obesity: Int = 0,
                       var kidneyFail: Int = 0,
                       var tobacco: Int = 0,
                       var vaccinated: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(sex)
        parcel.writeInt(pneumonia)
        parcel.writeInt(age)
        parcel.writeInt(pregnancy)
        parcel.writeInt(diabetes)
        parcel.writeInt(copd)
        parcel.writeInt(asthma)
        parcel.writeInt(imSupress)
        parcel.writeInt(hypertension)
        parcel.writeInt(other)
        parcel.writeInt(cardiovascular)
        parcel.writeInt(obesity)
        parcel.writeInt(kidneyFail)
        parcel.writeInt(tobacco)
        parcel.writeInt(vaccinated)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PatientData> {
        override fun createFromParcel(parcel: Parcel): PatientData {
            return PatientData(parcel)
        }

        override fun newArray(size: Int): Array<PatientData?> {
            return arrayOfNulls(size)
        }
    }
}
