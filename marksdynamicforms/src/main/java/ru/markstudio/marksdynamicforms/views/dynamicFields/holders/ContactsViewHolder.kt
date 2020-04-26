package ru.mysmartflat.kortros.view.customViews.dynamic

import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.markstudio.marksdynamicforms.R
import ru.markstudio.marksdynamicforms.views.dynamicFields.Contact
import ru.markstudio.marksdynamicforms.views.dynamicFields.ContactsField
import ru.markstudio.marksdynamicforms.views.dynamicFields.Field


class ContactsViewHolder(itemView: View) : BaseViewHolder(itemView) {

    private var isRequired = false

    override fun bind(item: Field) {
        (itemView as RecyclerView).apply {
            layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
            adapter = ContactsAdapter((item as ContactsField).contactList)
        }
    }
}

class ContactsAdapter(val contacts: List<Contact>) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.field_contact, parent, false))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(contact: Contact) {
            (itemView as TextView).apply {
                text = contact.data
                movementMethod = LinkMovementMethod()
            }
        }
    }

    override fun getItemCount() = contacts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

}
