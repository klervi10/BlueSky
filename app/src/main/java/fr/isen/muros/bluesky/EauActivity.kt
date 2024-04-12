package fr.isen.muros.bluesky

import android.content.Intent
import androidx.compose.runtime.Composable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import fr.isen.muros.bluesky.ui.theme.BlueSkyTheme

class EauActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlueSkyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    ToolBar()
                    NavigationBar()
                }
            }
        }
    }
}

@Composable
fun ToolBar(modifier: Modifier= Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "BlueSky",
            textAlign = TextAlign.Center,
            color = Color.White,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .background(Color.Blue)
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 16.dp)

        )
    }
}

@Composable
fun NavigationBar(modifier: Modifier= Modifier)
{

}