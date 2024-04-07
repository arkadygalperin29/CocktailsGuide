package dev.agalperin.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.agalperin.database.dao.CocktailDao
import dev.agalperin.database.models.CocktailDBO

class CocktailDatabase internal constructor(private val dataBase: CocktailRoomDatabase) {

    val cocktailDao: CocktailDao
        get() = dataBase.cocktailDao()

}

@Database(
    entities = [CocktailDBO::class],
    version = 1
)
internal abstract class CocktailRoomDatabase : RoomDatabase() {

    abstract fun cocktailDao(): CocktailDao
}

fun CocktailDatabase(applicationContext: Context): CocktailDatabase {

    val cocktailsRoomDatabase =
        Room.databaseBuilder(
            applicationContext.applicationContext, CocktailRoomDatabase::class.java, "database-name"
        ).build()

    return CocktailDatabase(cocktailsRoomDatabase)
}