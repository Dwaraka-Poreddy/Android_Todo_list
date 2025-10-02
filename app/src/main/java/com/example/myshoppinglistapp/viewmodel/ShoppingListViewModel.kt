package com.example.myshoppinglistapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myshoppinglistapp.data.ShoppingRepository
import com.example.myshoppinglistapp.model.ShoppingItem

class ShoppingListViewModel : ViewModel() {

    private val _items = mutableStateListOf<ShoppingItem>().apply {
        addAll(ShoppingRepository.preloadedItems)
    }

    val items: List<ShoppingItem> get() = _items

    private var idTracker = ShoppingRepository.preloadedItems.size

    var isDialogVisible by mutableStateOf(false)
        private set

    fun showDialog() {
        isDialogVisible = true
    }

    fun hideDialog() {
        isDialogVisible = false
    }

    fun addItem(name: String, quantity: Int) {
        _items.add(ShoppingItem(idTracker++, name, quantity))
        hideDialog()
    }

    fun editItem(id: Int, newName: String, newQuantity: Int) {
        val index = _items.indexOfFirst { it.id == id }
        if (index >= 0) {
            _items[index] = _items[index].copy(name = newName, quantity = newQuantity, isEditing = false)
        }
    }

    fun deleteItem(id: Int) {
        _items.removeAll { it.id == id }
    }

    fun setEditing(id: Int) {
        _items.replaceAll { it.copy(isEditing = it.id == id) }
    }
}
