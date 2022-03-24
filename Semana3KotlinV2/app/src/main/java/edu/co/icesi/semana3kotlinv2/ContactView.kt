package edu.co.icesi.semana3kotlinv2

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactView(itemView:View) : RecyclerView.ViewHolder(itemView) {

    //State
    var contact:Contact?=null

    //Listerners
    var listener : OnContactDelete? = null
    var listenerCall : OnCall? = null

    //Identificar los UI components
    var nameRow : TextView = itemView.findViewById(R.id.nameRow)
    var phoneRow : TextView = itemView.findViewById(R.id.phoneRow)
    var imageView : ImageView = itemView.findViewById(R.id.imageRow)
    var deleteRow : Button = itemView.findViewById(R.id.deleteRow)
    var callBtn : Button = itemView.findViewById(R.id.callBtn)

    init {
        deleteRow.setOnClickListener {
            //Toast.makeText(itemView.context,contact?.name, Toast.LENGTH_LONG).show()
            contact?.let {
                listener?.onDelete(it)
            }

        }

        callBtn.setOnClickListener {
            //Toast.makeText(itemView.context,contact?.phone, Toast.LENGTH_LONG).show()
            contact?.let {
                listenerCall?.onCall(callBtn.context, it.phone)
            }
        }
    }

    interface OnContactDelete{
        fun onDelete(contact: Contact)
    }

    interface OnCall{
        fun onCall(context: Context, phone: String)
    }

}