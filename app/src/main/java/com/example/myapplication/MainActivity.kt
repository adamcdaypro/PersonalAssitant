package com.example.myapplication

import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.Contact
import com.example.myapplication.ui.ContactListFragment
import com.example.myapplication.ui.ContactViewModel


class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(ContactViewModel::class.java)
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkPermissions()
    }

    private fun checkPermissions() {
        if (checkSelfPermission(android.Manifest.permission.WRITE_CONTACTS) == PackageManager.PERMISSION_GRANTED
            && checkSelfPermission(android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED
        ) {
            createContactsFragment()
        } else {
            val permissions = arrayOf(
                android.Manifest.permission.WRITE_CONTACTS,
                android.Manifest.permission.READ_CONTACTS
            )
            requestPermissions(permissions, 1)
        }
    }

    private fun createContactsFragment() {
        val fragment = supportFragmentManager.findFragmentByTag(ContactListFragment.TAG)
        if (fragment == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, ContactListFragment.newInstance(1))
                .commit()
        }
    }
}