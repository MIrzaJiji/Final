package com.example.afinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_to_do.*

class ProfileActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val navController = findNavController(R.id.container_fragment)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_View)

        bottomNavigationView.setupWithNavController(navController)

    }


    fun addBtn(view: View) {
        var db = DataBaseHandler(this, null)
        if (titleEt.text.isNotEmpty() && description.text.isNotEmpty()) {

            var todolist = TodoList(title.toString(), description.text.toString())

            db.insertData(todolist)

        } else {
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
        }

    }


    fun readData(view: View) {
        val context = this
        var db = DataBaseHandler(context, null)
        var data = db.readData()
//        var data1 = db.getAllData()
        var title = db.getTitle()
        var description = db.getDescription()

//        rvTodo.layoutManager = LinearLayoutManager(this)
//        val todoAdapter = ToDoAdapter(this, title,description)
//        rvTodo.adapter = todoAdapter
        rvTodo.layoutManager = LinearLayoutManager(this)
        val todoAdapter = ToDoAdapter(this, data)
        rvTodo.adapter = todoAdapter
        for (i in 0..data.size - 1) {


//            Log.i("data", (data.get(i).id.toString() + " " + data.get(i).title + " " + data.get(i).description))

        }
    }

    fun getMail(view: View) {
        var strUser: String? = intent.getStringExtra("value")
        Log.i("mail",strUser.toString())
        tvGetMail.text = strUser.toString()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logOut -> signOut()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun signOut() {
        mAuth?.signOut()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}