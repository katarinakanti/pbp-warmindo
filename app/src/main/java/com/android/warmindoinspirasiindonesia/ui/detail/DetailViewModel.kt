package com.android.warmindoinspirasiindonesia.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel : ViewModel() {

    private val _detail = MutableLiveData<Detail>()
    val detail: LiveData<Detail> = _detail

    fun setDetail(detail: Detail) {
        _detail.value = detail
    }
}