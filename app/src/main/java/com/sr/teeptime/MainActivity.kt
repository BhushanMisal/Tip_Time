package com.sr.teeptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sr.teeptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    //creating binding veriable to acces all resouces without "findResourceById " method

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initializing binding object whis is use to access views
        binding = ActivityMainBinding.inflate(layoutInflater)

        //specify the root of the hirarchy of views of the APP
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }


    }

    private fun calculateTip() {
        //geting input text and converting it in to the double
        val cost = binding.costOfService.text.toString().toDoubleOrNull()
        //checking the input string is not empty if it is empty it will return to on create fuction
        if (cost == null) {
            binding.tipResult.text = ""
            return
        }
        //calculate the tip percentage

        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost

        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        displayTip(tip)


    }

    //function for display a tip amount
    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

}