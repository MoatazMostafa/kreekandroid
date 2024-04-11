package com.kreek.kreekandroid.data.firebase.doctor.get

import com.google.firebase.firestore.FirebaseFirestore
import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor
import com.kreek.kreekandroid.data.firebase.doctor.model.mapToDoctor
import kotlinx.coroutines.tasks.await

class GetDoctorDataSourceImpl: GetDoctorDataSource {
    override suspend fun invoke(id: String): Doctor {
        val fireStore = FirebaseFirestore.getInstance()
        val document = fireStore.collection("kreek_doctor").document(id).get().await()
        return mapToDoctor(document)
    }
}