package com.example.radiobutton

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.radiobutton.ui.theme.RadioButtonTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RadioButtonTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    CarConfiguratorApp()
                }
            }
        }
    }
}


@Composable
fun CarConfiguratorApp() {
    var selectedColor by rememberSaveable { mutableStateOf("Красный") }
    var selectedTrim by rememberSaveable { mutableStateOf("Базовая") }
    val scrollState = rememberScrollState()

    val carPrice = when (selectedTrim) {
        "Classic" -> 2_104_900
        "Comfort" -> 2_174_900
        "Luxe" -> 2_289_900
        "Style" -> 2_329_900
        else -> 0
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,

        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Конфигуратор авто",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Blue
        )
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(Color.LightGray, shape = RoundedCornerShape(16.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CarImage(selectedColor)
            Spacer(modifier = Modifier.height(16.dp))
            ColorSelection(selectedColor) { color -> selectedColor = color }
        }


        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(Color.LightGray, shape = RoundedCornerShape(16.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TrimSelection(selectedTrim) { trim -> selectedTrim = trim }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Стоимость: $carPrice USD",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Green
            )

        }
    }
}

@Composable
fun CarImage(color: String) {
    val imageRes = when (color) {
        "Белый" -> R.drawable.white_car
        "Красный" -> R.drawable.red_car
        "Синий" -> R.drawable.blue_car
        "Серый" -> R.drawable.gray_car
        "Черный" -> R.drawable.black_car
        else -> R.drawable.white_car
    }

    Image(
        painter = painterResource(id = imageRes),
        contentDescription = "Автомобиль",
        modifier = Modifier.size(200.dp)
    )
}

@Composable
fun ColorSelection(selectedColor: String, onColorSelected: (String) -> Unit) {
    Column {
        Text(text = "Выберите цвет:", style = MaterialTheme.typography.headlineSmall)
        Row(verticalAlignment = Alignment.CenterVertically) {
            ColorRadioButton("Белый", selectedColor, onColorSelected, Color.White)
            ColorRadioButton("Красный", selectedColor, onColorSelected, Color.Red)
            ColorRadioButton("Синий", selectedColor, onColorSelected, Color.Blue)
            ColorRadioButton("Серый", selectedColor, onColorSelected, Color.Gray)
            ColorRadioButton("Черный", selectedColor, onColorSelected, Color.Black)
        }
    }
}

@Composable
fun ColorRadioButton(
    color: String,
    selectedColor: String,
    onColorSelected: (String) -> Unit,
    colorValue: Color
) {
    RadioButton(
        selected = selectedColor == color,
        onClick = { onColorSelected(color) },
        colors = RadioButtonDefaults.colors(
            selectedColor = colorValue,
            unselectedColor = colorValue
        )
    )
}


@Composable
fun TrimSelection(selectedTrim: String, onTrimSelected: (String) -> Unit) {
    Column {
        Text(text = "Выберите комплектацию:", style = MaterialTheme.typography.headlineSmall)
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedTrim == "Classic",
                    onClick = { onTrimSelected("Classic") }
                )
                Text(text = "Classic", style = MaterialTheme.typography.bodyLarge)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedTrim == "Comfort",
                    onClick = { onTrimSelected("Comfort") }
                )
                Text(text = "Comfort", style = MaterialTheme.typography.bodyLarge)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedTrim == "Luxe",
                    onClick = { onTrimSelected("Luxe") }
                )
                Text(text = "Luxe", style = MaterialTheme.typography.bodyLarge)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedTrim == "Style",
                    onClick = { onTrimSelected("Style") }
                )
                Text(text = "Style", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
