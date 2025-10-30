package com.example.slideshow


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.slideshow.ui.theme.SlideshowTheme
import androidx.compose.runtime.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SlideshowTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SlideshowApp()
                }
            }
        }
    }
}


@Composable
fun SlideshowApp() {
    //List of images
    val images = listOf(
        R.drawable.img_2975,
        R.drawable.img_2971,
        R.drawable.img_2972,
        R.drawable.img_2973,
        R.drawable.image
    )


    //List of captions
    val captions = listOf(
        "Iced coffee",
        "Chocolate cake",
        "Confetti cake pop",
        "Ice cream",
        "Chocolate chip muffin"
    )

    var currentIndex by remember { mutableStateOf(0) }
    var inputText by remember { mutableStateOf("") }


    Box(modifier = Modifier.fillMaxSize()) {
        //Background image
        Image(
            painter = painterResource(id = images[currentIndex]),
            contentDescription = "Background image",
            modifier = Modifier.size(900.dp),
            contentScale = ContentScale.Crop
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //TextField and "Go" button
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                TextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    label = { Text(stringResource(R.string.enter_number)) },
                    modifier = Modifier.width(150.dp)
                )
                Button(onClick = {
                    val number = inputText.toIntOrNull()
                    if (number != null && number in 1..images.size) {
                        currentIndex = number - 1
                    }
                }) {
                    Text("Go")
                }
            }

            //Caption
            Text(
                text = captions[currentIndex],
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 8.dp)
            )

            //Back/Next buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Button(onClick = {
                    currentIndex = (currentIndex - 1 + images.size) % images.size
                }) { Text("Back") }


                Button(onClick = {
                    currentIndex = (currentIndex + 1) % images.size
                }) { Text("Next") }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun SlideshowPreview() {
    SlideshowTheme {
        SlideshowApp()
    }
}
