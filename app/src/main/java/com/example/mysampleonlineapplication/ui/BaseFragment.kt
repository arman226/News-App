package com.example.mysampleonlineapplication.ui

import androidx.annotation.LayoutRes
import dagger.android.support.DaggerFragment
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseFragment <VDB:ViewDataBinding>:DaggerFragment(){
    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModelVariable(): Pair<Int, ViewModel>?
}