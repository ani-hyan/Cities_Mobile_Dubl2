package com.example.cities_mobile_dubl2.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cities_mobile_dubl2.MainActivity
import com.example.cities_mobile_dubl2.constants.THIRD_SCREEN_ROUTE
import com.example.cities_mobile_dubl2.constants.WELCOME_SCREEN_ROUTE
import com.example.cities_mobile_dubl2.view.WeatherList
import com.example.cities_mobile_dubl2.viewmodel.WeatherViewModel

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun CitiesScreen(navController: NavHostController, activity: MainActivity, viewModel: WeatherViewModel = viewModel()) {
    val weatherList = viewModel.weatherData.value
    val selectedTemperatureUnit = viewModel.selectedTemperatureUnit.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        Button(
            onClick = {
                navController.navigate(THIRD_SCREEN_ROUTE)
            },
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Start)
        ) {
            Text(text = "Settings:")
        }

        Button(
            onClick = {
                navController.navigate(WELCOME_SCREEN_ROUTE)
            },
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Start)
        ) {
            Text(text = "Go Back")
        }

        if (weatherList != null) {
            WeatherList(weatherList = weatherList, viewModel = viewModel)
        }

        Spacer(modifier = Modifier.height(35.dp))




    }
}
