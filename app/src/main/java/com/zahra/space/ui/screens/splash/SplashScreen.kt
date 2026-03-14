package com.zahra.space.ui.screens.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.zahra.space.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onTimeout: () -> Unit
) {
    var dots by remember { mutableStateOf("") }
    var quoteIndex by remember { mutableStateOf(0) }
    
    val quotes = listOf(
        "Setiap langkah kecil menuju kebaikan adalah investasi untuk dirimu",
        "Jadilah seperti bunga yang tetap mekar meski di tempat yang tak terlihat",
        "Disiplin adalah jembatan antara mimpi dan kenyataan",
        "Allah tidak membebani seseorang melainkan sesuai kesanggupannya"
    )
    
    LaunchedEffect(Unit) {
        while (true) {
            dots = when (dots) {
                "" -> "."
                "." -> ".."
                ".." -> "..."
                else -> ""
            }
            delay(500)
        }
    }
    
    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            quoteIndex = (quoteIndex + 1) % quotes.size
        }
    }
    
    LaunchedEffect(Unit) {
        delay(3000)
        onTimeout()
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Pink60,
                        Cream
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "🌙",
                fontSize = 80.sp,
                modifier = Modifier.padding(16.dp)
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "Zahra Space",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Gold60
            )
            
            Spacer(modifier = Modifier.height(48.dp))
            
            Text(
                text = "\"${quotes[quoteIndex]}\"",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp,
                color = SoftBrown,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Memuat",
                    style = MaterialTheme.typography.bodySmall,
                    color = SoftBrown.copy(alpha = 0.7f)
                )
                Text(
                    text = dots,
                    style = MaterialTheme.typography.bodySmall,
                    color = Gold60,
                    fontSize = 18.sp
                )
            }
        }
        
        Text(
            text = "v1.0 • untuk Zahra",
            style = MaterialTheme.typography.bodySmall,
            color = SoftBrown.copy(alpha = 0.5f),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        )
    }
}
