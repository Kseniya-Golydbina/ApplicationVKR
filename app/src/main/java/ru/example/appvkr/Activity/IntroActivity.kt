package ru.example.appvkr.Activity

import android.content.Intent
import android.os.Bundle
import ru.example.appvkr.R
import ru.example.appvkr.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setVariable()
        window.statusBarColor = resources.getColor(R.color.white)
    }
    private fun setVariable(){
        binding.loginButton.setOnClickListener{
            if(mAuth.currentUser != null){
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
        binding.signButton.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}