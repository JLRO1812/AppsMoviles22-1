package edu.co.icesi.semana2akotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private var alfa : Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermissions(arrayOf(
           android.Manifest.permission.CALL_PHONE
        ),0)


        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::onResult)

        loginBtn.setOnClickListener{
            val username = usernameET.text.toString()
            val password = passwordET.text.toString()

            Toast.makeText(this, "${username}:${password}", Toast.LENGTH_LONG).show()

            val intent = Intent(this, ProfileActivity::class.java).apply{
                putExtra("username",username)
            }
            //startActivityForResult(intent) //Solo para enviar
            launcher.launch(intent) //Callback
        }
    }
    private fun onResult(result: ActivityResult){
        if(result.resultCode == RESULT_CANCELED){
            Toast.makeText(this,"El usuario no esta correctamente fomrateado", Toast.LENGTH_LONG)
        }else if(result.resultCode == RESULT_OK){
            val data : Intent? = result.data
            val newuser = data?.extras?.getString("newuser")
            usernameET.setText(newuser)
        }
    }

    fun concatenar(a:String, b:String): String{
        return "${a}:${b}"
    }
}