package edu.co.icesi.semana2akotlinv2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        /*
        //Envio de la pantalla main a profile
        val username = intent.extras?.getString("username")

        userTel.setText(username)

        //Resolver el resultado que vamos a darle a MainActivity

        callBtn.setOnClickListener {
            val intent = Intent().apply {
                putExtra("newuser", userTel.text.toString())
            }
            if(userTel.text.toString().isEmpty()){
                setResult(Activity.RESULT_CANCELED)
            }else{
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
        */

        callBtn.setOnClickListener {
            //LLamado al sistema operativo
            val  intent = Intent(Intent.ACTION_CALL)
            intent.setData(Uri.parse("tel:${userTel.text}"))
            startActivity(intent)

        }



    }
}