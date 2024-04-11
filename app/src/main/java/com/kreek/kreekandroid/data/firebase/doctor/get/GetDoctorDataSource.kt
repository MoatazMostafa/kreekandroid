package com.kreek.kreekandroid.data.firebase.doctor.get

import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor

interface GetDoctorDataSource {
    suspend operator fun invoke(id: String): Doctor
}