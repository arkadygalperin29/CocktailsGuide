package dev.agalperin.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.agalperin.database.models.CocktailDBO
import dev.agalperin.database.models.IngredientDBO
import dev.agalperin.database.models.IngredientDetailedDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {
    @Query("SELECT * FROM ingredients")
    suspend fun getAll(): List<IngredientDBO>

    @Query("SELECT * FROM ingredients")
    fun observeAll(): Flow<List<IngredientDBO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(list: List<IngredientDBO>)

    @Query("SELECT * FROM ingredients_detailed WHERE strIngredient = :name")
    suspend fun getIngredientDetailed(name: String): IngredientDetailedDBO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSingle(ingredientDetailedDBO: IngredientDetailedDBO)

    @Delete
    suspend fun remove(list: List<IngredientDBO>)

    @Query("DELETE FROM ingredients")
    suspend fun clean()
}