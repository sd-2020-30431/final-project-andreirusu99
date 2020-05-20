package com.shopping.baseproject.core.shoppingCart

import android.view.View
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.shopping.baseproject.R
import com.shopping.baseproject.ShoppingCartBinding
import com.shopping.baseproject.core.main.grocerylist.GroceryListViewModel
import com.shopping.baseproject.core.shoppingCart.CartAdapter
import com.shopping.baseproject.data.models.AisleModel
import com.shopping.baseproject.shared.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ShoppingCartFragment :
    BaseFragment<ShoppingCartBinding, GroceryListViewModel>(R.layout.fr_shopping_cart) {

    override val viewModel by sharedViewModel<GroceryListViewModel>(from = { activity as ViewModelStoreOwner })
    private val adapter by lazy {
        CartAdapter { handleOnCampaignItemClick(it) }
    }

    override fun setupViews() {
        binding?.rvShoppingCart?.adapter = adapter
        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        viewModel.cart.observe(this, adapter::submitList)
        viewModel.cartCounter.observeNonNull(this) {
            if (it==0)
                binding?.tvEmptyCart?.visibility= View.VISIBLE
            else
                binding?.tvEmptyCart?.visibility= View.GONE
        }
    }

    private fun handleOnCampaignItemClick(aisleModel: AisleModel) {
        viewModel.addOrRemoveFromCart(aisleModel)
    }

    private fun setupListeners() = with(findNavController()) {
        binding?.fabCart?.setOnClickListener {
            navigate(ShoppingCartFragmentDirections.actionShoppingCartFragmentToMapFragment())
        }
    }
}
