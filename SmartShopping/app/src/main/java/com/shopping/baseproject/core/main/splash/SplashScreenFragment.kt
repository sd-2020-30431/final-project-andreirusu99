package com.shopping.baseproject.core.main.splash

import android.os.Bundle
import android.view.View
import com.google.android.gms.tasks.OnCompleteListener
import com.shopping.baseproject.R
import com.shopping.baseproject.SplashScreenBinding
import com.shopping.baseproject.shared.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("MagicNumber")
class SplashScreenFragment :
    BaseFragment<SplashScreenBinding, SplashScreenViewModel>(R.layout.fr_splash_screen) {
    override val viewModel by viewModel<SplashScreenViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        viewModel.start()

    }

    override fun setupViews() {
        binding?.splashImage?.animate()?.scaleX(2.0f)?.scaleY(2.0f)?.setDuration(2000)
            ?.rotation(360f)
            ?.start()

        binding?.splashScreenText?.animate()?.scaleX(1.5f)?.scaleY(1.5f)?.setDuration(2000)?.start()
    }
}
