package com.shopping.baseproject.core.main.shoppingroute

import androidx.recyclerview.widget.DiffUtil
import com.shopping.baseproject.CellMapBinding
import com.shopping.baseproject.R
import com.shopping.baseproject.data.models.StoreCellModel
import com.shopping.baseproject.shared.base.BaseAdapter

class MapAdapter(private val onItemClick: (StoreCellModel) -> Unit) :
    BaseAdapter<StoreCellModel, CellMapBinding>(R.layout.cell_item,
        object : DiffUtil.ItemCallback<StoreCellModel>() {
            override fun areItemsTheSame(oldItem: StoreCellModel, newItem: StoreCellModel) =
                oldItem.type == newItem.type

            override fun areContentsTheSame(oldItem: StoreCellModel, newItem: StoreCellModel) =
                oldItem == newItem
        }) {

    private lateinit var clickedStoreCellModel: StoreCellModel

    override fun bind(binding: CellMapBinding, item: StoreCellModel) = with(binding) {
        isInPath = item.isInPath
        color = item.type
        isYouHere = ::clickedStoreCellModel.isInitialized && item.id == clickedStoreCellModel.id
        aislePin.setOnClickListener {
            onItemClick(item)
            clickedStoreCellModel = item
        }
    }
}
