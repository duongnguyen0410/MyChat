package com.example.mychat

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mychat.databinding.ActivityCreateProfileBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import de.hdodenhof.circleimageview.CircleImageView

class CreateProfileActivity : AppCompatActivity() {

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
    }

    //tvUpload Clicked
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun onImageViewClick(view: View) {
        openImagePicker()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1){
            // Get the url of the image from data
            selectedImageUri = data?.data!!
            if(null != selectedImageUri){
                // Update the user image
                ivProfile.setImageURI(selectedImageUri)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun openImagePicker() {
        // Open Image Picker
        val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
        startActivityForResult(intent, 1)
    }
}