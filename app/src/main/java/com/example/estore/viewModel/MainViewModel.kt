package com.example.estore.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.estore.model.BrandModel
import com.example.estore.model.ItemsModel
import com.example.estore.model.SliderModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel(): ViewModel() {

    private val firebaseDatabase =  FirebaseDatabase.getInstance()
    private val _banner = MutableLiveData<List<SliderModel>>()
    private val _brands = MutableLiveData<MutableList<BrandModel>>()
    private val _popular = MutableLiveData<MutableList<ItemsModel>>()

    val banners: LiveData<List<SliderModel>> = _banner
    val brands: LiveData<MutableList<BrandModel>> = _brands
    val popular: LiveData<MutableList<ItemsModel>> = _popular


    fun loadBanner(){
        val ref = firebaseDatabase.getReference("Banner")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()
                for (childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(SliderModel::class.java)
                    if (list != null){
                        lists.add(list)
                    }
                    _banner.value = lists
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun loadBrands(){
        val ref = firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<BrandModel>()
                for (childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(BrandModel::class.java)
                    if (list != null){
                        lists.add(list)
                    }
                }
                _brands.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun loadPopular(){
        val ref = firebaseDatabase.getReference("Items")
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                for (childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(ItemsModel::class.java)
                    if (list != null){
                        lists.add(list)
                    }
                }
                _popular.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}