package com.zahra.space.ui.screens.pets

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PetsScreen(navController: NavController) {
    var selectedPet by remember { mutableStateOf(petsList.first()) }
    
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
                text = "🐾 Teman Virtualku",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Gold60
            )
            
            IconButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Selected Pet Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.3f)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = selectedPet.emoji,
                    fontSize = 80.sp
                )
                
                Text(
                    text = selectedPet.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = SoftBrown
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Stats
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    PetStat("🍖", "${selectedPet.hunger}%")
                    PetStat("😊", "${selectedPet.happiness}%")
                    PetStat("🧼", "${selectedPet.cleanliness}%")
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Action buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ActionButton(icon = "🍖", text = "Makan")
                    ActionButton(icon = "🎾", text = "Main")
                    ActionButton(icon = "🛁", text = "Bersih")
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Pet List
        Text(
            text = "Peliharaanku",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = SoftBrown
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(petsList) { pet ->
                PetThumbnail(
                    pet = pet,
                    isSelected = pet.id == selectedPet.id,
                    onClick = { selectedPet = pet }
                )
            }
        }
    }
}

data class Pet(
    val id: Int,
    val name: String,
    val emoji: String,
    val hunger: Int,
    val happiness: Int,
    val cleanliness: Int
)

val petsList = listOf(
    Pet(1, "Mimi", "🐱", 80, 90, 70),
    Pet(2, "Lulu", "🐰", 60, 85, 90),
    Pet(3, "Coco", "🐹", 70, 75, 80)
)

@Composable
fun PetStat(icon: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(icon, fontSize = 20.sp)
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            color = Gold60
        )
    }
}

@Composable
fun ActionButton(icon: String, text: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Pink60.copy(alpha = 0.3f)),
            contentAlignment = Alignment.Center
        ) {
            Text(icon, fontSize = 24.sp)
        }
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = SoftBrown
        )
    }
}

@Composable
fun PetThumbnail(
    pet: Pet,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .size(80.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Pink60.copy(alpha = 0.3f) else Color.White.copy(alpha = 0.3f)
        ),
        border = if (isSelected) BorderStroke(2.dp, Gold60) else null
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(pet.emoji, fontSize = 32.sp)
            Text(
                text = pet.name,
                style = MaterialTheme.typography.bodySmall,
                color = SoftBrown
            )
        }
    }
}
