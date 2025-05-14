package com.kobe.mimaa.data.source.logique_metier

import java.time.LocalDate
import java.util.UUID

// Suivi quotidien/semanal enrichi
data class PregnancyTracking(
    val id: String = UUID.randomUUID().toString(),
    val date: String = LocalDate.now().toString(),
    val week: Int,
    val day: Int, // Jour dans la semaine (1-7)
    val weight: Double? = null,
    val bloodPressure: String? = null, // "120/80"
    val temperature: Double? = null,
    val symptoms: List<SymptomEntry> = emptyList(),
    val medicationsTaken: List<MedicationTaken> = emptyList(),
    val mood: Int? = null, // 1-5
    val notes: String = "",
    val babyMovements: Int? = null,
    val kickCount: Int? = null // Compte des mouvements
)