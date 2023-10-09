package com.example.vottakvot.domain

data class IntensityItem(
    val type : IntensityType,
    val value: Boolean,
)

enum class IntensityType {
    INTENSITY_HIGH, INTENSITY_MEDIUM, INTENSITY_LOW
}


