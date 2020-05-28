package com.shopping.baseproject.core.main.doneShopping

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.shopping.baseproject.DoneShoppingBinding
import com.shopping.baseproject.R
import com.shopping.baseproject.shared.base.BaseFragment
import kotlinx.coroutines.delay
import org.koin.androidx.viewmodel.ext.android.viewModel

class DoneShoppingFragment :
    BaseFragment<DoneShoppingBinding, DoneShoppingViewModel>(R.layout.fr_done_shopping) {

    override val viewModel by viewModel<DoneShoppingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.backButton?.setOnClickListener {
         findNavController().navigate(DoneShoppingFragmentDirections.actionDoneShoppingFragmentToGroceryListFragment())
        }
        val anim = binding?.ivAnimatedCheck?.drawable as Animatable
        anim.start()
    }
}