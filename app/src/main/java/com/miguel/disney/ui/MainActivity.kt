package com.miguel.disney.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.miguel.disney.R
import com.miguel.disney.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAddNote.setOnClickListener{
            val intent = Intent(this, AddPersonajeActivity::class.java)
            startActivity(intent)
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_note) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvMenu.setupWithNavController(navController)

//        binding.bnvMenu.setOnItemSelectedListener { item ->
//            when(item.itemId) {
//                R.id.note_list -> {
//                    binding.txtHelloWord.text = "LISTA"
//                    true
//                }
//                R.id.note_favorite -> {
//                    binding.txtHelloWord.text = "FAVORITO"
//                    true
//                }
//                R.id.note_info -> {
//                    binding.txtHelloWord.text = "INFO"
//                    true
//                }
//                else -> {
//                    false
//                }
//            }
//        }

    }
}