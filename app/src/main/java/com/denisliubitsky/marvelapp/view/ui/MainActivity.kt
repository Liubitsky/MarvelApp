package com.denisliubitsky.marvelapp.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.denisliubitsky.marvelapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.appbar)
        toolbar.setBackgroundColor(resources.getColor(R.color.colorPrimary, null))
        setSupportActionBar(toolbar)
    }
}