package com.trihard.konselink.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.trihard.konselink.MainActivity
import com.trihard.konselink.R
import com.trihard.konselink.models.profile.AccountBody
import com.trihard.konselink.models.profile.LoginBody
import com.trihard.konselink.networking.repositories.ProfileRepository
import com.trihard.konselink.view_model.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Callback

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel
    private lateinit var sharedPref: SharedPreferences
    private val profileRepository = ProfileRepository.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        sharedPref = this.getSharedPreferences(getString(R.string.user_shared_pref_file_key), Context.MODE_PRIVATE) ?: return
        viewModel.loginResult.observe(this, Observer {
            if (it.actualBody != null) {
                with(sharedPref.edit()) {
                    profileRepository.setUserId(this, this@LoginActivity, it.actualBody?.data!!._id)
                    profileRepository.setToken(this, this@LoginActivity, it.actualBody?.data!!._token)
                    profileRepository.setRole(this, this@LoginActivity, it.actualBody?.data!!._role)
                    apply()
                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Log.e("Register Failure", it.errorBody?.string())
                Toast.makeText(this, "Login Gagal", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun loginButtonAction(v: View) {
        val emailText = email_login_input.text.toString()
        val passwordText = password_login_input.text.toString()
        viewModel.login(LoginBody(emailText, passwordText))
        for (i in 0 until credential_layout.childCount) {
            credential_layout.getChildAt(i).isClickable = false
        }
        login_loading.visibility = View.VISIBLE
        credential_layout.apply {
            alpha = 0.5f
        }

    }

    fun registerAsUserClickAction(v: View) {
        val intent = Intent(this, RegisterUserActivity::class.java)
        startActivity(intent)
    }

    fun registerAsCounselorClickAction(v: View) {
        val intent = Intent(this, RegisterCounselorActivity::class.java)
        startActivity(intent)
    }
}
