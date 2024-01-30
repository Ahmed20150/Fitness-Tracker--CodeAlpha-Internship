package com.example.codealphafitnesstrackerapp

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.gtappdevelopers.kotlingfgproject.GridRVAdapter

class MainActivity : AppCompatActivity() {
    lateinit var courseGRV: GridView
    lateinit var courseList: List<GridViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        courseGRV = findViewById(R.id.idGRV)
        courseList = ArrayList<GridViewModel>()


        courseList = courseList + GridViewModel("Calculate Hydration", R.drawable.water ,
            HydrationActivity::class.java)
        courseList = courseList + GridViewModel("Calculate BPM", R.drawable.heart ,
            BPMActivity::class.java)
        courseList = courseList + GridViewModel("Meal Plan", R.drawable.meal,
            MealActivity::class.java)
        courseList = courseList + GridViewModel("Record OutDoor Run", R.drawable.running_boy ,
            RunActivity::class.java )
//        courseList = courseList + GridViewModel("Javascript", R.drawable.js)

        val courseAdapter = GridRVAdapter(courseList = courseList, this@MainActivity)


        courseGRV.adapter = courseAdapter


        courseGRV.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val targetActivityClass = courseList[position].targetActivity

            val intent = Intent(this@MainActivity, targetActivityClass)
            startActivity(intent)


             finish()
        }





    }
}