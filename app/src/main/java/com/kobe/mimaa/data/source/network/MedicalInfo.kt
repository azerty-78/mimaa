package com.kobe.mimaa.data.source.network

import java.util.Date

// Informations médicales de base avec champs obligatoires/optionnels
data class MedicalInfo(
    val birthDate: Date? = null, // Format: "YYYY-MM-DD" (OBLIGATOIRE)
    val height: Double? = null, // en cm
    val initialWeight: Double? = null, // en kg (poids avant grossesse)
    val currentWeight: Double? = null, // en kg
    val bloodType: String? = null, // "A+", "B-", etc.
    val rhFactor: String? = null, // "+" ou "-"
    val previousPregnancies: Int = 0,
    val previousComplications: List<String> = emptyList(),
    val allergies: List<String> = emptyList(),
    val chronicDiseases: List<String> = emptyList(),
    val currentMedications: List<String> = emptyList()
){
    // Calcul de l'IMC initial
    fun calculateInitialBMI(): Double? {
        return if (height != null && height > 0 && initialWeight != null) {
            initialWeight / ((height / 100) * (height / 100))
        } else {
            null
        }
    }
}