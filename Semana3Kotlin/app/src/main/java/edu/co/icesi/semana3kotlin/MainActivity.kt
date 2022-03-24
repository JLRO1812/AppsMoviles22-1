package edu.co.icesi.semana3kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var contactRecycler: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: ContacsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutManager = LinearLayoutManager(this)

        contactRecycler.layoutManager = layoutManager
        contactRecycler.setHasFixedSize(true)

        adapter = ContacsAdapter()
        contactRecycler.adapter = adapter

        addBtn.setOnClickListener{

            val contact = Contact(UUID.randomUUID().toString(), nameET.text.toString(), telET.text.toString())
            adapter.addContact(contact)

        }
    }
}