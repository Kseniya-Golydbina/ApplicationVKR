package ru.example.appvkr.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import ru.example.appvkr.R

open class BaseActivity : AppCompatActivity() {
    protected lateinit var mAuth: FirebaseAuth
    protected lateinit var database: FirebaseDatabase
    private val TAG = "uilover"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = FirebaseDatabase.getInstance()
        mAuth = FirebaseAuth.getInstance()
        window.statusBarColor = resources.getColor(R.color.white, null)
    }
}