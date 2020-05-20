package com.shopping.baseproject.core.shoppingCart

import androidx.recyclerview.widget.DiffUtil
import com.shopping.baseproject.ItemCartBinding
import com.shopping.baseproject.R
import com.shopping.baseproject.data.models.AisleModel
import com.shopping.baseproject.shared.base.BaseAdapter
import com.squareup.picasso.Picasso

class CartAdapter(private val onItemClick: (AisleModel) -> Unit) :
    BaseAdapter<AisleModel, ItemCartBinding>(
        R.layout.item_cart,
        object : DiffUtil.ItemCallback<AisleModel>() {
            override fun areItemsTheSame(oldItem: AisleModel, newItem: AisleModel) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: AisleModel, newItem: AisleModel) =
                oldItem == newItem
        }) {

    override fun bind(binding: ItemCartBinding, item: AisleModel) {
        with(binding) {
            aisle = item
            Picasso.get()
                .load(item.icon)
                .error(R.drawable.ic_loading)
                .into(ivCategory)
            layoutCategory.setOnClickListener { onItemClick(item) }
        }
    }
}
