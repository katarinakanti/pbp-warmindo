package com.android.warmindoinspirasiindonesia

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.android.warmindoinspirasiindonesia.ui.detail.Detail

class DatabaseHelper(private val context: Context)
    :SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    // Existing user table
    companion object {
        private const val DATABASE_NAME = "warmindo.db"
        private const val DATABASE_VERSION = 1
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
        private const val COLUMN_ID_DETAIL_TRANSAKSI = "id_transaksi"
        private const val COLUMN_ID_MENU_DETAIL = "idmenu"
        private const val COLUMN_NAMA_MENU_DETAIL = "namamenu"
        private const val COLUMN_JUMLAH = "jumlah"
        private const val COLUMN_SUBTOTAL = "subtotal"
        private const val COLUMN_STATUS_DETAIL = "status"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery =("CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_USERNAME TEXT, " +
                "$COLUMN_EMAIL TEXT, " +
                "$COLUMN_PASSWORD TEXT)")
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
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

    fun insertMeja(idWarung: Int, kodeMeja: String): Long {
        val values = ContentValues().apply {
            put(COLUMN_ID_WARUNG_MEJA, idWarung)
            put(COLUMN_KODE_MEJA, kodeMeja)
        }
        val db = writableDatabase
        return db.insert(TABLE_MEJA, null, values)
    }

    fun getAllMeja(): List<Meja> {
        val mejaList = mutableListOf<Meja>()
        val query = "SELECT * FROM $TABLE_MEJA"
        val db = readableDatabase
        val cursor = db.rawQuery(query, null)

        cursor.use {
            while (it.moveToNext()) {
                val idMejaIndex = it.getColumnIndex(COLUMN_ID_MEJA)
                val idWarungIndex = it.getColumnIndex(COLUMN_ID_WARUNG_MEJA)
                val kodeMejaIndex = it.getColumnIndex(COLUMN_KODE_MEJA)

                // Check if the column indices are valid
                if (idMejaIndex >= 0 && idWarungIndex >= 0 && kodeMejaIndex >= 0) {
                    val idMeja = it.getInt(idMejaIndex)
                    val idWarung = it.getInt(idWarungIndex)
                    val kodeMeja = it.getString(kodeMejaIndex)

                    val meja = Meja(idMeja, idWarung, kodeMeja)
                    mejaList.add(meja)
                } else {
                    // Handle the case where column indices are not valid
                    // This could be due to a column name typo or other issues
                    // You may log an error, throw an exception, or take appropriate action
                }
            }
        }

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
            put(COLUMN_TANGGAL, tanggal)
            put(COLUMN_WAKTU, waktu)
            put(COLUMN_SHIFT, shift)
            put(COLUMN_ID_PENGGUNA, idPengguna)
            put(COLUMN_ID_PELANGGAN, idPelanggan)
            put(COLUMN_STATUS, status)
            put(COLUMN_KODE_MEJA_TRANSAKSI, kodeMeja)
            put(COLUMN_NAMA_PELANGGAN, namaPelanggan)
            put(COLUMN_TOTAL, total)
            put(COLUMN_METODE_PEMBAYARAN, metodePembayaran)
            put(COLUMN_TOTAL_DISKON, totalDiskon)
            put(COLUMN_ID_PROMOSI, idPromosi)
        }
        val db = writableDatabase
        return db.insert(TABLE_TRANSAKSI, null, values)
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

    //Fungsi Update Status
    fun updateStatus(status : Detail){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_STATUS_DETAIL, status.status)
        }
        val whereClause = "$COLUMN_ID_DETAIL_TRANSAKSI= ?"
        val whereArgs = arrayOf(status.id.toString())
        db.update(TABLE_NAME, values, whereClause, whereArgs)
        db.close()
    }

    fun getDetailByID(detailID : Int): Detail{
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_DETAIL_TRANSAKSI WHERE $COLUMN_ID_DETAIL_TRANSAKSI = $detailID"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_DETAIL_TRANSAKSI))
        val status = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS_DETAIL))

        cursor.close()
        db.close()
        return Detail(id, status)
    }



    // Delete Transaksi record
    fun deleteTransaksi(idTransaksi: Int): Int {
        val db = writableDatabase
        return db.delete(TABLE_TRANSAKSI, "$COLUMN_ID_TRANSAKSI=?", arrayOf(idTransaksi.toString()))
    }


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
            put(COLUMN_ID_TRANSAKSI, idTransaksi)
            put(COLUMN_ID_MENU_DETAIL, idMenuDetail)
            put(COLUMN_NAMA_MENU_DETAIL, namaMenuDetail)
            put(COLUMN_HARGA, harga)
            put(COLUMN_JUMLAH, jumlah)
            put(COLUMN_SUBTOTAL, subtotal)
            put(COLUMN_STATUS_DETAIL, statusDetail)
        }
        val db = writableDatabase
        return db.insert(TABLE_DETAIL_TRANSAKSI, null, values)
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