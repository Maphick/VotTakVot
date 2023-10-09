package com.example.vottakvot.domain

data class TrainConfiguration(
    val intensity: List<IntensityItem> = listOf(
        IntensityItem(type = IntensityType.INTENSITY_HIGH, value = false),
        IntensityItem(type = IntensityType.INTENSITY_MEDIUM, value = true),
        IntensityItem(type = IntensityType.INTENSITY_LOW, value = false),
    ),
    val level: List<Leveltem> = listOf(
        Leveltem(type = LevelType.LEVEL_BEGINNER, value = false),
        Leveltem(type = LevelType.LEVEL_BEGINNER, value = false),
        Leveltem(type = LevelType.LEVEL_BEGINNER, value = false),
        Leveltem(type = LevelType.LEVEL_BEGINNER, value = false)
    )
)

   // val goal:


    //Intensity = Intensity(intensity_high = true,  intensity_medium = true, intensity_low = false),
   // val level: Level = Level(level_beginner = false, level_advanced = true, level_expert = false, level_master = false),
   // val type: Type = Type(type_cardio = false, type_power = true, type_static = false, type_stretch = true, type_lymphatic = false),
   // val goal: Goal = Goal(goal_tone = false, goal_muscle = true, goal_weight = false, goal_energy = true)






/*
data class Intensity(
    val intensity_high : Boolean,
    val intensity_medium : Boolean,
    val intensity_low : Boolean
)

data class Level(
    val level_beginner : Boolean,
    val level_advanced : Boolean,
    val level_expert : Boolean,
    val level_master : Boolean
)

data class Kind(
    val kind_cardio : Boolean,
    val kind_power : Boolean,
    val kind_static : Boolean,
    val kind_stretch : Boolean,
    val kind_lymphatic : Boolean
)

data class Goal(
    val goal_tone : Boolean,
    val goal_muscle : Boolean,
    val goal_weight : Boolean,
    val goal_energy : Boolean
)

data class IsOnOff(
    // тип параметра
    val string: String,
    // выбран/не выбран
    val boolean: Boolean
)
enum class Intensity {
    INTENSITY_HIGH, INTENSITY_MEDIUM, INTENSITY_LOW
}

enum class Level {
   LEVEL_BEGINNER, LEVEL_ADVANCED, LEVEL_EXPERT, LEVEL_MASTER
}

enum class Type {
    CARDIO, POWER, STATIC, STRETCH, LYMPHATIC
}

enum class Goal {
    TONE, MUSCLE, WEIGHT, ENERGY
}

*/