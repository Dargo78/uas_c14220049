package uas.c14220049.app.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataKesehatan(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "tanggal")
    var tanggal: String? = null,

    @ColumnInfo(name = "bb")
    var bb: Int? = 0,

    @ColumnInfo(name = "tekanan")
    var tekanan: Int? = 0,

    @ColumnInfo(name = "catatan")
    var catatan: String? = null,
)