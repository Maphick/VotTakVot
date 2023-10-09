package com.example.vottakvot.domain

data class Leveltem(
    val type : LevelType,
    val value: Boolean,
)

enum class LevelType {
    LEVEL_BEGINNER, LEVEL_ADVANCED, LEVEL_EXPERT, LEVEL_MASTER
}
