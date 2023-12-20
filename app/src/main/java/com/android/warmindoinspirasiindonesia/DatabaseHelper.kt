package com.android.warmindoinspirasiindonesia

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.android.warmindoinspirasiindonesia.ui.home.Shift
import com.android.warmindoinspirasiindonesia.ui.home.ShiftBak

class DatabaseHelper(private val context: Context)
    :SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    // Existing user table
    companion object {
        private const val DATABASE_NAME = "warmindo.db"
        private const val DATABASE_VERSION = 4
        private const val TABLE_NAME = "user"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PASSWORD = "password"

        // Meja Table
        private const val TABLE_MEJA = "meja"
        private const val COLUMN_ID_MEJA = "idmeja"
        private const val COLUMN_ID_WARUNG_MEJA = "idwarung"
        private const val COLUMN_KODE_MEJA = "kodemeja"

        // Menu Table
        private const val TABLE_MENU = "menu"
        private const val COLUMN_ID_MENU = "idmenu"
        private const val COLUMN_NAMA_MENU = "namamenu"
        private const val COLUMN_KATEGORI = "kategori"
        private const val COLUMN_HARGA = "harga"
        private const val COLUMN_GAMBAR = "gambar"

        // Transaksi Table
        private const val TABLE_TRANSAKSI = "transaksi"
        private const val COLUMN_ID_TRANSAKSI = "idtransaksi"
        private const val COLUMN_TANGGAL = "tanggal"
        private const val COLUMN_WAKTU = "waktu"
        private const val COLUMN_SHIFT = "shift"
        private const val COLUMN_ID_PENGGUNA = "idpengguna"
        private const val COLUMN_ID_PELANGGAN = "idpelanggan"
        private const val COLUMN_STATUS = "status"
        private const val COLUMN_KODE_MEJA_TRANSAKSI = "kodemeja"
        private const val COLUMN_NAMA_PELANGGAN = "namapelanggan"
        private const val COLUMN_TOTAL = "total"
        private const val COLUMN_METODE_PEMBAYARAN = "metodepembayaran"
        private const val COLUMN_TOTAL_DISKON = "totaldiskon"
        private const val COLUMN_ID_PROMOSI = "idpromosi"

        // DetailTransaksi Table
        private const val TABLE_DETAIL_TRANSAKSI = "detailtransaksi"
        private const val COLUMN_ID_MENU_DETAIL = "idmenu"
        private const val COLUMN_NAMA_MENU_DETAIL = "namamenu"
        private const val COLUMN_JUMLAH = "jumlah"
        private const val COLUMN_SUBTOTAL = "subtotal"
        private const val COLUMN_STATUS_DETAIL = "status"
    }

    object ShiftContract {
        // Table name
        const val TABLE_NAME = "shifts"

        // Columns
        const val COLUMN_ID = "_id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_TIME = "time"
    }

    data class Shift(
        val id: Long,
        val title: String,
        val time: String
    )


    private val tableCreationQueries = listOf(
        "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_USERNAME TEXT, " +
                "$COLUMN_EMAIL TEXT, " +
                "$COLUMN_PASSWORD TEXT)",

        "CREATE TABLE ${ShiftContract.TABLE_NAME} (" +
                "${ShiftContract.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${ShiftContract.COLUMN_TITLE} TEXT, " +
                "${ShiftContract.COLUMN_TIME} TEXT)",

        "CREATE TABLE ${MejaContract.TABLE_NAME} (" +
                "${MejaContract.COLUMN_ID_MEJA} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${MejaContract.COLUMN_ID_WARUNG_MEJA} INTEGER, " +
                "${MejaContract.COLUMN_KODE_MEJA} TEXT)",

        "CREATE TABLE ${MenuContract.TABLE_NAME} (" +
                "${MenuContract.COLUMN_ID_MENU} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${MenuContract.COLUMN_NAMA_MENU} TEXT, " +
                "${MenuContract.COLUMN_KATEGORI} TEXT, " +
                "${MenuContract.COLUMN_HARGA} REAL, " +
                "${MenuContract.COLUMN_GAMBAR} TEXT)",

        "CREATE TABLE ${TransaksiContract.TABLE_NAME} (" +
                "${TransaksiContract.COLUMN_ID_TRANSAKSI} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${TransaksiContract.COLUMN_TANGGAL} TEXT, " +
                "${TransaksiContract.COLUMN_WAKTU} TEXT, " +
                "${TransaksiContract.COLUMN_SHIFT} INTEGER, " +
                "${TransaksiContract.COLUMN_ID_PENGGUNA} INTEGER, " +
                "${TransaksiContract.COLUMN_ID_PELANGGAN} INTEGER, " +
                "${TransaksiContract.COLUMN_STATUS} TEXT, " +
                "${TransaksiContract.COLUMN_KODE_MEJA_TRANSAKSI} TEXT, " +
                "${TransaksiContract.COLUMN_NAMA_PELANGGAN} TEXT, " +
                "${TransaksiContract.COLUMN_TOTAL} REAL, " +
                "${TransaksiContract.COLUMN_METODE_PEMBAYARAN} TEXT, " +
                "${TransaksiContract.COLUMN_TOTAL_DISKON} REAL, " +
                "${TransaksiContract.COLUMN_ID_PROMOSI} INTEGER)",

        "CREATE TABLE ${DetailTransaksiContract.TABLE_NAME} (" +
                "${DetailTransaksiContract.COLUMN_ID_TRANSAKSI} INTEGER, " +
                "${DetailTransaksiContract.COLUMN_ID_MENU_DETAIL} INTEGER, " +
                "${DetailTransaksiContract.COLUMN_NAMA_MENU_DETAIL} TEXT, " +
                "${DetailTransaksiContract.COLUMN_HARGA} REAL, " +
                "${DetailTransaksiContract.COLUMN_JUMLAH} INTEGER, " +
                "${DetailTransaksiContract.COLUMN_SUBTOTAL} REAL, " +
                "${DetailTransaksiContract.COLUMN_STATUS_DETAIL} TEXT, " +
                "FOREIGN KEY(${DetailTransaksiContract.COLUMN_ID_TRANSAKSI}) REFERENCES ${TransaksiContract.TABLE_NAME}(${TransaksiContract.COLUMN_ID_TRANSAKSI}), " +
                "FOREIGN KEY(${DetailTransaksiContract.COLUMN_ID_MENU_DETAIL}) REFERENCES ${MenuContract.TABLE_NAME}(${MenuContract.COLUMN_ID_MENU}), " +
                "PRIMARY KEY(${DetailTransaksiContract.COLUMN_ID_TRANSAKSI}, ${DetailTransaksiContract.COLUMN_ID_MENU_DETAIL}))"
    )


    override fun onCreate(db: SQLiteDatabase?) {
        tableCreationQueries.forEach { query ->
            db?.execSQL(query)
        }

        seedShifts(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        listOf("user", "shifts").forEach {tableName ->
            val dropTableQuery = "DROP TABLE IF EXISTS $tableName"
            db?.execSQL(dropTableQuery)
        }
        onCreate(db)
//        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
//        db?.execSQL(dropTableQuery)
//        onCreate(db)
    }

    fun seedShifts(db: SQLiteDatabase?) {
        val shifts = ShiftBak.getShift()

        for (shift in shifts) {
            val values = ContentValues().apply {
                put(ShiftContract.COLUMN_TITLE, shift.getTitle())
                put(ShiftContract.COLUMN_TIME, shift.getTime())
            }

            db?.insert(ShiftContract.TABLE_NAME, null, values)
        }

    }


    fun insertUser(username: String, email:String, password:String): Long {
        val values = ContentValues().apply{
            put(COLUMN_USERNAME, username)
            put(COLUMN_EMAIL, email)
            put(COLUMN_PASSWORD, password)
        }
        val db = writableDatabase
        return db.insert(TABLE_NAME, null, values)
    }

    fun getAllShifts(): List<Shift> {
        val shiftsList = mutableListOf<Shift>()
        val db = readableDatabase
        val projection = arrayOf(
            ShiftContract.COLUMN_ID,
            ShiftContract.COLUMN_TITLE,
            ShiftContract.COLUMN_TIME
        )

        val cursor = db.query(
            ShiftContract.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(ShiftContract.COLUMN_ID))
                val title = getString(getColumnIndexOrThrow(ShiftContract.COLUMN_TITLE))
                val time = getString(getColumnIndexOrThrow(ShiftContract.COLUMN_TIME))

                val shift = Shift(id, title, time)
                shiftsList.add(shift)
            }
        }

        cursor.close()
        return shiftsList
    }

    object MejaContract {
        // Table name
        const val TABLE_NAME = "MEJA"

        // Columns
        const val COLUMN_ID_MEJA = "ID_MEJA"
        const val COLUMN_ID_WARUNG_MEJA = "ID_WARUNG"
        const val COLUMN_KODE_MEJA = "KODE_MEJA"
    }

    data class Meja(val idMeja: Int, val idWarung: Int, val kodeMeja: String)


    // Methods to interact with the "meja" table
    fun insertMeja(idWarung: Int, kodeMeja: String): Long {
        val values = ContentValues().apply {
            put(MejaContract.COLUMN_ID_WARUNG_MEJA, idWarung)
            put(MejaContract.COLUMN_KODE_MEJA, kodeMeja)
        }
        val db = writableDatabase
        return db.insert(MejaContract.TABLE_NAME, null, values)
    }

    fun getAllMeja(): List<Meja> {
        val mejaList = mutableListOf<Meja>()
        val db = readableDatabase
        val projection = arrayOf(
            MejaContract.COLUMN_ID_MEJA,
            MejaContract.COLUMN_ID_WARUNG_MEJA,
            MejaContract.COLUMN_KODE_MEJA
        )

        val cursor = db.query(
            MejaContract.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor) {
            while (moveToNext()) {
                val idMeja = getInt(getColumnIndexOrThrow(MejaContract.COLUMN_ID_MEJA))
                val idWarung = getInt(getColumnIndexOrThrow(MejaContract.COLUMN_ID_WARUNG_MEJA))
                val kodeMeja = getString(getColumnIndexOrThrow(MejaContract.COLUMN_KODE_MEJA))

                val meja = Meja(idMeja, idWarung, kodeMeja)
                mejaList.add(meja)
            }
        }

        cursor.close()
        return mejaList
    }


    // Update Meja record
    fun updateMeja(idMeja: Int, idWarung: Int, kodeMeja: String): Int {
        val values = ContentValues().apply {
            put(COLUMN_ID_WARUNG_MEJA, idWarung)
            put(COLUMN_KODE_MEJA, kodeMeja)
        }
        val db = writableDatabase
        return db.update(TABLE_MEJA, values, "$COLUMN_ID_MEJA=?", arrayOf(idMeja.toString()))
    }

    // Delete Meja record
    fun deleteMeja(idMeja: Int): Int {
        val db = writableDatabase
        return db.delete(TABLE_MEJA, "$COLUMN_ID_MEJA=?", arrayOf(idMeja.toString()))
    }

    object MenuContract {
        // Table name
        const val TABLE_NAME = "MENU"

        // Columns
        const val COLUMN_ID_MENU = "ID_MENU"
        const val COLUMN_NAMA_MENU = "NAMA_MENU"
        const val COLUMN_KATEGORI = "KATEGORI"
        const val COLUMN_HARGA = "HARGA"
        const val COLUMN_GAMBAR = "GAMBAR"
    }

    data class Menu(val idMenu: Int, val namaMenu: String, val kategori: String, val harga: Double, val gambar: String)

    fun insertMenu(namaMenu: String, kategori: String, harga: Double, gambar: String): Long {
        val values = ContentValues().apply {
            put(COLUMN_NAMA_MENU, namaMenu)
            put(COLUMN_KATEGORI, kategori)
            put(COLUMN_HARGA, harga)
            put(COLUMN_GAMBAR, gambar)
        }
        val db = writableDatabase
        return db.insert(TABLE_MENU, null, values)
    }

    // Retrieve all Menu records
    fun getAllMenu(): List<Menu> {
        val menuList = mutableListOf<Menu>()
        val query = "SELECT * FROM $TABLE_MENU"
        val db = readableDatabase
        val cursor = db.rawQuery(query, null)

        cursor.use {
            while (it.moveToNext()) {
                val idMenuIndex = it.getColumnIndex(COLUMN_ID_MENU)
                val namaMenuIndex = it.getColumnIndex(COLUMN_NAMA_MENU)
                val kategoriIndex = it.getColumnIndex(COLUMN_KATEGORI)
                val hargaIndex = it.getColumnIndex(COLUMN_HARGA)
                val gambarIndex = it.getColumnIndex(COLUMN_GAMBAR)

                // Check if the column index is valid before extracting values
                if (idMenuIndex != -1 && namaMenuIndex != -1 && kategoriIndex != -1 && hargaIndex != -1 && gambarIndex != -1) {
                    val idMenu = it.getInt(idMenuIndex)
                    val namaMenu = it.getString(namaMenuIndex)
                    val kategori = it.getString(kategoriIndex)
                    val harga = it.getDouble(hargaIndex)
                    val gambar = it.getString(gambarIndex)

                    val menu = Menu(idMenu, namaMenu, kategori, harga, gambar)
                    menuList.add(menu)
                } else {
                    // Handle the case where one or more columns are not found
                    // You might want to log an error or handle it in another way
                }
            }
        }

        return menuList
    }


    // Update Menu record
    fun updateMenu(idMenu: Int, namaMenu: String, kategori: String, harga: Double, gambar: String): Int {
        val values = ContentValues().apply {
            put(COLUMN_NAMA_MENU, namaMenu)
            put(COLUMN_KATEGORI, kategori)
            put(COLUMN_HARGA, harga)
            put(COLUMN_GAMBAR, gambar)
        }
        val db = writableDatabase
        return db.update(TABLE_MENU, values, "$COLUMN_ID_MENU=?", arrayOf(idMenu.toString()))
    }

    // Delete Menu record
    fun deleteMenu(idMenu: Int): Int {
        val db = writableDatabase
        return db.delete(TABLE_MENU, "$COLUMN_ID_MENU=?", arrayOf(idMenu.toString()))
    }

    object TransaksiContract {
        // Table name
        const val TABLE_NAME = "TRANSAKSI"

        // Columns
        const val COLUMN_ID_TRANSAKSI = "ID_TRANSAKSI"
        const val COLUMN_TANGGAL = "TANGGAL"
        const val COLUMN_WAKTU = "WAKTU"
        const val COLUMN_SHIFT = "SHIFT"
        const val COLUMN_ID_PENGGUNA = "ID_PENGGUNA"
        const val COLUMN_ID_PELANGGAN = "ID_PELANGGAN"
        const val COLUMN_STATUS = "STATUS"
        const val COLUMN_KODE_MEJA_TRANSAKSI = "KODE_MEJA_TRANSAKSI"
        const val COLUMN_NAMA_PELANGGAN = "NAMA_PELANGGAN"
        const val COLUMN_TOTAL = "TOTAL"
        const val COLUMN_METODE_PEMBAYARAN = "METODE_PEMBAYARAN"
        const val COLUMN_TOTAL_DISKON = "TOTAL_DISKON"
        const val COLUMN_ID_PROMOSI = "ID_PROMOSI"
    }

    data class Transaksi(
        val idTransaksi: Int,
        val tanggal: String,
        val waktu: String,
        val shift: Int,
        val idPengguna: Int,
        val idPelanggan: Int?,
        val status: String,
        val kodeMejaTransaksi: String,
        val namaPelanggan: String,
        val total: Double,
        val metodePembayaran: String,
        val totalDiskon: Double,
        val idPromosi: Int?
    )

    fun insertTransaksi(
        tanggal: String,
        waktu: String,
        shift: Int,
        idPengguna: Int,
        idPelanggan: Int?,
        status: String,
        kodeMeja: String,
        namaPelanggan: String,
        total: Double,
        metodePembayaran: String,
        totalDiskon: Double,
        idPromosi: Int?
    ): Long {
        val values = ContentValues().apply {
            put(TransaksiContract.COLUMN_TANGGAL, tanggal)
            put(TransaksiContract.COLUMN_WAKTU, waktu)
            put(TransaksiContract.COLUMN_SHIFT, shift)
            put(TransaksiContract.COLUMN_ID_PENGGUNA, idPengguna)
            put(TransaksiContract.COLUMN_ID_PELANGGAN, idPelanggan)
            put(TransaksiContract.COLUMN_STATUS, status)
            put(TransaksiContract.COLUMN_KODE_MEJA_TRANSAKSI, kodeMeja)
            put(TransaksiContract.COLUMN_NAMA_PELANGGAN, namaPelanggan)
            put(TransaksiContract.COLUMN_TOTAL, total)
            put(TransaksiContract.COLUMN_METODE_PEMBAYARAN, metodePembayaran)
            put(TransaksiContract.COLUMN_TOTAL_DISKON, totalDiskon)
            put(TransaksiContract.COLUMN_ID_PROMOSI, idPromosi)
        }
        val db = writableDatabase
        return db.insert(TransaksiContract.TABLE_NAME, null, values)
    }

    // Retrieve all Transaksi records
    fun getAllTransaksi(): List<Transaksi> {
        val transaksiList = mutableListOf<Transaksi>()
        val query = "SELECT * FROM $TABLE_TRANSAKSI"
        val db = readableDatabase
        val cursor = db.rawQuery(query, null)

        cursor.use {
            while (it.moveToNext()) {
                val idTransaksiIndex = it.getColumnIndex(COLUMN_ID_TRANSAKSI)
                val tanggalIndex = it.getColumnIndex(COLUMN_TANGGAL)
                val waktuIndex = it.getColumnIndex(COLUMN_WAKTU)
                val shiftIndex = it.getColumnIndex(COLUMN_SHIFT)
                val idPenggunaIndex = it.getColumnIndex(COLUMN_ID_PENGGUNA)
                val idPelangganIndex = it.getColumnIndex(COLUMN_ID_PELANGGAN)
                val statusIndex = it.getColumnIndex(COLUMN_STATUS)
                val kodeMejaTransaksiIndex = it.getColumnIndex(COLUMN_KODE_MEJA_TRANSAKSI)
                val namaPelangganIndex = it.getColumnIndex(COLUMN_NAMA_PELANGGAN)
                val totalIndex = it.getColumnIndex(COLUMN_TOTAL)
                val metodePembayaranIndex = it.getColumnIndex(COLUMN_METODE_PEMBAYARAN)
                val totalDiskonIndex = it.getColumnIndex(COLUMN_TOTAL_DISKON)
                val idPromosiIndex = it.getColumnIndex(COLUMN_ID_PROMOSI)

                // Check if the column index is valid before extracting values
                if (idTransaksiIndex != -1 && tanggalIndex != -1 && waktuIndex != -1 && shiftIndex != -1 &&
                    idPenggunaIndex != -1 && idPelangganIndex != -1 && statusIndex != -1 &&
                    kodeMejaTransaksiIndex != -1 && namaPelangganIndex != -1 && totalIndex != -1 &&
                    metodePembayaranIndex != -1 && totalDiskonIndex != -1 && idPromosiIndex != -1
                ) {
                    val idTransaksi = it.getInt(idTransaksiIndex)
                    val tanggal = it.getString(tanggalIndex)
                    val waktu = it.getString(waktuIndex)
                    val shift = it.getInt(shiftIndex)
                    val idPengguna = it.getInt(idPenggunaIndex)
                    val idPelanggan = it.getInt(idPelangganIndex)
                    val status = it.getString(statusIndex)
                    val kodeMejaTransaksi = it.getString(kodeMejaTransaksiIndex)
                    val namaPelanggan = it.getString(namaPelangganIndex)
                    val total = it.getDouble(totalIndex)
                    val metodePembayaran = it.getString(metodePembayaranIndex)
                    val totalDiskon = it.getDouble(totalDiskonIndex)
                    val idPromosi = it.getInt(idPromosiIndex)

                    val transaksi = Transaksi(
                        idTransaksi, tanggal, waktu, shift, idPengguna, idPelanggan, status,
                        kodeMejaTransaksi, namaPelanggan, total, metodePembayaran, totalDiskon, idPromosi
                    )
                    transaksiList.add(transaksi)
                } else {
                    // Handle the case where one or more columns are not found
                    // You might want to log an error or handle it in another way
                }
            }
        }

        return transaksiList
    }


    // Update Transaksi record
    fun updateTransaksi(
        idTransaksi: Int,
        tanggal: String,
        waktu: String,
        shift: Int,
        idPengguna: Int,
        idPelanggan: Int?,
        status: String,
        kodeMejaTransaksi: String,
        namaPelanggan: String,
        total: Double,
        metodePembayaran: String,
        totalDiskon: Double,
        idPromosi: Int?
    ): Int {
        val values = ContentValues().apply {
            put(COLUMN_TANGGAL, tanggal)
            put(COLUMN_WAKTU, waktu)
            put(COLUMN_SHIFT, shift)
            put(COLUMN_ID_PENGGUNA, idPengguna)
            put(COLUMN_ID_PELANGGAN, idPelanggan)
            put(COLUMN_STATUS, status)
            put(COLUMN_KODE_MEJA_TRANSAKSI, kodeMejaTransaksi)
            put(COLUMN_NAMA_PELANGGAN, namaPelanggan)
            put(COLUMN_TOTAL, total)
            put(COLUMN_METODE_PEMBAYARAN, metodePembayaran)
            put(COLUMN_TOTAL_DISKON, totalDiskon)
            put(COLUMN_ID_PROMOSI, idPromosi)
        }
        val db = writableDatabase
        return db.update(TABLE_TRANSAKSI, values, "$COLUMN_ID_TRANSAKSI=?", arrayOf(idTransaksi.toString()))
    }

    // Delete Transaksi record
    fun deleteTransaksi(idTransaksi: Int): Int {
        val db = writableDatabase
        return db.delete(TABLE_TRANSAKSI, "$COLUMN_ID_TRANSAKSI=?", arrayOf(idTransaksi.toString()))
    }

    object DetailTransaksiContract {
        // Table name
        const val TABLE_NAME = "DETAIL_TRANSAKSI"

        // Columns
        const val COLUMN_ID_TRANSAKSI = "ID_TRANSAKSI"
        const val COLUMN_ID_MENU_DETAIL = "ID_MENU"
        const val COLUMN_NAMA_MENU_DETAIL = "NAMA_MENU_DETAIL"
        const val COLUMN_HARGA = "HARGA"
        const val COLUMN_JUMLAH = "JUMLAH"
        const val COLUMN_SUBTOTAL = "SUBTOTAL"
        const val COLUMN_STATUS_DETAIL = "STATUS_DETAIL"
    }

    data class DetailTransaksi(
        val idTransaksi: Int,
        val idMenuDetail: Int,
        val namaMenuDetail: String,
        val harga: Double,
        val jumlah: Int,
        val subtotal: Double,
        val statusDetail: String
    )

    // CRUD operations for DetailTransaksi Table
    fun insertDetailTransaksi(
        idTransaksi: Int,
        idMenuDetail: Int,
        namaMenuDetail: String,
        harga: Double,
        jumlah: Int,
        subtotal: Double,
        statusDetail: String
    ): Long {
        val values = ContentValues().apply {
            put(DetailTransaksiContract.COLUMN_ID_TRANSAKSI, idTransaksi)
            put(DetailTransaksiContract.COLUMN_ID_MENU_DETAIL, idMenuDetail)
            put(DetailTransaksiContract.COLUMN_NAMA_MENU_DETAIL, namaMenuDetail)
            put(DetailTransaksiContract.COLUMN_HARGA, harga)
            put(DetailTransaksiContract.COLUMN_JUMLAH, jumlah)
            put(DetailTransaksiContract.COLUMN_SUBTOTAL, subtotal)
            put(DetailTransaksiContract.COLUMN_STATUS_DETAIL, statusDetail)
        }
        val db = writableDatabase
        return db.insert(DetailTransaksiContract.TABLE_NAME, null, values)
    }

    // Retrieve all DetailTransaksi records
    fun getAllDetailTransaksi(): List<DetailTransaksi> {
        val detailTransaksiList = mutableListOf<DetailTransaksi>()
        val query = "SELECT * FROM $TABLE_DETAIL_TRANSAKSI"
        val db = readableDatabase
        val cursor = db.rawQuery(query, null)

        cursor.use {
            while (it.moveToNext()) {
                val idTransaksiIndex = it.getColumnIndex(COLUMN_ID_TRANSAKSI)
                val idMenuDetailIndex = it.getColumnIndex(COLUMN_ID_MENU_DETAIL)
                val namaMenuDetailIndex = it.getColumnIndex(COLUMN_NAMA_MENU_DETAIL)
                val hargaIndex = it.getColumnIndex(COLUMN_HARGA)
                val jumlahIndex = it.getColumnIndex(COLUMN_JUMLAH)
                val subtotalIndex = it.getColumnIndex(COLUMN_SUBTOTAL)
                val statusDetailIndex = it.getColumnIndex(COLUMN_STATUS_DETAIL)

                // Check if the column index is valid before extracting values
                if (idTransaksiIndex != -1 && idMenuDetailIndex != -1 && namaMenuDetailIndex != -1 &&
                    hargaIndex != -1 && jumlahIndex != -1 && subtotalIndex != -1 && statusDetailIndex != -1
                ) {
                    val idTransaksi = it.getInt(idTransaksiIndex)
                    val idMenuDetail = it.getInt(idMenuDetailIndex)
                    val namaMenuDetail = it.getString(namaMenuDetailIndex)
                    val harga = it.getDouble(hargaIndex)
                    val jumlah = it.getInt(jumlahIndex)
                    val subtotal = it.getDouble(subtotalIndex)
                    val statusDetail = it.getString(statusDetailIndex)

                    val detailTransaksi = DetailTransaksi(
                        idTransaksi, idMenuDetail, namaMenuDetail, harga, jumlah, subtotal, statusDetail
                    )
                    detailTransaksiList.add(detailTransaksi)
                } else {
                    // Handle the case where one or more columns are not found
                    // You might want to log an error or handle it in another way
                }
            }
        }

        return detailTransaksiList
    }


    // Update DetailTransaksi record
    fun updateDetailTransaksi(
        idTransaksi: Int,
        idMenuDetail: Int,
        namaMenuDetail: String,
        harga: Double,
        jumlah: Int,
        subtotal: Double,
        statusDetail: String
    ): Int {
        val values = ContentValues().apply {
            put(COLUMN_ID_MENU_DETAIL, idMenuDetail)
            put(COLUMN_NAMA_MENU_DETAIL, namaMenuDetail)
            put(COLUMN_HARGA, harga)
            put(COLUMN_JUMLAH, jumlah)
            put(COLUMN_SUBTOTAL, subtotal)
            put(COLUMN_STATUS_DETAIL, statusDetail)
        }
        val db = writableDatabase
        return db.update(TABLE_DETAIL_TRANSAKSI, values, "$COLUMN_ID_TRANSAKSI=?", arrayOf(idTransaksi.toString()))
    }

    // Delete DetailTransaksi record
    fun deleteDetailTransaksi(idTransaksi: Int): Int {
        val db = writableDatabase
        return db.delete(TABLE_DETAIL_TRANSAKSI, "$COLUMN_ID_TRANSAKSI=?", arrayOf(idTransaksi.toString()))
    }

    fun readUser(email:String, password:String): Boolean{
        val db = readableDatabase
        val selection = "$COLUMN_EMAIL = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(email, password)
        val cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null,null)

        val userExists = cursor.count> 0
        cursor.close()
        return userExists
    }

}

data class Meja(val idMeja: Int, val idWarung: Int, val kodeMeja: String)
data class Menu(val idMenu: Int, val namaMenu: String, val kategori: String, val harga: Double, val gambar: String)
data class Transaksi(
    val idTransaksi: Int, val tanggal: String, val waktu: String, val shift: Int,
    val idPengguna: Int, val idPelanggan: Int?, val status: String, val kodeMeja: String,
    val namaPelanggan: String, val total: Double, val metodePembayaran: String,
    val totalDiskon: Double, val idPromosi: Int?
)
data class DetailTransaksi(
    val idTransaksi: Int, val idMenu: Int, val namaMenu: String, val harga: Double,
    val jumlah: Int, val subtotal: Double, val status: String
)