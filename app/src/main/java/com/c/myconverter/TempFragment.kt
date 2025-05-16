package com.c.myconverter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import java.text.DecimalFormat


class TempFragment : Fragment() {
    private lateinit var inputEditText: EditText
    private lateinit var outputTextView: TextView
    private lateinit var fromSpinner: Spinner
    private lateinit var toSpinner: Spinner
    private lateinit var convertButton: Button

    private val tempUnits = arrayOf("Celsius", "Fahrenheit", "Kelvin")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_temp, container, false)

        inputEditText = view.findViewById(R.id.input_edit_text)
        outputTextView = view.findViewById(R.id.output_text_view)
        fromSpinner = view.findViewById(R.id.from_spinner)
        toSpinner = view.findViewById(R.id.to_spinner)
        convertButton = view.findViewById(R.id.convert_button)

        // Adapter untuk spinner
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, tempUnits)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        fromSpinner.adapter = adapter
        toSpinner.adapter = adapter

        convertButton.setOnClickListener {
            convertTemperature()
        }

        return view
    }

    private fun convertTemperature() {
        try {
            val input = inputEditText.text.toString().toDouble()
            val fromUnit = fromSpinner.selectedItem.toString()
            val toUnit = toSpinner.selectedItem.toString()
            val result = when {
                fromUnit == toUnit -> input
                fromUnit == "Celsius" && toUnit == "Fahrenheit" -> (input * 9/5) + 32
                fromUnit == "Celsius" && toUnit == "Kelvin" -> input + 273.15
                fromUnit == "Fahrenheit" && toUnit == "Celsius" -> (input - 32) * 5/9
                fromUnit == "Fahrenheit" && toUnit == "Kelvin" -> (input - 32) * 5/9 + 273.15
                fromUnit == "Kelvin" && toUnit == "Celsius" -> input - 273.15
                fromUnit == "Kelvin" && toUnit == "Fahrenheit" -> (input - 273.15) * 9/5 + 32
                else -> 0.0
            }

            val df = DecimalFormat("#.##")
            outputTextView.text = "${df.format(result)} $toUnit"
        } catch (e: Exception) {
            outputTextView.text = "Masukkan nilai yang valid"
        }
    }
}