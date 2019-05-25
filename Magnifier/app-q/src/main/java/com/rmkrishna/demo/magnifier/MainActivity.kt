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

        var outRect = Rect()
        sampleText.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                    val viewPosition = IntArray(2)
                    sampleText.getDrawingRect(outRect)

                    sampleText.getLocationOnScreen(viewPosition)
                    outRect.offset(viewPosition[0], viewPosition[1])


                    if (outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                        var currentXPosition = event.rawX - viewPosition[0]
                        var currentYPosition = event.rawY - viewPosition[1]
                        magnifier.show(
                            currentXPosition,
                            currentYPosition,
                            currentXPosition,
                            currentYPosition - (viewPosition[1].toFloat()/ 2)
                        )

//                        magnifier.show(
//                            currentXPosition,
//                            currentYPosition
//                        )
                    } else {
                        magnifier.dismiss()
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
