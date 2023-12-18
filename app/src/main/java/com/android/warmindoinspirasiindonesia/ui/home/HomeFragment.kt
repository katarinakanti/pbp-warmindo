package com.android.warmindoinspirasiindonesia.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.warmindoinspirasiindonesia.R
import com.android.warmindoinspirasiindonesia.adapter.ShiftAdapter
import com.android.warmindoinspirasiindonesia.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    private var selectedShift: Shift? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        val shiftAdapter = ShiftAdapter(requireContext(), viewModel.getShifts()) { selectedShift ->
            // Handle the selected shift item click
            Toast.makeText(requireContext(), "Shift selected: ${selectedShift.getTitle()}", Toast.LENGTH_SHORT).show()

            // Update the selected shift
            this.selectedShift = selectedShift

            // Enable or disable the MASUK button based on whether a shift is selected
            updateMasukButtonState()
        }

        binding.rvShift.layoutManager = LinearLayoutManager(requireContext())
        binding.rvShift.adapter = shiftAdapter

        val masukButton: Button = view.findViewById(R.id.button2)
        masukButton.setOnClickListener {
            // Check if a shift is selected before performing MASUK button actions
            if (selectedShift != null) {
                // Perform MASUK button actions
                Toast.makeText(requireContext(), "MASUK button clicked", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Please select a shift first", Toast.LENGTH_SHORT).show()
            }
        }

        // Initially, the MASUK button is disabled
        updateMasukButtonState()
    }

    private fun updateMasukButtonState() {
        val masukButton: Button = requireView().findViewById(R.id.button2)
//        masukButton.setBackgroundResource(if (selectedShift != null) R.drawable.button_background else R.drawable.button_background2)
        masukButton.setBackgroundColor(Color.RED);
    }
}