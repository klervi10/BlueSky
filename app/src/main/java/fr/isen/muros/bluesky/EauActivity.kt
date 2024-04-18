package fr.isen.muros.bluesky

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
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
fun EauScreen() {
    val database = FirebaseDatabase.getInstance("https://bluesky-a4117-default-rtdb.europe-west1.firebasedatabase.app/")
    val ref = database.getReference("/")

    var pH by remember { mutableStateOf<String?>(null) }
    var turbidite by remember { mutableStateOf<String?>(null) }
    var valIndex by remember { mutableStateOf<Int?>(null) }
    var time by remember { mutableStateOf<String?>(null) }


    LaunchedEffect(Unit) {
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                pH = snapshot.child("pH").getValue(String::class.java)
                turbidite = snapshot.child("turbidite").getValue(String::class.java)
                valIndex = snapshot.child("index_eau").getValue(Int::class.java)
                time = snapshot.child("eau_time").getValue(String::class.java)

                Log.d("EauScreen", "pH: $pH")
                Log.d("EauScreen", "Turbidité: $turbidite")
                Log.d("Index de l'eau", "index de l'eau : $valIndex")
                Log.d("time", "time: $time")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("EauScreen", "Erreur lors de la récupération des données: ${error.message}")
            }
        })
    }

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
            valIndex?.let {
                IndexEau(it)
            }
            DataEau(pH, turbidite)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Dernière mise à jour à : ${time ?: "Chargement..."}",
                    textAlign = TextAlign.Center
                )
            }
        }

        NavigationBar(modifier = Modifier.fillMaxWidth())
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
fun IndexEau(valIndex: Int) {
    val circleColor = when (valIndex) {
        in 0..1 -> Color.Red
        in 2..3 -> Color(0xFFFFA500)
        else -> Color(0xE200BD03)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(circleColor, shape = CircleShape)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = valIndex.toString(),
                color = Color.White,
                style = TextStyle(fontSize = 48.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
fun DataEau(pH: String?, turbidite: String?) {
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
                    text = turbidite ?: "",
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
                    text = pH ?: "",
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
                .clickable { }
                .padding(8.dp),
            colorFilter = ColorFilter.tint(iconTint)
        )
        Image(
            painter = painterResource(id = R.drawable.appareils),
            contentDescription = "Image 4",
            modifier = Modifier
                .size(36.dp)
                .clickable { }
                .padding(8.dp),
            colorFilter = ColorFilter.tint(iconTint)
        )
    }
}