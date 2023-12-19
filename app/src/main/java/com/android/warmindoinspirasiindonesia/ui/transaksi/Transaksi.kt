package com.android.warmindoinspirasiindonesia.ui.transaksi

class Transaksi {
    private var meja: String? = null
    private var jam: String? = null
    private var status: String? = null

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

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }
}