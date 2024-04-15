package com.kreek.kreekandroid.data.firebase.doctor.get

import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor

interface GetDoctorByNumberDataSource {
    suspend operator fun invoke(mobileNumber: String): Doctor?
}