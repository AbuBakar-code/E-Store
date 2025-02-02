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
import com.example.estore.databinding.ViewholderColorBinding

class ColorAdapter(val items: MutableList<String>): RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {
    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorAdapter.ColorViewHolder {
        context = parent.context
        val binding = ViewholderColorBinding.inflate(LayoutInflater.from(context), parent, false)
        return ColorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorAdapter.ColorViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(items[position])
            .into(holder.binding.pic)
        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

            if (selectedPosition == position){
                holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg_selected)
            }
            else{
                holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ColorViewHolder(val binding: ViewholderColorBinding): RecyclerView.ViewHolder(binding.root)
}