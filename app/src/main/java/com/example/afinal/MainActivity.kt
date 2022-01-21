package com.example.afinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.afinal.fragments.PageFragment1
import com.example.afinal.fragments.PageFragment2
import java.util.ArrayList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.widget.Toast


import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.fragment_account.*

import kotlinx.android.synthetic.main.page_1.*
import kotlinx.android.synthetic.main.page_2.*


class MainActivity : AppCompatActivity() {


    private lateinit var pager: ViewPager
    private lateinit var pagerAdapter: PagerAdapter


    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance();

        val list: MutableList<Fragment> = ArrayList()

        list.add(PageFragment1())
        list.add(PageFragment2())

        pager = findViewById<ViewPager>(R.id.pager)
        pagerAdapter = SlidePagerAdapter(supportFragmentManager, list)

        pager.setAdapter(pagerAdapter)


    }
//
//    override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = mAuth!!.currentUser
//        if (currentUser != null) {
//            reload()
//        }
//
//    }

    private fun reload(user: FirebaseUser) {
        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra("value",user.email)
        Log.i("mail in intent", user.email.toString())
        startActivity(intent)
    }

    fun login(view: View) {
        if (mail.text.isNotEmpty() && password.text.isNotEmpty()) {

            mAuth?.signInWithEmailAndPassword(mail.text.toString(), password.text.toString())
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = mAuth!!.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }


        } else {
            Toast.makeText(this, "fields are empty", Toast.LENGTH_SHORT).show()
        }

    }

    fun registration(view: View) {

        Log.i("pas1", reg_password.text.toString())
        Log.i("pas2", confirm.text.toString())
        if (reg_password.text.toString() == confirm.text.toString() && reg_password.text.isNotEmpty() && reg_mail.text.isNotEmpty()) {

            mAuth!!.createUserWithEmailAndPassword(reg_mail.text.toString(),
                reg_password.text.toString())
                .addOnCompleteListener(
                    this
                ) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = mAuth!!.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            this, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        updateUI(null)
                    }

                    // ...
                }
        } else {
            Toast.makeText(this, "problem in field", Toast.LENGTH_SHORT).show()
        }

    }

    private fun updateUI(user: FirebaseUser?) {

        if (user != null) {
            reload(user)
            Toast.makeText(this, "not null", Toast.LENGTH_SHORT).show()
            if (user.equals(null)) {
                Toast.makeText(this, "null", Toast.LENGTH_SHORT).show()
            }
        }
    }
}