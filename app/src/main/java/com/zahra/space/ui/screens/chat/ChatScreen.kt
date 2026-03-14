package com.zahra.space.ui.screens.chat

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ChatScreen(navController: NavController) {
    var messages by remember { mutableStateOf(messagesList) }
    var inputText by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Color.Green)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Fjr Online",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Green
                )
            }
            
            Text(
                text = "💬 Chat",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Gold60
            )
            
            Spacer(modifier = Modifier.width(40.dp))
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Messages
        LazyColumn(
            modifier = Modifier.weight(1f),
            reverseLayout = true,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(messages.reversed()) { message ->
                MessageBubble(message = message)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Input
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.3f)
                )
            ) {
                TextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    placeholder = { Text("Ketik pesan...") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            Spacer(modifier = Modifier.width(8.dp))
            
            IconButton(
                onClick = {
                    if (inputText.isNotBlank()) {
                        val newMessage = Message(
                            id = messages.size + 1,
                            text = inputText,
                            isMe = true,
                            time = Date()
                        )
                        messages = listOf(newMessage) + messages
                        inputText = ""
                    }
                },
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Gold60)
            ) {
                Icon(Icons.Default.Send, contentDescription = "Send", tint = Color.White)
            }
        }
    }
}

data class Message(
    val id: Int,
    val text: String,
    val isMe: Boolean,
    val time: Date
)

val messagesList = listOf(
    Message(1, "Assalamu'alaikum! 👋", false, Date()),
    Message(2, "Wa'alaikumussalam! 😊", true, Date()),
    Message(3, "Gimana harimu?", false, Date()),
    Message(4, "Alhamdulillah baik, kamu?", true, Date())
)

@Composable
fun MessageBubble(message: Message) {
    val timeFormat = remember { SimpleDateFormat("HH:mm", Locale("id")) }
    
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isMe) Arrangement.End else Arrangement.Start
    ) {
        Card(
            modifier = Modifier.widthIn(max = 250.dp),
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = if (message.isMe) 16.dp else 4.dp,
                bottomEnd = if (message.isMe) 4.dp else 16.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = if (message.isMe) Pink60.copy(alpha = 0.3f) else Color.White.copy(alpha = 0.3f)
            )
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = message.text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = SoftBrown
                )
                Text(
                    text = timeFormat.format(message.time),
                    style = MaterialTheme.typography.bodySmall,
                    color = SoftBrown.copy(alpha = 0.5f),
                    fontSize = 8.sp,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}
