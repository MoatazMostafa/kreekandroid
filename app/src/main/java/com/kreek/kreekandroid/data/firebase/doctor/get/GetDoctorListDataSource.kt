package com.kreek.kreekandroid.data.firebase.doctor.get

import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor

interface GetDoctorListDataSource {
    suspend operator fun invoke(): List<Doctor>
}