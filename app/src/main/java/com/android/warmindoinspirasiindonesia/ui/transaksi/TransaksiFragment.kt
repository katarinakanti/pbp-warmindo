package com.android.warmindoinspirasiindonesia.ui.transaksi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.warmindoinspirasiindonesia.R
import com.android.warmindoinspirasiindonesia.TransaksiActivity.Companion.EXTRA_SELECTED_SHIFT
import com.android.warmindoinspirasiindonesia.adapter.TransaksiAdapter
import com.android.warmindoinspirasiindonesia.databinding.FragmentTransaksiBinding

class TransaksiFragment : Fragment() {
    private lateinit var binding: FragmentTransaksiBinding
    private lateinit var viewModel: TransaksiViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransaksiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shift = binding.textView4
        val hour = binding.textView6

        // Inisialisasi ViewModel
        viewModel = ViewModelProvider(this)[TransaksiViewModel::class.java]

        // Sample list of Transaksi items (replace this with your actual data)
        val sampleTransaksiList = viewModel.getTransaksis()

        // Set up RecyclerView with the TransaksiAdapter
        val recyclerView: RecyclerView = view.findViewById(R.id.rv_transaksi)
        val adapter = TransaksiAdapter(requireContext(), sampleTransaksiList)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        if(arguments != null) {
            shift.text = arguments?.getString(EXTRA_SHIFT_TITLE)
            hour.text = arguments?.getString(EXTRA_SHIFT_HOUR)
        }

        binding.button3.setOnClickListener {
            findNavController().navigate(R.id.action_nav_transaksi_to_nav_detail)
        }

    }

    companion object {
        const val EXTRA_SHIFT_TITLE = "extra_shift_title"
        const val EXTRA_SHIFT_HOUR = "extra_shift_hour"
    }
}
