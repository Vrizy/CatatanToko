package com.kelompoksatu.catatantoko.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kelompoksatu.catatantoko.Database.DB
import com.kelompoksatu.catatantoko.databinding.ActivityLoginScreenBinding
import kotlinx.coroutines.*
import java.util.prefs.Preferences

class LoginScreen : AppCompatActivity() {

    lateinit var binding: ActivityLoginScreenBinding
    private lateinit var db: DB
    lateinit var preferences: com.kelompoksatu.catatantoko.Utils.Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpPreference()
        db = DB.getInstance(this)
        setUpAction()


    }


    private fun setUpPreference() {
        preferences = com.kelompoksatu.catatantoko.Utils.Preferences(this)
        if (preferences.getValueLogin().equals(true)) {
            finishAffinity()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }


    /**-----UNTUK VALIDASI LOGIN-----**/
    private fun setUpAction() {
        binding.apply {
            btnLogin.setOnClickListener {
                val username = tvFillUsername.text.toString()
                val password = tvFillPassword.text.toString()

                if (username.isEmpty()) {
                    Toast.makeText(
                        this@LoginScreen,
                        "Username Tidak Boleh Kosong",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                if (password.isEmpty()) {
                    Toast.makeText(
                        this@LoginScreen,
                        "Password Tidak Boleh Kosong",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                GlobalScope.launch(Dispatchers.IO) {
                    val isUserExist = async { db.userDao().checkUsername(username) }.await()
                    val user = async { db.userDao().getUser(username, password) }.await()
                    if (isUserExist == null) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@LoginScreen,
                                "Username tidak terdaftar",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else if (user == null) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@LoginScreen, "Password Salah", Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@LoginScreen, "Login Success", Toast.LENGTH_SHORT)
                                .show()
                            val intent = Intent(this@LoginScreen, MainActivity::class.java)
                            intent.putExtra("username", username)
                            startActivity(intent)
                            preferences.setValueLogin(true)
                            preferences.setUsername(username)
                        }
                    }
                }
            }
        }
    }

}