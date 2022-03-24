package edu.co.icesi.semana3kotlinv2

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ContactsAdapter : RecyclerView.Adapter<ContactView>(), ContactView.OnContactDelete, ContactView.OnCall{

    private val contacts = ArrayList<Contact>()

    /*
    init {
        contacts.add(Contact("A", "Andres","123456"))
        contacts.add(Contact("B", "Camilo","456123"))
        contacts.add(Contact("C", "Domi","789456"))
        contacts.add(Contact("D", "Jose","456789"))
    }
    */

    fun addContact(contact: Contact){
        contacts.add(contact)
        notifyItemInserted(contacts.size-1)
    }

    //Genera un esqueleto gracias al XML
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactView {
        //Inflater: XML -> View
        var inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.contactrow,parent,false)
        val contactView = ContactView(row)
        contactView.listener = this
        contactView.listenerCall = this
        return contactView
    }

    //Con el esqueleto ya formado, se le ponen los datos correspondientes al esqueleto
    override fun onBindViewHolder(skeleton: ContactView, position: Int) {
        val contact = contacts[position]
        skeleton.contact = contact
        skeleton.nameRow.text = contact.name
        skeleton.phoneRow.text = contact.phone
    }

    //Este métodod permite al adapatador saber cuantos elementos se tiene
    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onDelete(contact: Contact) {
        val index = contacts.indexOf(contact)
        contacts.removeAt(index)
        notifyItemRemoved(index)
    }

    override fun onCall(context: Context, phone: String) {
        val  intent = Intent(Intent.ACTION_CALL)
        intent.setData(Uri.parse("tel:${phone}"))
        context.startActivity(intent)
    }
}