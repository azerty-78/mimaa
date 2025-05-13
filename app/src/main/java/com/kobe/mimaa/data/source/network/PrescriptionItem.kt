package com.kobe.mimaa.data.source.network

data class PrescriptionItem(
    val medication: String,
    val dosage: String,
    val frequency: String,
    val duration: String,
    val purpose: String = "",
    val startDate: String? = null,
    val endDate: String? = null
)