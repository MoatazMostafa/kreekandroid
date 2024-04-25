package com.kreek.kreekandroid.ui.features.host

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kreek.kreekandroid.common.manager.navigation.KreekNavDestination
import com.kreek.kreekandroid.ui.features.authentication.AuthenticationScreen
import com.kreek.kreekandroid.ui.features.authentication.AuthenticationViewModel
import com.kreek.kreekandroid.ui.features.chatroom.ChatRoomScreen
import com.kreek.kreekandroid.ui.features.chatroom.ChatRoomViewModel
import com.kreek.kreekandroid.ui.features.home.HomeScreen
import com.kreek.kreekandroid.ui.features.home.HomeViewModel
import com.kreek.kreekandroid.ui.features.patientinfo.PatientInfoScreen
import com.kreek.kreekandroid.ui.features.patientinfo.PatientInfoViewModel
import com.kreek.kreekandroid.ui.features.selectdoctorchat.SelectDoctorChatScreen
import com.kreek.kreekandroid.ui.features.selectdoctorchat.SelectDoctorChatViewModel
import com.kreek.kreekandroid.ui.features.splash.SplashScreen
import com.kreek.kreekandroid.ui.features.splash.SplashViewModel
import com.kreek.kreekandroid.ui.shared.composables.TopAppBar
import com.kreek.kreekandroid.ui.shared.uimodel.TopBarAction
import com.kreek.kreekandroid.ui.shared.uimodel.TopBarProperties
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {

    var topBarProperties by remember { mutableStateOf(TopBarProperties(showTopBar = false)) }

    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .navigationBarsPadding(),
        topBar = {
            TopAppBar(
                showTopBar = topBarProperties.showTopBar,
                showKreekLogo = topBarProperties.showKreekLogo,
                showBackButton = topBarProperties.showBackButton,
                topBarActionList = topBarProperties.topBarActionList,
                onTopBarActionClick = topBarProperties.topBarActionClick,
                onBackButtonClick = topBarProperties.backButtonClick,
            )
        }
    )
    { innerPadding ->
        Column(modifier = modifier.zIndex(100f)) {
            NavHost(
                navController = navController,
                startDestination = KreekNavDestination.Splash.navComposableDestination
            ) {
                composable(route = KreekNavDestination.Splash.navComposableDestination) {
                    val splashViewModel: SplashViewModel = koinViewModel<SplashViewModel>().apply {
                        updateNavController(navController = navController)
                        parametersOf(navController)
                    }

                    topBarProperties = TopBarProperties(showTopBar = false)
                    SplashScreen(
                        modifier = Modifier.paddedModifier(
                            innerPaddingValues = innerPadding,
                            showToolbar = false,
                        ),
                        splashViewModel = splashViewModel
                    )
                }
                composable(route = KreekNavDestination.Authentication.navComposableDestination) {
                    val authenticationViewModel: AuthenticationViewModel = koinViewModel<AuthenticationViewModel>().apply {
                        updateNavController(navController = navController)
                        parametersOf(navController)
                    }

                    topBarProperties = TopBarProperties(showTopBar = false)
                    AuthenticationScreen(
                        modifier = Modifier.paddedModifier(
                            innerPaddingValues = innerPadding,
                            showToolbar = false,
                        ),
                        authenticationViewModel = authenticationViewModel
                    )
                }
                composable(route = KreekNavDestination.Home.navComposableDestination) {
                    topBarProperties = TopBarProperties(
                        showTopBar = true,
                        showKreekLogo = true,
                        showBackButton = false,
                        topBarActionList = listOf(
                            TopBarAction.Profile,
                        ),
                        topBarActionClick = {
                            //TODO handle topBar button click
                            when (it) {
                                TopBarAction.Search -> {
                                    //TODO navigate to menu screen
                                }
                                TopBarAction.Profile -> {
                                    navController.navigate(KreekNavDestination.SelectDoctorChat.navComposableDestination)
                                }
                                TopBarAction.Menu -> {
                                    //TODO navigate to menu screen
                                }
                                else -> {
                                }
                            }
                        }
                    )

                    val homeViewModel: HomeViewModel = koinViewModel<HomeViewModel>().apply {
                        updateNavController(navController = navController)
                        parametersOf(navController)
                    }

                    HomeScreen(
                        modifier = Modifier.paddedModifier(
                            innerPaddingValues = innerPadding,
                            showToolbar = true,
                        ),
                        homeViewModel = homeViewModel
                    )
                }

                composable(route = KreekNavDestination.ChatRoom.navComposableDestination)
                { backStackEntry ->
                    topBarProperties = TopBarProperties(
                        showTopBar = true,
                        showKreekLogo = false,
                        showBackButton = true,
                        backButtonClick = { navController.popBackStack() },
                        topBarActionList = listOf(),
                        topBarActionClick = {
                            //TODO handle topBar button click
                        }
                    )

                    val patientChatRoomViewModel: ChatRoomViewModel =
                        koinViewModel<ChatRoomViewModel> {
                            parametersOf(backStackEntry.arguments)
                        }.apply {
                            updateNavController(navController = navController)
                            parametersOf(navController)
                        }

                    ChatRoomScreen(
                        modifier = Modifier.paddedModifier(
                            innerPaddingValues = innerPadding,
                            showToolbar = true,
                        ),
                        patientChatRoomViewModel = patientChatRoomViewModel
                    )
                }

                composable(route = KreekNavDestination.PatientInfo.navComposableDestination)
                { backStackEntry ->
                    topBarProperties = TopBarProperties(
                        showTopBar = true,
                        showKreekLogo = false,
                        showBackButton = true,
                        topBarActionList = listOf(TopBarAction.Menu),
                        backButtonClick = { navController.popBackStack() },
                        topBarActionClick = {

                        }
                    )

                    val patientInfoViewModel: PatientInfoViewModel =
                        koinViewModel<PatientInfoViewModel> {
                            parametersOf(backStackEntry.arguments)
                        }.apply {
                            updateNavController(navController = navController)
                            parametersOf(navController)
                        }

                    PatientInfoScreen(
                        modifier = Modifier.paddedModifier(
                            innerPaddingValues = innerPadding,
                            showToolbar = true,
                        ),
                        patientInfoViewModel = patientInfoViewModel
                    )
                }

                composable(route = KreekNavDestination.SelectDoctorChat.navComposableDestination) {
                    topBarProperties = TopBarProperties(
                        showTopBar = true,
                        showKreekLogo = false,
                        showBackButton = true,
                        backButtonClick = { navController.popBackStack() },
                        topBarActionList = listOf(),
                        topBarActionClick = {
                            //TODO handle topBar button click
                        }
                    )

                    val selectDoctorChatViewModel: SelectDoctorChatViewModel = koinViewModel<SelectDoctorChatViewModel>().apply {
                        updateNavController(navController = navController)
                        parametersOf(navController)
                    }

                    SelectDoctorChatScreen(
                        modifier = Modifier.paddedModifier(
                            innerPaddingValues = innerPadding,
                            showToolbar = true,
                        ),
                        selectDoctorChatViewModel = selectDoctorChatViewModel
                    )
                }

            }
        }
    }
}

private fun Modifier.paddedModifier(
    innerPaddingValues: PaddingValues,
    showToolbar: Boolean
): Modifier {
    val padding = PaddingValues(
        start = 16.dp,
        end = 16.dp,
        top = if (showToolbar) innerPaddingValues.calculateTopPadding() else 0.dp,
    )
    return then(Modifier.padding(padding))
}

