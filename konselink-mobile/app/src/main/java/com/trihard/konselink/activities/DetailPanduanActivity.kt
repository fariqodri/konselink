package com.trihard.konselink.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.trihard.konselink.R
import com.trihard.konselink.models.panduan.Panduan
import kotlinx.android.synthetic.main.activity_detail_panduan.*

class DetailPanduanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_panduan)
        val panduan = collectData()
        bindData(panduan)
        toolbar.setNavigationIcon(R.drawable.back_button)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun collectData(): Panduan {
        return intent.getParcelableExtra("Detail")
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
