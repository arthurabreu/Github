package com.arthur.github.utils.nav

import android.content.Context
import androidx.navigation.NavController

typealias NavigationCommand = NavController.(Context) -> Unit
