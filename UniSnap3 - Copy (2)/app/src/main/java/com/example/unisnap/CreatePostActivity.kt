@file:OptIn(ExperimentalAnimationApi::class)

package com.example.unisnap

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material.icons.rounded.Stop
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import coil.compose.rememberImagePainter

class CreatePostActivity : ComponentActivity() {

    val context: Context = this
    var captionn : String = ""
    var descriptionn : String = ""

    private val PICK_IMAGES_CODE = 0

    var images: MutableList<Uri> = mutableStateListOf()

    val voiceToTextParser by lazy {
        VoiceToTextParser(application)
    }

    val voiceToTextParser2 by lazy {
        VoiceToTextParser(application)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(
                modifier = Modifier
                    .fillMaxSize(), color = Color(0xFF2A2A2A)
            ) {
                Column {
                    postContents(context)

                }
            }
        }
    }

    @Composable
    private fun postContents(context: Context) {


        Column(verticalArrangement = Arrangement.Bottom, modifier = Modifier.fillMaxSize()) {

            Column(verticalArrangement = Arrangement.Center) {
                scrollableImages()
            }



            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    onClick = {
                        pickImagesIntent();

                    },
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFB19994))

                ) {
                    Text(text = "Pick Media", color = Color(0xFFE5FCFF))
                }
                inputCaption()
                inputDescription()
                Button(
                    onClick = {
                        val intent = Intent(context, MarketActivity::class.java)
                        val arrayList = ArrayList(images)
                        intent.putParcelableArrayListExtra("images", arrayList)
                        intent.putExtra("caption", captionn)
                        intent.putExtra("description", descriptionn)
                        startActivity(intent)
                    },
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFB19994))

                ) {
                    Text(text = "POST", color = Color(0xFFE5FCFF))
                }
            }


        }
    }


    @Composable
    private fun inputCaption() {

        var canRecord1 by remember {
            mutableStateOf(false)
        }

        val recordAudioLauncher1 = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted ->
                canRecord1 = isGranted
            }
        )

        LaunchedEffect(key1 = recordAudioLauncher1) {
            recordAudioLauncher1.launch(Manifest.permission.RECORD_AUDIO)
        }

        val captionState by voiceToTextParser.state.collectAsState()

        var caption by remember { mutableStateOf("") }


        LaunchedEffect(captionState.spokenText) {
            caption = captionState.spokenText
            captionn = captionState.spokenText
        }



        Row(
            modifier = Modifier
                .padding(20.dp)
        ) {

            LazyColumn {
                item {
                    TextField(
                        modifier = Modifier
                            .width(275.dp)
                            .background(Color(0xFFB19994), RoundedCornerShape(20.dp))
                            .clip(RoundedCornerShape(20.dp)),
                        value = caption,
                        onValueChange = { caption = it; captionn = it },
                        label = {
                            Text("Add a caption")
                        },
                        maxLines = 1,
                    )
                }

            }

            Spacer(modifier = Modifier.width(8.dp))

            AnimatedContent(targetState = captionState.isSpeaking) { isSpeaking ->

                Button(
                    onClick = {
                        if (captionState.isSpeaking) {
                            voiceToTextParser.stopListening()
                        } else {
                            voiceToTextParser.startListening("en-US")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFB19994)),
                    shape = RoundedCornerShape(100.dp),
                ) {
                    AnimatedContent(targetState = captionState.isSpeaking) { isSpeaking ->
                        if (isSpeaking) {
                            Icon(
                                imageVector = Icons.Rounded.Stop,
                                contentDescription = null, tint = Color(0xFFE5FCFF)
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Rounded.Mic,
                                contentDescription = null,
                                tint = Color(0xFFE5FCFF)
                            )
                        }

                    }
                }
            }
        }


    }


    @Composable
    private fun inputDescription() {

        var canRecord by remember {
            mutableStateOf(false)
        }

        val recordAudioLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted ->
                canRecord = isGranted
            }
        )

        LaunchedEffect(key1 = recordAudioLauncher) {
            recordAudioLauncher.launch(Manifest.permission.RECORD_AUDIO)
        }

        val descriptionState by voiceToTextParser2.state.collectAsState()

        var description by remember { mutableStateOf("") }


        LaunchedEffect(descriptionState.spokenText) {
            description = descriptionState.spokenText
            descriptionn = descriptionState.spokenText
        }




        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {


            LazyColumn {
                item {
                    TextField(
                        modifier = Modifier
                            .width(275.dp)
                            .background(Color(0xFFB19994), RoundedCornerShape(20.dp))
                            .clip(RoundedCornerShape(20.dp)),
                        value = description,
                        onValueChange = { description = it; descriptionn = it },
                        label = {
                            Text("Add a description", color = Color.Black)
                        },
                        maxLines = 2,
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))

            AnimatedContent(targetState = descriptionState.isSpeaking) { isSpeaking ->

                Button(
                    onClick = {
                        if (descriptionState.isSpeaking) {
                            voiceToTextParser2.stopListening()
                        } else {
                            voiceToTextParser2.startListening("en-US")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFB19994)),
                    shape = RoundedCornerShape(100.dp),
                ) {
                    AnimatedContent(targetState = descriptionState.isSpeaking) { isSpeaking ->
                        if (isSpeaking) {
                            Icon(
                                imageVector = Icons.Rounded.Stop,
                                contentDescription = null, tint = Color(0xFFE5FCFF)
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Rounded.Mic,
                                contentDescription = null,
                                tint = Color(0xFFE5FCFF)
                            )
                        }

                    }
                }
            }
        }


    }


    @Composable
    private fun scrollableImages() {
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


    private fun pickImagesIntent() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "*/*"
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/*", "video/*"))
        }

        startActivityForResult(
            Intent.createChooser(intent, "Select image(s)/video(s)"),
            PICK_IMAGES_CODE
        )
    }


    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGES_CODE && resultCode == RESULT_OK) {
            if (data!!.clipData != null) {
                for (i in 0 until data!!.clipData!!.itemCount) {
                    val imageUri = data.clipData!!.getItemAt(i).uri
                    images.add(imageUri)
                }
            }
        }
    }
}
