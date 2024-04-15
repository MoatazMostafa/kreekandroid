package com.kreek.kreekandroid.data.firebase.patient.get

import com.google.firebase.firestore.FirebaseFirestore
import com.kreek.kreekandroid.data.firebase.patient.model.Patient
import com.kreek.kreekandroid.data.firebase.patient.model.mapToPatient
import kotlinx.coroutines.tasks.await

class GetPatientDataSourceImpl: GetPatientDataSource {
    override suspend operator fun invoke(patientId: String): Patient {
        val fireStore = FirebaseFirestore.getInstance()
        val document = fireStore.collection("kreek_patient").document(patientId).get().await()
        return mapToPatient(document)
    }
}