package com.android.warmindoinspirasiindonesia.ui.transaksi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.warmindoinspirasiindonesia.ui.home.Shift
import com.android.warmindoinspirasiindonesia.ui.home.ShiftBak

class TransaksiViewModel : ViewModel() {
    fun getTransaksis(): ArrayList<Transaksi> {
        return TransaksiBak.getTransaksi()
    }
}