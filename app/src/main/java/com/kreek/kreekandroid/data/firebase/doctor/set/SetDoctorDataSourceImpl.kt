package com.kreek.kreekandroid.data.firebase.doctor.set

import com.google.firebase.firestore.FirebaseFirestore
import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor

class SetDoctorDataSourceImpl : SetDoctorDataSource {
    override fun invoke(doctor: Doctor) {
        val fireStore = FirebaseFirestore.getInstance()
        fireStore.collection("kreek_doctor").document(doctor.id).set(doctor)
    }
}