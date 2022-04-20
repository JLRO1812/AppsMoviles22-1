package edu.co.icesi.semana8kotlin

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import edu.co.icesi.semana8kotlin.databinding.ActivityHomeBinding
import java.io.File

class HomeActivity : AppCompatActivity() , WebImageFragment.OnURLListener {

    private lateinit var binding: ActivityHomeBinding
    private var file:File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val cameraLauncher = registerForActivityResult(StartActivityForResult(), ::onCameraResult)
        val galleryLauncher = registerForActivityResult(StartActivityForResult(), ::onGalleryResult)



        binding.cameraBtn.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            file = File("${getExternalFilesDir(null)}/photo.png")
            val uri = FileProvider.getUriForFile(this,packageName,file!!)
            intent.putExtra(MediaStore.EXTRA_OUTPUT,uri)

            Log.e(">>>",file?.path.toString())

            cameraLauncher.launch(intent)
        }
        binding.galleryBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            galleryLauncher.launch(intent)
        }
        binding.webBtn.setOnClickListener{
            val dialog = WebImageFragment()
            dialog.listener = this
            dialog.show(supportFragmentManager, "webDialog")
        }
    }

    fun onCameraResult(result: ActivityResult){
        /*Thumbnail
        val bitmap = result.data?.extras?.get("data") as Bitmap
        binding.image.setImageBitmap(bitmap)
        */
        if(result.resultCode == RESULT_OK){
            val bitmap = BitmapFactory.decodeFile(file?.path)
            val thumbnail = Bitmap.createScaledBitmap(bitmap,bitmap.width/4,bitmap.height/4,true)
            binding.image.setImageBitmap(thumbnail)
        }else if(result.resultCode == RESULT_CANCELED){
            Toast.makeText(this,"No tomó foto", Toast.LENGTH_SHORT).show()
        }
    }

    fun onGalleryResult(result: ActivityResult){
        if(result.resultCode == RESULT_OK){
            val uriImage = result.data?.data
            uriImage?.let {
                binding.image.setImageURI(uriImage)
            }
        }
    }

    override fun onURL(url: String) {
        Log.e(">>>",url)
        Glide.with(this).load(url).centerCrop().into(binding.image)
    }
}