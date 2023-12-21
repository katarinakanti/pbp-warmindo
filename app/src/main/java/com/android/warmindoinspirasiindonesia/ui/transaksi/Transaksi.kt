package com.android.warmindoinspirasiindonesia.ui.transaksi

class Transaksi {
    var nomerMeja: String? = null
    var jam: String? = null
    var status: String? = null

    fun getMeja(): String? {
        return nomerMeja
    }

    fun setMeja(nomerMeja: String?) {
        this.nomerMeja = nomerMeja
    }

    fun getWaktu(): String? {
        return jam
    }

    fun setWaktu(jam: String?) {
        this.jam = jam
    }

    fun getStatusBayar(): String? {
        return status
    }

    fun setStatusBayar(status: String?) {
        this.status = status
    }
}