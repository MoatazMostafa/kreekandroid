package com.kreek.kreekandroid.domain.model

import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor

data class DoctorDomainModel(
    val id: String,
    val name: String,
    val mobileNumber: String,
    val email: String,
    val profilePic: String,
    val speciality: String,
    val privetChatRoomIds: List<String>,
    val groupChatRoomIds: List<String>
)

fun DoctorDomainModel.toDataModel(): Doctor =
    Doctor(
        id = id,
        name = name,
        mobileNumber = mobileNumber,
        email = email,
        profilePic = profilePic,
        speciality = speciality,
        privetChatRoomIds = privetChatRoomIds,
        groupChatRoomIds = groupChatRoomIds
    )

fun Doctor.toDomainModel(): DoctorDomainModel =
    DoctorDomainModel(
        id = id,
        name = name,
        mobileNumber = mobileNumber,
        email = email,
        profilePic = profilePic,
        speciality = speciality,
        privetChatRoomIds = privetChatRoomIds,
        groupChatRoomIds = groupChatRoomIds
    )