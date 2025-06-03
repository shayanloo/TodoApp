package com.example.todoapp.features.feature_todo.ui.component

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun InfoDialog(isFa: Boolean, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (isFa) "راهنمای این بخش" else "Section Guide") },
        text = { Text(if (isFa) "این بخش برای مدیریت و مشاهده اطلاعات مربوط به این قسمت است." else "This section is for managing and viewing information related to this part.") },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(if (isFa) "باشه" else "OK")
            }
        }
    )
} 