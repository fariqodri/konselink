package com.trihard.konselink.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.trihard.konselink.R
import com.trihard.konselink.models.panduan.Panduan
import com.trihard.konselink.view_model.PanduanViewModel
import kotlinx.android.synthetic.main.activity_detail_panduan.*

class DetailPanduanActivity : AppCompatActivity() {

    private lateinit var viewModel: PanduanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_panduan)
        val _panduan = collectData()
        viewModel = ViewModelProviders.of(this).get(PanduanViewModel::class.java)
        viewModel.loadOne(_panduan.id)
        viewModel.findOne().observe(this, Observer {
            val panduanData = it.article
            loading_detail_activity.visibility = View.GONE
            bindData(panduanData)
        })
        toolbar.setNavigationIcon(R.drawable.back_button)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun collectData(): Panduan {
        return intent.getParcelableExtra("panduan")
    }

    private fun bindData(panduan: Panduan) {
        detail_panduan_title.text = panduan.title
        detail_panduan_author.text = getString(R.string.author_prefix, panduan.writer)
        detail_panduan_content.text = panduan.content
        Glide
            .with(this)
            .load(panduan.thumbnail)
            .dontTransform()
            .placeholder(R.drawable.thumbnail_placeholder)
            .into(detail_panduan_banner)
    }
}
