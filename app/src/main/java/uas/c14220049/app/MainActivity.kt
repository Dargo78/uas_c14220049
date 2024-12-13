package uas.c14220049.app

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import uas.c14220049.app.database.DataKesehatan
import uas.c14220049.app.database.DataKesehatanDB

class MainActivity : AppCompatActivity() {
    private lateinit var RoomDB: DataKesehatanDB
    private lateinit var adapterKesehatan: AdapterKesehatan
    private var arrKesehatan: MutableList<DataKesehatan> = mutableListOf()

    private val FirebaseDB = Firebase.firestore

    fun successDialog(message: String) : AlertDialog.Builder {
        return AlertDialog.Builder(this)
            .setMessage(message)
            .setTitle("Success")
    }

    fun errorDialog(message: String): AlertDialog.Builder {
        return AlertDialog.Builder(this)
            .setMessage("Gagal menghapus data belanja")
            .setTitle("Error")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        RoomDB = DataKesehatanDB.getDatabase(this)
        adapterKesehatan = AdapterKesehatan(arrKesehatan)

        val _rvKesehatan = findViewById<RecyclerView>(R.id.rvKesehatan)
        _rvKesehatan.layoutManager = LinearLayoutManager(this)
        _rvKesehatan.adapter = adapterKesehatan

        val _fabAdd = findViewById<FloatingActionButton>(R.id.fabAdd)
        _fabAdd.setOnClickListener {
            startActivity(Intent(this, InputDataKesehatanActivity::class.java))
        }

        val _fabUpload = findViewById<FloatingActionButton>(R.id.fabUpload)
        _fabUpload.setOnClickListener {
            CoroutineScope(Dispatchers.Main).async {
                val daftarKesehatan = RoomDB.dataKesehatanDAO().selectAll()

                daftarKesehatan.map {
                    val kesehatan = uas.c14220049.app.DataKesehatan(it.tanggal, it.bb, it.tekanan, it.catatan)
                    FirebaseDB.collection("DataKesehatan")
                        .document(it.id.toString())
                        .set(kesehatan)
                        .addOnSuccessListener {
                            successDialog("Berhasil mengupload data kesehatan ke firebase")
                                .show()
                        }
                        .addOnFailureListener {
                            errorDialog("Gagal mengupload data kesehatan ke firebase")
                                .show()
                        }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        CoroutineScope(Dispatchers.Main).async {
            val daftarKesehatan = RoomDB.dataKesehatanDAO().selectAll()
            adapterKesehatan.isiData(daftarKesehatan)
        }
    }
}