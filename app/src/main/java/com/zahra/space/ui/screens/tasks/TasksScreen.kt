package com.zahra.space.ui.screens.tasks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TasksScreen(navController: NavController) {
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
            
            Text(
                text = "📋 Listku",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Gold60
            )
            
            IconButton(onClick = { /* Show add dialog */ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Task List
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(taskList) { task ->
                TaskCard(task = task)
            }
        }
    }
}

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val type: String,
    val progress: Int,
    val isCompleted: Boolean = false
)

val taskList = listOf(
    Task(1, "Tahajud", "Bangun jam 3 pagi", "tahajud", 60),
    Task(2, "Ngaji 1 Juz", "Khatam 30 hari", "ngaji", 30),
    Task(3, "Gym", "Push day", "gym", 75),
    Task(4, "Belajar Kotlin", "Compose", "belajar", 45)
)

@Composable
fun TaskCard(task: Task) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.3f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Gold60.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = when (task.type) {
                        "tahajud" -> "🌙"
                        "ngaji" -> "📖"
                        "gym" -> "💪"
                        else -> "✅"
                    },
                    fontSize = 24.sp
                )
            }
            
            Spacer(modifier = Modifier.width(12.dp))
            
            // Task info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = SoftBrown
                )
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = SoftBrown.copy(alpha = 0.7f)
                )
            }
            
            // Progress
            Text(
                text = "${task.progress}%",
                style = MaterialTheme.typography.bodySmall,
                color = Gold60
            )
        }
    }
}
