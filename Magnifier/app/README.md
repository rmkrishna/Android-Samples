##  Android P Magnifier Implementation

- Creating magnifier instance
- Show the magnifier when user touch the view
- Dismiss the magnifier

To add magnifier to the view,  just send the view as the parameter to the magnifier constructor :

    val magnifier = Magnifier(view)

Now magnifier just added the view but it will not show immediately, we should trigger to show the magnifier when ever we needed mostly when we just touch the view: 

    sampleText.setOnTouchListener { _, event ->  
      when (event.action) {  
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {  
                magnifier.show(event.rawX, event.rawY)  
            }  
            MotionEvent.ACTION_UP -> {  
                magnifier.dismiss()  
            }  
        }  
        true  
    }

Problem in the above approach is, magnifier will not show as we expected, because `event` in `setOnTouchListener` will always return the point which respect to the screen position, but we gave the view to magnifier which will not have anything in that position, To make it work we should find the exact position in the view and we should provide to the magnifier:

    // Getting Visible drawing bounds for the view  
    val outRect = Rect()  
    
    // Getting Views's X and Y location from the screen  
    val viewPosition = IntArray(2)  
      
    // Wait till the screen to render
    sampleText.post {  
      sampleText.getDrawingRect(outRect)  
        sampleText.getLocationOnScreen(viewPosition)  
      
        // Setting offset to compare the rect with touch event raw values  
      outRect.offset(viewPosition[0], viewPosition[1])  
    }  
      
    sampleText.setOnTouchListener { _, event ->  
      when (event.action) {  
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {  
                magnifier.show(event.rawX - viewPosition[0], event.rawY - viewPosition[1])  
            }  
            MotionEvent.ACTION_UP -> {  
                magnifier.dismiss()  
            }  
        }  
        true  
    } 

Ah! again we have one more problem, now magnifier is showing in all the view in the entire screen(One way it's good) but I would like to see the magnifier only in the given view and should dismiss when we touch and move out of the screen:

    if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {  
        magnifier.dismiss()  
        return@setOnTouchListener false  
    }  
    magnifier.show(event.rawX - viewPosition[0], event.rawY - viewPosition[1])


*Now Everything is fine!*

Android P API missed few configuration for developers to customized, But [Android Q added few more configuaration](https://github.com/rmkrishna/Android-Samples/tree/master/Magnifier/app-q)