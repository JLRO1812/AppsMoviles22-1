package edu.co.icesi.firestoreejemplokotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.co.icesi.firestoreejemplokotlin.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            val username = binding.usernameET.text.toString()
            val pass = binding.passET.text.toString()
            val user = User(UUID.randomUUID().toString(), username, pass)

            val query = Firebase.firestore.collection("users").whereEqualTo("username", username)
            query.get().addOnCompleteListener { task ->

                //Si el usuario no existe crearlo e iniciar sesion con él
                if(task.result?.size() == 0){
                    Firebase.firestore.collection("users").document(user.id).set(user)
                    val intent = Intent(this, HomeActivity::class.java).apply {
                        putExtra("user", user)
                    }
                    startActivity(intent)
                }

                //Si ya existe, descargar el usuario e iniciar sesion con el
                else{
                    lateinit var existingUser : User
                    for(document in task.result!!){
                        existingUser = document.toObject(User::class.java)
                        break
                    }
                    if(existingUser.pass == pass){
                        val intent = Intent(this, HomeActivity::class.java).apply {
                            putExtra("user", existingUser)
                        }
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}