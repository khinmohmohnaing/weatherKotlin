package com.example.dell.weatherappkotlin.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.dell.weatherappkotlin.R
import com.example.dell.weatherappkotlin.model.InfoList
import com.example.dell.weatherappkotlin.model.root
import com.example.dell.weatherappkotlin.view.base.BaseVH
import com.example.dell.weatherappkotlin.view.viewholder.weatherViewHolder

class MyAdapter(context: Context): RecyclerView.Adapter<BaseVH>() {
    lateinit var rootInfo:root
    lateinit var onClickItem : BaseVH.onclick
    fun addList(rootInfo:root){
        this.rootInfo=rootInfo
        Log.i("size",rootInfo.list.size.toString())
    }
    fun getList(position: Int): InfoList {
        return rootInfo.list.get(position)
    }
    fun setOnClick(onClickItem :BaseVH.onclick){
        this.onClickItem=onClickItem
    }
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): BaseVH {
        return weatherViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_view, parent,
                false
            ))
    }

    override fun getItemCount(): Int {
        return rootInfo.list.size
    }

    override fun onBindViewHolder(holder: BaseVH, position: Int) {
        holder.bind(rootInfo.list.get(position), onClickItem, position)
    }
}