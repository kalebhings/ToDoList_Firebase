package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    private lateinit var editText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        // Adding the items to the list when the add button is pressed
        add.setOnClickListener {
            itemList.add(editText.text.toString())
            listView.adapter = adapter
            adapter.notifyDataSetChanged()
            editText.text.clear()
        }

        // Clear the list when the clear button is pressed
        clear.setOnClickListener {
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
                    adapter.remove(itemList.get(item))
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }
    }
}