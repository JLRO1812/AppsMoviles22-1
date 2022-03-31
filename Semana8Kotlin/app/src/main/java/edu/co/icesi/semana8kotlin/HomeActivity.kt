package edu.co.icesi.semana8kotlin

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.core.content.FileProvider
import edu.co.icesi.semana8kotlin.databinding.ActivityHomeBinding
import java.io.File

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var file:File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val launcher = registerForActivityResult(StartActivityForResult(), ::onResult)


        binding.cameraBtn.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            file = File("${getExternalFilesDir(null)}/photo.png")
            val uri = FileProvider.getUriForFile(this,packageName,file!!)
            intent.putExtra(MediaStore.EXTRA_OUTPUT,uri)

            Log.e(">>>",file?.path.toString())

            launcher.launch(intent)
        }
    }

    fun onResult(result: ActivityResult){
        /*Thumbnail
        val bitmap = result.data?.extras?.get("data") as Bitmap
        binding.image.setImageBitmap(bitmap)
        */
        if(result.resultCode == RESULT_OK){
            val bitmap = BitmapFactory.decodeFile(file?.path)
            binding.image.setImageBitmap(bitmap)
        }else if(result.resultCode == RESULT_CANCELED){

        }
    }
}