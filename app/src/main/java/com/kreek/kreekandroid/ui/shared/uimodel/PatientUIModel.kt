package com.kreek.kreekandroid.ui.shared.uimodel

import com.kreek.kreekandroid.data.firebase.patient.model.PatientGender
import com.kreek.kreekandroid.domain.model.PatientDataDomainModel
import com.kreek.kreekandroid.domain.model.PatientDomainModel

data class PatientUIModel(
    val patientData: PatientDataUIModel,
    val medicalHistory:List<String> = emptyList(),
    val examinations:List<String> = emptyList(),
    val integration:List<String> = emptyList(),
    val management:List<String> = emptyList(),
)

data class PatientDataUIModel(
    val id: String = "",
    val name: String = "",
    val age: Int = 0,
    val dateOfBirth: String = "",
    val gender: PatientGender = PatientGender.MALE,
    val medicalRecordNumber: String = "",
    val dateOfAdmission: String = "",
    val attendingPhysician: String = "",
    val mobileNumber: String = "",
    val nationalId:String = "",
    val email: String = "",
)

fun PatientUIModel.toDomainModel(): PatientDomainModel =
    PatientDomainModel(
        patientData = patientData.toDomainModel(),
        medicalHistory = medicalHistory,
        examinations = examinations,
        integration = integration,
        management = management,
    )

fun PatientDataUIModel.toDomainModel(): PatientDataDomainModel =
    PatientDataDomainModel(
        id = id,
        name = name,
        age = age,
        dateOfBirth = dateOfBirth,
        gender = gender,
        medicalRecordNumber = medicalRecordNumber,
        dateOfAdmission = dateOfAdmission,
        attendingPhysician = attendingPhysician,
        mobileNumber = mobileNumber,
        nationalId = nationalId,
        email = email,
    )
fun PatientDomainModel.toUIModel(): PatientUIModel =
    PatientUIModel(
        patientData = patientData.toUIModel(),
        medicalHistory = medicalHistory,
        examinations = examinations,
        integration = integration,
        management = management,
    )
fun PatientDataDomainModel.toUIModel(): PatientDataUIModel =
    PatientDataUIModel(
        id = id,
        name = name,
        age = age,
        dateOfBirth = dateOfBirth,
        gender = gender,
        medicalRecordNumber = medicalRecordNumber,
        dateOfAdmission = dateOfAdmission,
        attendingPhysician = attendingPhysician,
        mobileNumber = mobileNumber,
        nationalId = nationalId,
        email = email,
    )
