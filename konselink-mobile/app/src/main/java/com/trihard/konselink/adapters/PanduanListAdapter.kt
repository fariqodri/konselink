package com.trihard.konselink.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.trihard.konselink.R
import com.trihard.konselink.activities.DetailPanduanActivity
import com.trihard.konselink.models.panduan.Panduan
import kotlinx.android.synthetic.main.panduan_card.view.*
import kotlin.math.min

class PanduanListAdapter (val panduans: List<Panduan>, val context: Context) : RecyclerView.Adapter<PanduanViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PanduanViewHolder {
        return PanduanViewHolder(LayoutInflater.from(context).inflate(R.layout.panduan_card, parent, false))
    }

    override fun getItemCount(): Int {
        return panduans.size
    }

    override fun onBindViewHolder(holder: PanduanViewHolder, position: Int) {
        val panduan = panduans.get(position)
        holder.panduanTitle.text = panduan.title
        Glide
            .with(context)
            .load(panduan.thumbnail)
            .dontTransform()
            .placeholder(R.drawable.thumbnail_placeholder)
            .into(holder.panduanThumbnail)
        val content: String
        if (panduan.content.length >= 100) {
            content = context.getString(R.string.ellipsis, panduan.content.substring(0, 100))
        } else {
            content = panduan.content
        }
        holder.panduanContent.text = content
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailPanduanActivity::class.java)
            intent.putExtra("panduan", panduan)
            context.startActivity(intent)
        }
    }
}

class PanduanViewHolder (view: View): RecyclerView.ViewHolder(view) {
    val panduanTitle = view.panduan_title
    val panduanContent = view.panduan_content
    val panduanThumbnail = view.panduan_thumbnail
}