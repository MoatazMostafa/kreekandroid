package com.kreek.kreekandroid.ui.shared.uimodel

import com.kreek.kreekandroid.R

enum class PatientDiagnosis{
    Chest,
    Abdomen,
    Bone,
    Skin,
    Cardiology,
    Neurology,
    Kidney,
    Liver,
    Blood,
    Muscle,
    Ophthalmology,
    ENT,
    Dental,
    Gynecological,
    Obstetrical,
    Colon,
    Pediatric,
    Psychiatric,
    Other
}

fun PatientDiagnosis.getIconResource(): Int {
    return when(this){
        PatientDiagnosis.Chest -> R.drawable.ic_lungs
        PatientDiagnosis.Abdomen -> R.drawable.ic_stomach
        PatientDiagnosis.Bone -> R.drawable.ic_bone
        else -> R.drawable.ic_lying_patient
//        PatientDiagnosis.Skin -> R.drawable.ic_skin
//        PatientDiagnosis.Cardiology -> R.drawable.ic_cardiology
//        PatientDiagnosis.Neurology -> R.drawable.ic_neurology
//        PatientDiagnosis.Kidney -> R.drawable.ic_kidney
//        PatientDiagnosis.Liver -> R.drawable.ic_liver
//        PatientDiagnosis.Blood -> R.drawable.ic_blood
//        PatientDiagnosis.Muscle -> R.drawable.ic_muscle
//        PatientDiagnosis.Ophthalmology -> R.drawable.ic_ophthalmology
//        PatientDiagnosis.ENT -> R.drawable.ic_ent
//        PatientDiagnosis.Dental -> R.drawable.ic_dental
//        PatientDiagnosis.Gynecological -> R.drawable.ic_gynecological
//        PatientDiagnosis.Obstetrical -> R.drawable.ic_obstetrical
//        PatientDiagnosis.Colon -> R.drawable.ic_colon
//        PatientDiagnosis.Pediatric -> R.drawable.ic_pediatric
//        PatientDiagnosis.Psychiatric -> R.drawable.ic_psychiatric
    }
}
