package com.example.varorussell.db

import androidx.room.*

@TypeConverters(TheTypeConverters::class)
@Database(entities = [Temp::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun nowPlayingDao(): NowPlayingDao

}

@Entity
data class Temp(
    @PrimaryKey(autoGenerate = true) val uuid: Int,
    val id: Int)

class TheTypeConverters {
    @TypeConverter
    fun fromListIntToString(intList: List<Int>): String = intList.toString()
    @TypeConverter
    fun toListIntFromString(stringList: String): List<Int> {
        val result = ArrayList<Int>()
        val split =stringList.replace("[","").replace("]","").replace(" ","").split(",")
        for (n in split) {
            try {
                result.add(n.toInt())
            } catch (e: Exception) {

            }
        }
        return result
    }
}