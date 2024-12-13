package uas.c14220049.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import uas.c14220049.app.database.DataKesehatanDB

class MainActivity : AppCompatActivity() {
    private lateinit var RoomDB: DataKesehatanDB

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

        val _fabAdd = findViewById<FloatingActionButton>(R.id.fabAdd)
        _fabAdd.setOnClickListener {
            startActivity(Intent(this, InputDataKesehatanActivity::class.java))
        }

    }
}