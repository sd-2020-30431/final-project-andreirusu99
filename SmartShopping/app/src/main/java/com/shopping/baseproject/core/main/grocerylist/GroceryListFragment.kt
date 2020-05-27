package com.shopping.baseproject.core.main.grocerylist

import android.view.View
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.shopping.baseproject.GroceryListBinding
import com.shopping.baseproject.R
import com.shopping.baseproject.data.models.AisleModel
import com.shopping.baseproject.shared.base.BaseFragment
import kotlinx.android.synthetic.main.fr_grocery_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class GroceryListFragment :
    BaseFragment<GroceryListBinding, GroceryListViewModel>(R.layout.fr_grocery_list) {

    override val viewModel by sharedViewModel<GroceryListViewModel>(from = { activity as ViewModelStoreOwner })
    private val adapter by lazy { GroceryListAdapter { handleOnAisleItemClick(it) } }

    override fun setupViews() {
        binding?.rvGroceryList?.adapter = adapter
        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        viewModel.groceryList.observe(this) {
            fab_cart.visibility = View.VISIBLE
            rv_grocery_list.visibility = View.VISIBLE
            progress.visibility = View.GONE
            adapter.submitList(it)
        }
        viewModel.cartCounter.observeNonNull(this) {
            binding?.fabCart?.count = it
        }
    }

    private fun handleOnAisleItemClick(aisleModel: AisleModel) {
        viewModel.addOrRemoveFromCart(aisleModel)
        adapter.notifyDataSetChanged()
    }

    private fun setupListeners() = with(findNavController()) {
        binding?.fabCart?.setOnClickListener {
            navigate(GroceryListFragmentDirections.actionGroceryListFragmentToShoppingCartFragment())
        }
    }
}
