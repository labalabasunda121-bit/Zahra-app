package com.zahra.space

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zahra.space.ui.screens.dashboard.DashboardScreen
import com.zahra.space.ui.screens.tasks.TasksScreen
import com.zahra.space.ui.screens.savings.SavingsScreen
import com.zahra.space.ui.screens.pets.PetsScreen
import com.zahra.space.ui.screens.cooking.CookingScreen
import com.zahra.space.ui.screens.chat.ChatScreen
import com.zahra.space.ui.screens.profile.ProfileScreen
import com.zahra.space.ui.screens.splash.SplashScreen
import com.zahra.space.ui.screens.onboarding.OnboardingScreen
import com.zahra.space.ui.theme.ZahraSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZahraSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "splash"
                    ) {
                        composable("splash") {
                            SplashScreen(
                                onTimeout = {
                                    navController.navigate("onboarding") {
                                        popUpTo("splash") { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable("onboarding") {
                            OnboardingScreen(
                                onComplete = {
                                    navController.navigate("dashboard") {
                                        popUpTo("onboarding") { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable("dashboard") { DashboardScreen(navController) }
                        composable("tasks") { TasksScreen(navController) }
                        composable("savings") { SavingsScreen(navController) }
                        composable("pets") { PetsScreen(navController) }
                        composable("cooking") { CookingScreen(navController) }
                        composable("chat") { ChatScreen(navController) }
                        composable("profile") { ProfileScreen(navController) }
                    }
                }
            }
        }
    }
}
