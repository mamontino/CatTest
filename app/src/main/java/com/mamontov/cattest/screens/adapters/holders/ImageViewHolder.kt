package com.mamontov.cattest.screens.adapters.holders

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mamontov.cattest.R
import com.mamontov.cattest.screens.inflate
import com.mamontov.domain.entities.Cat
import kotlinx.android.synthetic.main.image_item.view.*

class ImageViewHolder(
    parent: ViewGroup,
    onFavoriteClickListener: (Cat?) -> Unit,
    onImageClickListener: (Cat?) -> Unit
) : RecyclerView.ViewHolder(parent.inflate(R.layout.image_item)) {

    private lateinit var item: Cat

    init {
        itemView.favorites.setOnClickListener { onFavoriteClickListener(item) }
        itemView.setOnClickListener { onImageClickListener(item) }
    }

    fun bind(cat: Cat) {
        item = cat

        itemView.apply {
            Glide
                .with(this.context)
                .load(item.url)
                .centerCrop()
                .placeholder(R.drawable.loading_image)
                .into(image)
        }
    }
}


