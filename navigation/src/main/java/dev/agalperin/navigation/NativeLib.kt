package dev.agalperin.navigation

class NativeLib {

    /**
     * A native method that is implemented by the 'navigation' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'navigation' library on application startup.
        init {
            System.loadLibrary("navigation")
        }
    }
}