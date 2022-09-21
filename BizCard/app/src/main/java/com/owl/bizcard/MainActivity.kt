package com.owl.bizcard

import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.owl.bizcard.model.Portfolio
import com.owl.bizcard.ui.theme.BizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CreateCard()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BizCardTheme {
        CreateCard()
    }
}

@Composable
fun CreateCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(360.dp)
                .padding(12.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(corner = CornerSize(15.dp))
        ) {
            Column(
                modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateCircleImage(
                    painter = painterResource(id = R.drawable.img),
                    contentDesc = stringResource(id = R.string.test_image_cont_desc)
                )
                Divider()
                CreateInfo()
            }
        }
    }
}

@Composable
private fun CreateCircleImage(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDesc: String
) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            modifier = modifier.size(135.dp),
            painter = painter,
            contentDescription = contentDesc
        )
    }
}

@Composable
private fun CreateInfo() {
    val portfolioButtonState = remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.test_name),
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant
        )
        Text(
            text = stringResource(id = R.string.test_position),
            style = MaterialTheme.typography.subtitle1
        )
        Button(onClick = {
            Log.d("Tag Button", "Clicked")
            portfolioButtonState.value = !portfolioButtonState.value
        }) {
            Text(
                text = stringResource(id = R.string.test_button),
                style = MaterialTheme.typography.button
            )
        }
        if (portfolioButtonState.value)
            PortfolioContent()
        else
            Box {}
    }
}

@Composable
fun PortfolioContent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
    ) {
        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 0.8.dp, color = Color.LightGray)
        ) {
            val list = mutableListOf<Portfolio>().apply {
                add(
                    Portfolio(
                        "Supper cute",
                        "Tue 30 Aug 2022",
                        painterResource(id = R.drawable.img_1)
                    )
                )
                add(
                    Portfolio(
                        "Born to be a star",
                        "Fri 16 Sep 2022",
                        painterResource(id = R.drawable.img_2)
                    )
                )
                add(
                    Portfolio(
                        "Love me",
                        "Wed 21 Sep 2022",
                        painterResource(id = R.drawable.img_3)
                    )
                )
            }
            Portfolio(list)
        }
    }
}

@Composable
fun Portfolio(data: List<Portfolio>) {
    LazyColumn {
        items(data) { item ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                shape = RectangleShape
            ) {
                PortfolioItem(item = item)
            }
        }
    }
}

@Composable
fun PortfolioItem(item: Portfolio) {
    Row(
        modifier = Modifier
            .padding(4.dp)
            .background(MaterialTheme.colors.surface)
            .padding(bottom = 4.dp)
    ) {
        CreateCircleImage(
            modifier = Modifier.size(80.dp),
            painter = item.img,
            contentDesc = item.activity
        )
        Column(modifier = Modifier
            .padding(4.dp)
            .align(alignment = Alignment.CenterVertically)) {
            Text(
                text = item.activity,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.subtitle1
            )
            Text(text = item.date, style = MaterialTheme.typography.subtitle2)
        }
    }
}