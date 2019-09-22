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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PanduanViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // TODO initialize ViewModel
//        viewModel.init()
        return inflater.inflate(R.layout.panduan_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        panduanRv = panduan_list
        // TODO connect with API through ViewModel
//        viewModel.panduanRepository.observe(this, Observer { panduanResponse ->
//            val panduans = panduanResponse.panduans
//            panduanArraylist.addAll(panduans)
//            panduanListAdapter?.notifyDataSetChanged()
//        })
        val panduans = ArrayList<Panduan>()
        panduans.add(Panduan(1, "Test 1", "Qori", "https://www.canterbury.ac.nz/science/schools-and-departments/psyc-speech-hear/postgraduate-study/clinical-psychology/Abstract-head-with-lit-up-brain_5340072790064601823.jpg",
            "https://www.canterbury.ac.nz/science/schools-and-departments/psyc-speech-hear/postgraduate-study/clinical-psychology/Abstract-head-with-lit-up-brain_5340072790064601823.jpg", "hello everyone"))
        panduans.add(Panduan(2, "Test 1", "Qori", "https://pi.tedcdn.com/r/pf.tedcdn.com/images/playlists/theories-of-evolution_325169627.jpg?quality=89&w=256",
            "https://pi.tedcdn.com/r/pf.tedcdn.com/images/playlists/theories-of-evolution_325169627.jpg?quality=89&w=256", "hello everyone now we\'re back with the biggest Youtuber of all time which is lolll"))
        panduanArraylist.addAll(panduans)
        panduanListAdapter?.notifyDataSetChanged()
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
