package fr.isen.muros.bluesky

import android.content.Intent
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.border
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
                    EauScreen()
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
fun EauScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        ToolBar(modifier = Modifier.fillMaxWidth())

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Data()
        }

        NavigationBar(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun Data() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0x6B0060F0)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .padding(4.dp)
                    .weight(1f)
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Paramètres",
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
            }
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .padding(4.dp)
                    .weight(1f)
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Vos Valeurs",
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
            }
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .padding(4.dp)
                    .weight(1f)
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Valeurs acceptables",
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .padding(4.dp)
                    .weight(1f)
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Turbidité",
                    textAlign = TextAlign.Center,
                )
            }
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .padding(4.dp)
                    .weight(1f)
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "val turb",
                    textAlign = TextAlign.Center,
                )
            }
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .padding(4.dp)
                    .weight(1f)
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = " 0 - 300    (µg/m3)",
                    textAlign = TextAlign.Center,
                    //style = TextStyle(fontWeight = FontWeight.Bold)
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .padding(4.dp)
                    .weight(1f)
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "pH",
                    textAlign = TextAlign.Center,
                    )
            }
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .padding(4.dp)
                    .weight(1f)
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "val_pH",
                    textAlign = TextAlign.Center,
                )
            }
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .padding(4.dp)
                    .weight(1f)
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "0 - 1000 (ppm)",
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
fun NavigationBar(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val iconTint = Color.White

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.Blue),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.accueil),
            contentDescription = "Image 1",
            modifier = Modifier
                .size(36.dp)
                .clickable {
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                }
                .padding(8.dp),
            colorFilter = ColorFilter.tint(iconTint)
        )
        Image(
            painter = painterResource(id = R.drawable.air),
            contentDescription = "Image 2",
            modifier = Modifier
                .size(36.dp)
                .clickable {
                    val intent = Intent(context, AirActivity::class.java)
                    context.startActivity(intent)
                }
                .padding(8.dp),
            colorFilter = ColorFilter.tint(iconTint)
        )
        Image(
            painter = painterResource(id = R.drawable.eau),
            contentDescription = "Image 3",
            modifier = Modifier
                .size(36.dp)
                .clickable {  }
                .padding(8.dp),
            colorFilter = ColorFilter.tint(iconTint)
        )
        Image(
            painter = painterResource(id = R.drawable.appareils),
            contentDescription = "Image 4",
            modifier = Modifier
                .size(36.dp)
                .clickable {  }
                .padding(8.dp),
            colorFilter = ColorFilter.tint(iconTint)
        )
    }
}