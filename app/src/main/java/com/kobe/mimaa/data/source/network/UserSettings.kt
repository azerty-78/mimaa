package com.kobe.mimaa.data.source.network

// Paramètres utilisateur
data class UserSettings(
    val notificationEnabled: Boolean = true,
    val weightUnit: String = "kg", // "kg" ou "lbs"
    val lengthUnit: String = "cm", // "cm" ou "in"
    val temperatureUnit: String = "celsius", // "celsius" ou "fahrenheit"
    val reminderSettings: ReminderSettings = ReminderSettings(),
    val dataSharing: DataSharingSettings = DataSharingSettings()
)

data class ReminderSettings(
    val medicationReminders: Boolean = true,
    val appointmentReminders: Boolean = true,
    val appointmentAdvanceHours: Int = 24,
    val dailyTrackingReminder: Boolean = true,
    val reminderTime: String = "20:00"
)

data class DataSharingSettings(
    val shareWithPartner: Boolean = false,
    val shareWithDoctor: Boolean = false,
    val doctorAccessCode: String? = null
)