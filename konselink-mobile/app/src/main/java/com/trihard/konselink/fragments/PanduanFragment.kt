package com.trihard.konselink.fragments

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
import com.trihard.konselink.view_model.PanduanViewModel
import kotlinx.android.synthetic.main.panduan_fragment.*

class PanduanFragment : Fragment() {

    companion object {
        fun newInstance() = PanduanFragment()
    }

    private lateinit var viewModel: PanduanViewModel
    private val panduanArraylist = ArrayList<Panduan>()
    private var panduanListAdapter: PanduanListAdapter? = null
    private lateinit var panduanRv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PanduanViewModel::class.java)
        viewModel.loadAll()
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
        viewModel.findAll().observe(this, Observer { panduanResponse ->
            val panduans = panduanResponse.panduans
            loading_list_panduan.visibility = View.GONE
            panduanArraylist.addAll(panduans)
            panduanListAdapter?.notifyDataSetChanged()
        })
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        if (panduanListAdapter == null) {
            panduanListAdapter = PanduanListAdapter(panduanArraylist, context!!)
            panduanRv.layoutManager = LinearLayoutManager(context!!)
            panduanRv.adapter = panduanListAdapter
            panduanRv.itemAnimator = DefaultItemAnimator()
        } else {
            panduanListAdapter?.notifyDataSetChanged()
        }
    }

}
