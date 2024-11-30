package com.example.tempo2.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.core.app.NotificationCompat.MessagingStyle.Message

@Composable
fun FirstTimeDialog(
    message: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { /* Evitamos el cierre al hacer clic fuera del diálogo */ },
        icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
        title = { Text(text = "Aviso de seguridad") },
        text = { Text(text = message) },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Entendido")
            }
        }
    )

}