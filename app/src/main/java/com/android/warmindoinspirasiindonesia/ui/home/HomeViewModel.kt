package com.android.warmindoinspirasiindonesia.ui.home

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    fun getShifts(): ArrayList<Shift> {
        return ShiftBak.getShift()
    }
}
