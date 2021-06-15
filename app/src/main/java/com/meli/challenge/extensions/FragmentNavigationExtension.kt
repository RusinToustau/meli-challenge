package com.meli.challenge.extensions

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun AppCompatActivity.showFragment(
    fragment: Fragment, addToBackStack: Boolean, clearStack: Boolean = false, @IdRes containerViewId: Int) {

    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(containerViewId, fragment)
    if (addToBackStack) {
        transaction.addToBackStack(null)
    }
    if (clearStack) {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
    transaction.commitAllowingStateLoss()

}