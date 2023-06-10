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
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mychat.Interfaces.AppConstants
import com.example.mychat.databinding.ActivityCreateProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import de.hdodenhof.circleimageview.CircleImageView

class CreateProfileActivity : AppCompatActivity() {

    private var selectedImageUri: Uri ?= null
    private lateinit var imageUrl: String
    private lateinit var userName: String
    private lateinit var phoneNumber: String

    private lateinit var ivProfile: CircleImageView
    private lateinit var etUsername: EditText
    private lateinit var etPhoneNum: EditText
    private lateinit var btDone: Button

    private var databaseReference: DatabaseReference? = null
    private var firebaseAuth: FirebaseAuth? = null
    private var storageReference: StorageReference? = null

    private lateinit var binding: ActivityCreateProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ivProfile = binding.ivProfile
        etUsername = binding.etUserName
        etPhoneNum = binding.etPhoneNumber
        btDone = binding.btDone

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
            selectedImageUri = data?.data!! // Get the url of the image from data
            if(null != selectedImageUri){
                ivProfile.setImageURI(selectedImageUri) // Update the user image
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun openImagePicker() {
        // Open Image Picker
        val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
        startActivityForResult(intent, 1)
    }

    fun onDoneButtonClick(view: View){
        if (checkData()){
            uploadData(userName, phoneNumber, selectedImageUri!!)
        }
    }

    private fun uploadData(name: String, phone: String, image: Uri) {
        storageReference!!.child(firebaseAuth!!.uid + AppConstants.PATH).putFile(image).addOnSuccessListener {
            val task = it.storage.downloadUrl
            task.addOnCompleteListener { uri ->
                imageUrl = uri.result.toString()
                val map = mapOf(
                    "name" to name,
                    "phone" to phone,
                    "image" to imageUrl
                )
                databaseReference!!.child(firebaseAuth!!.uid!!).updateChildren(map)
            }
        }
    }

    private fun checkData(): Boolean {
        userName = etUsername.text.toString().trim()
        phoneNumber = etPhoneNum.text.toString().trim()

        if (userName.isEmpty()) {
            etUsername.error = "Filed is required"
            return false
        } else if (phoneNumber.isEmpty()) {
            etPhoneNum.error = "Filed is required"
            return false
        }
        return true
    }
}