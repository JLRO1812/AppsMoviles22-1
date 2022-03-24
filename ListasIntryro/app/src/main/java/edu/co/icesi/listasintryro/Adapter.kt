package edu.co.icesi.listasintryro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Adapter : RecyclerView.Adapter<ContactRow>() {

    val data = ArrayList<Contact>()


    init {
        data.add(Contact("Juan","123456789","8:54"))
        data.add(Contact("Fernanda","123484489","8:55"))
        data.add(Contact("Josue","895756789","8:57"))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactRow {

        //XML -> View
        //Proceso de Inflate
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.activity_contractrow,parent,false)
        val contactRow = ContactRow(view)
        return contactRow
    }

    override fun onBindViewHolder(holder: ContactRow, position: Int) {
        val contact = data[position]
        holder.nameRow.text = contact.name
        holder.phoneRow.text = contact.phone
        holder.dateRow.text = contact.date
    }

    override fun getItemCount(): Int {
        return data.size
    }


}