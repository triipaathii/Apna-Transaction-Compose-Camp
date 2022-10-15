package com.example.apnatransaction

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apnatransaction.modal.Transaction
import com.example.apnatransaction.ui.theme.ApnaTransactionTheme
import transactionList
import java.util.*

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

    val tempList = remember { mutableStateListOf(transactionList) }
    Scaffold(
        floatingActionButton = {
            BottomAddButton()
        },
    ) {
        LazyColumn {
            item { AppTopBar() }
            item { UserInput() }
            items(transactionList.size) { index ->
                TransactionItem(modifier = Modifier, transactionList[index])
            }
        }

    }

}

@Composable
fun UserInput() {


    var title by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    val date = remember { mutableStateOf("") }

    //pick date
    val mContext = LocalContext.current

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            date.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, mYear, mMonth, mDay
    )

    //----------------- UI------------------
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //title
        OutlinedTextField(
            value = title,
            onValueChange = {title = it},
            placeholder = { Text(text = "Title") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        //amount
        OutlinedTextField(
            value = amount,
            onValueChange = {amount = it},
            placeholder = { Text(text = "Amount") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        //date
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = " Date: ${date.value}",
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )

            Button(
                onClick = { mDatePickerDialog.show() },
            ) {
                Text(text = "Choose Date", color = Color.White)
            }


        }

        Spacer(modifier = Modifier.height(12.dp))

        //add button
        Button(onClick = { addTransaction(title,amount,date) }) {
            Text(text = "Add Transaction")
        }
    }
}

@Composable
fun BottomAddButton(modifier: Modifier = Modifier) {
    Surface(
        color = Color.Black,
        elevation = 10.dp,
        modifier = modifier
            .size(65.dp)
            .clip(RoundedCornerShape(65.dp))
    ) {
        IconButton(
            onClick = { },
            modifier = Modifier
        ) {
            Icon(Icons.Filled.Add, tint = Color.White, contentDescription = null)
        }
    }
}

@Composable
fun AppTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

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


fun addTransaction(title: String, amount: String, date: MutableState<String>) {
    val temp = Transaction(
        title = title,
        amount = amount,
        dateChosen = date
    )
    transactionList.add(temp)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ApnaTransactionTheme {
        ApnaTransaction()
    }
}