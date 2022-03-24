package edu.co.icesi.semana2akotlinv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var alfa : Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermissions(arrayOf(
            android.Manifest.permission.CALL_PHONE
        ),0)

        val launcher = registerForActivityResult(StartActivityForResult(), ::onResult)


        loginBtn.setOnClickListener {
            val username = usernameET.text.toString()
            val password = passwordET.text.toString()
            Toast.makeText(this, concatenar(username,password), Toast.LENGTH_LONG).show()

            //Llamar otra actividad
            val intent = Intent(this, ProfileActivity::class.java).apply {
                putExtra("username", username)
            }
            //startActivity
            launcher.launch(intent)

        }
    }
    //Recibe lo que retorne la otra actividad
    private fun onResult(result: ActivityResult){
        if(result.resultCode == RESULT_CANCELED){
            Toast.makeText(this, "El usuario no esta correctamente formateado", Toast.LENGTH_LONG).show()
        }else if(result.resultCode == RESULT_OK){
            val data = result.data
            val newuser = data?.extras?.getString("newuser")
            usernameET.setText(newuser)
        }
    }


    fun concatenar(a:String, b:String): String {
        return "${a}:${b}"
    }
}