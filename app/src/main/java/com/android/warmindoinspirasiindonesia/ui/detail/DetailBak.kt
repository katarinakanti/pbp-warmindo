package com.android.warmindoinspirasiindonesia.ui.detail

class DetailBak {
    companion object{
        private val shift = arrayOf(
            "Shift 1",
            "Shift 2"
        )
        private val time = arrayOf(
            "11.00-17.00",
            "17.00-23.00"
        )
        private val nomerPesanan = arrayOf(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10"
        )
        private val nomerMeja = arrayOf(
            "Meja 1",
            "Meja 2",
            "Meja 3",
            "Meja 4",
            "Meja 5",
        )
        private val total = arrayOf(
            "18.000",
            "22.000",
            "25.000",
            "35.000",
            "45.000",
            "50.000",
            "18.000",
            "21.000",
            "20.000",
            "12.000"
        )
        private val pembayaran = arrayOf(
            "Cash",
            "Kartu Kredit",
            "Debit",
            "E-Wallet"
        )
        private val status = arrayOf(
            "Baru",
            "Diproses",
            "Disajikan",
            "Selesai",
            "Selesai"
        )
        private val menu2 = arrayOf(
            "15.000",
            "8.000",
            "5.000",
            "3.000",
            "15.000",
            "10.000",
            "7.000",
            "6.000",
            "8.000",
            "9.000"
        )
        private val menu4 = arrayOf(
            "1.000",
            "8.000",
            "5.000",
            "2.000",
            "3.000",
            "5.000",
            "7.000",
            "6.000",
            "8.000",
            "7.000"
        )
        fun getDetail(): ArrayList<Detail> {
            val details = ArrayList<Detail>()
            for (i in shift.indices) {
                val detail = Detail()
                detail.setTitle(shift[i])
                detail.setJam(time[i])
                detail.setNomerPesan(nomerPesanan[i])
                detail.setMeja(nomerMeja[i])
                detail.setTotalBiaya(total[i])
                detail.setMetode(pembayaran[i])
                detail.setStatusBayar(status[i])
                detail.setMenu(menu2[i])
                detail.setMenuLain(menu4[i])
                details.add(detail)
            }
            return details
        }
    }
}