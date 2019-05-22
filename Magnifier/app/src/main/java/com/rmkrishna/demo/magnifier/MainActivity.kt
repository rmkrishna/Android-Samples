package com.rmkrishna.demo.magnifier

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Magnifier
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val magnifier = Magnifier(sampleText)

        sampleText.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN or MotionEvent.ACTION_MOVE -> {
                    magnifier.show(event.x, event.y)
                }
                MotionEvent.ACTION_UP -> {
                    magnifier.dismiss()
                }
            }
            true
        }
    }
}
