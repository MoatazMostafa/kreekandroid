package com.kreek.kreekandroid.data.firebase.patient.get

import com.kreek.kreekandroid.data.firebase.patient.model.Patient

interface GetPatientDataSource {
    suspend operator fun invoke(patientId: String): Patient
}