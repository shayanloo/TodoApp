package com.example.todoapp.features.feature_todo.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val defaultEmojis = listOf("\uD83D\uDCCB", "\u2B50", "\uD83C\uDFE0", "\u2764\uFE0F", "\uD83D\uDCBC", "\uD83C\uDFCB\u200D♂️", "\uD83D\uDCA7", "\uD83D\uDED2", "\uD83C\uDFAF", "\uD83D\uDCDA", "\uD83D\uDCDD", "\uD83C\uDFB5", "\uD83C\uDF4E", "\uD83D\uDE97", "\uD83D\uDCA1", "\uD83C\uDF1F", "\uD83C\uDF89", "\uD83D\uDEE0️", "\uD83E\uDDF9", "\uD83E\uDD8A", "\uD83D\uDECF️", "\uD83E\uDDD1\u200D\uD83D\uDCBB", "\uD83D\uDCC5", "\uD83D\uDCC8", "\uD83E\uDDFA", "\uD83C\uDF7D️", "\uD83E\uDDEA", "\uD83E\uDDF6", "\uD83E\uDDE2", "\uD83C\uDFAE", "\uD83C\uDFAC", "\uD83C\uDFA8")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddListBottomSheet(
    show: Boolean,
    onDismiss: () -> Unit,
    onAdd: (String, String) -> Unit
) {
    if (show) {
        var title by remember { mutableStateOf("") }
        var selectedEmoji by remember { mutableStateOf(defaultEmojis.first()) }
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            containerColor = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("ایجاد لیست جدید", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("عنوان لیست") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow {
                    items(defaultEmojis.size) { index ->
                        val emoji = defaultEmojis[index]
                        Text(
                            text = emoji,
                            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                            modifier = Modifier
                                .padding(4.dp)
                                .clickable { selectedEmoji = emoji }
                                .background(
                                    if (selectedEmoji == emoji) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f) else Color.Transparent,
                                    shape = MaterialTheme.shapes.small
                                )
                                .padding(8.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(onClick = onDismiss) {
                        Text("انصراف")
                    }
                    Button(
                        onClick = { if (title.isNotBlank()) onAdd(title, selectedEmoji) },
                        enabled = title.isNotBlank()
                    ) {
                        Text("افزودن")
                    }
                }
            }
        }
    }
} 