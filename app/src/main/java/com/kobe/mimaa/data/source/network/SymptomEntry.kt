package com.kobe.mimaa.data.source.network

// Classes complémentaires (restent similaires mais adaptées)
data class SymptomEntry(
    val type: String, // "nausea", "back_pain", etc.
    val intensity: Int, // 1-10
    val duration: String, // "2h", "all_day", etc.
    val triggers: String = "" // "after_meal", "evening", etc.
)