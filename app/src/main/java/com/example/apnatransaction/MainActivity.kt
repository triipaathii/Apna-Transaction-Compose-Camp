package com.example.apnatransaction

import WeekChart
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apnatransaction.modal.Transaction
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
    var transactionList: MutableList<Transaction> by remember {
        mutableStateListOf()
    }

    Scaffold(
        floatingActionButton = {
            BottomAddButton()
        },
    ) {
        LazyColumn {
            item { AppTopBar() }
            item { WeekChart() }
            items(10){
                TransactionItem()
            }
        }

    }

}

@Composable
fun BottomAddButton(modifier: Modifier = Modifier){
    Surface(
        color = Color.Black,
        elevation = 10.dp,
        modifier = modifier
            .size(65.dp)
            .clip(RoundedCornerShape(65.dp))
    ) {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
        ) {
            Icon(Icons.Filled.Add, tint = Color.White, contentDescription = null)
        }
    }
}

@Composable
fun AppTopBar(modifier: Modifier = Modifier){
    Row (
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
            ){

        Text(
            text = "Apna Transaction",
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3F0071),
            fontSize = 25.sp,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(16.dp)
        )

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