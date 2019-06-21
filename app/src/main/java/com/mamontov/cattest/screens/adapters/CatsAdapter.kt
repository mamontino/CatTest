package com.mamontov.cattest.screens.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mamontov.cattest.screens.adapters.holders.ImageViewHolder
import com.mamontov.domain.entities.Cat
import timber.log.Timber

class CatsAdapter(
    private val onFavouriteClickListener: (position: Int, item: Cat?) -> Unit,
    private val onImageClickListener: (item: Cat?) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private companion object {
        const val INCORRECT_INDEX = -1
        const val START_POSITION = 0
        const val CAT_ITEM = 0
    }

    private val cats: MutableList<Cat> = mutableListOf()

    override fun getItemCount(): Int =
        cats.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CAT_ITEM -> ImageViewHolder(parent, onFavouriteClickListener, onImageClickListener)
            else -> throw Exception("Incorrect viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ImageViewHolder -> holder.bind(cats[position], position)
            else -> throw Exception("Incorrect viewType")
        }
    }

    fun addAll(list: List<Cat>) {
        cats.addAll(list)
        notifyDataSetChanged()
    }

    fun replace(position: Int, cat: Cat) {
        Timber.e("replace: $position")
            cats[position] = cat
            notifyItemChanged(position)
    }

    fun remove(item: Cat) {
        val index = cats.indexOf(item)

        if (index != INCORRECT_INDEX) {
            cats.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    fun clear() {
        cats.clear()
        notifyItemRangeRemoved(cats.size, START_POSITION)
    }
}
