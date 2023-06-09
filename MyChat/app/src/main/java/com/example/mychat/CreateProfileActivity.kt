package com.example.mychat

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mychat.databinding.ActivityCreateProfileBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import de.hdodenhof.circleimageview.CircleImageView

class CreateProfileActivity : AppCompatActivity() {

    private val PICK_IMAGE_REQUEST = 1
    private val PERMISSION_REQUEST_CODE = 2

    private lateinit var ivProfile: CircleImageView
    private var selectedImageUri: Uri ?= null
    private lateinit var storageReference: StorageReference

    private lateinit var binding: ActivityCreateProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ivProfile = binding.ivProfile

        val storage = Firebase.storage
        storageReference = storage.reference

//        val tvUpload: TextView = binding.tvUpload
//        tvUpload.setOnClickListener {
//            onImageViewClick()
//        }
    }

    fun onImageViewClick(view: View) {
//        lateinit var galleryIntent: Intent
//        galleryIntent.setAction(Intent.ACTION_GET_CONTENT)
//        galleryIntent.setType("image/*")
//        startActivityForResult(galleryIntent, 2)
        Log.d("tvUpload", "onImageViewClick: clicked")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 2 && resultCode == RESULT_OK && data != null){
            selectedImageUri = data.data
            ivProfile.setImageURI(selectedImageUri)
        }
    }

    private fun openImagePicker() {

    }
}