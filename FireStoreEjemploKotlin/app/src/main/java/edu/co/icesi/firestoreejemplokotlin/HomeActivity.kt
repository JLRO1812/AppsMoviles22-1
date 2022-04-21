package edu.co.icesi.firestoreejemplokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.co.icesi.firestoreejemplokotlin.databinding.ActivityHomeBinding
import edu.co.icesi.firestoreejemplokotlin.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var adapter: ArrayAdapter<User>
    private lateinit var users: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        users = ArrayList()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, users)
        binding.userListView.adapter = adapter

        Firebase.firestore.collection("users").get().addOnCompleteListener{ task->
            for(doc in task.result!!){
                val user = doc.toObject(User::class.java)
                users.add(user)
                adapter.notifyDataSetChanged()
            }
        }
    }
}