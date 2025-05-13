package com.kobe.mimaa.data.source.network


enum class AppointmentType {
    CONSULTATION, ULTRASOUND, BLOOD_TEST, URINE_TEST,
    GLUCOSE_TEST, VACCINATION, OTHER
}

data class AppointmentResults(
    val documents: List<String> = emptyList(), // URLs des documents
    val notes: String = "",
    val prescriptions: List<String> = emptyList() // IDs des prescriptions
)