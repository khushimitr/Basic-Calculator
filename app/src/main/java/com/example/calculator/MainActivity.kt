package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
//        Toast.makeText(this,"Button Works",Toast.LENGTH_SHORT).show()
        input.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View) {
        input.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onOperator(view: View) {
        if (lastNumeric && !isOPeratorAdded(input.text.toString())) {
            input.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    private fun isOPeratorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("-") || value.contains("+")
        }
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            input.append(".")
            lastNumeric = false
            lastDot = true;
        } else {
            Toast.makeText(this, "Invalid use of Decimal Point", Toast.LENGTH_SHORT).show()
        }
    }

    fun onEqual(view: View) {
        if (lastNumeric) {
            var tvValue = input.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                // -33 - 5

                if (tvValue.contains("-")) {
                    //99 - 1
                    val splitValue = tvValue.split("-")
                    var op1_l = splitValue[0] //99
                    var op2_r = splitValue[1] //1

                    if (!prefix.isEmpty()) {
                        op1_l = prefix + op1_l
                    }
                    input.text = removeExtraZeroes((op1_l.toDouble() - op2_r.toDouble()).toString())
                } else if (tvValue.contains("+")) {
                    //99 - 1
                    val splitValue = tvValue.split("+")
                    var op1_l = splitValue[0] //99
                    var op2_r = splitValue[1] //1

                    if (!prefix.isEmpty()) {
                        op1_l = prefix + op1_l
                    }
                    input.text = removeExtraZeroes((op1_l.toDouble() + op2_r.toDouble()).toString())
                } else if (tvValue.contains("*")) {
                    //99 - 1
                    val splitValue = tvValue.split("*")
                    var op1_l = splitValue[0] //99
                    var op2_r = splitValue[1] //1

                    if (!prefix.isEmpty()) {
                        op1_l = prefix + op1_l
                    }
                    input.text = removeExtraZeroes((op1_l.toDouble() * op2_r.toDouble()).toString())
                } else if (tvValue.contains("/")) {
                    //99 - 1
                    val splitValue = tvValue.split("/")
                    var op1_l = splitValue[0] //99
                    var op2_r = splitValue[1] //1

                    if (!prefix.isEmpty()) {
                        op1_l = prefix + op1_l
                    }
                    if (op2_r != "0") {
                        input.text = removeExtraZeroes((op1_l.toDouble() / op2_r.toDouble()).toString())
                    }
                    else
                    {
                        input.text = "infinity"
                    }
                }

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun removeExtraZeroes(result : String) : String{
        var value = result
        if(result.contains("-0"))
        {
            value = result.substring(1)
        }
        if(result.contains(".0"))
        {
            value = result.substring(0,result.length - 2)
        }

        return value
    }

}
