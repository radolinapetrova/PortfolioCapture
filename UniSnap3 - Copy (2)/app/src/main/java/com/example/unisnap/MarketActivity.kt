package com.example.unisnap

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.rememberImagePainter
import androidx.compose.runtime.*
import java.util.Objects


class MarketActivity() : ComponentActivity() {

    private lateinit var imgs: ArrayList<Uri>
    private lateinit var captionn: String
    private lateinit var descriptionn: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context: Context = this
//        val imgs: MutableList<Uri> = images

        imgs = intent.getParcelableArrayListExtra("images")!!
        captionn = intent.getStringExtra("caption")!!
        descriptionn = intent.getStringExtra("description")!!

        setContent {
            Surface() {
                MarketActivity(
                    context = context,
                    images = imgs,
                    captionn = captionn,
                    descriptionn = descriptionn
                )
            }
        }


    }

}


@Composable
fun MarketActivity(
    context: Context,
    images: MutableList<Uri>?,
    descriptionn: String,
    captionn: String
) {


    var description =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc placerat luctus luctus. Donec hendrerit, neque eu tincidunt commodo, nisl dolor varius ex, et consequat orci ex vitae diam. Suspendisse potenti. Phasellus massa nisl, feugiat vitae hendrerit ac, tempor at ex. Nunc bibendum mauris vel lectus finibus laoreet. Vivamus maximus auctor neque. Pellentesque molestie, nibh consectetur sagittis fringilla, arcu velit congue eros, sed luctus felis justo sed nunc. Nam enim tortor, lobortis pretium erat id, tempor sollicitudin justo. Phasellus at accumsan dui, sit amet consequat ipsum. Nunc fringilla in augue at laoreet. In hendrerit iaculis enim, et lacinia sapien sagittis nec. Interdum et malesuada fames ac ante ipsum primis in faucibus. Ut congue arcu nec tellus luctus, quis suscipit lorem sodales. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc porttitor sem vitae tortor ornare finibus. Praesent sit amet ex eget eros congue commodo."

    fun launchYourClass(context: Context) {
        val intent = Intent(context, CreatePostActivity::class.java)
        context.startActivity(intent)
    }


    Surface(
        modifier = Modifier
            .background(Color(0xFF2A2A2A))
            .fillMaxSize()
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF2A2A2A))
                .padding(10.dp)
        ) {

            Image(
                painter = painterResource(id = R.mipmap.logo),
                contentDescription = "logo",
                modifier = Modifier
                    .height(100.dp)
                    .width(150.dp)
            )
            Button(
                onClick = {
                    launchYourClass(context)

                },
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFB19994))

            ) {
                Text(text = "Add Post", color = Color(0xFFE5FCFF))
            }


            if (descriptionn == "" || captionn == "") {
                post(
                    "Portfolio Capture",
                    description, images
                )
            } else {
                post(
                    captionn,
                    descriptionn, images
                )
            }

        }
    }
}


data class UserProfile(
    val comment: String,
    val firstName: String,
    val lastName: String
)


 val comments = listOf(
    UserProfile("Very good job",  "Angela", "Tasheva"),
    UserProfile( "Amazing",  "Stoil", "Bushnakov"),
    UserProfile( "Amazing",  "Stefani", "Li"),
    UserProfile("Amazing",  "R", "S"),
)


@Composable
fun Comment(firstname: String, lastname: String, comment: String) {

    Row(
        modifier = Modifier
            .background(
                color = Color(0xFFB19994),
                shape = RoundedCornerShape(10.dp)
            )
            .size(360.dp, 70.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = " ", fontSize = 20.sp, color = Color.White)
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
                text = comment,
                color = Color.White,
                fontSize = 15.sp,
                maxLines = 5
            );
        }
    }
}


@Composable
fun post(caption: String, description: String, images: MutableList<Uri>?) {

    var isOverflowing by remember { mutableStateOf(false) }

    LazyColumn() {
        item {
            repeat(3) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(top = 16.dp, start = 8.dp, end = 8.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = caption,
                        fontSize = 20.sp,
                        color = Color(0xFFE5FCFF)
                    )

                    Text(
                        text = description,
                        fontSize = 15.sp,
                        color = Color(0xFFE5FCFF),

                        textAlign = TextAlign.Justify,
                        maxLines = 2,
                        overflow = TextOverflow.Clip,
                        onTextLayout = { textLayoutResult ->
                            isOverflowing = textLayoutResult.didOverflowWidth
                        }
                    )
                    if (isOverflowing) {
                        ClickableText(
                            text = AnnotatedString(
                                text = "...see more",
                                spanStyle = SpanStyle(fontWeight = FontWeight.Bold)
                            ),
                            onClick = { /* handle click */ },
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(bottom = 20.dp),

                            style = MaterialTheme.typography.body2.copy(
                                color = Color(0xFFE5FCFF),
                            )
                        )
                    }





                    if (images.isNullOrEmpty()) {
                        LazyRow(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            item {
                                repeat(8) {
                                    Image(
                                        painterResource(id = R.mipmap.tsveta_dogo2_foreground),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(200.dp)
                                            .aspectRatio(3f / 4f)

                                    )


                                }

                            }
                        }
                    } else {
                        LazyRow(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(images) { media ->

                                Image(
                                    painter = rememberImagePainter(media),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .width(200.dp)
                                        .aspectRatio(3f / 4f)
                                        .background(Color(0xFFB19994))
                                )
                            }
                        }
                    }
                }



                LazyColumn(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 25.dp, end = 0.dp, bottom = 0.dp)
                        .height(200.dp)
                        .fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)
                )
                {

                    items(comments) { bitch ->
                        Comment(
                            comment = bitch.comment,
                            firstname = bitch.firstName,
                            lastname = bitch.lastName
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))


            }
        }
    }
}



