package com.trihard.konselink.fragments

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.trihard.konselink.R
import com.trihard.konselink.adapters.PanduanListAdapter
import com.trihard.konselink.models.panduan.Panduan
import com.trihard.konselink.networking.repositories.ProfileRepository
import com.trihard.konselink.view_model.PanduanViewModel
import kotlinx.android.synthetic.main.panduan_fragment.*

class PanduanFragment : Fragment() {

    companion object {
        fun newInstance() = PanduanFragment()
    }

    private lateinit var viewModel: PanduanViewModel
    private var panduanArraylist = ArrayList<Panduan>()
    private lateinit var panduanListAdapter: PanduanListAdapter
    private lateinit var panduanRv: RecyclerView
    private lateinit var sharedPreferences: SharedPreferences
    private val profileRepository = ProfileRepository.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PanduanViewModel::class.java)
        sharedPreferences = context!!.getSharedPreferences(getString(R.string.user_shared_pref_file_key), Context.MODE_PRIVATE) ?: return
        viewModel.loadAll(profileRepository.getToken(sharedPreferences, context!!))
        panduanListAdapter = PanduanListAdapter(panduanArraylist, context!!)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.panduan_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        panduanRv = panduan_list
        if (panduanArraylist.isNotEmpty()) {
            loading_list_panduan.visibility = View.GONE
        }
        viewModel.findAll().observe(this, Observer { panduanResponse ->
            val panduans = panduanResponse.panduans
            loading_list_panduan.visibility = View.GONE
            panduanArraylist.clear()
            panduanArraylist.addAll(panduans)
            panduanListAdapter.notifyDataSetChanged()
        })
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
//        if (panduanListAdapter == null) {
        panduanListAdapter = PanduanListAdapter(panduanArraylist, context!!)
        panduanRv.layoutManager = LinearLayoutManager(context!!)
        panduanRv.adapter = panduanListAdapter
        panduanRv.itemAnimator = DefaultItemAnimator()
//        } else {
//            panduanListAdapter?.notifyDataSetChanged()
//        }
    }

}
