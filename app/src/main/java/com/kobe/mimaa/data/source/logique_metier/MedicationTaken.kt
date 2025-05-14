package com.kobe.mimaa.data.source.logique_metier

data class MedicationTaken(
    val prescriptionId: String? = null,
    val name: String,
    val dosage: String,
    val time: String,
    val notes: String = ""
)