package com.example.myshoppinglistapp.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myshoppinglistapp.model.ShoppingItem

@Composable
fun ShoppingListItem(
    modifier: Modifier = Modifier,
    item: ShoppingItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth()
            .border(BorderStroke(2.dp, Color(0xFF02042E)), RoundedCornerShape(16.dp))
            .background(Color(0xFF02042E).copy(alpha = 0.3f), RoundedCornerShape(16.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = item.name, modifier = modifier.padding(8.dp))
        Text(text = "Qty: ${item.quantity}", modifier = modifier.padding(8.dp))
        Row(modifier = modifier.padding(8.dp)) {
            IconButton(onClick = onEditClick) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Btn")
            }
            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Btn")
            }
        }
    }
}
