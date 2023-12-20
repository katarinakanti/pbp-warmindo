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
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.warmindoinspirasiindonesia.databinding.ActivityMainBinding
import com.android.warmindoinspirasiindonesia.ui.home.HomeFragment
import com.android.warmindoinspirasiindonesia.ui.home.Shift

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar
    private lateinit var masukButton: Button
    private var isShiftClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_detail, R.id.nav_slideshow
            ), drawerLayout
        )

        // Get the Toolbar from app_bar_main.xml
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar) // Set the Toolbar as the support action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        //    masukButton = findViewById(R.id.button2)
        //    masukButton.setOnClickListener {
        //        val homeFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as? HomeFragment
        //        val selectedShift = homeFragment?.getSelectedShift()
        //
        //        // Check if a shift is selected before performing MASUK button actions
        //        if (selectedShift != null) {
        //            // Create an Intent to start TransaksiActivity
        //            val intent = Intent(this, TransaksiActivity::class.java)
        //
        //            // Pass the selectedShift data as an extra with the Intent
        //            intent.putExtra(TransaksiActivity.EXTRA_SELECTED_SHIFT, selectedShift.getTitle())
        //
        //            startActivity(intent)
        //        } else {
        //            Toast.makeText(this, "Please select a shift first", Toast.LENGTH_SHORT).show()
        //        }
        //    }
        //
        // }
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
