package com.muzaffar.backupdata

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.muzaffar.backupdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private var prefs : SharedPreferences? = null
    private var edit: SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // Initialize the shared preference [nama file,nama db]
        // Context.MODE_PRIVATE hanya boleh dibaca di dalam projek ini
        prefs = getSharedPreferences("messages", Context.MODE_PRIVATE)

        edit = prefs?.edit()



        binding.buttonSave.setOnClickListener {

            // BILA Button save ditekan, saya akan simpan data yang dimasukkan dioleh pengguna
            // sebagai "mesej"
            edit?.putString("mesej",binding.savedData.text.toString())
            // SAVEEEEE
            edit?.commit()


        }

        binding.buttonRetrieve.setOnClickListener {
            // Bila button retrieved ditekan, saya akan dapatkan nilai yang telah disimpan
            // dengan nama "mesej"
            // Jika ada saya akan tunjukkan di dalam retrievedData
            // Jika tiada saya akan tunjukkan ""
            var savedString = prefs?.getString("mesej","")
            binding.retrievedData.setText(savedString)

        }

    }
}