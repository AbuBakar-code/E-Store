package com.example.estore.helper

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.estore.model.ItemsModel


class ManagmentCart(val context: Context) {

    private val TAG = "ManagementCart"
    private val tinyDB = TinyDB(context)

    fun insertItem(item: ItemsModel) {
        var listItem = getListCart()
        val existAlready = listItem.any { it.title == item.title }
        val index = listItem.indexOfFirst { it.title == item.title }

        if (existAlready) {
            listItem[index].numberInCart = item.numberInCart
        } else {
            listItem.add(item)
        }
        tinyDB.putListObject("CartList", listItem)
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Item inserted: $item") // Add a log statement to track inserted item
    }

    fun getListCart(): java.util.ArrayList<ItemsModel> {
        val listItem =  tinyDB.getListObject("CartList") ?: arrayListOf()
        Log.d(TAG, "Cart items retrieved: $listItem")
        return listItem
    }

    fun minusItem(listItem: ArrayList<ItemsModel>, position: Int, listener: ChangeNumberItemsListener) {
        if (listItem[position].numberInCart == 1) {
            listItem.removeAt(position)
        } else {
            listItem[position].numberInCart--
        }
        tinyDB.putListObject("CartList", listItem)
        listener.onChanged()
    }

    fun plusItem(listItem: ArrayList<ItemsModel>, position: Int, listener: ChangeNumberItemsListener) {
        listItem[position].numberInCart++
        tinyDB.putListObject("CartList", listItem)
        listener.onChanged()
    }

    fun getTotalFee(): Double {
        val listItem = getListCart()
        var fee = 0.0
        for (item in listItem) {
            fee += item.price * item.numberInCart
        }
        return fee
    }
}