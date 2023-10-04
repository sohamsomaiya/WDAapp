package com.example.wda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var drawer: DrawerLayout
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        navigationView = findViewById(R.id.NavView)
//        val header = navigationView.getHeaderView(0)
        drawer = findViewById(R.id.navDrawer)
        drawerToggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    drawer.closeDrawer(GravityCompat.START)
                }
                R.id.menu_profile -> {
//                    val profileintent = Intent(this, ProfileActivity::class.java)
//                    startActivity(profileintent)
                    drawer.closeDrawer(GravityCompat.START)
                }
                R.id.menu_logout -> {
                    val preferences = getSharedPreferences("Credentials", 0)
                    Toast.makeText(this, "Logged Out SuccessFully", Toast.LENGTH_SHORT).show()
                    val intent= Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                    drawer.closeDrawer(GravityCompat.START)
                }
            }
            true
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return if (drawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)

    }
}