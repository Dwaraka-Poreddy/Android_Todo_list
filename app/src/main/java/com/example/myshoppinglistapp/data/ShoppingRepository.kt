package com.example.myshoppinglistapp.data

import com.example.myshoppinglistapp.model.ShoppingItem

object ShoppingRepository {
    val preloadedItems = listOf(
        ShoppingItem(0, "Milk", 2),
        ShoppingItem(1, "Bread", 1),
        ShoppingItem(2, "Eggs", 12),
        ShoppingItem(3, "Butter", 1),
        ShoppingItem(4, "Cheese", 1),
        ShoppingItem(5, "Apples", 6),
        ShoppingItem(6, "Bananas", 6),
        ShoppingItem(7, "Chicken", 1),
        ShoppingItem(8, "Rice", 1),
        ShoppingItem(9, "Tomatoes", 4)
    )
}
