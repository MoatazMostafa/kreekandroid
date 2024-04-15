package com.kreek.kreekandroid.data.firebase.doctor.get

import com.google.firebase.firestore.FirebaseFirestore
import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor
import com.kreek.kreekandroid.data.firebase.doctor.model.mapToDoctor
import kotlinx.coroutines.tasks.await

class GetDoctorListDataSourceImpl: GetDoctorListDataSource {
    override suspend fun invoke(): List<Doctor> {
        val fireStore = FirebaseFirestore.getInstance()
        val document = fireStore.collection("kreek_doctor").get().await()
        return document.map { mapToDoctor(it) }
    }
}