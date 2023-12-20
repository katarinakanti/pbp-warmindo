package com.android.warmindoinspirasiindonesia.ui.transaksi

class TransaksiBak {
    companion object{
        private val title = arrayOf(
            "Shift 1",
            "Shift 2"
        )
        private val time = arrayOf(
            "11.00-17.00",
            "17.00-23.00"
        )
        private val meja = arrayOf(
            "Meja 1",
            "Meja 2",
            "Meja 3",
            "Meja 4",
            "Meja 5",
        )
        private val jam = arrayOf(
            "16.25.p.m.",
            "16.20.p.m.",
            "12.15.p.m.",
            "11.15.p.m.",
            "11.00.p.m."
        )
        private val status = arrayOf(
            "Belum Dibayar",
            "Sedang Dimasak",
            "Sudah Dibayar",
            "Sudah Dibayar",
            "Sudah Dibayar"
        )
        fun getTransaksi(): ArrayList<Transaksi> {
            val transaksis = ArrayList<Transaksi>()
            for (i in title.indices) {
                val transaksi = Transaksi()
                transaksi.setTitle(title[i])
                transaksi.setTime(time[i])
                transaksi.setNomerMeja(meja[i])
                transaksi.setStatusBayar(status[i])
                transaksis.add(transaksi)
            }
            return transaksis
        }
    }
}