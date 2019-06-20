package com.mamontov.cattest.screens.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mamontov.cattest.screens.adapters.holders.ImageViewHolder
import com.mamontov.domain.entities.Cat

class CatsAdapter(
    private val onFavoriteClickListener: (item: Cat?) -> Unit,
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
            CAT_ITEM -> ImageViewHolder(parent, onFavoriteClickListener, onImageClickListener)
            else                -> throw Exception("Incorrect viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ImageViewHolder     -> holder.bind(cats[position])
            else                -> throw Exception("Incorrect viewType")
        }
    }

    fun add(cat: Cat) {
        cats.add(cat)
        notifyItemInserted(itemCount)
    }

    fun add(position: Int, cat: Cat) {
        cats.add(position, cat)
        notifyItemInserted(position)
    }

    fun addAll(list: List<Cat>) {
        cats.addAll(list)
        notifyDataSetChanged()
    }

    fun replace(previous: Cat, current: Cat) {
        val index = cats.indexOf(previous)
        if (index != INCORRECT_INDEX) {
            cats[index] = current
            notifyItemChanged(index)
        }
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
