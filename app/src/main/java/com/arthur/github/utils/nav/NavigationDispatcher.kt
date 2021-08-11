package com.arthur.github.utils.nav

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class NavigationDispatcher
@Inject constructor() {
    private val _navigationEmitter = MutableLiveData<SingleEvent<NavigationCommand>>()
    val navigationEmitter: LiveData<SingleEvent<NavigationCommand>> = _navigationEmitter

    fun emit(navigationCommand: NavigationCommand) {
        _navigationEmitter.value = SingleEvent(navigationCommand)
    }
}
