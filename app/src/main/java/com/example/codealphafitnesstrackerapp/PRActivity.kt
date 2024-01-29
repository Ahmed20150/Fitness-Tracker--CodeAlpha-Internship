package com.example.codealphafitnesstrackerapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

var maxRun = RunActivity.maxDuration
class PRActivity: AppCompatActivity() {
    private lateinit var backBtn: Button
    private lateinit var prList: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pr)

        backBtn  = findViewById(R.id.backBtn)
        prList = findViewById(R.id.prList)

        backBtn.setOnClickListener {
            redirectBack()
        }

        loadPR()

    }

    private fun redirectBack() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun addToPrList(str: String){

    }

    fun loadPR(){
        var runText = "Maximum Run:"+ maxRun
        prList.text= runText
    }



    private fun retrieveSavedText(): String {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val mealsJson = sharedPreferences.getString("Meals", "")

        val savedMeals: MutableList<String> =
            Gson().fromJson(mealsJson, object : TypeToken<MutableList<String>>() {}.type)
                ?: mutableListOf()

        return savedMeals.joinToString("\n\n") { it }
    }

    private fun saveMealsToSharedPreferences() {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        val mealsJson = Gson().toJson(Meals)
        editor.putString("Meals", mealsJson)

        editor.apply()
    }

}