package com.kreek.kreekandroid.data.firebase.doctor.get

import com.google.firebase.firestore.FirebaseFirestore
import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor
import com.kreek.kreekandroid.data.firebase.doctor.model.mapToDoctor
import kotlinx.coroutines.tasks.await

class GetDoctorByNumberDataSourceImpl: GetDoctorByNumberDataSource {
    override suspend fun invoke(mobileNumber: String): Doctor? {
        val fireStore = FirebaseFirestore.getInstance()
        val querySnapshot = fireStore.collection("kreek_doctor")
            .whereEqualTo("mobileNumber", mobileNumber)
            .get()
            .await()

        // If there's a match, return the first document found
        return if (querySnapshot.documents.isNotEmpty()) {
            mapToDoctor(querySnapshot.documents[0])
        } else {
            null
        }
    }
}