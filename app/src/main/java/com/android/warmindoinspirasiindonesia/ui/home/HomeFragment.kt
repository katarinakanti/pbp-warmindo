package com.android.warmindoinspirasiindonesia.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.warmindoinspirasiindonesia.R
import com.android.warmindoinspirasiindonesia.adapter.ShiftAdapter
import com.android.warmindoinspirasiindonesia.databinding.FragmentHomeBinding
import com.android.warmindoinspirasiindonesia.ui.transaksi.TransaksiFragment

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

        val masukButton: Button = view.findViewById(R.id.button2)


        val shiftAdapter = ShiftAdapter(requireContext(), viewModel.getShifts()) { selectedShift ->
            // Handle the selected shift item click
            Toast.makeText(
                requireContext(),
                "Shift selected: ${selectedShift.getTitle()}",
                Toast.LENGTH_SHORT
            ).show()

            // Update the selected shift
            this.selectedShift = selectedShift

            // Enable or disable the MASUK button based on whether a shift is selected
            updateMasukButtonState(masukButton)
        }

        binding.rvShift.layoutManager = LinearLayoutManager(requireContext())
        binding.rvShift.adapter = shiftAdapter

        masukButton.setOnClickListener {

            val mBundle = Bundle()
            //     send mbundle with selectedShift
            mBundle.apply {
                putString(TransaksiFragment.EXTRA_SHIFT_TITLE, selectedShift?.getTitle())
                putString(TransaksiFragment.EXTRA_SHIFT_HOUR, selectedShift?.getTime())
            }
            findNavController().navigate(R.id.action_nav_home_to_nav_transaksi, mBundle)
        }


        // Initially, the MASUK button is disabled
        updateMasukButtonState(masukButton)
    }

    private fun updateMasukButtonState(masukButton: Button) {
        masukButton.isEnabled = selectedShift != null
    }

    fun getSelectedShift(): Shift? {
        return selectedShift
    }

}