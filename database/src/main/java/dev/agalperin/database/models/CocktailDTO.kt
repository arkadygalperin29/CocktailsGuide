package dev.agalperin.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cocktails")
data class CocktailDBO(
    @PrimaryKey
    @ColumnInfo("id") val id: String = "1",
    @ColumnInfo("name") val name: String? = null,
    @ColumnInfo("category") val category: String? = null,
    @ColumnInfo("alcoholic") val alcoholic: String? = null,
    @ColumnInfo("videoLink") val videoLink: String? = null,
    @ColumnInfo("tags") val tags: String? = null,
    @ColumnInfo("glass") val glass: String? = null,
    @ColumnInfo("instructions") val instructions: String? = null,
    @ColumnInfo("drinkImage") val drinkImage: String? = null,
    @ColumnInfo("strIngredient1") val strIngredient1: String? = null,
    @ColumnInfo("strIngredient2") val strIngredient2: String? = null,
    @ColumnInfo("strIngredient3") val strIngredient3: String? = null,
    @ColumnInfo("strIngredient4") val strIngredient4: String? = null,
    @ColumnInfo("strIngredient5") val strIngredient5: String? = null,
    @ColumnInfo("strIngredient6") val strIngredient6: String? = null,
    @ColumnInfo("strIngredient7") val strIngredient7: String? = null,
    @ColumnInfo("strIngredient8") val strIngredient8: String? = null,
    @ColumnInfo("strIngredient9") val strIngredient9: String? = null,
    @ColumnInfo("strIngredient10") val strIngredient10: String? = null,
    @ColumnInfo("strIngredient11") val strIngredient11: String? = null,
    @ColumnInfo("strIngredient12") val strIngredient12: String? = null,
    @ColumnInfo("strIngredient13") val strIngredient13: String? = null,
    @ColumnInfo("strIngredient14") val strIngredient14: String? = null,
    @ColumnInfo("strIngredient15") val strIngredient15: String? = null,
    @ColumnInfo("strMeasure1") val strMeasure1: String? = null,
    @ColumnInfo("strMeasure2") val strMeasure2: String? = null,
    @ColumnInfo("strMeasure3") val strMeasure3: String? = null,
    @ColumnInfo("strMeasure4") val strMeasure4: String? = null,
    @ColumnInfo("strMeasure5") val strMeasure5: String? = null,
    @ColumnInfo("strMeasure6") val strMeasure6: String? = null,
    @ColumnInfo("strMeasure7") val strMeasure7: String? = null,
    @ColumnInfo("strMeasure8") val strMeasure8: String? = null,
    @ColumnInfo("strMeasure9") val strMeasure9: String? = null,
    @ColumnInfo("strMeasure10") val strMeasure10: String? = null,
    @ColumnInfo("strMeasure11") val strMeasure11: String? = null,
    @ColumnInfo("strMeasure12") val strMeasure12: String? = null,
    @ColumnInfo("strMeasure13") val strMeasure13: String? = null,
    @ColumnInfo("strMeasure14") val strMeasure14: String? = null,
    @ColumnInfo("strMeasure15") val strMeasure15: String? = null,
)