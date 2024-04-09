package dev.agalperin.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients_detailed")
data class IngredientDetailedDBO(
    @PrimaryKey
    @ColumnInfo("idIngredient") val id: String,
    @ColumnInfo("strIngredient") val name: String? = null,
    @ColumnInfo("strDescription") val description: String? = null,
    @ColumnInfo("strType") val drinkType: String? = null,
    @ColumnInfo("strAlcohol") val isAlcoholic: String? = null,
    @ColumnInfo("strABV") val alcoholicVolume: String? = null
)
