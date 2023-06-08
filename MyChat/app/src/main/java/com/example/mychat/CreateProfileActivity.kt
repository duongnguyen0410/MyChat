package com.example.mychat

import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
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

    private lateinit var imageView: ImageView
    private lateinit var selectedImageUri: Uri
    private lateinit var storageReference: StorageReference

    private lateinit var binding: ActivityCreateProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val ivProfile: CircleImageView = binding.ivProfile

        val storage = Firebase.storage
        storageReference = storage.reference
    }

    fun onImageViewClick(view: View) {

    }

    private fun openImagePicker() {

    }
}