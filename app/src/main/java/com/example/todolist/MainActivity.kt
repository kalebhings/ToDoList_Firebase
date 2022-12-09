package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.*
import androidx.core.view.get
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var editText : EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        val db = FirebaseDatabase.getInstance().reference

        // Assign values of the buttons to variable
        val add = findViewById<Button>(R.id.add)
        val clear = findViewById<Button>(R.id.clear)
        val delete = findViewById<Button>(R.id.delete)

        // Assign listView to a variable
        val listView = findViewById<ListView>(R.id.listView)

        // Assign the variable that was initialized late to the text input
        editText = findViewById(R.id.editText)

        // Initializing the array lists and the adapter
        var itemList = arrayListOf<String>()
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, itemList)

        var getData = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children) {
                    var itemName = i.child("task").getValue().toString()
                    if (! itemList.contains(itemName))
                        itemList.add(itemName)
                        listView.adapter = adapter
                        adapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }

        // Adding the items to the list when the add button is pressed
        add.setOnClickListener {
            var item: String = editText.text.toString()
            db.child(item + "1").setValue(Tasks(item))
            editText.text.clear()
        }

        // Clear the list when the clear button is pressed
        clear.setOnClickListener {
            db.removeValue()
            itemList.clear()
            adapter.notifyDataSetChanged()
        }

        // Clear the item that is selected when the delete button is pressed
        delete.setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1

            while (item >= 0) {
                if (position.get(item))
                {
                    var result = itemList.get(item)
                    db.child(result).child(result).setValue(null)
                    db.child(result + "1").setValue(null)
                    adapter.remove(itemList.get(item))
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }


        db.addValueEventListener(getData)
        db.addListenerForSingleValueEvent(getData)
    }

    public override fun onStart() {
        super.onStart()
        // Checks if user is signed in when the app starts. If they are, send them to next activity
        val currentUser = Firebase.auth.currentUser
        if (currentUser == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}