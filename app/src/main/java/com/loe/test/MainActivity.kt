package com.loe.test

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        letterView.setOnLetterChangedListener()
        { s, position ->
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
        }

        imageTextView.setLeftImage(R.mipmap.search_black, resources.getColor(R.color.colorPrimary))

        stateButton.setOnClickListener()
        {
            switchButton.isChecked = !switchButton.isChecked
        }

//        var g = GradientDrawable()
//        g.setColor(ContextCompat.getColor(this, R.color.colorPrimary))
//        g.cornerRadius = 12f
//        buttonTest.background = g

//        buttonTest.stateListAnimator = null
    }
}