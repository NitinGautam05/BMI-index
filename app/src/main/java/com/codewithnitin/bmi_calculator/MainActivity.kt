package com.codewithnitin.bmi_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weigthText = findViewById<EditText>(R.id.etWeight)
        val heigthText = findViewById<EditText>(R.id.etHeight)
        val calcButton = findViewById<Button>(R.id.btnCalculate)

        calcButton.setOnClickListener {
            val weigth = weigthText.text.toString()
            val heigth = heigthText.text.toString()

            if(validateInput(weigth, heigth)){
               val bmi = weigth.toFloat()/((heigth.toFloat()/100)*(heigth.toFloat()/100))
               val bmi2Digits = String.format("%.2f", bmi).toFloat()
               displayResult(bmi2Digits)
            }
        }
    }


    private  fun validateInput(weigth:String?, heigth:String?):Boolean{

        return when{
            weigth.isNullOrEmpty() -> {
                Toast.makeText(this, "Weigth is empty", Toast.LENGTH_LONG).show()
                return false
            }
            heigth.isNullOrEmpty() -> {
                Toast.makeText(this, "Heigth is empty", Toast.LENGTH_LONG).show()
                return false
            }
            else ->{
                return true
            }
        }
    }

    private fun displayResult(bmi:Float){
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDescription = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvInfo)

        resultIndex.text = bmi.toString()
        info.text = "(normal range is 18.5 - 24.9)"

        var resultText = ""
        var color = 0

        when{
            bmi <18.50 -> {
                resultText = "Underweigth"
                color = R.color.under_weigth
            }
            bmi in 18.50..24.99 ->{
                resultText = "Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99 ->{
                resultText = "Overweigth"
                color = R.color.over_weigth
            }
            bmi > 29.99 ->{
                resultText = "Obese"
                color = R.color.obese
            }
        }

        resultDescription.setTextColor(ContextCompat.getColor(this,color))
        resultDescription.text = resultText
    }
}