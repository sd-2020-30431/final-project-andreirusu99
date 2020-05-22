package com.shopping.baseproject.core.main.grocerylist

import androidx.recyclerview.widget.DiffUtil
import com.shopping.baseproject.ItemGroceryCategoryBinding
import com.shopping.baseproject.R
import com.shopping.baseproject.data.models.AisleModel
import com.shopping.baseproject.shared.base.BaseAdapter
import com.squareup.picasso.Picasso

class GroceryListAdapter(private val onItemClick: (AisleModel) -> Unit) :
    BaseAdapter<AisleModel, ItemGroceryCategoryBinding>(
        R.layout.item_grocery_category,
        object : DiffUtil.ItemCallback<AisleModel>() {
            override fun areItemsTheSame(oldItem: AisleModel, newItem: AisleModel) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: AisleModel, newItem: AisleModel) =
                oldItem == newItem
        }) {

    override fun bind(binding: ItemGroceryCategoryBinding, item: AisleModel) {
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
