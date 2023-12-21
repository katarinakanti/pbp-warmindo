package com.android.warmindoinspirasiindonesia.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.warmindoinspirasiindonesia.ui.home.Shift
import com.android.warmindoinspirasiindonesia.ui.home.ShiftBak

class DetailViewModel : ViewModel() {
    private val details = MutableLiveData<List<Detail>>() // Change to List<Detail>

    fun setDetails(detailList: List<Detail>) {
        details.value = detailList
    }

    fun getDetails(): LiveData<List<Detail>> {
        return details
    }
}