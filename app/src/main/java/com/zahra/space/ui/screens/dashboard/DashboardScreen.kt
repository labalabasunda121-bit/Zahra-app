package com.zahra.space.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DashboardScreen(navController: NavController) {
    var currentTime by remember { mutableStateOf(Date()) }
    var greeting by remember { mutableStateOf("") }
    
    LaunchedEffect(Unit) {
        while (true) {
            currentTime = Date()
            val calendar = Calendar.getInstance()
            greeting = when (calendar.get(Calendar.HOUR_OF_DAY)) {
                in 0..10 -> "Selamat Pagi"
                in 11..14 -> "Selamat Siang"
                in 15..17 -> "Selamat Sore"
                else -> "Selamat Malam"
            }
            delay(1000)
        }
    }
    
    val dateFormat = remember { SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id")) }
    val timeFormat = remember { SimpleDateFormat("HH:mm", Locale("id")) }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Pink60, Cream)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.3f)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "$greeting,",
                            style = MaterialTheme.typography.bodyLarge,
                            color = SoftBrown,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Zahra",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = Gold60,
                            fontSize = 24.sp
                        )
                    }
                    
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = timeFormat.format(currentTime),
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = SoftBrown,
                            fontSize = 28.sp
                        )
                        Text(
                            text = dateFormat.format(currentTime),
                            style = MaterialTheme.typography.bodySmall,
                            color = SoftBrown.copy(alpha = 0.7f)
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Quote Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Pink60.copy(alpha = 0.2f)
                )
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.FormatQuote, contentDescription = null, tint = Gold60)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "\"Jadilah seperti bunga yang tetap mekar meski di tempat yang tak terlihat\"",
                        style = MaterialTheme.typography.bodyLarge,
                        color = SoftBrown,
                        fontSize = 14.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(Icons.Default.FormatQuote, contentDescription = null, tint = Gold60)
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Stats Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatCard(
                    icon = Icons.Default.CheckCircle,
                    value = "5/10",
                    label = "Selesai",
                    color = Gold60
                )
                StatCard(
                    icon = Icons.Default.AccountBalanceWallet,
                    value = "Rp 250rb",
                    label = "Tabungan",
                    color = Gold60
                )
                StatCard(
                    icon = Icons.Default.Whatshot,
                    value = "7",
                    label = "Hari",
                    color = Gold60
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Feature Grid
            Text(
                text = "Menu Utama",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = SoftBrown
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(features) { feature ->
                    FeatureCard(
                        feature = feature,
                        onClick = { navController.navigate(feature.route) },
                        theme = "pink"
                    )
                }
            }
        }
    }
}

val features = listOf(
    Feature("tasks", "📋", "Listku"),
    Feature("savings", "💰", "Tabungan"),
    Feature("pets", "🐾", "Hewan"),
    Feature("cooking", "🍳", "Masak"),
    Feature("chat", "💬", "Chat"),
    Feature("profile", "👤", "Profil")
)

data class Feature(val route: String, val emoji: String, val title: String)

@Composable
fun StatCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    value: String,
    label: String,
    color: Color
) {
    Card(
        modifier = Modifier.weight(1f),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.5f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = SoftBrown,
                fontSize = 16.sp
            )
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = SoftBrown.copy(alpha = 0.7f),
                fontSize = 10.sp
            )
        }
    }
}

@Composable
fun FeatureCard(
    feature: Feature,
    onClick: () -> Unit,
    theme: String
) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.3f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = feature.emoji, fontSize = 32.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = feature.title,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = SoftBrown
            )
        }
    }
}
