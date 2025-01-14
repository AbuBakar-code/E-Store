package com.example.estore.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.estore.R
import com.example.estore.adapter.CartAdapter
import com.example.estore.databinding.ActivityCartBinding
import com.example.estore.helper.ChangeNumberItemsListener
import com.example.estore.helper.ManagmentCart

class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managementCart: ManagmentCart
    private var tax: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagmentCart(this)

        setVariable()
        initCart()
        calculateCart()
    }

    private fun initCart(){
        binding.recyclerViewCart.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewCart.adapter = CartAdapter(managementCart.getListCart(), this, object : ChangeNumberItemsListener{
            override fun onChanged() {
                calculateCart()
            }
        })

        with(binding){
            emptyCartTxt.visibility = if (managementCart.getListCart().isEmpty()) View.VISIBLE else View.GONE
            scrollView2.visibility = if (managementCart.getListCart().isEmpty()) View.GONE else View.VISIBLE
        }
    }

    private fun setVariable(){
        binding.back.setOnClickListener { finish() }
    }

    private fun calculateCart(){
        val percentTax = 0.02
        val deliveryFee = 10.0
        tax = Math.round((managementCart.getTotalFee() * percentTax) * 100)/100.0
        val total = Math.round((managementCart.getTotalFee() + tax + deliveryFee) * 100)/100.0
        val itemTotal = Math.round(managementCart.getTotalFee()*100)/100.0

        with(binding){
            totalFeeTxt.text = "$$itemTotal"
            taxTxt.text = "$$tax"
            delevieryTxt.text = "$$deliveryFee"
            totalTxt.text = "$$total"
        }
    }
}