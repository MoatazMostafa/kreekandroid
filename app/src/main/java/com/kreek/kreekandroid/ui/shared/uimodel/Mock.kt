package com.kreek.kreekandroid.ui.shared.uimodel

fun getMockPatient(): PatientUIModel =
    PatientUIModel(
        name = "Anas Omran",
        patientId = "0",
        age = 25,
        patientDiagnosis = PatientDiagnosis.Bone,
        lastMessagesItem = MessageItem(
            messageText = "Moamen: This patient needs more care and has a critical case to be handled by ...",
            messageDate = "1/3/2024",
            messageSenderId = "0",
            messageSenderName = "Moamen Farouk"
        ),
        messagesItemList = mutableListOf(
            MessageItem(
                messageText = "Moamen: This patient needs more care and has a critical case to be handled by ...",
                messageDate = "1/3/2024",
                messageSenderId = "0",
                messageSenderName = "Moamen Farouk"
            ),
            MessageItem(
                messageText = "Moamen: This patient needs more care and has a critical case to be handled by ...",
                messageDate = "1/3/2024",
                messageSenderId = "1",
                messageSenderName = "Moamen Farouk"
            ),
            MessageItem(
                messageText = "Moamen: This patient needs more care and has a critical case to be handled by ...",
                messageDate = "1/3/2024",
                messageSenderId = "0",
                messageSenderName = "Moamen Farouk"
            ),
            MessageItem(
                messageText = "Moamen: This patient needs more care and has a critical case to be handled by ...",
                messageDate = "1/3/2024",
                messageSenderId = "1",
                messageSenderName = "Moamen Farouk"
            )
        )
    )


fun getMockPatientList(): List<PatientUIModel> =
    listOf(
        PatientUIModel(
            name = "Anas Omran",
            patientId = "0",
            age = 25,
            patientDiagnosis = PatientDiagnosis.Bone,
            lastMessagesItem = MessageItem(
                messageText = "Moamen: This patient needs more care and has a critical case to be handled by ...",
                messageDate = "1/3/2024",
                messageSenderId = "0",
                messageSenderName = "Moamen Farouk"
            )
        ),
        PatientUIModel(
            name = "Khaled Mohsen",
            patientId = "0",
            age = 40,
            patientDiagnosis = PatientDiagnosis.Chest,
            lastMessagesItem = MessageItem(
                messageText = "Moamen: This patient needs more care and has a critical case to be handled by ...",
                messageDate = "1/3/2024",
                messageSenderId = "0",
                messageSenderName = "Moamen Farouk"
            )
        ),
        PatientUIModel(
            name = "Nada Abdelhalim",
            patientId = "0",
            age = 35,
            patientDiagnosis = PatientDiagnosis.Abdomen,
            lastMessagesItem = MessageItem(
                messageText = "Moamen: This patient needs more care and has a critical case to be handled by ...",
                messageDate = "1/3/2024",
                messageSenderId = "0",
                messageSenderName = "Moamen Farouk"
            )
        ),
        PatientUIModel(
            name = "Mostafa Youssef",
            patientId = "0",
            age = 55,
            patientDiagnosis = PatientDiagnosis.Cardiology,
            lastMessagesItem = MessageItem(
                messageText = "Moamen: This patient needs more care and has a critical case to be handled by ...",
                messageDate = "1/3/2024",
                messageSenderId = "0",
                messageSenderName = "Moamen Farouk"
            )
        ),
        PatientUIModel(
            name = "Sally Abdelkhaleq",
            patientId = "0",
            age = 5,
            patientDiagnosis = PatientDiagnosis.Pediatric,
            lastMessagesItem = MessageItem(
                messageText = "Moamen: This patient needs more care and has a critical case to be handled by ...",
                messageDate = "1/3/2024",
                messageSenderId = "0",
                messageSenderName = "Moamen Farouk"
            )
        )
    )



