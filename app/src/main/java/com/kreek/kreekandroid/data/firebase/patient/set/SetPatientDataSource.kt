package com.kreek.kreekandroid.data.firebase.patient.set

import com.kreek.kreekandroid.data.firebase.patient.model.Patient

interface SetPatientDataSource {
    operator fun invoke(patient: Patient)
}