package com.example.examennach.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "Pokemon")
class Pokemon(
    @SerializedName("id") @PrimaryKey(autoGenerate = false) val id: Int,
    @SerializedName("num") val num: Int,
    @SerializedName("name") val name: String,
    @SerializedName("img") val img: String,
    @SerializedName("height") val height: String,
    @SerializedName("weight") val weight: String,
    @SerializedName("candy") val candy: String,
    @SerializedName("candy_count") val candy_count: Int,
    @SerializedName("egg") val egg: String,
    @SerializedName("spawn_chance") val spawn_chance: Double = 0.toDouble(),
    @SerializedName("avg_spawns") val avg_spawns: Double = 0.toDouble(),
    @SerializedName("spawn_time") val spawn_time: String,
    var isChecked: Boolean = false
)
