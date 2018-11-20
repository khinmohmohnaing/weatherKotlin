package com.example.dell.weatherappkotlin.view.viewholder

import android.view.View
import com.bumptech.glide.Glide
import com.example.dell.weatherappkotlin.model.InfoList
import com.example.dell.weatherappkotlin.view.base.BaseVH
import kotlinx.android.synthetic.main.card_view.view.*

class weatherViewHolder(itemView: View): BaseVH(itemView) {
    override fun bind(itemlist: InfoList, onclicklistener: onclick, position: Int) {

        itemView.weathername.text=itemlist.weather.get(0).description
        itemView.cloudss.text=itemlist.clouds.all.toString()
        itemView.winds.text=itemlist.wind.speed.toString()
        itemView.dates.text=itemlist.dt_txt.toString()
        Glide.with(itemView.context)
            .load("http://api.openweathermap.org/img/w/" +
                    itemlist.weather.get(0).icon).into(itemView.img)
        itemView.setOnClickListener{itemView->onclicklistener.onItemClick(position)}
        itemView.detailbtn.setOnClickListener{itemView->onclicklistener.onItemClick(position)}

    }
}

