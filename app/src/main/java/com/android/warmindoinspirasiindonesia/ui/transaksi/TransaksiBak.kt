package com.android.warmindoinspirasiindonesia.ui.transaksi

class TransaksiBak {
    companion object{
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
            "Baru",
            "Diproses",
            "Disajikamn",
            "Selesai",
            "Selesai"
        )
        fun getTransaksi(): ArrayList<Transaksi> {
            val transaksis = ArrayList<Transaksi>()
            for (i in meja.indices) {
                val transaksi = Transaksi()
                transaksi.setNomerMeja(meja[i])
                transaksi.setWaktu(jam[i])
                transaksi.setStatus(status[i])
                transaksis.add(transaksi)
            }
            return transaksis
        }
    }
}