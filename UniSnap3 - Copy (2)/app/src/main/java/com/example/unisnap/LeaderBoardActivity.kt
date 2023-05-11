package com.example.unisnap


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

@Composable
fun LeaderBoardActivity(){


    data class LeaderProfile(
        val modifier: Modifier?,
        val position: Int,
        val image: Painter?,
        val firstName: String,
        val lastName: String
    )


    val executives = listOf(
        LeaderProfile(
            Modifier.padding(vertical = 8.dp),
            2,
            image = painterResource(id = R.mipmap.radka_dogo2_foreground),
            "Radka",
            "Petrova"
        ),
        LeaderProfile(
            Modifier.padding(vertical = 0.dp),
            1,
            image = painterResource(id = R.mipmap.tsveta_dogo2_foreground),
            "Tsveta",
            "Pandurska"
        ),
        LeaderProfile(
            Modifier.padding(vertical = 12.dp),
            3,
            image = painterResource(id = R.mipmap.stoil_dogo_foreground),
            "Stoil",
            "Bushnakov"
        )
    )
    val ranking = listOf(
        LeaderProfile(null, 4, null, "Martin", "Iliev"),
        LeaderProfile(null, 5, null, "Georgi", "NeSiSpomnqm"),
        LeaderProfile(null, 6, null, "Simeona", "Gabrielova"),
        LeaderProfile(null, 7, null, "Eliza", "Traikovic"),
        LeaderProfile(null, 8, null, "MiQ", "Vasilecvska")
    )

    Surface(
        modifier = Modifier
            .fillMaxSize(), color = Color(0xFF2A2A2A)
    ) {
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
//            Image(
//                modifier = Modifier.size(170.dp, 200.dp),
//                painter = painterResource(id = R.mipmap.ic_launcher_foreground),
//                contentDescription = "logo",
//                contentScale = ContentScale.FillBounds
//            )
            Image(
                painter = painterResource(id = R.mipmap.logo),
                contentDescription = "logo",
                modifier = Modifier
                    .height(100.dp)
                    .width(150.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Most Active",
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp,
                    color = Color(0xFFFFFFFF)
                )
                Text(
                    text = "People This Week",
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp,
                    color = Color(0xFFFFFFFF)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 25.dp, 0.dp, 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (leader in executives) {
                LeaderProfilee(
                    leader.modifier!!,
                    leader.position,
                    leader.image!!,
                    leader.firstName,
                    leader.lastName
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .padding(25.dp, 0.dp, 0.dp, 0.dp)
                .height(300.dp)
                .fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            items(ranking) { bitch ->
                Comment(
                    position = bitch.position,
                    firstname = bitch.firstName,
                    lastname = bitch.lastName
                )
            }
        }
    }


}

@Composable
fun LeaderProfilee(
    modifier: Modifier,
    position: Int,
    icon: Painter,
    firstname: String,
    lastname: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$position", color = Color.White)
        Box(
            modifier = Modifier
                .size(if (position != 1) 120.dp else 150.dp)
                .clip(RoundedCornerShape(100)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = icon,
                contentDescription = "$firstname pic",
                contentScale = ContentScale.Crop
            )
        }
        Text(text = firstname.substring(0, 1) + lastname.substring(0, 1), color = Color.White)
    }
}

@Composable
fun Comment(position: Int, firstname: String, lastname: String) {

    Row(
        modifier = Modifier
            .background(
                color = Color(0xFFB19994),
                shape = RoundedCornerShape(10.dp)
            )
            .size(360.dp, 70.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "$position", fontSize = 20.sp, color = Color.White)
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 10.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .width(50.dp)
                    .fillMaxHeight()
                    .zIndex(70f)
                    .background(
                        color = Color(0xFF985F6F),
                        shape = RoundedCornerShape(100)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            )
            {
                Text(
                    text = firstname.substring(0, 1) + lastname.substring(0, 1),
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                );
            }
            Text(
                modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp),
                text = firstname + " " + lastname,
                color = Color.White,
                fontSize = 20.sp
            );
        }
    }
}