package com.zahra.space.ui.screens.onboarding

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun OnboardingScreen(
    onComplete: () -> Unit
) {
    var step by remember { mutableStateOf(0) }
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf("pink") }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        if (selectedColor == "pink") Pink60 else Gold60,
                        Cream
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            // Progress indicator
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(3) { index ->
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(
                                if (index <= step) Gold60
                                else Gold60.copy(alpha = 0.3f)
                            )
                    )
                    if (index < 2) Spacer(modifier = Modifier.width(8.dp))
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            when (step) {
                0 -> Step1(
                    onNext = { step = 1 },
                    selectedColor = selectedColor
                )
                1 -> Step2(
                    name = name,
                    onNameChange = { name = it },
                    age = age,
                    onAgeChange = { age = it },
                    selectedColor = selectedColor,
                    onColorChange = { selectedColor = it },
                    onNext = { step = 2 },
                    onBack = { step = 0 }
                )
                2 -> Step3(
                    name = name,
                    selectedColor = selectedColor,
                    onComplete = onComplete
                )
            }
        }
    }
}

@Composable
fun Step1(
    onNext: () -> Unit,
    selectedColor: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "✨",
            fontSize = 80.sp,
            modifier = Modifier.padding(16.dp)
        )
        
        Text(
            text = "Assalamu'alaikum! 👋",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Gold60
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Selamat datang di Zahra Space.\nRuang pribadi untuk mencatat kebaikan,\nmenabung mimpi, dan tumbuh bersama.",
            style = MaterialTheme.typography.bodyLarge,
            color = SoftBrown,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(
            onClick = onNext,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedColor == "pink") Pink60 else Gold60
            ),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Mulai", color = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(Icons.Default.ArrowForward, contentDescription = null, tint = Color.White)
        }
    }
}

@Composable
fun Step2(
    name: String,
    onNameChange: (String) -> Unit,
    age: String,
    onAgeChange: (String) -> Unit,
    selectedColor: String,
    onColorChange: (String) -> Unit,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Kenalan dulu yuk! 💭",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Gold60
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Nama panggilan") },
            placeholder = { Text("Contoh: Zahra") },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = age,
            onValueChange = onAgeChange,
            label = { Text("Usia") },
            placeholder = { Text("Contoh: 22") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "Pilih warna favorit:",
            style = MaterialTheme.typography.bodyMedium,
            color = SoftBrown,
            modifier = Modifier.align(Alignment.Start)
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ColorOption(
                color = Pink60,
                isSelected = selectedColor == "pink",
                onClick = { onColorChange("pink") }
            )
            ColorOption(
                color = Gold60,
                isSelected = selectedColor == "gold",
                onClick = { onColorChange("gold") }
            )
            ColorOption(
                color = PurpleSoft,
                isSelected = selectedColor == "purple",
                onClick = { onColorChange("purple") }
            )
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
                Spacer(modifier = Modifier.width(4.dp))
                Text("Kembali")
            }
            
            Button(
                onClick = onNext,
                enabled = name.isNotBlank() && age.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedColor == "pink") Pink60 else Gold60
                )
            ) {
                Text("Lanjut")
                Spacer(modifier = Modifier.width(4.dp))
                Icon(Icons.Default.ArrowForward, contentDescription = null)
            }
        }
    }
}

@Composable
fun ColorOption(
    color: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(color)
            .clickable { onClick() }
            .then(
                if (isSelected) Modifier.border(
                    width = 3.dp,
                    color = Gold60,
                    shape = CircleShape
                ) else Modifier
            )
    )
}

@Composable
fun Step3(
    name: String,
    selectedColor: String,
    onComplete: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "🎉",
            fontSize = 80.sp,
            modifier = Modifier.padding(16.dp)
        )
        
        Text(
            text = "Selamat datang, $name!",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Gold60
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Profil kamu sudah siap.\nSekarang waktunya memulai perjalanan\nmenuju versi terbaik dirimu.",
            style = MaterialTheme.typography.bodyLarge,
            color = SoftBrown,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(
            onClick = onComplete,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedColor == "pink") Pink60 else Gold60
            ),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Mulai Jelajahi", color = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(Icons.Default.ArrowForward, contentDescription = null, tint = Color.White)
        }
    }
}
