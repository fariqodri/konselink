package com.trihard.konselink.fragments


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.trihard.konselink.R
import com.trihard.konselink.activities.LoginActivity
import com.trihard.konselink.networking.repositories.ProfileRepository
import com.trihard.konselink.view_model.UserViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private val profileRepository = ProfileRepository.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = context!!.getSharedPreferences(getString(R.string.user_shared_pref_file_key), Context.MODE_PRIVATE) ?: return
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        viewModel.findOne(profileRepository.getToken(sharedPreferences, context!!))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        logout_button.setOnClickListener {
            with(sharedPreferences.edit()) {
                profileRepository.clearUserData(this)
                apply()
            }
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity!!.finish()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.accountResult.observe(this, Observer {
            user_data_loading.visibility = View.GONE
            val user = it.data!!
            username_data.text = user._username
            email_data.text = user._email
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}
