package fr.isen.muros.bluesky

import android.os.Bundle
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import fr.isen.muros.bluesky.ui.theme.BlueSkyTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlueSkyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Transparent,
                    contentColor = Color.White,
                    content = {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Canvas(modifier = Modifier.fillMaxSize()) {
                                drawRect(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color(0xFF005BC3),
                                            Color(0xFF6CB8EB)
                                        )
                                    ),
                                    size = size
                                )
                            }
                            Column {
                                Header()
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun Header() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = "Contrôler l'énergie pour mieux l'utiliser",
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = {val intent = Intent(context, AirActivity::class.java)
                context.startActivity(intent)},
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 32.dp)
                .wrapContentHeight(Alignment.CenterVertically),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Blue)
        )
        {
            Text(
                "Mon Air",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {val intent = Intent(context, EauActivity::class.java)
                context.startActivity(intent)},
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 32.dp)
                .wrapContentHeight(Alignment.CenterVertically),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Blue)
        ) {
            Text(
                "Mon Eau",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 32.dp)
                .wrapContentHeight(Alignment.CenterVertically),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Blue)
        ) {
            Text(
                "Mes appareils",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}