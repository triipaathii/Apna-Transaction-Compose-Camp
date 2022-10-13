package com.example.apnatransaction

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.apnatransaction.modal.Transaction

@Composable
fun TransactionItem(modifier: Modifier = Modifier, transaction: Transaction) {
    var expanded by remember {
        mutableStateOf(false)
    }
    val amount = (transaction.amount).toDouble()
    Card(
        elevation = 5.dp,
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .padding(top = 16.dp, end = 16.dp, start = 16.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier
                        .size(75.dp)
                        .background(MaterialTheme.colors.background)
                        .clip(RoundedCornerShape(100.dp))
                ) {
                    Text(
                        text = "\u20B9${amount}",
                        modifier = modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = transaction.title,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "Oct 10, 2022")
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier,
                ) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            if(expanded) {
                Spacer(modifier = Modifier.height(16.dp))
                Divider(color = Color.Gray, thickness = 2.dp)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "DESCRIPTION",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = when(transaction.description){
                        null -> "\nNo description provided"
                        else -> "\n${transaction.description}"
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

        }
    }
}
