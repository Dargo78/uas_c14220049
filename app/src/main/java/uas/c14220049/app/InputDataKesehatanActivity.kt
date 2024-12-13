package uas.c14220049.app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import uas.c14220049.app.database.DataKesehatan
import uas.c14220049.app.database.DataKesehatanDB
import uas.c14220049.app.helper.DateHelper.getCurrentDate
import kotlin.math.tan

class InputDataKesehatanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_input_data_kesehatan)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val DB = DataKesehatanDB.getDatabase(this)
        val tanggal = getCurrentDate()

        val _etBB = findViewById<EditText>(R.id.etBB)
        val _etTekanan = findViewById<EditText>(R.id.etTekanan)
        val _etCatatan = findViewById<EditText>(R.id.etCatatan)

        val _btnTambah = findViewById<Button>(R.id.btnTambah)
        _btnTambah.setOnClickListener {
            CoroutineScope(Dispatchers.IO).async {
                DB.dataKesehatanDAO().insert(
                    DataKesehatan(
                        tanggal = tanggal,
                        bb = _etBB.text.toString().toInt(),
                        tekanan = _etTekanan.text.toString().toInt(),
                        catatan = _etCatatan.text.toString()
                    )
                )
            }
            finish()
        }
    }
}