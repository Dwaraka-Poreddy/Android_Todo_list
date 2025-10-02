package com.example.myshoppinglistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import com.example.myshoppinglistapp.ui.ShoppingListScreen
import com.example.myshoppinglistapp.ui.theme.MyShoppingListAppTheme
import com.example.myshoppinglistapp.viewmodel.ShoppingListViewModel

class MainActivity : ComponentActivity() {
    private val shoppingListViewModel: ShoppingListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyShoppingListAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                horizontal = innerPadding.calculateLeftPadding(
                                    LocalLayoutDirection.current
                                )
                            )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.background), // put image in res/drawable
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        ShoppingListScreen(
                            Modifier.padding(innerPadding),
                            viewModel= shoppingListViewModel
                        )
                    }
                }
            }
        }
    }
}
