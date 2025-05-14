package com.kobe.mimaa.data.source.logique_metier

// Données échographiques pour calcul précis
data class UltrasoundData(
    val date: String, // Date de l'échographie
    val weeks: Int, // Semaine de grossesse
    val days: Int, // Jours en plus
    val crl: Float? = null, // Longueur cranio-caudale en mm
    val bpd: Float? = null, // Diamètre bipariétal en mm
    val accuracy: Int = 2 // 1=très précis, 5=peu précis
)