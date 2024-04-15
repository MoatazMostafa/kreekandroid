package com.kreek.kreekandroid.data.firebase.patient.model

import com.google.firebase.firestore.DocumentSnapshot


data class Patient(
    val patientData: PatientData,
    val medicalHistory: List<String> = emptyList(),
    val examinations: List<String> = emptyList(),
    val integration: List<String> = emptyList(),
    val management: List<String> = emptyList(),
)

data class PatientData(
    val id: String = "",
    val name: String = "",
    val age: Int = 0,
    val dateOfBirth: String = "",
    val gender: String = "",
    val medicalRecordNumber: String = "",
    val dateOfAdmission: String = "",
    val attendingPhysician: String = "",
    val mobileNumber: String = "",
    val nationalId: String = "",
    val email: String = "",
)

enum class PatientGender(val value: String) {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");
    companion object {
        fun fromString(value: String): PatientGender {
            return PatientGender.entries.firstOrNull { it.value == value } ?: MALE
        }
    }
}

fun mapToPatient(document: DocumentSnapshot): Patient {
    return Patient(
        patientData = PatientData(
            id = document.getString("id") ?: "",
            name = document.getString("name") ?: "",
            age = document.getLong("age")?.toInt() ?: 0,
            dateOfBirth = document.getString("dateOfBirth") ?: "",
            gender = document.getString("gender") ?: "",
            medicalRecordNumber = document.getString("medicalRecordNumber") ?: "",
            dateOfAdmission = document.getString("dateOfAdmission") ?: "",
            attendingPhysician = document.getString("attendingPhysician") ?: "",
            mobileNumber = document.getString("mobileNumber") ?: "",
            nationalId = document.getString("nationalId") ?: "",
            email = document.getString("email") ?: "",
        ),
        medicalHistory = document.get("medicalHistory") as? List<String> ?: emptyList(),
        examinations = document.get("examinations") as? List<String> ?: emptyList(),
        integration = document.get("integration") as? List<String> ?: emptyList(),
        management = document.get("management") as? List<String> ?: emptyList(),
    )
}

