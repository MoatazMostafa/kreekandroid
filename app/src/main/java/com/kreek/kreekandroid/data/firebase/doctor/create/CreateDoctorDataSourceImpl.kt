package com.kreek.kreekandroid.data.firebase.doctor.create

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor

class CreateDoctorDataSourceImpl : CreateDoctorDataSource {
    override fun invoke(doctor: Doctor) {
        val database = FirebaseDatabase.getInstance()
        val fireStore = FirebaseFirestore.getInstance()
        fireStore.collection("kreek_doctor").document(doctor.id).set(doctor)
    }
}