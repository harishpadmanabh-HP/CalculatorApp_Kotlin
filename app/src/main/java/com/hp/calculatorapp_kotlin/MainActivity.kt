package com.hp.calculatorapp_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
//use this import for ignoring findviewby id
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var operand1 : Double ?= null
    private var operand2  : Double = 0.0
    private var pendingOperation = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      //clearing texts
        result.setText("")
        newNumber.setText("")
        displayOperation.setText("")


        //using one click listener for all button
        val listener = View.OnClickListener { view ->
            val b = view as Button
            newNumber.append(b.text)

        }

        //setting listener for the buttons
        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDot.setOnClickListener(listener)

        //listener for operators
        val opListener = View.OnClickListener { view ->
            val op = (view as Button) .text.toString()  //getting text of clicked button
            val value = newNumber.text.toString()

            if(value.isNotEmpty())
                performOperation(value,op)

            pendingOperation=op
            displayOperation.text = pendingOperation
        }

        //setting click listeners for operators buttons
        buttoneql.setOnClickListener(opListener)
        buttonadd.setOnClickListener(opListener)
        buttonsub.setOnClickListener(opListener)
        buttonmul.setOnClickListener(opListener)
        buttondiv.setOnClickListener(opListener)



    }

    private fun performOperation(value: String, op: String) {
        if(operand1 == null)
        {
            operand1 = value.toDouble()
        }else
        {
            operand2 = value.toDouble()
            if(pendingOperation == "=")
            {
                pendingOperation = op
            }
            when(pendingOperation){
                "=" -> operand1=operand2
                "/" -> if(operand2 == 0.0)
                          {
                               operand1= Double.NaN//not a number
                          }else{
                                operand1 = operand1!! / operand2
                          }
                "*" -> operand1 = operand1!! * operand2
                "+" -> operand1 = operand1!! + operand2
                "-" -> operand1 = operand1!! - operand2

            }
        }

        result.setText(operand1.toString())
        newNumber.setText("")
    }
}
