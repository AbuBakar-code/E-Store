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
import com.example.estore.databinding.ViewholderSizeBinding

class SizeAdapter(val items: MutableList<String>): RecyclerView.Adapter<SizeAdapter.ColorViewHolder>() {
    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeAdapter.ColorViewHolder {
        context = parent.context
        val binding = ViewholderSizeBinding.inflate(LayoutInflater.from(context), parent, false)
        return ColorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SizeAdapter.ColorViewHolder, position: Int) {

        holder.binding.sizeTxt.text = items[position]

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

            if (selectedPosition == position){
                holder.binding.sizeLayout.setBackgroundResource(R.drawable.grey_bg_selected)
                holder.binding.sizeTxt.setTextColor(context.resources.getColor(R.color.purple))
            }
            else{
                holder.binding.sizeLayout.setBackgroundResource(R.drawable.grey_bg)
                holder.binding.sizeTxt.setTextColor(context.resources.getColor(R.color.black))

            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ColorViewHolder(val binding: ViewholderSizeBinding): RecyclerView.ViewHolder(binding.root)
}