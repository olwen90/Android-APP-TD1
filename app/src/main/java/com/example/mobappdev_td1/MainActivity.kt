package com.example.mobappdev_td1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    companion object{
        var dollar = 1.21
        var peso = 23.92
        var yen = 125.57
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onStart(){

        super.onStart()
        val text = "Hello toast!"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()

        val ConvertButton = findViewById<Button>(R.id.button)
        val ConvertedValue = findViewById<EditText>(R.id.editTextNumber)

        val spinner: Spinner = findViewById(R.id.moneySpinner)
        val spinner2: Spinner = findViewById(R.id.Spinner2)

        ArrayAdapter.createFromResource(
            this,
            R.array.moneys,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }


        ArrayAdapter.createFromResource(
            this,
            R.array.moneys,
            android.R.layout.simple_spinner_item
        ).also { adapter2 ->
            // Specify the layout to use when the list of choices appears
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner2.adapter = adapter2
        }

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            Convert()
        }
    }

    fun Convert(){
        val tv1: TextView = findViewById(R.id.TextResult)
        val tv2: EditText = findViewById(R.id.editTextNumber)

        val number = tv2.text.toString().toDoubleOrNull()
        val isDouble = number != null

        val spinner1: Spinner = findViewById(R.id.moneySpinner)
        val source: String = spinner1.getSelectedItem().toString()

        val spinner2: Spinner = findViewById(R.id.Spinner2)
        val destination: String = spinner2.getSelectedItem().toString()

        var euro=0.0

        if(isDouble) {

            if (source==destination){
                tv1.text =tv2.text.toString() + source
            }
            else {
                //Converting to euro
                if (source == "Peso") {
                    euro = tv2.text.toString().toDouble() * (1/MainActivity.peso)
                } else if (source == "Yen") {
                    euro = tv2.text.toString().toDouble() * (1/MainActivity.yen)
                } else if (source == "Dollar") {
                    euro = tv2.text.toString().toDouble() * (1/MainActivity.dollar)
                }

                //Converting to destination money
                if (destination == "Peso") {
                    tv1.text = (euro * MainActivity.peso).toString() + " Peso"
                } else if (destination == "Yen") {
                    tv1.text = (euro * MainActivity.yen).toString() + " ¥"
                } else if (destination == "Dollar") {
                    tv1.text = (euro * MainActivity.dollar).toString() + " $"
                }
            }
        }
        else{
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(
                applicationContext,
                "Error: please enter numerical value.",
                duration
            )
            toast.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    //olivier.cros@protonmail.com

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.action_settings -> {
                val myIntent = Intent(this, SettingsActivity::class.java)
                startActivity(myIntent)

                true
            }
            R.id.logs -> {

                true
            }
            R.id.about_us -> {

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    public fun toastTest(){
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, "This is a test.", duration)
        toast.show()
        return
    }
}