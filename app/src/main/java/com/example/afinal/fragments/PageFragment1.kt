package com.example.afinal.fragments

import android.widget.EditText
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import android.R
import android.util.Log


class PageFragment1 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val rotView = inflater.inflate(com.example.afinal.R.layout.page_1, container, false) as ViewGroup


        return rotView

    }



}