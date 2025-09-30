package com.example.cleanarchitecturecomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cleanarchitecturecomposeapp.ui.theme.CleanArchitectureComposeAppTheme
import com.example.presentation_common.navigation.NavRoutes
import com.example.presentation_post.list.PostListScreen
import com.example.presentation_post.single.PostScreen
import com.example.presentation_user.UserScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CleanArchitectureComposeAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    App(navController)
                }
            }
        }
    }
}

@Composable
fun App(navController: NavHostController) {
    NavHost(navController, startDestination = NavRoutes.Posts.route) {
        composable(route = NavRoutes.Posts.route) {
            PostListScreen(hiltViewModel(),navController)
        }
        composable(
            route = NavRoutes.Post.route,
            arguments = NavRoutes.Post.arguments
        ) {
            PostScreen(
                hiltViewModel(),
                NavRoutes.Post.fromEntry(it)
            )
        }
        composable(
            route = NavRoutes.User.route,
            arguments = NavRoutes.User.arguments
        ) {
            UserScreen(
                hiltViewModel(),
                NavRoutes.User.fromEntry(it)
            )
        }
    }
}
