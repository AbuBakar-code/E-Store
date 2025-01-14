package com.example.estore.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.estore.R
import com.example.estore.databinding.ViewholderBrandBinding
import com.example.estore.model.BrandModel

class BrandAdapter(val items: MutableList<BrandModel>): RecyclerView.Adapter<BrandAdapter.BrandViewHolder>() {
    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandAdapter.BrandViewHolder {
        context = parent.context
        val binding = ViewholderBrandBinding.inflate(LayoutInflater.from(context), parent, false)
        return BrandViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BrandAdapter.BrandViewHolder, position: Int) {
        val item = items[position]
        holder.binding.title.text = item.title
        Glide.with(holder.itemView.context).load(item.picURL).into(holder.binding.pic)
        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

            holder.binding.title.setTextColor(context.resources.getColor(R.color.white))
            if (selectedPosition == position){
                holder.binding.pic.setBackgroundResource(0)
                holder.binding.mainLayout.setBackgroundResource(R.drawable.purple_btn_bg)
                ImageViewCompat.setImageTintList(holder.binding.pic, ColorStateList.valueOf(context.getColor(R.color.white)))
                holder.binding.title.visibility = View.VISIBLE
            }
            else{
                holder.binding.pic.setBackgroundResource(R.drawable.grey_bg)
                holder.binding.mainLayout.setBackgroundResource(0)
                ImageViewCompat.setImageTintList(holder.binding.pic, ColorStateList.valueOf(context.getColor(R.color.black)))
                holder.binding.title.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class BrandViewHolder(val binding: ViewholderBrandBinding): RecyclerView.ViewHolder(binding.root)
}