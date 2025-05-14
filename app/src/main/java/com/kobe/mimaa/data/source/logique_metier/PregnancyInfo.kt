package com.kobe.mimaa.data.source.logique_metier

import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class PregnancyInfo(
    val lastPeriodDate: String? = null, // Dernières règles (format "YYYY-MM-DD")
    val conceptionDate: String? = null, // Date de conception si connue
    val ultrasoundDates: List<UltrasoundData> = emptyList(), // Données échographiques
    val finalDueDate: String? = null, // Date retenue après calcul
    val pregnancyType: String = "single", // "single", "twins", "triplets", etc.
    val conceptionMethod: String = "natural", // "natural", "ivf", "iui", etc.
    val ivfTransferDate: String? = null, // Pour FIV
    val isHighRisk: Boolean = false,
    val riskFactors: List<String> = emptyList()
){
    // Calcul automatique de la date d'accouchement selon différentes méthodes
    fun calculateDueDate(): String? {
        // Priorité 1: Calcul basé sur l'échographie la plus précise
        ultrasoundDates.sortedByDescending { it.accuracy }.firstOrNull()?.let {
            return calculateFromUltrasound(it)
        }

        // Priorité 2: Date de conception (pour FIV)
        if (conceptionMethod == "ivf" && ivfTransferDate != null) {
            return LocalDate.parse(ivfTransferDate)
                .plusDays(266) // 38 semaines pour FIV
                .toString()
        }

        // Priorité 3: Dernières règles (règle de Naegele)
        lastPeriodDate?.let {
            return LocalDate.parse(it)
                .plusDays(281) // 40 semaines
                .toString()
        }

        return null
    }

    private fun calculateFromUltrasound(ultrasound: UltrasoundData): String {
        return LocalDate.parse(ultrasound.date)
            .plusDays(((280 - ultrasound.weeks * 7) - ultrasound.days).toLong())
            .toString()
    }

    // Mise à jour de la semaine actuelle
    fun currentWeek(): Int {
        finalDueDate?.let {
            val dueDate = LocalDate.parse(it)
            val today = LocalDate.now()
            val weeks = ChronoUnit.WEEKS.between(today, dueDate)
            return 40 - weeks.toInt()
        }
        return 0
    }

}