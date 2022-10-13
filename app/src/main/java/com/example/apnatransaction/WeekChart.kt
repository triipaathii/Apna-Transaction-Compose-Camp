import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WeekChart(modifier: Modifier = Modifier) {
        Row (
            modifier = modifier
                .padding(32.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            DayChart()
            DayChart()
            DayChart()
            DayChart()
            DayChart()
            DayChart()
            DayChart()
        }
}

@Composable
private fun DayChart(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "\u20B955"
        )
        Box() {
            Surface (
                color = Color.Gray,
                border = BorderStroke(color = Color.Black, width = 1.dp),
                modifier = modifier
                    .height(100.dp)
                    .width(10.dp)
                    .clip(RoundedCornerShape(50.dp))
            ){}
            Surface (
                color = Color.Magenta,
                modifier = modifier
                    .height(25.dp)
                    .width(10.dp)
                    .clip(RoundedCornerShape(50.dp))
            ){}
        }
        Text(
            text = "M"
        )
    }
}
