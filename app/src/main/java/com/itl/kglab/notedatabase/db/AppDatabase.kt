package com.itl.kglab.notedatabase.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// exportSchema = false 不要輸出 Database info
@Database(entities = [NoteData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var db: AppDatabase? = null
        private val LOCK = Any()

        private fun build(context: Context) =
            Room.databaseBuilder(
                context = context,
                klass = AppDatabase::class.java,
                name = "noteData"
            ).build()

        operator fun invoke(context: Context) = db ?: synchronized(LOCK) {
            db ?: build(context).also { db = it }
        }
    }
}