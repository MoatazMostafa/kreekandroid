package com.kreek.kreekandroid.domain.model

import com.kreek.kreekandroid.data.firebase.patient.model.Patient
import com.kreek.kreekandroid.data.firebase.patient.model.PatientData
import com.kreek.kreekandroid.data.firebase.patient.model.PatientGender

data class PatientDomainModel(
    val patientData: PatientDataDomainModel,
    val medicalHistory: List<String> = emptyList(),
    val examinations: List<String> = emptyList(),
    val integration: List<String> = emptyList(),
    val management: List<String> = emptyList(),
)

data class PatientDataDomainModel(
    val id: String = "",
    val name: String = "",
    val age: Int = 0,
    val dateOfBirth: String = "",
    val gender: PatientGender = PatientGender.MALE,
    val medicalRecordNumber: String = "",
    val dateOfAdmission: String = "",
    val attendingPhysician: String = "",
    val mobileNumber: String = "",
    val nationalId: String = "",
    val email: String = "",
)


fun PatientDomainModel.toDataModel(): Patient =
    Patient(
        patientData = patientData.toDataModel(),
        medicalHistory = medicalHistory,
        examinations = examinations,
        integration = integration,
        management = management,
    )

fun PatientDataDomainModel.toDataModel(): PatientData =
    PatientData(
        id = id,
        name = name,
        age = age,
        dateOfBirth = dateOfBirth,
        gender = gender.value,
        medicalRecordNumber = medicalRecordNumber,
        dateOfAdmission = dateOfAdmission,
        attendingPhysician = attendingPhysician,
        mobileNumber = mobileNumber,
        nationalId = nationalId,
        email = email,
    )

fun Patient.toDomainModel(): PatientDomainModel =
    PatientDomainModel(
        patientData = patientData.toDomainModel(),
        medicalHistory = medicalHistory,
        examinations = examinations,
        integration = integration,
        management = management,
    )

fun PatientData.toDomainModel(): PatientDataDomainModel =
    PatientDataDomainModel(
        id = id,
        name = name,
        age = age,
        dateOfBirth = dateOfBirth,
        gender = PatientGender.fromString(gender),
        medicalRecordNumber = medicalRecordNumber,
        dateOfAdmission = dateOfAdmission,
        attendingPhysician = attendingPhysician,
        mobileNumber = mobileNumber,
        nationalId = nationalId,
        email = email,
    )