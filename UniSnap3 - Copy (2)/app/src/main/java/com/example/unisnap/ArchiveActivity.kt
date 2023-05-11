package com.example.unisnap

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class ArchiveActivity() : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            Surface {

                MyApp()


            }
        }


    }

}

//val rank = listOf(
//    UserProfile(
//        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc placerat luctus luctus. Donec hendrerit, neque eu tincidunt commodo, nisl dolor varius ex, et consequat orci ex vitae diam.",
//
//        "Angela",
//        "Tasheva"
//    ),
//    UserProfile("Very good job",  "Angela", "Tasheva"),
//    UserProfile( "Amazing",  "Stoil", "Bushnakov"),
//    UserProfile( "Amazing",  "Stefani", "Li"),
//    UserProfile("Amazing",  "R", "S"),
//)

@Composable
fun MyApp(navController: NavHostController = rememberNavController()) {

    NavHost(navController, startDestination = "archivePost") {
        composable("archivePost") { ArchiveActivityy(navController) }
        composable("details") { DetailedPostActivity() }
        composable("feed"){ FeedActivityy(navController)}
    }
}


@Composable
fun ArchiveActivityy(navController: NavHostController) {


    var text = buildAnnotatedString {
        append("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis ultrices turpis justo, aliquet vulputate lectus fringilla ut. Aliquam hendrerit tempor ligula, non vulputate tellus scelerisque ac. In sed eros a urna interdum ornare. Aenean rhoncus enim in enim pharetra, vel tempor eros venenatis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vestibulum eu odio mauris. Morbi efficitur aliquam vehicula. Ut euismod semper faucibus.")
        addStyle(style = SpanStyle(fontSize = 12.sp, color = Color.White), start = 0, end = length)

    }




    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Image(
                painter = painterResource(id = R.mipmap.logo),
                contentDescription = "logo",
                modifier = Modifier
                    .height(100.dp)
                    .width(150.dp)
            )
            
            Spacer(modifier = Modifier.width(30.dp))
            
            Column(modifier = Modifier
                .height(30.dp)
                .width(160.dp)
                .background(color = Color(0xFFB19994), shape = RoundedCornerShape(10)), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Export as PDF", textAlign = TextAlign.Center)
                
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
        

        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            item {
                repeat(3) {


                    Row() {
                        Column(
                            modifier = Modifier
                                .background(Color(0xFFD9D9D9), shape = RoundedCornerShape(10))
                                .width(250.dp)
                                .height(350.dp)
                                .padding(10.dp)
                                .clickable(onClick = { navController.navigate("details") }),

                            ) {
                            Column(
                                modifier = Modifier
                                    .background(Color(0xFF000000), shape = RoundedCornerShape(20))
                                    .width(100.dp)
                                    .height(130.dp)
                                    .padding(20.dp),

                                ) {}
                        }

                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "01.04.2023",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 15.dp)
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                }
            }
        }
    }


}


@Composable
fun Commentar(firstname: String, lastname: String, comment: String) {


    Row(
        modifier = Modifier
            .background(
                color = Color(0xFFB19994),
                shape = RoundedCornerShape(10.dp)
            )
            .size(200.dp, 40.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = " ", fontSize = 10.sp, color = Color.White)
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 10.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .width(30.dp)
                    .height(60.dp)
                    .zIndex(100f)
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
                text = comment,
                color = Color.White,
                fontSize = 10.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            );
        }
    }
}


@Composable
fun DetailedPostActivity() {

    val onBackPressedCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (this as? Activity)?.onBackPressed()
            }
        }
    }


    var text = buildAnnotatedString {
        append("See less...")
        addStyle(style = SpanStyle(fontSize = 12.sp, color = Color.White), start = 0, end = length)

    }

    LazyColumn() {
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.logo),
                    contentDescription = "logo",
                    modifier = Modifier
                        .height(100.dp)
                        .width(150.dp)

                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .background(Color(0xFFD9D9D9), shape = RoundedCornerShape(10))
                            .width(250.dp)
                            .height(400.dp)
                            .padding(10.dp),

                        ) {
                        Column(
                            modifier = Modifier
                                .background(Color(0xFF111111), shape = RoundedCornerShape(20))
                                .width(100.dp)
                                .height(120.dp)
                                .padding(20.dp),

                            ) {}
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Portfolio Capture",
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(start = 8.dp)
                        );
                        Text(
                            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis ultrices turpis justo, aliquet vulputate lectus fringilla ut. Aliquam hendrerit tempor ligula, non vulputate tellus scelerisque ac. In sed eros a urna interdum ornare. Aenean rhoncus enim in enim pharetra, vel tempor eros venenatis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vestibulum eu odio mauris. Morbi efficitur aliquam vehicula. Ut euismod semper faucibus.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis ultrices turpis justo, aliquet vulputate lectus fringilla ut. Aliquam hendrerit tempor ligula, non vulputate tellus scelerisque ac. In sed eros a urna interdum ornare. Aenean rhoncus enim in enim pharetra, vel tempor eros venenatis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vestibulum eu odio mauris. Morbi efficitur aliquam vehicula. Ut euismod semper faucibus.\n",
                            fontSize = 12.sp,
                            color = Color.White,
                            modifier = Modifier.padding(8.dp),
                            textAlign = TextAlign.Justify
                        );


                        LazyColumn(
                            Modifier
                                .height(170.dp)
                                .padding(start = 8.dp), verticalArrangement = Arrangement.Bottom
                        ) {
                            items(comments) { person ->
                                Comment(
                                    comment = person.comment,
                                    firstname = person.firstName,
                                    lastname = person.lastName,
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }

                    }

                }

                Text(
                    text = "01.04.2023",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 15.dp)
                )


            }
        }
    }
}