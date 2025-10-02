package com.example.myshoppinglistapp.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myshoppinglistapp.model.ShoppingItem
import com.example.myshoppinglistapp.ui.components.ShoppingItemEditor
import com.example.myshoppinglistapp.ui.components.ShoppingListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(modifier: Modifier = Modifier) {
    var sItems by remember { mutableStateOf(listOf<ShoppingItem>()) }
    var showDialog by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }
    var idTracker by remember { mutableIntStateOf(0) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Shopping List",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color(0xFF686afd).copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                    .padding(top = 16.dp, bottom = 70.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(sItems) { item ->
                        if (item.isEditing) {
                            ShoppingItemEditor(
                                item = item, onEditComplete = { editedName, editedQuantity ->
                                    sItems = sItems.map { it.copy(isEditing = false) }
                                    val editedItem = sItems.find { it.id == item.id }
                                    editedItem?.let {
                                        it.name = editedName
                                        it.quantity = editedQuantity
                                    }
                                }, modifier = modifier
                            )
                        } else {
                            ShoppingListItem(item = item, onEditClick = {
                                sItems = sItems.map { it.copy(isEditing = it.id == item.id) }
                            }, onDeleteClick = {
                                sItems = sItems - item
                            })
                        }
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(
                    Color(0xFF686afd).copy(alpha = 0.7f),
                    shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                )
        ) {
            Button(
                onClick = { showDialog = true },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF02042e))
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add Icon",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Add Item",
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }

    if (showDialog) {
        val focusRequester = remember { FocusRequester() }

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
        AlertDialog(
            onDismissRequest = { showDialog = false },
            containerColor = Color(0xFF02042E).copy(alpha = 0.9f),
            shape = RoundedCornerShape(16.dp),
            confirmButton = {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = {
                        if (itemName.isNotBlank()) {
                            val newItem = ShoppingItem(
                                id = idTracker,
                                name = itemName,
                                quantity = itemQuantity.toIntOrNull() ?: 1,
                                isEditing = false,
                            )
                            sItems = sItems + newItem
                            idTracker += 1
                            showDialog = false
                            itemName = ""
                            itemQuantity = "1"
                        }
                    }) {
                        Text("Add")
                    }

                    Button(onClick = {
                        showDialog = false
                    }) {
                        Text("Cancel")
                    }
                }
            },
            title = { Text("Add Shopping Item", color = Color.White) },
            text = {
                Column {
                    OutlinedTextField(
                        value = itemName,
                        onValueChange = { itemName = it },
                        singleLine = true,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .focusRequester(focusRequester),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = Color.White,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.White.copy(alpha = 0.7f),
                            unfocusedIndicatorColor = Color.White.copy(alpha = 0.3f),
                            disabledIndicatorColor = Color.Gray,
                        ),
                        placeholder = { Text("Item Name", color = Color.White.copy(alpha = 0.5f)) })
                    OutlinedTextField(
                        value = itemQuantity,
                        onValueChange = { itemQuantity = it },
                        singleLine = true,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = Color.White,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.White.copy(alpha = 0.7f),
                            unfocusedIndicatorColor = Color.White.copy(alpha = 0.3f),
                            disabledIndicatorColor = Color.Gray,
                        ),
                        placeholder = { Text("Quantity", color = Color.White.copy(alpha = 0.5f)) })
                }
            })
    }
}
