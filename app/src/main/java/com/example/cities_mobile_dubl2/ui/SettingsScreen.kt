package com.example.cities_mobile_dubl2.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cities_mobile_dubl2.R
import com.example.cities_mobile_dubl2.constants.SECOND_SCREEN_ROUTE
import com.example.cities_mobile_dubl2.viewmodel.WeatherViewModel







enum class TemperatureUnit {
    CELSIUS, FAHRENHEIT
}


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun SettingsScreen(navController: NavHostController, viewModel: WeatherViewModel = viewModel()) {
    val weatherList = viewModel.weatherData.value



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var selectedTemperatureUnit by remember { mutableStateOf(TemperatureUnit.CELSIUS) }

        


        Text(
            text = "Choose in which temperature unit you would like to access the data",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .wrapContentSize(Alignment.Center)

        )

        ToggleSwitch(
            selectedUnit = selectedTemperatureUnit,
            onUnitSelected = { unit -> WeatherViewModel.setSelectedTemperatureUnit(unit) }
        )


        Spacer(modifier = Modifier.height(35.dp))

        Button(
            onClick = {
                navController.navigate(SECOND_SCREEN_ROUTE)
            },
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),

            ) {
            Text(text = "Go Back")
        }

    }
}


@Composable
fun ToggleSwitch(
    selectedUnit: TemperatureUnit,
    onUnitSelected: (TemperatureUnit) -> Unit
) {
    val backgroundColor = MaterialTheme.colorScheme.background
    val selectedColor = colorResource(id = R.color.teal_200)
    val unselectedColor = colorResource(id = R.color.black)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(backgroundColor)
                .clip(RoundedCornerShape(16.dp))
                .shadow(4.dp)
                .padding(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clipToBounds()
                    .background(if (selectedUnit == TemperatureUnit.CELSIUS) selectedColor else unselectedColor)
                    .clickable { onUnitSelected(TemperatureUnit.CELSIUS) }
                    .padding(4.dp)
            ) {
                Text(
                    text = "Celsius",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp),
                    color = if (selectedUnit == TemperatureUnit.CELSIUS) MaterialTheme.colorScheme.onBackground else colorResource(id = R.color.white)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clipToBounds()
                    .background(if (selectedUnit == TemperatureUnit.FAHRENHEIT) selectedColor else unselectedColor)
                    .clickable { onUnitSelected(TemperatureUnit.FAHRENHEIT) }
                    .padding(4.dp)
            ) {
                Text(
                    text = "Fahrenheit",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp),
                    color = if (selectedUnit == TemperatureUnit.FAHRENHEIT) MaterialTheme.colorScheme.onBackground else colorResource(id = R.color.white)
                )
            }
        }
    }
}
