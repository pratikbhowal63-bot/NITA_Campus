package com.example.nitacampus

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.net.toUri
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity2 : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var menuIcon: ImageView

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main2)

        window.insetsController?.setSystemBarsAppearance( 0, android.view.WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        menuIcon = findViewById(R.id.menuIcon)

        val headerView = navigationView.getHeaderView(0)
        val tvNavName = headerView.findViewById<TextView>(R.id.tvName)
        val tvRoll = headerView.findViewById<TextView>(R.id.tvRoll)

        val userId = intent.getStringExtra("username") ?: intent.getStringExtra("userId") ?: ""

        headerView.setOnClickListener {
            val intent = Intent(this, profile::class.java)
            intent.putExtra("username", userId)
            startActivity(intent)
            drawerLayout.closeDrawer(GravityCompat.START)
        }

        navigationView.setNavigationItemSelectedListener { item ->

            when(item.itemId){

                R.id.profile -> {
                    val intent = Intent(this, profile::class.java)
                    intent.putExtra("username", userId)
                    startActivity(intent)
                }


                    R.id.nav_dark_mode -> {

                        AppCompatDelegate.setDefaultNightMode(
                            AppCompatDelegate.MODE_NIGHT_YES
                        )
                        true
                    }


                R.id.admins -> {
                    startActivity(Intent(this, NextActivity::class.java))
                }

                R.id.contact -> {
                    startActivity(Intent(this, NextActivity::class.java))
                }

                R.id.logout -> {
                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent(this, SignIn::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
            }

            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        val tvHello = findViewById<TextView>(R.id.tvHello)

        if (userId.isNotEmpty()) {

            val databaseReference = FirebaseDatabase.getInstance().getReference("Users")

            databaseReference.child(userId).get()
                .addOnSuccessListener { snapshot ->

                    val name = snapshot.child("name").value.toString()

                    tvHello.text = "Hello $name"
                    tvNavName.text = name
                    val roll = snapshot.child("password").value.toString()
                    tvRoll.text = roll

                    val points = snapshot.child("points").value.toString()
                    val tvPoints = findViewById<TextView>(R.id.Points)
                    tvPoints.text = points

                    val streak = snapshot.child("streak").value.toString()
                    val tvStreak = findViewById<TextView>(R.id.Streak)
                    tvStreak.text = "$streak days"
                }
                .addOnFailureListener {
                    tvHello.text = "Hello"
                }
        }

        menuIcon.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        val notices = findViewById<ImageView>(R.id.Notice)
        val mis = findViewById<ImageView>(R.id.mis)
        val mess = findViewById<ImageView>(R.id.mess)
        val calender = findViewById<ImageView>(R.id.calender)
        val chat = findViewById<ImageView>(R.id.chat)
        val schlorship = findViewById<ImageView>(R.id.schlorship)
        val clubs = findViewById<ImageView>(R.id.clubs)
        val Events = findViewById<ImageView>(R.id.Events)


        val year = findViewById<ImageView>(R.id.year)
        val CSE = findViewById<ImageView>(R.id.CSE)
        val ECE = findViewById<ImageView>(R.id.ECE)
        val EE = findViewById<ImageView>(R.id.EE)
        val EIE = findViewById<ImageView>(R.id.EIE)
        val MECH = findViewById<ImageView>(R.id.MECH)
        val CV = findViewById<ImageView>(R.id.CV)
        val CHEMICAL = findViewById<ImageView>(R.id.CHEMICAL)
        val PE = findViewById<ImageView>(R.id.PE)
        val BIO = findViewById<ImageView>(R.id.BIO)
        val PHYSICS = findViewById<ImageView>(R.id.PHYSICS)
        val CHEMISTRY = findViewById<ImageView>(R.id.CHEMISTRY)
        val MATHS = findViewById<ImageView>(R.id.MATHS)


        notices.setOnClickListener {
            val intent = Intent(this, NextActivity::class.java)
            startActivity(intent)
        }

        mis.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = "https://mis.nita.ac.in/iitmsv4eGq0RuNHb0G5WbhLmTKLmTO7YBcJ4RHuXxCNPvuIw=?enc=EGbCGWnlHNJ/WdgJnKH8DA==".toUri()
            startActivity(intent)
        }

        mess.setOnClickListener {
            val intent = Intent(this, NextActivity::class.java)
            startActivity(intent)
        }

        calender.setOnClickListener {
            val intent = Intent(this, NextActivity::class.java)
            startActivity(intent)
        }

        chat.setOnClickListener {
            val intent = Intent(this, NextActivity::class.java)
            startActivity(intent)
        }

        schlorship.setOnClickListener {
            val intent = Intent(this, NextActivity::class.java)
            startActivity(intent)
        }

        clubs.setOnClickListener {
            val intent = Intent(this, NextActivity::class.java)
            startActivity(intent)
        }

        Events.setOnClickListener {
            val intent = Intent(this, NextActivity::class.java)
            startActivity(intent)
        }


        year.setOnClickListener {
            val intent = Intent(this, FirstYearActivity::class.java)
            startActivity(intent)
        }

        CSE.setOnClickListener {
            val intent = Intent(this, CSEActivity::class.java)
            startActivity(intent)
        }

        ECE.setOnClickListener {
            val intent = Intent(this, ECEActivity::class.java)
            startActivity(intent)
        }

        EE.setOnClickListener {
            val intent = Intent(this, EEActivity::class.java)
            startActivity(intent)
        }

        EIE.setOnClickListener {
            val intent = Intent(this, EIEActivity::class.java)
            startActivity(intent)
        }

        MECH.setOnClickListener {
            val intent = Intent(this, MechanicalActivity::class.java)
            startActivity(intent)
        }

        CV.setOnClickListener {
            val intent = Intent(this, CivilActivity::class.java)
            startActivity(intent)
        }

        CHEMICAL.setOnClickListener {
            val intent = Intent(this, ChemicalActivity::class.java)
            startActivity(intent)
        }

        PE.setOnClickListener {
            val intent = Intent(this, ProductionActivity::class.java)
            startActivity(intent)
        }

        BIO.setOnClickListener {
            val intent = Intent(this, BioActivity::class.java)
            startActivity(intent)
        }

        PHYSICS.setOnClickListener {
            val intent = Intent(this, PhysicsActivity::class.java)
            startActivity(intent)
        }

        CHEMISTRY.setOnClickListener {
            val intent = Intent(this, ChemistryActivity::class.java)
            startActivity(intent)
        }

        MATHS.setOnClickListener {
            val intent = Intent(this, MathsActivity::class.java)
            startActivity(intent)
        }

        val aiButton = findViewById<ImageView>(R.id.robo)
        aiButton.setOnClickListener {
            val intent = Intent(this, AiAssistantActivity::class.java)
            startActivity(intent)
        }

        if (userId.isNotEmpty()) {

            val gm = GamificationManager()

            gm.addPoints(userId, 5)

            gm.updateStreak(userId)

            val tvBadge =
                findViewById<TextView>(R.id.tvBadge)

            FirebaseDatabase.getInstance()
                .getReference("Users")
                .child(userId)
                .child("badge")
                .get()
                .addOnSuccessListener {

                    val badge =
                        it.getValue(String::class.java)

                    tvBadge.text =
                        badge ?: "Beginner"
                }
        }

    }
}