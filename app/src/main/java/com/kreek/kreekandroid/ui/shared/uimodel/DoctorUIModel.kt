package com.kreek.kreekandroid.ui.shared.uimodel

import com.kreek.kreekandroid.domain.model.DoctorDomainModel

data class DoctorUIModel(
    var id: String = "",
    var name: String = "",
    var mobileNumber: String = "",
    var email: String = "",
    var profilePic: String = "",
    var speciality: String = "",
    var degree: String = "",
    var department: String = "",
    var hospital: String = "",
)

fun DoctorDomainModel.toUIModel(): DoctorUIModel =
    DoctorUIModel(
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

fun DoctorUIModel.toDomainModel(): DoctorDomainModel =
    DoctorDomainModel(
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