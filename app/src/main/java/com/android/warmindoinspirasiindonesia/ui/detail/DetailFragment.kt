package com.android.warmindoinspirasiindonesia.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.warmindoinspirasiindonesia.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        viewModel.detail.observe(viewLifecycleOwner, Observer { detail ->
            binding.shift.text = detail.shift
            binding.time.text = detail.time
            binding.nomerPesanan2.text = detail.nomerPesanan
            binding.nomerMeja2.text = detail.nomerMeja
            binding.menu1.text = detail.menu2
            binding.menu3.text = detail.menu4
            binding.total.text = detail.total
            binding.pembayaran.text = detail.pembayaran
            binding.status.text = detail.status
        })

        return binding.root
    }
}