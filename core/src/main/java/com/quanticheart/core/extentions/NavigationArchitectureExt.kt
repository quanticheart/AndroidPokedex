package com.quanticheart.core.extentions

import android.os.Bundle
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.ref.WeakReference

fun BottomNavigationView.setupNavigation(navController: NavController) {
    val weakReference = WeakReference(this)
    weakReference.get()?.setOnItemSelectedListener {
        NavigationUI.onNavDestinationSelected(it, navController)
        navController.popBackStack(it.itemId, inclusive = false)
        true
    }
    navController.addOnDestinationChangedListener(
        object : NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                if (weakReference.get() == null) {
                    navController.removeOnDestinationChangedListener(this)
                    return
                }
                weakReference.get()?.menu?.forEach { item ->
                    if (destination.hierarchy.any { it.id == item.itemId }) {
                        item.isChecked = true
                    }
                }
            }
        })
}

fun Fragment.navigateTo(idToNavigate: Int) = findNavController().navigate(idToNavigate)
