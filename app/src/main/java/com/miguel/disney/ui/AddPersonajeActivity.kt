package com.miguel.disney.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import com.google.android.material.chip.Chip
import com.google.firebase.firestore.FirebaseFirestore
import com.miguel.disney.databinding.ActivityAddPersonajeBinding

class AddPersonajeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPersonajeBinding
    private lateinit var openCameraLouncher: ActivityResultLauncher<Intent>
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPersonajeBinding.inflate(layoutInflater)
        firestore = FirebaseFirestore.getInstance()
//        openCameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
//            if(result.resultCode === RESULT_OK) {
//                val photo = result.data?.extras?.get("data") as Bitmap
//                binding.imgPhoto.setImageBitmap(photo)
//            }
//        }
        setContentView(binding.root)
//
////        binding.btnTakePhoto.setOnClickListener {
////            if (permissionValidated()) {
////                takePicture()
////            }
////        }
//
        binding.btnAddPelicula.setOnClickListener {
            val pelicula = binding.tilPelicula.editText?.text.toString()
            if(pelicula.isNotEmpty()) {
                val chip = Chip(this)
                chip.text = pelicula
                binding.cgLabels.addView(chip)
                binding.tilPelicula.editText?.setText("")
            }

            binding.btnRegisterPersonaje.setOnClickListener {
                val nombre = binding.tilNombre.editText?.text.toString()
                val url = binding.tilUrl.editText?.text.toString()
                val hasPeliculas = binding.cgLabels.childCount > 0
                val isFavorite = binding.switchFavorite.isChecked

                if(nombre.isNotEmpty() && url.isNotEmpty() && hasPeliculas) {
                    addToFirestore(nombre,url,isFavorite)
                }
            }
        }
    }

    private fun addToFirestore(nombre: String, url: String, favorite: Boolean) {

        val peliculas: ArrayList<String>  = getPeliculas()
        val newPersonaje = hashMapOf<String, Any>(
            "nombre" to nombre,
            "url" to url,
            "isFavorite" to favorite,
            "peliculas" to peliculas
        )
        firestore.collection("personaje").add(newPersonaje)
            .addOnSuccessListener{
                Toast.makeText(this, "Personaje agregado con id: ${it.id}", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(this, "Ocurrio un error", Toast.LENGTH_SHORT).show()
            }
    }

    private fun getPeliculas(): ArrayList<String> {
        val peliculas = ArrayList<String>()
        val peliculasCount = binding.cgLabels.childCount
        for (i in 0 until peliculasCount) {
            val chip = binding.cgLabels.getChildAt(i) as Chip
            peliculas.add(chip.text.toString())
        }
        return peliculas
    }
//
    private fun getGetNotesFromFirestore() {
        firestore.collection("personaje").whereEqualTo("isFavorite", true).get().addOnSuccessListener {
            for (document in it.documents) {
                Log.d("Personaje firebase", document.id)
            }
        }
    }
//
//    private fun permissionValidated(): Boolean {
//        val cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
//        val permissionList: MutableList<String> = mutableListOf()
//
//        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
//            permissionList.add(Manifest.permission.CAMERA)
//        }
//
//        if (permissionList.isNotEmpty()) {
//            ActivityCompat.requestPermissions(this, permissionList.toTypedArray(), 100)
//            return false
//        }
//
//        return true
//    }
//
////    override fun onRequestPermissionsResult(
////        request: Int,
////        permission: Array<out String>,
////        grantResult: IntArray
////    ) {
//////        super.onRequestPermissionsResult(request, permission, grantResult)
//////        when (request) {
//////            100 -> {
//////                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//////                    takePicture()
//////                }
//////            }
//////        }
////    }
}