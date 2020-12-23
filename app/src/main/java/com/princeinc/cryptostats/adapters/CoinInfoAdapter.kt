package com.princeinc.cryptostats.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.princeinc.cryptostats.R
import com.princeinc.cryptostats.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coin_info.view.*

class CoinInfoAdapter (private val context:Context): RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var coinInfoList:List<CoinPriceInfo> = listOf()
        set(value) {
            field =
                value   // Каждый раз когда будем присваивать значение, вызывается этот Сеттер и..
            notifyDataSetChanged() // ..RecyclerView обновит список
        }

    var onCoinClickListener: OnCoinClickListener ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info, parent, false)
        return CoinInfoViewHolder(view)
    }

    override fun getItemCount() = coinInfoList.size

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        with(holder) {
            with(coin) {
                val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                val lastUpdateTimeTemplate = context.resources.getString(R.string.lastUpdate_time_template)
                textViewSymbols.text = String.format(symbolsTemplate, fromSymbol, toSymBol)
                textViewPrice.text = price
                textViewLastUpdateTime.text = String.format(lastUpdateTimeTemplate, getFormattedTime())
                Picasso.get().load(getFullImageUrl()).into(imageViewLogoCoin)
                itemView.setOnClickListener{
                    onCoinClickListener?.onCoinClick(this)
                }
            }
        }
    }


    inner class CoinInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewLogoCoin = itemView.imageViewLogoIcon
        val textViewSymbols = itemView.textViewSymbols
        val textViewPrice = itemView.textViewPrice
        val textViewLastUpdateTime = itemView.textViewLastUpdateTime
    }
    interface OnCoinClickListener{
        fun onCoinClick(coinPriceInfo: CoinPriceInfo)
    }
}