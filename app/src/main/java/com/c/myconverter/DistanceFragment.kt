package com.c.myconverter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.ArrayAdapter
import java.text.DecimalFormat

class DistanceFragment : Fragment() {

    private lateinit var inputEditText: EditText
    private lateinit var outputTextView: TextView
    private lateinit var fromSpinner: Spinner
    private lateinit var toSpinner: Spinner
    private lateinit var convertButton: Button

    private val distanceUnits = arrayOf("Kilometer", "Meter", "Centimeter", "Mile", "Yard", "Foot", "Inch")

    // Faktor konversi ke meter
    private val conversionFactors = mapOf(
        "Kilometer" to 1000.0,
        "Meter" to 1.0,
        "Centimeter" to 0.01,
        "Mile" to 1609.34,
        "Yard" to 0.9144,
        "Foot" to 0.3048,
        "Inch" to 0.0254
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_distance, container, false)

        inputEditText = view.findViewById(R.id.input_edit_text)
        outputTextView = view.findViewById(R.id.output_text_view)
        fromSpinner = view.findViewById(R.id.from_spinner)
        toSpinner = view.findViewById(R.id.to_spinner)
        convertButton = view.findViewById(R.id.convert_button)

        // Adapter untuk spinner
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, distanceUnits)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        fromSpinner.adapter = adapter
        toSpinner.adapter = adapter

        convertButton.setOnClickListener {
            convertDistance()
        }

        return view
    }

    private fun convertDistance() {
        try {
            val input = inputEditText.text.toString().toDouble()
            val fromUnit = fromSpinner.selectedItem.toString()
            val toUnit = toSpinner.selectedItem.toString()

            // Konversi ke meter terlebih dahulu
            val meters = input * (conversionFactors[fromUnit] ?: 1.0)

            // Konversi dari meter ke unit tujuan
            val result = meters / (conversionFactors[toUnit] ?: 1.0)

            val df = DecimalFormat("#.####")
            outputTextView.text = "${df.format(result)} $toUnit"
        } catch (e: Exception) {
            outputTextView.text = "Masukkan nilai yang valid"
        }
    }
}