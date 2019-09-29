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
import com.trihard.konselink.models.profile.RegisterBody
import com.trihard.konselink.view_model.UserViewModel
import kotlinx.android.synthetic.main.activity_register_counselor.*

class RegisterCounselorActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_counselor)
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        viewModel.registerResult.observe(this, Observer {
            if (it.actualBody != null) {
                with(sharedPref.edit()) {
                    putInt("userId", it.actualBody?.data!!._id)
                    putString("token", it.actualBody?.data!!._token)
                    putString("role", it.actualBody?.data!!._role)
                    apply()
                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Log.e("Register Failure", it.errorBody?.string())
                Toast.makeText(this, "Pendaftaran Gagal", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun registerAsCounselorAction(v: View) {
        val username = username_input.text.toString()
        val email = email_input.text.toString()
        val password = password_input.text.toString()
        val confirmPassword = password_confirm_input.text.toString()
        val phone = phone_input.text.toString()
        val community = community_input.text.toString()

        if (password == confirmPassword) {
            viewModel.register(RegisterBody(username, email, phone, password, "konselor", community))
            for (i in 0 until register_input_layout.childCount) {
                register_input_layout.getChildAt(i).isClickable = false
            }
            register_input_layout.alpha = 0.5f
            register_loading.visibility = View.VISIBLE

        } else {
            Toast.makeText(this, "Konfirmasi password Anda berbeda dengan Password", Toast.LENGTH_LONG).show()
        }
    }
}
