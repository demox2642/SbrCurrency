package com.example.base_ui.view

import android.view.LayoutInflater
import android.widget.ImageView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.base_ui.R
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

@Composable
fun PicassoImage(
    loadUrl: String,
    modifier: Modifier
) {
    AndroidView(
        factory = { context ->
            val view = LayoutInflater.from(context).inflate(R.layout.image, null, false)
            val imageView = view.findViewById<ImageView>(R.id.imageView)

            Picasso.get()
                .load(loadUrl)
                .placeholder(R.drawable.ic_placeholder)
                .into(imageView)

            view
        },
        modifier = modifier,
        update = { }
    )
}
