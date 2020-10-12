package com.triarc.android.lib.support.manager

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import java.lang.IllegalStateException
import androidx.fragment.app.FragmentManager as AndroidFragmentManager

/**
 * Created by Devanshu Verma on 20 May, 2020
 */
class FragmentManager constructor(private val context: Context, private val manager: AndroidFragmentManager) {

    fun addFragment(fragmentContainer: Int, fragmentClassName: String, bundle: Bundle? = null, transition: Int = FragmentTransaction.TRANSIT_NONE, transitionStyle: Int = FragmentTransaction.TRANSIT_NONE) {
        val targetFragment = Fragment.instantiate(context, fragmentClassName)
        targetFragment.arguments = bundle

        val currentFragment = findFragmentById(fragmentContainer)

        manageFragment(fragmentContainer = fragmentContainer, targetFragment = targetFragment, tag = fragmentClassName, currentFragment =
            if (currentFragment != null) currentFragment::class.java.name else fragmentClassName, transition = transition, transitionStyle = transitionStyle)
    }

    fun replaceFragment(fragmentContainer: Int, fragmentClassName: String, bundle: Bundle? = null, transition: Int = FragmentTransaction.TRANSIT_NONE, transitionStyle: Int = FragmentTransaction.TRANSIT_NONE) {
        if(findFragmentById(fragmentContainer) == null)
            throw IllegalStateException("Fragment backstack is empty")

        val targetFragment = Fragment.instantiate(context, fragmentClassName)
        targetFragment.arguments = bundle

        manageFragment(fragmentContainer, targetFragment, fragmentClassName, transition, transitionStyle)
    }

    fun removeFragment(tag: String) = removeFragment(findFragmentByTag(tag))

    fun removeFragment(fragmentContainer: Int) = removeFragment(findFragmentById(fragmentContainer))

    fun findFragmentById(fragmentContainer: Int): Fragment? = manager.findFragmentById(fragmentContainer)

    fun findFragmentByTag(currentFragment: String): Fragment? = manager.findFragmentByTag(currentFragment)

    fun getBackStackEntryCount(): Int = manager.backStackEntryCount

    fun popBackStack(transition: Int = FragmentTransaction.TRANSIT_NONE, transitionStyle: Int = FragmentTransaction.TRANSIT_NONE) {
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.setTransition(transition)
        transaction.setTransitionStyle(transitionStyle)
        manager.popBackStack()
    }

    private fun removeFragment(currentFragment: Fragment?) {
        currentFragment?.let {fragment ->
            val transaction: FragmentTransaction = manager.beginTransaction()
            transaction.remove(fragment)
            transaction.commitAllowingStateLoss()
            popBackStack()
        }
    }

    private fun manageFragment(fragmentContainer: Int, targetFragment: Fragment, tag: String, transition: Int = FragmentTransaction.TRANSIT_NONE, transitionStyle: Int = FragmentTransaction.TRANSIT_NONE) {
        val transaction = manager.beginTransaction()

        transaction.replace(fragmentContainer, targetFragment, tag)
        transaction.addToBackStack(tag)
        transaction.setTransition(transition)
        transaction.setTransitionStyle(transitionStyle)
        transaction.commitAllowingStateLoss()
        manager.executePendingTransactions()
    }

    private fun manageFragment(fragmentContainer: Int, targetFragment: Fragment, tag: String, currentFragment: String?, transition: Int = FragmentTransaction.TRANSIT_NONE, transitionStyle: Int = FragmentTransaction.TRANSIT_NONE) {
        val transaction = manager.beginTransaction()

        if(currentFragment != null) {
            findFragmentByTag(currentFragment)?.let {
                transaction.hide(it)
            }
        }

        transaction.add(fragmentContainer, targetFragment, tag)
        transaction.addToBackStack(tag)
        transaction.setTransition(transition)
        transaction.setTransitionStyle(transitionStyle)
        transaction.commitAllowingStateLoss()
        manager.executePendingTransactions()
    }
}
