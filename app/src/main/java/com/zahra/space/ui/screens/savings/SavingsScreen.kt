package com.zahra.space.ui.screens.savings

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
import androidx.navigation.NavController
import java.text.NumberFormat
import java.util.*

@Composable
fun SavingsScreen(navController: NavController) {
    var savings by remember { mutableStateOf(savingsList) }
    
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
                text = "💰 Tabunganku",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Gold60
            )
            
            IconButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Total Savings Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.3f)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Total Tabungan",
                    style = MaterialTheme.typography.bodyLarge,
                    color = SoftBrown
                )
                Text(
                    text = "Rp ${NumberFormat.getNumberInstance(Locale("id", "ID")).format(1500000)}",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = Gold60
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Savings List
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(savings) { saving ->
                SavingCard(saving = saving)
            }
        }
    }
}

data class Saving(
    val id: Int,
    val name: String,
    val amount: Long,
    val target: Long,
    val icon: String
)

val savingsList = listOf(
    Saving(1, "Umroh", 500000, 2000000, "🕋"),
    Saving(2, "Laptop Baru", 750000, 5000000, "💻"),
    Saving(3, "Liburan", 250000, 1500000, "✈️")
)

@Composable
fun SavingCard(saving: Saving) {
    val progress = saving.amount.toFloat() / saving.target.toFloat()
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.3f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = saving.icon, fontSize = 24.sp, modifier = Modifier.padding(end = 8.dp))
                Text(
                    text = saving.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = SoftBrown
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Rp ${NumberFormat.getNumberInstance(Locale("id", "ID")).format(saving.amount)}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = Gold60
                )
                Text(
                    text = "target: Rp ${NumberFormat.getNumberInstance(Locale("id", "ID")).format(saving.target)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = SoftBrown.copy(alpha = 0.7f)
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Progress bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(SoftBrown.copy(alpha = 0.1f), RoundedCornerShape(4.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .fillMaxHeight()
                        .background(Gold60, RoundedCornerShape(4.dp))
                )
            }
        }
    }
}
