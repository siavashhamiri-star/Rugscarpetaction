package com.example.farshbazar.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.farshbazar.ui.screens.*
import com.example.farshbazar.ui.viewmodel.MainViewModel

sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomNavItem("home", "Home", Icons.Default.Home)
    object Vendors : BottomNavItem("vendors", "Vendors", Icons.Default.Storefront)
    object Ecosystem : BottomNavItem("ecosystem", "Ecosystem", Icons.Default.Hub)
    object Manifesto : BottomNavItem("manifesto", "Vision", Icons.Default.MenuBook)
    object Account : BottomNavItem("account", "Account", Icons.Default.Person)
}

val bottomNavItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.Vendors,
    BottomNavItem.Ecosystem,
    BottomNavItem.Manifesto,
    BottomNavItem.Account
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppNavigation(
    viewModel: MainViewModel,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Farsh Bazaar | فرش بازار") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                actions = {
                    IconButton(onClick = { navController.navigate("feedback") }) {
                        Icon(
                            imageVector = Icons.Default.Comment,
                            contentDescription = "Feedback",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    IconButton(onClick = { navController.navigate("lingoview") }) {
                        Icon(
                            imageVector = Icons.Default.Translate,
                            contentDescription = "LingoView",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                bottomNavItems.forEach { item ->
                    val selected = currentRoute == item.route
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route) {
                HomeScreen(
                    viewModel = viewModel,
                    onNavigateToCarpetDetail = { carpetId, vendorId ->
                        navController.navigate("carpet_detail/$carpetId/$vendorId")
                    },
                    onNavigateToVendors = {
                        navController.navigate(BottomNavItem.Vendors.route)
                    },
                    onNavigateToEcosystem = {
                        navController.navigate(BottomNavItem.Ecosystem.route)
                    },
                    onNavigateToManifesto = {
                        navController.navigate(BottomNavItem.Manifesto.route)
                    },
                    onNavigateToCollaboration = {
                        navController.navigate("collaboration")
                    },
                    onNavigateToLingoView = {
                        navController.navigate("lingoview")
                    }
                )
            }

            composable(BottomNavItem.Vendors.route) {
                VendorsScreen(
                    viewModel = viewModel,
                    onNavigateToVendorDetail = { vendorId ->
                        navController.navigate("vendor_detail/$vendorId")
                    },
                    onNavigateToBecomeVendor = {
                        navController.navigate("become_vendor")
                    }
                )
            }

            composable(BottomNavItem.Ecosystem.route) {
                EcosystemScreen()
            }

            composable(BottomNavItem.Manifesto.route) {
                ManifestoScreen()
            }

            composable(BottomNavItem.Account.route) {
                AccountScreen(
                    viewModel = viewModel,
                    onNavigateToVendorDetail = { vendorId ->
                        navController.navigate("vendor_detail/$vendorId")
                    },
                    onNavigateToBecomeVendor = {
                        navController.navigate("become_vendor")
                    },
                    onNavigateToAbout = {
                        navController.navigate("about")
                    },
                    onNavigateToFeedback = {
                        navController.navigate("feedback")
                    }
                )
            }

            // Detailed Routes
            composable(
                route = "vendor_detail/{vendorId}",
                arguments = listOf(navArgument("vendorId") { type = NavType.StringType })
            ) { backStackEntry ->
                val vendorId = backStackEntry.arguments?.getString("vendorId") ?: ""
                VendorDetailScreen(
                    vendorId = vendorId,
                    viewModel = viewModel,
                    onNavigateToCarpetDetail = { carpetId, vId ->
                        navController.navigate("carpet_detail/$carpetId/$vId")
                    },
                    onNavigateToAddCarpet = { vId ->
                        navController.navigate("add_carpet/$vId")
                    },
                    onNavigateToEditVendor = { vId ->
                        navController.navigate("edit_vendor/$vId")
                    },
                    onBack = { navController.popBackStack() }
                )
            }

            composable(
                route = "carpet_detail/{carpetId}/{vendorId}",
                arguments = listOf(
                    navArgument("carpetId") { type = NavType.StringType },
                    navArgument("vendorId") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val carpetId = backStackEntry.arguments?.getString("carpetId") ?: ""
                val vendorId = backStackEntry.arguments?.getString("vendorId") ?: ""
                CarpetDetailScreen(
                    carpetId = carpetId,
                    vendorId = vendorId,
                    viewModel = viewModel,
                    onNavigateToEditCarpet = { cId, vId ->
                        navController.navigate("edit_carpet/$cId/$vId")
                    },
                    onNavigateToVendorDetail = { vId ->
                        navController.navigate("vendor_detail/$vId")
                    },
                    onBack = { navController.popBackStack() }
                )
            }

            composable(
                route = "add_carpet/{vendorId}",
                arguments = listOf(navArgument("vendorId") { type = NavType.StringType })
            ) { backStackEntry ->
                val vendorId = backStackEntry.arguments?.getString("vendorId") ?: ""
                AddEditCarpetScreen(
                    carpetId = null,
                    vendorId = vendorId,
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() }
                )
            }

            composable(
                route = "edit_carpet/{carpetId}/{vendorId}",
                arguments = listOf(
                    navArgument("carpetId") { type = NavType.StringType },
                    navArgument("vendorId") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val carpetId = backStackEntry.arguments?.getString("carpetId") ?: ""
                val vendorId = backStackEntry.arguments?.getString("vendorId") ?: ""
                AddEditCarpetScreen(
                    carpetId = carpetId,
                    vendorId = vendorId,
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() }
                )
            }

            composable("become_vendor") {
                AddEditVendorScreen(
                    vendorId = null,
                    viewModel = viewModel,
                    onNavigateToVendorDetail = { vId ->
                        navController.navigate("vendor_detail/$vId") {
                            popUpTo(BottomNavItem.Vendors.route)
                        }
                    },
                    onBack = { navController.popBackStack() }
                )
            }

            composable(
                route = "edit_vendor/{vendorId}",
                arguments = listOf(navArgument("vendorId") { type = NavType.StringType })
            ) { backStackEntry ->
                val vendorId = backStackEntry.arguments?.getString("vendorId") ?: ""
                AddEditVendorScreen(
                    vendorId = vendorId,
                    viewModel = viewModel,
                    onNavigateToVendorDetail = { vId ->
                        navController.popBackStack()
                    },
                    onBack = { navController.popBackStack() }
                )
            }

            composable("about") {
                AboutScreen(
                    onNavigateToManifesto = {
                        navController.navigate(BottomNavItem.Manifesto.route)
                    },
                    onNavigateToCollaboration = {
                        navController.navigate("collaboration")
                    }
                )
            }

            composable("collaboration") {
                CollaborationScreen()
            }

            composable("lingoview") {
                LingoViewScreen()
            }

            composable("feedback") {
                FeedbackScreen(viewModel = viewModel)
            }
        }
    }
}
