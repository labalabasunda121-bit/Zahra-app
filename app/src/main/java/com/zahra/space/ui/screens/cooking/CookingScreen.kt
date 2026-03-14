package com.zahra.space.ui.screens.cooking

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CookingScreen(navController: NavController) {
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
                text = "🍳 Dapur Zahra",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Gold60
            )
            
            IconButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Recipe List
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(recipes) { recipe ->
                RecipeCard(recipe = recipe)
            }
        }
    }
}

data class Recipe(
    val id: Int,
    val name: String,
    val description: String,
    val emoji: String,
    val cookingTime: Int,
    val difficulty: String
)

val recipes = listOf(
    Recipe(1, "Nasi Goreng", "Nasi goreng spesial", "🍚", 15, "Mudah"),
    Recipe(2, "Mie Goreng", "Mie goreng jawa", "🍜", 10, "Mudah"),
    Recipe(3, "Ayam Bakar", "Ayam bakar madu", "🍗", 30, "Sedang"),
    Recipe(4, "Rendang", "Rendang padang", "🥘", 120, "Sulit")
)

@Composable
fun RecipeCard(recipe: Recipe) {
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
                    .size(60.dp)
                    .background(Pink60.copy(alpha = 0.2f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(recipe.emoji, fontSize = 32.sp)
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = SoftBrown
                )
                Text(
                    text = recipe.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = SoftBrown.copy(alpha = 0.7f)
                )
                
                Row(
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Chip(
                        onClick = {},
                        colors = ChipDefaults.chipColors(
                            containerColor = Pink60.copy(alpha = 0.2f)
                        )
                    ) {
                        Text("⏱️ ${recipe.cookingTime} menit", fontSize = 10.sp)
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Chip(
                        onClick = {},
                        colors = ChipDefaults.chipColors(
                            containerColor = Gold60.copy(alpha = 0.2f)
                        )
                    ) {
                        Text(recipe.difficulty, fontSize = 10.sp)
                    }
                }
            }
            
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = SoftBrown)
        }
    }
}
