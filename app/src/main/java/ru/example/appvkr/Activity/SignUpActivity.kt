package ru.example.appvkr.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import ru.example.appvkr.databinding.ActivitySignUpBinding

class SignUpActivity : BaseActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val TAG = "SignUpActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setVariable()
    }

    private fun setVariable() {
        binding.signupBtn.setOnClickListener{
            val email = binding.userEdt.text.toString()
            val password = binding.passEdt.text.toString()

            if (password.length<6){
                Toast.makeText(this, "Пароль должен содержать больше 6 символов", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){
                    task ->
                    if (task.isSuccessful){
                        Log.i(TAG, "onComplete")
                        startActivity(Intent(this, MainActivity::class.java))
                    }else{
                        Log.i(TAG, "failure: ${task.exception}")
                        Toast.makeText(this, "Не удалось зарегистрироваться", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}