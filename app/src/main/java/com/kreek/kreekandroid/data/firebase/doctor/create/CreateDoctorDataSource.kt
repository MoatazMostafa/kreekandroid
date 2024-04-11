package com.kreek.kreekandroid.data.firebase.doctor.create

import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor

interface CreateDoctorDataSource {
    operator fun invoke(doctor: Doctor)
}