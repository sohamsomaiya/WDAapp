package com.example.wda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.card.MaterialCardView
import com.google.android.material.circularreveal.cardview.CircularRevealCardView
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var drawer: DrawerLayout
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView
    private lateinit var CreateWebCard:CircularRevealCardView
    private lateinit var EditWebCard:CircularRevealCardView
    private lateinit var ViewSiteStatusCard:CircularRevealCardView
    private lateinit var QueryCard:CircularRevealCardView
    private lateinit var WelcomeText:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        CreateWebCard=findViewById(R.id.CreateWebCard)
        EditWebCard=findViewById(R.id.EditWebCard)
        ViewSiteStatusCard=findViewById(R.id.ViewSiteStatusCard)
        QueryCard=findViewById(R.id.QueryCard)

        WelcomeText=findViewById(R.id.WelcomeTxt)
        val sp =getSharedPreferences("wda", MODE_PRIVATE)
        val UserName= sp.getString("Username","")
        Toast.makeText(this, UserName.toString(), Toast.LENGTH_SHORT).show()

        WelcomeText.setText("Welcome \n $UserName")

        CreateWebCard.setOnClickListener {
            val intent = Intent(this@HomeActivity,TypeActivity::class.java)
            startActivity(intent)
        }
        QueryCard.setOnClickListener {
            val intent = Intent(this@HomeActivity,QueryActivity::class.java)
            startActivity(intent)
        }
        ViewSiteStatusCard.setOnClickListener {
            val intent = Intent(this@HomeActivity,ViewWebStatusActivity::class.java)
            startActivity(intent)
        }
        EditWebCard.setOnClickListener {
            val intent = Intent(this@HomeActivity,SelectWebToEdit::class.java)
            startActivity(intent)
        }
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
                    val profileintent = Intent(this, ProfileActivity::class.java)
                    startActivity(profileintent)
                    drawer.closeDrawer(GravityCompat.START)
                }
                R.id.menu_logout -> {
                    val preferences = getSharedPreferences("wda", 0)
                    preferences.edit().remove("userId").apply()
                    preferences.edit().remove("contact").apply()
                    // Toast.makeText(this, "Logged Out SuccessFully", Toast.LENGTH_SHORT).show()
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