package com.kreek.kreekandroid.data.firebase.doctor.model

import com.google.firebase.firestore.DocumentSnapshot

data class Doctor(
    val id: String,
    val name: String,
    val mobileNumber: String,
    val email: String = "",
    val profilePic: String = "",
    val speciality: String,
    val degree: String,
    val department: String = "",
    val hospital: String = "",
)
fun mapToDoctor(document: DocumentSnapshot): Doctor {
    return Doctor(
        id = document.getString("id") ?: "",
        name = document.getString("name") ?: "",
        email = document.getString("email") ?: "",
        mobileNumber = document.getString("mobileNumber") ?: "",
        profilePic = document.getString("profilePic") ?: "",
        speciality = document.getString("speciality") ?: "",
        degree = document.getString("degree") ?: "",
        department = document.getString("department") ?: "",
        hospital = document.getString("hospital") ?: "",
    )
}