package dev.agalperin.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dev.agalperin.database.models.CocktailDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface CocktailDao {

    @Query("SELECT * FROM cocktails")
    suspend fun getAll(): List<CocktailDBO>

    @Query("SELECT * FROM cocktails")
    fun observeAll(): Flow<List<CocktailDBO>>

    @Insert
    suspend fun insert(list: List<CocktailDBO>)

    @Delete
    suspend fun remove(list: List<CocktailDBO>)

    @Query("DELETE FROM cocktails")
    suspend fun clean()
}