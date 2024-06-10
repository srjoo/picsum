package com.smwrd.picsum.view.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import com.smwrd.picsum.domain.model.Photo
import com.smwrd.picsum.domain.repository.PhotoRepository
import com.smwrd.picsum.ui.theme.PicsumTheme
import com.smwrd.picsum.view.compose.PhotoList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var repository: PhotoRepository

    private val _itemList = mutableStateListOf<Photo>()
    private val photos: MutableList<Photo> = _itemList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PicsumTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PhotoList(
                        photos = photos,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        CoroutineScope(Dispatchers.IO).launch(
            block = {
                repository.getPhotos().collect {
                    photos.addAll(it)
                }
            }
        )

    }
}