package com.example.mobappdev_td1

import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat


class SettingsActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Filling current values

        val CurrentValue: TextView = findViewById(R.id.CurrentValue)
        CurrentValue.setText("Curent values:   1€=" + MainActivity.dollar.toString() + "dollar  1€="+MainActivity.yen.toString()+ "yen  1€="+MainActivity.peso.toString()+"peso")

        //Filling spinner
        val spinner: Spinner = findViewById(R.id.spinner4)
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

        val button: Button = findViewById(R.id.change_currency)
        button.setOnClickListener {
            Change_currency()
        }

    }

    fun Change_currency() {
        val tv2: EditText = findViewById(R.id.Currency)
        val value = tv2.text.toString()

        val spinner1: Spinner = findViewById(R.id.spinner4)
        val source: String = spinner1.getSelectedItem().toString()

        val number = tv2.text.toString().toDoubleOrNull()
        val isDouble = number != null


        if (isDouble) {

            if (source == "Yen") {
                MainActivity.yen = value.toDouble()
            }
            if (source == "Dollar") {
                MainActivity.dollar = value.toDouble()
            }
            if (source == "Peso") {
                MainActivity.peso = value.toDouble()
            }

            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(
                applicationContext,
                "Currency changed with success",
                duration
            )
            toast.show()

            val CurrentValue: TextView = findViewById(R.id.CurrentValue)
            CurrentValue.setText("Curent values:   1€=" + MainActivity.dollar.toString() + "dollar  1€="+MainActivity.yen.toString()+ "yen  1€="+MainActivity.peso.toString()+"peso")


        } else {
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(
                applicationContext,
                "Error: please enter numerical value.",
                duration
            )
            toast.show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home ->         // Respond to the action bar's Up/Home button
                return false
        }
        return super.onOptionsItemSelected(item)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

        }
    }
}