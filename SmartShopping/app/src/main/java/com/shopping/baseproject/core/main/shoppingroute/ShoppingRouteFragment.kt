package com.shopping.baseproject.core.main.shoppingroute

import android.view.View
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.shopping.baseproject.R
import com.shopping.baseproject.ShoppingRouteBinding
import com.shopping.baseproject.core.main.grocerylist.GroceryListViewModel
import com.shopping.baseproject.shared.base.BaseFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fr_shopping_route.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ShoppingRouteFragment :
    BaseFragment<ShoppingRouteBinding, GroceryListViewModel>(R.layout.fr_shopping_route) {

    override val viewModel by sharedViewModel<GroceryListViewModel>(from = { activity as ViewModelStoreOwner })
    private val adapter by lazy {
        MapAdapter {
            handleOnCellItemClick(it)
        }
    }
    private val safeArgs: ShoppingRouteFragmentArgs by navArgs()

    override fun setupViews() {
        binding?.rvRoute?.let {
            it.layoutManager = GridLayoutManager(context, 19)
            it.adapter = adapter
        }
        viewModel.getShopMapFromAPI(safeArgs.storeId.toInt())
        setupObservers()
        setupListeners()
        viewModel.getPathFromAPI(
            safeArgs.storeId.toInt()
        )
    }

    private fun setupObservers() {
        viewModel.storeRoute.observe(this, adapter::submitList)
        viewModel.cart.observeNonNull(this) {
            viewModel.getPathFromAPI(safeArgs.storeId.toInt())
            adapter.notifyDataSetChanged()
        }
        viewModel._mapCallSucceeded.observeNonNull(this) {
            if (it) {
                progress.visibility = View.GONE
                groupItems.visibility = View.VISIBLE
                fab_done.visibility = View.VISIBLE
                fab_scan.visibility = View.VISIBLE
            }
        }
    }
}
