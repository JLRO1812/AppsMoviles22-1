package edu.co.icesi.firestoreejemplokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.co.icesi.firestoreejemplokotlin.databinding.ActivityChatBinding
import edu.co.icesi.firestoreejemplokotlin.databinding.ActivityHomeBinding
import java.util.*

class ChatActivity : AppCompatActivity() {

    private lateinit var user: User
    private lateinit var  contact: User

    private lateinit var binding: ActivityChatBinding

    private lateinit var chat:Chat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent. extras?.get("user") as User
        contact = intent.extras?.get("contact") as User

        Firebase.firestore.collection("users").document(user.id).collection("chats")
            .whereEqualTo("contactID", contact.id).get().addOnCompleteListener{ task->
                if(task.result?.size()==0){
                    createChat()
                }else{
                    for(document in task.result!!){
                        chat = document.toObject(Chat::class.java)
                        break
                    }
            }
        }
    }

    private fun createChat(){
        val id = UUID.randomUUID().toString()
        chat = Chat(id, contact.id)
        val foreingChat = Chat(id, user.id)
        Firebase.firestore.collection("users").document(user.id).collection("chats").document(id).set(chat)
        Firebase.firestore.collection("users").document(contact.id).collection("chats").document(id).set(foreingChat)

    }
}