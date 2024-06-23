package com.muzaffar.backupdata

import android.app.backup.BackupManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.muzaffar.backupdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private var prefs : SharedPreferences? = null
    private var edit: SharedPreferences.Editor? = null

    // Berkaitan backup (Create a variable BackupManager)
    private var backupManager:BackupManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // Initialize the shared preference [nama file,nama db]
        // Context.MODE_PRIVATE hanya boleh dibaca di dalam projek ini
        prefs = getSharedPreferences(BackupData.PREFS_TEST, Context.MODE_PRIVATE)
        edit = prefs?.edit()

        // Initialize the backupManager
        backupManager = BackupManager(this)

       // binding.buttonRetrieve.isEnabled = false

        binding.buttonSave.setOnClickListener {

            // BILA Button save ditekan, saya akan simpan data yang dimasukkan dioleh pengguna
            // sebagai "mesej"
            // key (filename)- > mesej
            // value => data entered by user
            // When we save we use the method putString (enter file name)
            // commit -> save
            edit?.putString("mesej",binding.savedData.text.toString())
            // SAVEEEEE
            edit?.commit()
            // UPDATE the backup in Google Drive
            Log.d("Test", "Calling backup....")
            backupManager?.dataChanged()

            // Jika data ada
            if (binding.savedData.text.isNotEmpty()){
                // Enabledkan button retrieve
                binding.buttonRetrieve.isEnabled = true
            }


        }

        binding.buttonRetrieve.setOnClickListener {
            // Bila button retrieved ditekan, saya akan dapatkan nilai yang telah disimpan
            // dengan nama "mesej"
            // Jika ada saya akan tunjukkan di dalam retrievedData
            // Jika tiada saya akan tunjukkan ""
            // getString adalah method untuk dapatkan data
            var savedString = prefs?.getString("mesej","")
            binding.retrievedData.setText(savedString)
            binding.retrievedData.isEnabled = true

        }

    }
}