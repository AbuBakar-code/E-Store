package com.example.estore.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.estore.adapter.BrandAdapter
import com.example.estore.adapter.PopularAdapter
import com.example.estore.adapter.SliderAdapter
import com.example.estore.databinding.ActivityMainBinding
import com.example.estore.model.SliderModel
import com.example.estore.viewModel.MainViewModel

class MainActivity : BaseActivity() {
    private val viewModel = MainViewModel()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()
        initBrands()
        initPopular()
        initBottomMenu()
    }

    private fun initBanner(){
        binding.progressBarBanner.visibility = View.VISIBLE
        viewModel.banners.observe(this, Observer{items ->
            banners(items)
            binding.progressBarBanner.visibility = View.GONE
        })
        viewModel.loadBanner()
    }

    private fun banners(images: List<SliderModel>){
        binding.viewPageSlider.adapter = SliderAdapter(images, binding.viewPageSlider)
        binding.viewPageSlider.clipToPadding = false
        binding.viewPageSlider.clipChildren = false
        binding.viewPageSlider.offscreenPageLimit = 3
        binding.viewPageSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewPageSlider.setPageTransformer(compositePageTransformer)

        if (images.size > 1){
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.viewPageSlider)
        }
    }

    private fun initBottomMenu(){
        binding.cartBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, CartActivity::class.java))
        }

    }

    private fun initBrands(){
        binding.progressBarBrand.visibility = View.VISIBLE
        viewModel.brands.observe(this, Observer{
            binding.recyclerViewBrand.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerViewBrand.adapter = BrandAdapter(it)
            binding.progressBarBrand.visibility = View.GONE
        })
        viewModel.loadBrands()
    }

    private fun initPopular(){
        binding.progressBarPopular.visibility = View.VISIBLE
        viewModel.popular.observe(this, Observer{
            binding.recyclerViewPopular.layoutManager = GridLayoutManager(this, 2)
            binding.recyclerViewPopular.adapter = PopularAdapter(it)
            binding.progressBarPopular.visibility = View.GONE
        })
        viewModel.loadPopular()
    }
}