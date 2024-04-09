package dev.agalperin.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "ingredients")
data class IngredientDBO(
    @PrimaryKey
    val ingredientName: String,
)
