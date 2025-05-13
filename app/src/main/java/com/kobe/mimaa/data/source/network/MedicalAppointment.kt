package com.kobe.mimaa.data.source.network

import java.util.UUID

// Gestion des rendez-vous médicaux améliorée
data class MedicalAppointment(
    val id: String = UUID.randomUUID().toString(),
    val type: AppointmentType, // "consultation", "ultrasound", etc.
    val date: String,
    val time: String,
    val doctor: DoctorInfo,
    val location: String,
    val preparation: String = "", // Préparation requise
    val notes: String = "",
    val isDone: Boolean = false,
    val results: AppointmentResults? = null
)
