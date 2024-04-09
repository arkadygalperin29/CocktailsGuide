package dev.agalperin.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.agalperin.database.dao.CocktailDao
import dev.agalperin.database.dao.IngredientDao
import dev.agalperin.database.models.CocktailDBO
import dev.agalperin.database.models.IngredientDBO
import dev.agalperin.database.models.IngredientDetailedDBO

class CocktailDatabase internal constructor(private val dataBase: CocktailRoomDatabase) {

    val cocktailDao: CocktailDao
        get() = dataBase.cocktailDao()
    val ingredientDao: IngredientDao
        get() = dataBase.ingredientDao()

}

@Database(
    entities = [CocktailDBO::class, IngredientDBO::class, IngredientDetailedDBO::class],
    version = 1,
    exportSchema = true,
    autoMigrations = []
)
internal abstract class CocktailRoomDatabase : RoomDatabase() {

    abstract fun cocktailDao(): CocktailDao

    abstract fun ingredientDao(): IngredientDao
}

fun CocktailDatabase(applicationContext: Context): CocktailDatabase {

    val cocktailsRoomDatabase =
        Room.databaseBuilder(
            applicationContext.applicationContext, CocktailRoomDatabase::class.java, "database-name"
        ).fallbackToDestructiveMigration().build()

    return CocktailDatabase(cocktailsRoomDatabase)
}