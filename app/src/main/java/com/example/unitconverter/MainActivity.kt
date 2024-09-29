package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}
@Composable()
fun GetMyBox(){
    Box {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(2.5.dp)
        )


        {
            Text(text = "Select")
            Icon(Icons.Default.ArrowDropDown,contentDescription = "Arrow Down")
        }
        DropdownMenu(expanded = true, onDismissRequest = {}){
            DropdownMenuItem(text = {Text("Centimeters")}, onClick = { /*TODO*/ })
            DropdownMenuItem(text = {Text("Meters")}, onClick = { /*TODO*/ })
            DropdownMenuItem(text = {Text("Feet")}, onClick = { /*TODO*/ })
            DropdownMenuItem(text = {Text("Millimeters")}, onClick = { /*TODO*/ })
        }
    }
}

@Composable
fun UnitConverter(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        //here all the ui elements will be stacked on each other
        Text("Unit Converter")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = {Text("myLabel")}
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            GetMyBox()
            GetMyBox()
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result:")
    }
}


@Preview(showBackground = true)
@Composable
fun unitConverterPreview() {
    UnitConverter()
}