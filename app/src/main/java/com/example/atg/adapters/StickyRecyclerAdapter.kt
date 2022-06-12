package com.example.atg.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atg.databinding.ItemLayoutBinding
import com.example.atg.databinding.SectionHeaderBinding
import com.example.atg.models.Section
import com.example.atg.models.SectionHeader
import com.example.atg.models.SectionItem
import com.shuhart.stickyheader.StickyAdapter

class StickyRecyclerAdapter : StickyAdapter<RecyclerView.ViewHolder, RecyclerView.ViewHolder>() {

    private var items: List<Section> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == Section.HEADER)
            HeaderViewHolder(
                SectionHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        else
            ItemViewHolder(
                ItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder && items[position] is SectionItem) {
            holder.bind(items[position] as SectionItem)
        } else if (holder is HeaderViewHolder && items[position] is SectionHeader) {
            holder.bind(items[position] as SectionHeader)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getHeaderPositionForItem(itemPosition: Int): Int {
        return items[itemPosition].sectionId()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder, headerPosition: Int) {
        var header: SectionHeader? = null
        for (item in items) {
            if (item is SectionHeader && item.sectionId == headerPosition)
                header = item
        }
        (holder as HeaderViewHolder).bind(header!!)
    }

    override fun onCreateHeaderViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return createViewHolder(parent, Section.HEADER)
    }

    class HeaderViewHolder(private val binding: SectionHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(header: SectionHeader) {
            binding.header = header
            binding.executePendingBindings()
        }
    }

    class ItemViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SectionItem) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    fun setData(item: List<Section>) {
        items = item
        notifyDataSetChanged()
    }
}