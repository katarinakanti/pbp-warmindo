package com.android.warmindoinspirasiindonesia.ui.home

class ShiftBak {
    companion object{
        private val shiftTitles = arrayOf(
            "Shift 1",
            "Shift 2"
        )
        private val shiftTime= arrayOf(
            "11.00-17.00",
            "17.00-23.00"
        )
        fun getShift(): ArrayList<Shift> {
            val shifts = ArrayList<Shift>()
            for (i in shiftTitles.indices) {
                val shift = Shift()
                shift.setTitle(shiftTitles[i])
                shift.setTime(shiftTime[i])
                shifts.add(shift)
            }
            return shifts
        }
    }
}