# Magnifier Sample for Android P and Q

![Magnifier image from Google](https://developer.android.com/guide/topics/text/images/magnifier-1.png)


> ***Android dev doc*** :  Magnifier widget is a virtual magnifying glass that displays an enlarged copy of a chosen View through an overlay pane that represents the lens

Android supports magnifier from Android P with minimal API.

- Can magnify any view TextView, EditText, ImageView and even Webview
- Can show the magnifier in desired position
- Can dismiss the magnifier

For More details about the implementation see in [Magnifier P Implementation](https://github.com/rmkrishna/Android-Samples/tree/master/Magnifier/app) 

In Android Q, Magnifier got few more flexible API for developers(using Builder)

- Instead of using direct constructor ~~`new Magnifier (view)`~~ we should use builder to get Magnifier object `new Magnifier.Builder(view).build()`
- Can set the InitialZoom level
- Can set the Elevation for the magnifier
- Can set the CornerRadius
- Can set the magnifier position

For More details about the implementation see in [Magnifier Q Implementation](https://github.com/rmkrishna/Android-Samples/tree/master/Magnifier/app-q)