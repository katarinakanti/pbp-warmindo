package com.android.warmindoinspirasiindonesia.ui.transaksi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.warmindoinspirasiindonesia.R
import com.android.warmindoinspirasiindonesia.adapter.ShiftAdapter
import com.android.warmindoinspirasiindonesia.adapter.TransaksiAdapter
import com.android.warmindoinspirasiindonesia.databinding.FragmentTransaksiBinding
import com.android.warmindoinspirasiindonesia.ui.home.HomeViewModel
import com.android.warmindoinspirasiindonesia.ui.home.Shift

class TransaksiFragment: Fragment() {
    private lateinit var binding: FragmentTransaksiBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var transaksiAdapter: TransaksiAdapter
    private lateinit var viewModel: TransaksiViewModel

    private var selectedTransaksi: Transaksi? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTransaksiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = view.findViewById<TextView>(R.id.title)
        val time= view.findViewById<TextView>(R.id.time)
        val button3 = view.findViewById<Button>(R.id.button3)

        title.text = getString(R.string.title)
        time.text = getString(R.string.time)
        button3.text = getString(R.string.tambah)

        title.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        time.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

        button3.setBackgroundResource(R.drawable.button_background)

        viewModel = ViewModelProvider(this)[TransaksiViewModel::class.java]

        val transaksiAdapter = TransaksiAdapter(requireContext(), viewModel.getTransaksis()) { selectedTransaksi ->
            // Handle the selected shift item click
            Toast.makeText(requireContext(), "Shift selected: ${selectedTransaksi.getNomerMeja()}", Toast.LENGTH_SHORT).show()

            // Update the selected shift
            this.selectedTransaksi = selectedTransaksi
        }

        binding.rvShift.layoutManager = LinearLayoutManager(requireContext())
        binding.rvShift.adapter = transaksiAdapter
    }
}
