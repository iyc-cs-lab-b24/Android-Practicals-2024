package com.example.contextmenu

import android.os.Bundle
//import android.support.v7.app.AppCompatActivity
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var array: Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title="long_press_on_list_item_to_open_context_menu"
//Find View By Id For ListView
        val listView = findViewById<ListView>(R.id.listView) as ListView
//Array of String Which Contain Name of Persons.
        array = arrayOf("Charul Ma'am (admin)", "Chandan ", "Sybal Ma'am", "Vakil Ma'am", "Python Ma'am", "Mudgul Ma'am", "Komal Ma'am",
            "Mehta Sir", "Shelke Sir")
//Creating Adapter
        val adp = ArrayAdapter(this@MainActivity,
            android.R.layout.simple_list_item_1, array)
//Set Adapter to ListView
        listView.adapter = adp
//Register ListView for Context Menu
        registerForContextMenu(listView)
    }
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo:
    ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
//Set Header of Context Menu
        menu!!.setHeaderTitle("Select Option")
        menu.add(0, v!!.id, 0, "Call")
        menu.add(0, v.id, 1, "SMS")
        menu.add(0, v.id, 2, "Email")
        menu.add(0, v.id, 3, "WhatsApp")
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
//Get Order of Selected Item
        val selectedItemOrder = item!!.order
//Get Title Of Selected Item
        val selectedItemTitle = item.title
//To get Name of Person Click on ListView
        val info = item.menuInfo as AdapterContextMenuInfo
        val listPosition = info.position
        val name = array[listPosition]
        Toast.makeText(this@MainActivity, " " + selectedItemTitle + " " +
                name, Toast.LENGTH_LONG).show()
        return true
    }
}
