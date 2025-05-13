package com.kobe.mimaa.data.source.network

data class MedicationTaken(
    val prescriptionId: String? = null,
    val name: String,
    val dosage: String,
    val time: String,
    val notes: String = ""
)