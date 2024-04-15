package com.kreek.kreekandroid.data.firebase.patient.set

import com.google.firebase.firestore.FirebaseFirestore
import com.kreek.kreekandroid.data.firebase.patient.model.Patient

class SetPatientDataSourceImpl : SetPatientDataSource {
    override fun invoke(patient: Patient) {
        val fireStore = FirebaseFirestore.getInstance()
        fireStore.collection("kreek_patient").document(patient.patientData.id).set(patient)
    }
}