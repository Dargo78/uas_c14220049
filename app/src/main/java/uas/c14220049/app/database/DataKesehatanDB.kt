package uas.c14220049.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataKesehatan::class], version = 1)
abstract class DataKesehatanDB: RoomDatabase() {
    abstract fun dataKesehatanDAO(): DataKesehatanDAO

    companion object {
        @Volatile
        private var INSTANCE: DataKesehatanDB? = null

        @JvmStatic
        fun getDatabase(context: Context): DataKesehatanDB {
            if (INSTANCE == null) {
                synchronized(DataKesehatanDB::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DataKesehatanDB::class.java, "historyBelanja_db"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE as DataKesehatanDB
        }
    }
}