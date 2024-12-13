package uas.c14220049.app

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import uas.c14220049.app.database.DataKesehatan
import uas.c14220049.app.database.DataKesehatanDB

class MainActivity : AppCompatActivity() {
    private lateinit var RoomDB: DataKesehatanDB
    private lateinit var adapterKesehatan: AdapterKesehatan
    private var arrKesehatan: MutableList<DataKesehatan> = mutableListOf()

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
    }

    override fun onStart() {
        super.onStart()

        CoroutineScope(Dispatchers.Main).async {
            val daftarKesehatan = RoomDB.dataKesehatanDAO().selectAll()
            adapterKesehatan.isiData(daftarKesehatan)


        }
    }
}