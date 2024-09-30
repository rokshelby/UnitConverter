package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

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
fun GetMyBox(

    toExpanded : MutableState<Boolean>,
    inputUnit : MutableState<String>,
    inputValue : MutableState<String>,
    outputValue : MutableState<String>,
    outputUnit : MutableState<String>,
    conversionFactor : MutableState<Double>,
    oConversionFactor : MutableState<Double>,
    ifInPut : Boolean
)
{

    fun convertUnits(){
        //?: - elvis operator
        val inputValueDouble = inputValue.value.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0/oConversionFactor.value).roundToInt()/100.0
        outputValue.value = result.toString()
    }

    Box {
        Button(
            onClick = {toExpanded.value = true },
            modifier = Modifier.padding(2.5.dp)

        )


        {
            if(ifInPut){
                Text(text = inputUnit.value)
            }
            else{
                Text(text = outputUnit.value)
            }
            Icon(Icons.Default.ArrowDropDown,contentDescription = "Arrow Down")
        }
        DropdownMenu(expanded = toExpanded.value, onDismissRequest = {toExpanded.value = false}){
            DropdownMenuItem(text = {Text("Centimeters")}, onClick = {

                toExpanded.value = false
                if(ifInPut){
                    inputUnit.value = "Centimeters"
                    conversionFactor.value = 0.01
                }
                else{
                    outputUnit.value = "Centimeters"
                    oConversionFactor.value = 0.01
                }
                convertUnits()



            })
            DropdownMenuItem(text = {Text("Meters")}, onClick = {

                toExpanded.value = false

                if(ifInPut){
                    inputUnit.value = "Meters"
                    conversionFactor.value = 1.0
                }
                else{
                    outputUnit.value = "Meters"
                    oConversionFactor.value = 1.0
                }

                convertUnits()
                 })
            DropdownMenuItem(text = {Text("Feet")}, onClick = {


                toExpanded.value = false

                if(ifInPut){
                    inputUnit.value = "Feet"
                    conversionFactor.value = 0.3048
                }
                else{
                    outputUnit.value = "Feet"
                    oConversionFactor.value = 0.3048
                }
                convertUnits()
            })
            DropdownMenuItem(text = {Text("Millimeters")}, onClick = {


                toExpanded.value = false

                if(ifInPut){
                    inputUnit.value = "Millimeters"
                    conversionFactor.value = 0.001
                }
                else{
                    outputUnit.value = "Millimeters"
                    oConversionFactor.value = 0.001
                }

                convertUnits()
            })
        }
    }
}

@Composable
fun UnitConverter(){

    val inputValue = remember { mutableStateOf("")}
    val outputValue = remember { mutableStateOf("") }
    val inputUnit = remember { mutableStateOf("Meters")}
    val outputUnit = remember { mutableStateOf("Meters")}
    val iExpanded = remember { mutableStateOf(false)}
    val oExpanded = remember { mutableStateOf(false)}
    val conversionFactor = remember { mutableDoubleStateOf(1.00) }
    val oConversionFactor = remember { mutableDoubleStateOf(1.00) }

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Serif,
        fontSize = 32.sp,
        color = Color.DarkGray

    )


    fun convertUnits(){
        //?: - elvis operator
        val inputValueDouble = inputValue.value.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.doubleValue * 100.0/oConversionFactor.doubleValue).roundToInt()/100.0
        outputValue.value = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        //here all the ui elements will be stacked on each other
        Text("Unit Converter",
            style = customTextStyle
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue.value,
            onValueChange = {
                inputValue.value = it
                convertUnits()
            },
            label = {Text(text = "Enter ${inputUnit.value}")}

        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            GetMyBox(iExpanded,inputUnit,inputValue,outputValue,outputUnit, conversionFactor, oConversionFactor, true)
            GetMyBox(oExpanded,inputUnit,inputValue,outputValue,outputUnit, conversionFactor, oConversionFactor, false)
        }
        Spacer(modifier = Modifier.height(16.dp))

        //Result Text
        Text("Result: ${outputValue.value} ${outputUnit.value}",
                style = customTextStyle
        )
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}