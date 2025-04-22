package com.example.thuchanhgiuaki

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var stopwatch: Chronometer //The stopwatch
    var running = false //Is the stopwatch running?
    var offset: Long = 0 //The base offset for the stopwatch

    val OFFSET_KEY = "offset"
    val RUNNING_KEY = "running"
    val BASE_KEY = "base"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        print("onCreate")

        stopwatch = findViewById<Chronometer>(R.id.stopwatch)
        //The start button starts the stopwatch if it's not running

//        if (savedInstanceState != null) {
//            offset = savedInstanceState.getLong(OFFSET_KEY)
//            running = savedInstanceState.getBoolean(RUNNING_KEY)
//            if (running) {
//                stopwatch.base = savedInstanceState.getLong(BASE_KEY)
//                stopwatch.start()
//            } else setBaseTime()
//        }

        val startButton = findViewById<Button>(R.id.start_button)
        startButton.setOnClickListener {
            if (!running) {
                setBaseTime()
                stopwatch.start()
                running = true
            }
        }
        //The pause button pauses the stopwatch if it’s running
        val pauseButton = findViewById<Button>(R.id.pause_button)
        pauseButton.setOnClickListener {
            if (running) {
                saveOffset()
                stopwatch.stop()
                running = false
            }
        }

        val resetButton = findViewById<Button>(R.id.reset_button)
        resetButton.setOnClickListener {
            offset = 0
            setBaseTime()
        }
    }
//    override fun onSaveInstanceState(savedInstanceState: Bundle) {
//        savedInstanceState.putLong(OFFSET_KEY, offset)
//        savedInstanceState.putBoolean(RUNNING_KEY, running)
//        savedInstanceState.putLong(BASE_KEY, stopwatch.base)
//        super.onSaveInstanceState(savedInstanceState)
//    }



    fun setBaseTime() {
        stopwatch.base = SystemClock.elapsedRealtime() - offset
    }
//Record the offset
    fun saveOffset() {
        offset = SystemClock.elapsedRealtime() - stopwatch.base
    }
    override fun onStart() {
        super.onStart()
        print("onStart")

    }

    override fun onResume() {
        super.onResume()
        print("onResume")
//        if (running) {
//            setBaseTime()
//            stopwatch.start()
//            offset = 0
//        }
    }

    override fun onPause() {
        super.onPause()
        print("onPause")
//        if (running) {
//            saveOffset()
//            stopwatch.stop()
//        }
    }

    override fun onStop() {
        super.onStop()
        print("onStop")
//        saveOffset()
//        stopwatch.stop()

    }

    override fun onRestart() {
        super.onRestart()
        print("onRestart")
//        setBaseTime()
//        stopwatch.start()
//        offset = 0
    }

    override fun onDestroy() {
        super.onDestroy()
        print("onDestroy")
    }

    fun print(msg: String){
        Log.d("Activity State ",msg)
    }


}