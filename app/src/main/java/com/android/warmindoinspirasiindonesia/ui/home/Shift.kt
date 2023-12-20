package com.android.warmindoinspirasiindonesia.ui.home

class Shift {
    private var id: Long = -1
    private var title:String? = null
    private var time:String? = null

    fun getId(): Long {
        return id
    }

    fun getTitle():String? {
        return title
    }

    fun setTitle(title:String?) {
        this.title = title
    }

    fun getTime():String? {
        return time
    }

    fun setTime(time:String?) {
        this.time = time
    }
}