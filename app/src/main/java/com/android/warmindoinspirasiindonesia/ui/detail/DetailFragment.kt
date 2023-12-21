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

        val table = binding.nomerMeja
        val note = binding.status

        // Retrieve arguments from the bundle
        val nomerMeja = arguments?.getString(EXTRA_TRANSAKSI_TABLE)
        val status = arguments?.getString(EXTRA_TRANSAKSI_NOTE)

        table.text = nomerMeja
        note.text = status

        // Update to use data from DetailBak
        val detailsFromBak = DetailBak.getDetail()
        viewModel.setDetails(detailsFromBak)

        viewModel.getDetails().observe(viewLifecycleOwner, Observer { detailList ->
            val detail = findDetailByNomerMejaAndStatus(detailList, nomerMeja, status)
            detail?.let {
                binding.shift.text = it.shift
                binding.time.text = it.time
                binding.nomerPesanan2.text = it.nomerPesanan
                binding.menu1.text = it.menu2
                binding.menu3.text = it.menu4
                binding.total.text = it.total
                binding.pembayaran.text = it.pembayaran
            }
        })
        return binding.root
    }

    private fun findDetailByNomerMejaAndStatus(
        details: List<Detail>,
        nomerMeja: String?,
        status: String?
    ): Detail? {
        return details.find { it.nomerMeja == nomerMeja && it.status == status }
    }

    companion object {
        const val EXTRA_TRANSAKSI_TABLE = "extra_transaksi_table"
        const val EXTRA_TRANSAKSI_NOTE = "extra_transaksi_note"
    }
}
