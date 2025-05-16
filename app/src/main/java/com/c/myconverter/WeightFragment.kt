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

class WeightFragment : Fragment() {

    private lateinit var inputEditText: EditText
    private lateinit var outputTextView: TextView
    private lateinit var fromSpinner: Spinner
    private lateinit var toSpinner: Spinner
    private lateinit var convertButton: Button

    private val weightUnits = arrayOf("Kilogram", "Gram", "Milligram", "Pound", "Ounce", "Ton")

    // Faktor konversi ke gram
    private val conversionFactors = mapOf(
        "Kilogram" to 1000.0,
        "Gram" to 1.0,
        "Milligram" to 0.001,
        "Pound" to 453.592,
        "Ounce" to 28.3495,
        "Ton" to 1000000.0
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weight, container, false)

        inputEditText = view.findViewById(R.id.input_edit_text)
        outputTextView = view.findViewById(R.id.output_text_view)
        fromSpinner = view.findViewById(R.id.from_spinner)
        toSpinner = view.findViewById(R.id.to_spinner)
        convertButton = view.findViewById(R.id.convert_button)

        // Adapter untuk spinner
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, weightUnits)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        fromSpinner.adapter = adapter
        toSpinner.adapter = adapter

        convertButton.setOnClickListener {
            convertWeight()
        }

        return view
    }

    private fun convertWeight() {
        try {
            val input = inputEditText.text.toString().toDouble()
            val fromUnit = fromSpinner.selectedItem.toString()
            val toUnit = toSpinner.selectedItem.toString()

            // Konversi ke gram terlebih dahulu
            val grams = input * (conversionFactors[fromUnit] ?: 1.0)

            // Konversi dari gram ke unit tujuan
            val result = grams / (conversionFactors[toUnit] ?: 1.0)

            val df = DecimalFormat("#.####")
            outputTextView.text = "${df.format(result)} $toUnit"
        } catch (e: Exception) {
            outputTextView.text = "Masukkan nilai yang valid"
        }
    }
}