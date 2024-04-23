package com.kreek.kreekandroid.ui.shared.uimodel

import com.kreek.kreekandroid.data.firebase.patient.model.PatientGender

fun getMockPatient(): PatientUIModel =
    PatientUIModel(
        patientData = getMockPatientData(),
        medicalHistory = listOf("Medical History 1", "Medical History 2"),
        examinations = listOf("Examination 1", "Examination 2"),
        integration = listOf("Integration 1", "Integration 2"),
        management = listOf("Management 1", "Management 2"),
    )

fun getMockPatientData(): PatientDataUIModel {
    return PatientDataUIModel(
        id = "1",
        name = "Patient Name",
        age = 25,
        dateOfBirth = "01/01/1998",
        gender = PatientGender.MALE,
        medicalRecordNumber = "123456",
        dateOfAdmission = "01/01/2021",
        attendingPhysician = "Dr. Attending Physician",
        mobileNumber = "1234567890",
        nationalId = "0987654321",
        email = ""
    )
}
fun getMockMessages(): List<ChatMessageUIModel> {
    return listOf(
        ChatMessageUIModel(
            senderId = "1",
            chatRoomId = "1",
            receiverId = "2",
            message = "Message 1",
            timestamp = 1620000000000,
        ),
        ChatMessageUIModel(
            senderId = "2",
            chatRoomId = "1",
            receiverId = "1",
            message = "Message 2",
            timestamp = 1620000000000,
        ),
        ChatMessageUIModel(
            senderId = "1",
            chatRoomId = "1",
            receiverId = "2",
            message = "Message 3",
            timestamp = 1620000000000,
        ),
        ChatMessageUIModel(
            senderId = "2",
            chatRoomId = "1",
            receiverId = "1",
            message = "Message 4",
            timestamp = 1620000000000,
        ),
    )
}