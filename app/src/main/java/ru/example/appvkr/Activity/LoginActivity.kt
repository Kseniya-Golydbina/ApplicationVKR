package ru.example.appvkr.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import ru.example.appvkr.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setVariable()
    }
    private fun setVariable(){
        binding.loginBtn.setOnClickListener{
            val email = binding.userEdt.text.toString()
            val password = binding.passEdt.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this){
                        task ->
                        if(task.isSuccessful){
                            startActivity(Intent(this, MainActivity::class.java))
                        }else{
                            Toast.makeText(this, "Пользователь не найден", Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{
                Toast.makeText(this, "Пожалуйста, введите email и пароль", Toast.LENGTH_SHORT).show()
            }
        }
    }
}