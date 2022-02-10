package edu.co.icesi.semana2akotlin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val username = intent.extras?.getString("username")
        newusernameET.setText(username)

        //Resolver el resultado que vamos a darle al MainActivity

        backBtn.setOnClickListener {
            val intent = Intent().apply {
                putExtra("newuser", newusernameET.text.toString())
            }

            if (newusernameET.text.toString().isEmpty()) {
                setResult(Activity.RESULT_CANCELED)
            } else {
                setResult(Activity.RESULT_OK, intent)
            }

            finish()
        }
    }
}