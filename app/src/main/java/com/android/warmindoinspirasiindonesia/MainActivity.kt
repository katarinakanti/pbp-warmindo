package com.android.warmindoinspirasiindonesia

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.warmindoinspirasiindonesia.databinding.ActivityMainBinding
import com.android.warmindoinspirasiindonesia.ui.home.Shift

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var isShiftClicked = false

    private var listMovies = ArrayList<Shift>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_detail, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val cardShift = findViewById<RecyclerView>(R.id.rv_shift)
        val btnMasuk = findViewById<Button>(R.id.button2)

        // Listener untuk card shift
        cardShift.setOnClickListener {
            // Ketika card shift diklik, ubah warna tombol menjadi hijau
            btnMasuk.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.cardMasuk))
            isShiftClicked = true // Set flag menjadi true karena card shift telah diklik
        }

        // Listener untuk tombol Masuk
        btnMasuk.setOnClickListener {
            if (isShiftClicked) {
                // Jika card shift sudah diklik sebelumnya, lanjut ke halaman DetailActivity
                val intent = Intent(this, DetailActivity::class.java)
                startActivity(intent)
            } else {
                // Jika card shift belum diklik, tidak melakukan apa-apa atau memberikan notifikasi kepada pengguna
                // Misalnya, tampilkan pesan toast
                Toast.makeText(this, "Anda harus memilih shift terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.activity_main_drawer, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
