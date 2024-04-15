package com.kreek.kreekandroid.data.firebase.doctor.set

import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor

interface SetDoctorDataSource {
    operator fun invoke(doctor: Doctor)
}