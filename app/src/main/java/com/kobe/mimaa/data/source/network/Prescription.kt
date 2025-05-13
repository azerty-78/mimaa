package com.kobe.mimaa.data.source.network

import java.time.LocalDate
import java.util.UUID

// Ordonnances et prescriptions médicales
data class Prescription(
    val id: String = UUID.randomUUID().toString(),
    val date: String = LocalDate.now().toString(),
    val doctor: DoctorInfo,
    val items: List<PrescriptionItem>,
    val notes: String = "",
    val isActive: Boolean = true
)