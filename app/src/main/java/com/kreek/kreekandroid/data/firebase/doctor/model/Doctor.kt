package com.kreek.kreekandroid.data.firebase.doctor.model

import com.google.firebase.firestore.DocumentSnapshot

data class Doctor(
    val id: String,
    val name: String,
    val mobileNumber: String,
    val email: String,
    val profilePic: String,
    val speciality: String,
    val privetChatRoomIds: List<String>,
    val groupChatRoomIds: List<String>
)
fun mapToDoctor(document: DocumentSnapshot): Doctor {
    return Doctor(
        id = document.getString("id") ?: "",
        name = document.getString("name") ?: "",
        email = document.getString("email") ?: "",
        mobileNumber = document.getString("mobileNumber") ?: "",
        profilePic = document.getString("profilePic") ?: "",
        speciality = document.getString("speciality") ?: "",
        groupChatRoomIds = document.get("groupChatRoomIds") as? List<String> ?: listOf(),
        privetChatRoomIds = document.get("privetChatRoomIds") as? List<String> ?: listOf()
    )
}