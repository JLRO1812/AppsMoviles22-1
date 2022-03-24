package edu.co.icesi.semana3kotlin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ContacsAdapter : RecyclerView.Adapter<ContactView>(), ContactView.OnContactDelete{

    private val contacts = ArrayList<Contact>()

    /*
    init{
        contacts.add(Contact("0","Jose","12345"))
        contacts.add(Contact("1","Luis","56789"))
        contacts.add(Contact("2","Restrepo","98745"))
        contacts.add(Contact("3","Obando","6321"))
    }
     */

    fun addContact(contact: Contact){
        contacts.add(contact)
        notifyItemInserted(contacts.size-1)
    }


    //Genera un esqueleto gracias al XML
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactView {
        //Inflater: xml -> view
        var inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.contactrow, parent,false)
        val contactView = ContactView(row)
        contactView.listener = this
        return contactView

    }

    //Con el esqueleto ya formado, se le ponen los datos correspondinte al esqueleto
    override fun onBindViewHolder(skeleton: ContactView, position: Int) {
        val contact = contacts[position]
        skeleton.contact = contact
        skeleton.nameRow.text = contact.name
        skeleton.phoneRow.text = contact.phone
    }

    //Este método permite al adaptador saber cuántos elementos se tiene
    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onDelete(contact: Contact) {
        val index = contacts.indexOf(contact)
        contacts.removeAt(index)
        notifyItemRemoved(index)
    }
}