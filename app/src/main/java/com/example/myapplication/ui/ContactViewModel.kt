package com.example.myapplication.ui

import android.app.Application
import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import android.provider.ContactsContract.Contacts
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Contact

class ContactViewModel(application: Application) : AndroidViewModel(application) {

    val contacts: LiveData<List<Contact>>

    init {
        contacts = MutableLiveData(getUserContacts(application))
    }

    private fun getUserContacts(context: Context): List<Contact> {
        val contactsCursor: Cursor = context.contentResolver?.query(
            Contacts.CONTENT_URI,
            arrayOf(Contacts.NAME_RAW_CONTACT_ID, Contacts.DISPLAY_NAME, Contacts.STARRED, Contacts.HAS_PHONE_NUMBER),
            Contacts.STARRED + " = true",
            null,
            Contacts.DISPLAY_NAME
        ) ?: return emptyList()

        val contacts = mutableListOf<Contact>()

        val contactRawContactIdIndex = contactsCursor.getColumnIndex(Contacts.NAME_RAW_CONTACT_ID)
        val contactNameIndex = contactsCursor.getColumnIndex(Contacts.DISPLAY_NAME)
        val contactStarredIndex = contactsCursor.getColumnIndex(Contacts.STARRED)
        val contactHasPhoneNumberIndex = contactsCursor.getColumnIndex(Contacts.HAS_PHONE_NUMBER)

        while (contactsCursor.moveToNext()) {
            val contact = Contact(
                rawContactId = contactsCursor.getInt(contactRawContactIdIndex),
                name = contactsCursor.getString(contactNameIndex),
                starred = contactsCursor.getInt(contactStarredIndex) == 1,
                hasPhoneNumber = contactsCursor.getInt(contactHasPhoneNumberIndex) == 1
            )
            contacts.add(contact)
        }
        contactsCursor.close()
        return contacts
    }
}
