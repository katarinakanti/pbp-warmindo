package com.android.warmindoinspirasiindonesia.ui.transaksi

class Transaksi {
    private var title: String? = null
    private var time: String? = null
    var meja: String? = null
    var jam: String? = null
    var status: String? = null
    var status2: String? = null

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getTime(): String? {
        return time
    }

    fun setTime(time: String?) {
        this.time = time
    }

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

    fun getStatusBayar2(): String? {
        return status2
    }

    fun setStatusBayar2(status2: String?) {
        this.status2 = status2
    }
}