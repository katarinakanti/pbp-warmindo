package com.android.warmindoinspirasiindonesia.ui.transaksi

class Transaksi {
    var meja: String? = null
    var jam: String? = null
    var status: String? = null

    fun getNomerMeja(): String? {
        return meja
    }

    fun setNomerMeja(meja: String?) {
        this.meja = meja
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