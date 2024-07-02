package com.example.estore.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.estore.adapter.ColorAdapter
import com.example.estore.adapter.SizeAdapter
import com.example.estore.adapter.SliderAdapter
import com.example.estore.databinding.ActivityDetailBinding
import com.example.estore.helper.ManagmentCart
import com.example.estore.model.ItemsModel
import com.example.estore.model.SliderModel

class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private var numberOrder = 1
    private lateinit var managementCart: ManagmentCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagmentCart(this)

        getBundle()
        banners()
        initList()
    }

    private fun initList(){
        val sizeList = ArrayList<String>()
        for (item in item.size){
            sizeList.add(item)
        }
        binding.sizeList.adapter = SizeAdapter(sizeList)
        binding.sizeList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val colorList = ArrayList<String>()
        for (imageUrl in item.picUrl){
            colorList.add(imageUrl)
        }
        binding.colorList.adapter = ColorAdapter(colorList)
        binding.colorList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun banners(){
        val sliderItems = ArrayList<SliderModel>()
        for (imageUrl in item.picUrl){
            sliderItems.add(SliderModel(imageUrl))
        }
        binding.slider.adapter = SliderAdapter(sliderItems, binding.slider)
        binding.slider.clipToPadding = false
        binding.slider.clipChildren = false
        binding.slider.offscreenPageLimit = 3
        binding.slider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        if (sliderItems.size > 1){
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.slider)
        }
    }

    private fun getBundle(){
        item = intent.getParcelableExtra("Object")!!
        binding.titleTxt.text = item.title
        binding.descriptionTxt.text = item.description
        binding.priceTxt.text = "$" + item.price
        binding.ratingTxt.text = "${item.rating} Rating"
        binding.addToCartBtn.setOnClickListener {
            item.numberInCart = numberOrder
            managementCart.insertItem(item)
        }
        binding.back.setOnClickListener { finish() }
        binding.cartBtn.setOnClickListener{
            startActivity(Intent(this, CartActivity::class.java))
        }
    }
}