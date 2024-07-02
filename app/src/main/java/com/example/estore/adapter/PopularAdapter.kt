package com.example.estore.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.estore.activity.DetailActivity
import com.example.estore.databinding.ViewholderRecomendedBinding
import com.example.estore.model.ItemsModel

class PopularAdapter(val items: MutableList<ItemsModel>): RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {
    private var context: Context? = null
    class PopularViewHolder(val binding: ViewholderRecomendedBinding): RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        context = parent.context
        val binding = ViewholderRecomendedBinding.inflate(LayoutInflater.from(context), parent, false)
        return PopularViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.binding.titleTxt.text = items[position].title
        holder.binding.priceTxt.text = "$" + items[position].price.toString()
        holder.binding.ratingTxt.text = items[position].rating.toString()

        val requestOptions = RequestOptions().transform(CenterCrop())
        Glide.with(holder.itemView.context).load(items[position].picUrl[0]).apply(requestOptions).into(holder.binding.pic)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("Object", items[position])
            holder.itemView.context.startActivity(intent)
        }
    }
}