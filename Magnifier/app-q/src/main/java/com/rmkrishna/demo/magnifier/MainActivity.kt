package com.rmkrishna.demo.magnifier

import android.graphics.Rect
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Magnifier
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val magnifier = Magnifier.Builder(sampleText)
            .setInitialZoom(2.0f)
            .setElevation(20.0f)
            .setClippingEnabled(true)
            .setCornerRadius(10.0f * 3).build()

        // Getting Visible drawing bounds of your view
        val outRect = Rect()

        // Getting Views's X and Y location from the screen
        val viewPosition = IntArray(2)

        sampleText.post {
            sampleText.getDrawingRect(outRect)
            sampleText.getLocationOnScreen(viewPosition)

            // Setting offset to compare the rect with touch event raw values
            outRect.offset(viewPosition[0], viewPosition[1])
        }

        sampleText.setOnTouchListener { _, event ->
            when (event.action) {


                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                    if (!canShowOutside.isChecked &&
                        !outRect.contains(event.rawX.toInt(), event.rawY.toInt())
                    ) {
                        magnifier.dismiss()
                        return@setOnTouchListener false
                    }

                    val currentXPosition = event.rawX - viewPosition[0]
                    val currentYPosition = event.rawY - viewPosition[1]

                    if (showCustomizeCenterMagnifier.isChecked) {
                        magnifier.show(
                            currentXPosition,
                            currentYPosition,
                            currentXPosition,
                            currentYPosition - (viewPosition[1].toFloat() / 2)
                        )
                    } else {
                        magnifier.show(
                            currentXPosition,
                            currentYPosition
                        )
                    }
                }
                MotionEvent.ACTION_UP -> {
                    magnifier.dismiss()
                }
            }
            true
        }
    }
}
