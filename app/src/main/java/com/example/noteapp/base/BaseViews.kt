package com.example.noteapp.base

interface BaseViews {

   fun showLoading()
   fun hideLoading()
   fun isInternetAvailable():Boolean
   fun showInternetError()

   fun showEmpty()

fun showServerError()

fun logOutUser()


}