package com.example.myshoppinglistapp.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myshoppinglistapp.model.ShoppingItem

@Composable
fun ShoppingItemEditor(
    modifier: Modifier = Modifier,
    item: ShoppingItem,
    onEditComplete: (String, Int) -> Unit
) {
    var editedName by remember { mutableStateOf(item.name) }
    var editedQuantity by remember { mutableStateOf(item.quantity.toString()) }

    Row(
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth()
            .border(BorderStroke(2.dp, Color(0xFF02042E)), RoundedCornerShape(16.dp))
            .background(Color(0xFF02042E).copy(alpha = 0.3f), RoundedCornerShape(16.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = editedName,
            onValueChange = { editedName = it },
            singleLine = true,
            modifier = Modifier
                .weight(2f)
                .height(48.dp)
                .padding(horizontal = 8.dp, vertical = 2.dp),
            textStyle = TextStyle(fontSize = 12.sp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.White.copy(alpha = 0.7f),
                unfocusedIndicatorColor = Color.White.copy(alpha = 0.3f),
                disabledIndicatorColor = Color.Gray
            ),
            placeholder = { Text("Item Name", color = Color.White.copy(alpha = 0.5f)) }
        )
        OutlinedTextField(
            value = editedQuantity,
            onValueChange = { editedQuantity = it },
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .height(48.dp)
                .padding(horizontal = 8.dp, vertical = 2.dp),
            textStyle = TextStyle(fontSize = 12.sp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.White.copy(alpha = 0.7f),
                unfocusedIndicatorColor = Color.White.copy(alpha = 0.3f),
                disabledIndicatorColor = Color.Gray
            ),
            placeholder = { Text("Qty", color = Color.White.copy(alpha = 0.5f)) }
        )
        Button(
            onClick = { onEditComplete(editedName, editedQuantity.toIntOrNull() ?: 1) },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Save")
        }
    }
}
