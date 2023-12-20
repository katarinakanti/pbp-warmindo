package com.android.warmindoinspirasiindonesia

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.android.warmindoinspirasiindonesia.databinding.ActivityTransaksiBinding
import com.android.warmindoinspirasiindonesia.ui.home.Shift

class TransaksiActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityTransaksiBinding
    private lateinit var toolbar: Toolbar

    companion object {
        const val EXTRA_SELECTED_SHIFT = "extra_selected_shift"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTransaksiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the selectedShift data from the Intent
        val selectedShift: String? = intent.getStringExtra(EXTRA_SELECTED_SHIFT)

        selectedShift?.let {
            Toast.makeText(this, "Shift selected in TransaksiActivity: ${it.toString()}", Toast.LENGTH_SHORT).show()
        }

        val drawerLayout: DrawerLayout = binding.drawerLayout
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_detail, R.id.nav_transaksi
            ), drawerLayout
        )

        // Get the Toolbar from app_bar_main.xml
        toolbar = findViewById(R.id.toolbar_transaksi)

        setSupportActionBar(toolbar) // Set the Toolbar as the support action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.activity_main_drawer, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
        val navController = findNavController(R.id.nav_fragment_content_transaksi)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
