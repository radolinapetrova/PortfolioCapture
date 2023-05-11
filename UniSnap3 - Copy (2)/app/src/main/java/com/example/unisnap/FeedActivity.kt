package com.example.unisnap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.runtime.Composable
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

class FeedActivity() : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            Surface {
                Nav()
            }
        }
    }

}

@Composable
fun Nav(navController: NavHostController = rememberNavController()) {
    NavHost(navController, startDestination = "feed") {
        composable("details") { DetailedPostActivity() }
        composable("feed") { FeedActivityy(navController) }
    }
}


@Composable
fun FeedActivityy(navController: NavHostController) {

    val scrollState = rememberScrollState()

    var text = buildAnnotatedString {
        append(
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis ultrices turpis justo, aliquet vulputate lectus fringilla ut. Aliquam hendrerit tempor ligula, non vulputate tellus scelerisque ac. In sed eros a urna interdum ornare. Aenean rhoncus enim in enim pharetra, vel tempor eros venenatis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vestibulum eu odio mauris. Morbi efficitur aliquam vehicula. Ut euismod semper faucibus.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis ultrices turpis justo, aliquet vulputate lectus fringilla ut. Aliquam hendrerit tempor ligula, non vulputate tellus scelerisque ac. In sed eros a urna interdum ornare. Aenean rhoncus enim in enim pharetra, vel tempor eros venenatis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vestibulum eu odio mauris. Morbi efficitur aliquam vehicula. Ut euismod semper faucibus.\n"
        )
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
                        ClickableText(
                            text = text,
                            modifier = Modifier.padding(start = 20.dp, top = 10.dp),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            onClick = { navController.navigate("details") }
                        );

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 20.dp, start = 10.dp),
                            ) {
                                Column(
                                    modifier = Modifier
                                        .width(60.dp)
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
                                        text = "RP",
                                        color = Color.White,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 20.sp
                                    );
                                }

                                Column(
                                    horizontalAlignment = Alignment.Start,
                                    modifier = Modifier
                                        .height(60.dp)
                                        .padding(start = 8.dp),
                                    verticalArrangement = Arrangement.Center
                                ) {

                                    Text(text = "Radolina Petrova", modifier = Modifier)
                                }


                            }

                            Spacer(modifier = Modifier.height(20.dp))
                            Row() {

                                Column() {
                                    Column(
                                        modifier = Modifier
                                            .background(
                                                Color(0xFFD9D9D9),
                                                shape = RoundedCornerShape(10)
                                            )
                                            .width(150.dp)
                                            .height(250.dp)
                                            .padding(10.dp),

                                        ) {
                                        Column(
                                            modifier = Modifier
                                                .background(
                                                    Color(0xFF000000),
                                                    shape = RoundedCornerShape(20)
                                                )
                                                .width(50.dp)
                                                .height(70.dp)
                                                .padding(20.dp),

                                            ) {}
                                    }
                                }

                                Column(modifier = Modifier.height(250.dp)) {


                                    LazyColumn(
                                        Modifier
                                            .height(200.dp)
                                            .padding(start = 8.dp),
                                        verticalArrangement = Arrangement.Bottom
                                    ) {
                                        items(comments) { person ->
                                            Commentar(
                                                comment = person.comment,
                                                firstname = person.firstName,
                                                lastname = person.lastName,
                                            )
                                            Spacer(modifier = Modifier.height(8.dp))
                                        }
                                    }
                                    Row(
                                        verticalAlignment = Alignment.Bottom,
                                        modifier = Modifier.padding(start = 8.dp, top = 15.dp)
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .width(120.dp)
                                                .height(30.dp)
                                                .background(
                                                    color = Color(0xFFB19994),
                                                    shape = RoundedCornerShape(30)
                                                ),
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(text = "Add a comment", fontSize = 15.sp)
                                        }

                                        Spacer(modifier = Modifier.width(8.dp))

                                        Text(text = "or")

                                        Spacer(modifier = Modifier.width(8.dp))

                                        Column(
                                            modifier = Modifier
                                                .width(50.dp)
                                                .height(30.dp)
                                                .background(
                                                    color = Color(0xFFB19994),
                                                    shape = RoundedCornerShape(30)
                                                ),
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Icon(
                                                imageVector = Icons.Rounded.Mic,
                                                contentDescription = null,
                                                tint = Color(0xFFE5FCFF)
                                            )
                                        }
                                    }

                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Column(
                                horizontalAlignment = Alignment.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 8.dp)
                                    .scrollable(
                                        state = scrollState,
                                        orientation = Orientation.Vertical
                                    )
                            ) {
                                Text(
                                    text = " Portfolio Capture",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis ultrices turpis justo, aliquet vulputate lectus fringilla ut. Aliquam hendrerit tempor ligula, non vulputate tellus scelerisque ac. In sed eros a urna interdum ornare. Aenean rhoncus enim in enim pharetra, vel tempor eros venenatis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vestibulum eu odio mauris. Morbi efficitur aliquam vehicula. Ut euismod semper faucibus.",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                )


                            }


                        }


                    }

                }
            }
        }
    }
}