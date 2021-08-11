package com.arthur.github.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.arthur.github.R
import com.arthur.github.databinding.ActivityMainBinding
import com.arthur.github.utils.nav.NavigationDispatcher
import com.arthur.github.utils.nav.consume
import com.arthur.github.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var navigationDispatcher: NavigationDispatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        navigationDispatcher.navigationEmitter.observe(this) {
            it.consume { command ->
                Navigation.findNavController(this, R.id.host_container_fragment_main).command(this)
            }
        }

        binding.viewModel = viewModel
    }
}
