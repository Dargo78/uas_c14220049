package uas.c14220049.app.database

import android.provider.ContactsContract.Data
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataKesehatanDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(data: DataKesehatan)

    @Query("SELECT * FROM DataKesehatan ORDER BY id ASC")
    fun selectAll() : MutableList<DataKesehatan>
}