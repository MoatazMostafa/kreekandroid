package com.kreek.kreekandroid.domain.model

import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor

data class DoctorDomainModel(
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

fun DoctorDomainModel.toDataModel(): Doctor =
    Doctor(
        id = id,
        name = name,
        mobileNumber = mobileNumber,
        email = email,
        profilePic = profilePic,
        speciality = speciality,
        degree = degree,
        department = department,
        hospital = hospital,
    )

fun Doctor.toDomainModel(): DoctorDomainModel =
    DoctorDomainModel(
        id = id,
        name = name,
        mobileNumber = mobileNumber,
        email = email,
        profilePic = profilePic,
        speciality = speciality,
        degree = degree,
        department = department,
    )