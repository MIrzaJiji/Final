package com.example.afinal.fragments

import android.widget.EditText
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.afinal.R
import kotlinx.android.synthetic.main.page_2.*

class PageFragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val rotView = inflater.inflate(R.layout.page_2, container, false) as ViewGroup
        return rotView
    }


}