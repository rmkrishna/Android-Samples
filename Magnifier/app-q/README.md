##  Android Q Magnifier Implementation

As we know Android supports [Magnifier in Android-P](https://github.com/rmkrishna/Android-Samples/tree/master/Magnifier/app) , In Q we got more flexible API to enhance the Magnifier view in the way of builder pattern.

To create instance, instead of using direct constructor(Like Android P way ~~`new Magnifier(view)`~~)  , we should use builder :

    val magnifier = Magnifier.Builder(view).build()

Now we can add more initial setting like InitialZoom level, Elevation, CornerRadius :

    val magnifier = Magnifier.Builder(view)  
        .setInitialZoom(2.0f)  
        .setElevation(20.0f)  
        .setCornerRadius(10.0f).build()

Showing and dismiss the Magnifier is same as [Magnifier in Android-P](https://github.com/rmkrishna/Android-Samples/tree/master/Magnifier/app) , but now we have one extra API to show the Magnifier and we can customize the place for the Magnifier, for example like this: 

    magnifier.show(sourceCenterX, sourceCenterY, magnifierCenterX, magnifierCenterY)


Done!!