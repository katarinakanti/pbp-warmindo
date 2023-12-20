package com.android.warmindoinspirasiindonesia.ui.detail

class Detail {

    var id: String? = null
    var shift: String? = null
    var time: String? = null
    var nomerPesanan: String? = null
    var nomerMeja: String? = null
    var total: String? = null
    var pembayaran: String? = null
    var status: String? = null
    var menu2: String? = null
    var menu4: String? = null

    fun getTitle(): String? {
        return shift
    }

    fun setTitle(shift: String?) {
        this.shift = shift
    }

    fun getJam(): String? {
        return time
    }

    fun setJam(time: String?) {
        this.time = time
    }

    fun getNomerPesan(): String? {
        return nomerPesanan
    }

    fun setNomerPesan(nomerPesanan: String?) {
        this.nomerPesanan = nomerPesanan
    }

    fun getMeja(): String? {
        return nomerMeja
    }

    fun setMeja(nomerMeja: String?) {
        this.nomerMeja = nomerMeja
    }

    fun getTotalBiaya(): String? {
        return total
    }

    fun setTotalBiaya(total: String?) {
        this.total = total
    }

    fun getMetode(): String? {
        return pembayaran
    }

    fun setMetode(pembayaran: String?) {
        this.pembayaran = pembayaran
    }

    fun getStatusBayar(): String? {
        return status
    }

    fun setStatusBayar(status: String?) {
        this.status = status
    }

    fun getMenu(): String? {
        return menu2
    }

    fun setMenu(menu2: String?) {
        this.menu2 = menu2
    }

    fun getMenuLain(): String? {
        return menu4
    }

    fun setMenuLain(menu4: String?) {
        this.menu4 = menu4
    }
}