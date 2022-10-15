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
    val list: MutableList<Transaction> = mutableListOf();
//    val tempList = remember { mutableStateListOf(list) }
    val tempList: MutableList<Transaction> = mutableListOf(
        Transaction(
            title = "Movie - Vikram Vedha",
            amount = "1355",
            dateChosen = "14/10/2022",
        ),
        Transaction(
            title = "CSE Books",
            amount = "555",
            dateChosen = "13/10/2022",
            description = "1.Ada - R.K. Singh\n2.DSA - Interview Questions"
        ),
        Transaction(
            title = "Cafeteria",
            amount = "210",
            dateChosen = "13/10/2022",
            description = "5 samosa, 3 chai, 2 Maaza"
        ),
        Transaction(
            title = "Cafeteria",
            amount = "110",
            dateChosen = "11/10/2022",
        ),
        Transaction(
            title = "Ice Cream",
            amount = "40",
            dateChosen = "11/10/2022",

        ),
        Transaction(
            title = "Science City",
            amount = "120",
            dateChosen = "9/10/2022",
        ),
        Transaction(
            title = "Lunch",
            amount = "300",
            dateChosen = "9/10/2022",
        ),
        Transaction(
            title = "Bus",
            amount = "250",
            dateChosen = "9/10/2022",
        )

    )
    Scaffold(
//        floatingActionButton = {
//            BottomAddButton()
//        },
    ) {
        LazyColumn {
            item { AppTopBar() }
            item { UserInput() }
            items(tempList.size) { index ->
                TransactionItem(modifier = Modifier, tempList[index])
            }
        }

    }

}

@Composable
fun UserInput() {

    var title by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    val date = remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

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
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //title
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                placeholder = { Text(text = "Title") },
                label = {Text(text = "Title")}
            )

            Spacer(modifier = Modifier.height(12.dp))

            //amount
            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                placeholder = { Text(text = "Amount") },
                label = {Text(text = "Amount") }
            )

            Spacer(modifier = Modifier.height(12.dp))

            // description
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                placeholder = { Text(text = "Description") },
                label = {Text(text = "Description")}
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
            Button(onClick = { addTransaction(title, amount, date.toString(), addItem = {}) }) {
                Text(text = "Add Transaction")
            }
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
            color = MaterialTheme.colors.primary,
            fontSize = 25.sp,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(16.dp)
        )

    }
}


fun addTransaction(title: String, amount: String, date: String, addItem: () -> Unit) {
    val temp = Transaction(
        title = title,
        amount = amount,
        dateChosen = date
    )
    addItem()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ApnaTransactionTheme {
        ApnaTransaction()
    }
}