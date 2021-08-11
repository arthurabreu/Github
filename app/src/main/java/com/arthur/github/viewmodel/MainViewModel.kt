package com.arthur.github.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arthur.github.network.onError
import com.arthur.github.network.onSuccess
import com.arthur.github.repository.SampleRepository
import com.arthur.github.utils.nav.NavigationDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val navigationDispatcher: NavigationDispatcher,
    private val repository: SampleRepository
) : ViewModel() {

    // Navigating with navigation dispatcher, consumed by MainActivity
    fun onLogInClicked() {
        navigationDispatcher.emit {
            popBackStack()
        }
    }

    fun fetchNames() {
        viewModelScope.launch {
            repository.getName()
                .onSuccess {
                    // do something with result
                }
                .onError {
                    // do something with error
                }
        }
    }
}
