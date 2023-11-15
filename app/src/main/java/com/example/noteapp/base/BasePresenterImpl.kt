package com.example.noteapp.base

import androidx.annotation.NonNull
import io.reactivex.rxjava3.disposables.Disposable

open class BasePresenterImpl:BasePresenter {

    @NonNull
    var disposable:Disposable?=null


    override fun onStop() {

        disposable?.dispose()

    }
}