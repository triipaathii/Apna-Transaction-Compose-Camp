package com.example.apnatransaction

import android.graphics.Paint.Style
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apnatransaction.ui.theme.ApnaTransactionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApnaTransactionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ApnaTransaction()
                }
            }
        }
    }
}

@Composable
fun ApnaTransaction() {
    TransactionList()

}

@Composable
fun TransactionList(){
    LazyColumn{
        items(10){
            TransactionItem()
        }
    }
}

@Composable
fun TransactionItem(modifier: Modifier = Modifier) {
    Card(
        elevation = 5.dp,
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Column(
            modifier = modifier.padding(16.dp)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
               Surface(
                   color = Color.Cyan,
                   modifier = modifier
                       .size(75.dp)
                       .clip(RoundedCornerShape(100.dp))
               ) {
                   Text(
                       text = "\u20B9555.0",
                       modifier = modifier
                           .fillMaxSize()
                           .wrapContentSize(Alignment.Center)
                   )
               }
                Spacer(modifier = modifier.width(16.dp))
                Column() {
                    Text(
                        text = "Patanjali",
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "Oct 10, 2022")
                }
                Spacer(modifier = modifier.weight(1f))
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = modifier,

                    ) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
            }

        }
    }
}

@Composable
fun WeekChart() {
    Card() {
        Row() {

        }
    }
}

@Composable
fun DayChart() {
    Column() {
        Text(
            text = "\u20B955"
        )
        Box() {
            Surface (
                color = Color.Gray
            ){}
        }
    }
}

@Composable
fun AddTransaction() {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ApnaTransactionTheme {
        ApnaTransaction()
    }
}