package com.example.codealphafitnesstrackerapp

//import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale



var runString= ""
class RunActivity  : AppCompatActivity() {
    private var isRunning = false
    private var elapsedTime: Long = 0
    private var startTime: Long = 0

    private lateinit var handler: Handler
    private lateinit var btnStart: Button
    private lateinit var btnStop:  Button
    private lateinit var btnReset: Button
    private lateinit var btnSave: Button
    private lateinit var btnBack: Button
    private lateinit var timeTextview: TextView

    companion object{
         var maxDuration: Long = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_run)

        handler = Handler()

        timeTextview = findViewById(R.id.timeTextView)
        btnStart = findViewById(R.id.btnStart)
        btnStop =  findViewById(R.id.btnStop)
        btnReset = findViewById(R.id.btnReset)
        btnSave = findViewById(R.id.btnSave)
        btnBack = findViewById(R.id.btnBack)

        btnStart.setOnClickListener {
            startStopwatch()
        }

        btnStop.setOnClickListener {
            stopStopwatch()
        }

        btnReset.setOnClickListener {
            resetStopwatch()
        }

        btnBack.setOnClickListener {
            redirectBack()
        }


    }

    private fun saveRun() {
        println("Maximum Duration: $maxDuration")
    }

    private fun redirectBack() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


    private val runnable = object : Runnable {
        override fun run() {
            updateUI()
            handler.postDelayed(this, 1000)
        }
    }

    private fun startStopwatch() {
        if (!isRunning && timeTextview.text.equals("00:00")) {
            isRunning = true
            btnStart.isEnabled = false
            btnStop.isEnabled = true
            btnReset.isEnabled = false

            // Use SystemClock.elapsedRealtime() to account for time when the app is paused
            startTime = SystemClock.elapsedRealtime() - elapsedTime
            handler.post(runnable)
        }
        else{
            isRunning = true
            btnStart.isEnabled = false
            btnStop.isEnabled = true
            btnReset.isEnabled = false

            startTime = elapsedTime
            handler.post(runnable)

        }

    }

    private fun stopStopwatch() {
        if (isRunning) {
            isRunning = false
            btnStart.isEnabled = true
            btnStop.isEnabled = false
            btnReset.isEnabled = true

            handler.removeCallbacks(runnable)


            if (elapsedTime > maxDuration) {
                maxDuration = elapsedTime
            }
        }
    }

    private fun resetStopwatch() {
        if (!isRunning) {
            elapsedTime = 0
            startTime = SystemClock.elapsedRealtime()
            updateUI()
        }
    }

    private fun updateUI() {
        val time = SystemClock.elapsedRealtime() - startTime
        val sdf = SimpleDateFormat("mm:ss", Locale.getDefault())
        val formattedTime = sdf.format(Date(time))

        timeTextview.text = formattedTime
    }
}