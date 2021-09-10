package com.example.movieviewerr.ui.view.fragments

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.movieviewerr.R
import com.example.movieviewerr.ui.view.MainActivity

open class BaseFragment(layoutId: Int) : Fragment(layoutId)  {

    lateinit var dialog: AlertDialog


    fun showLoading(cancelable: Boolean, activity: MainActivity) {

        val layoutInflater = LayoutInflater.from(activity.applicationContext)
        val view = layoutInflater.inflate(R.layout.loading, null)
        val progressDialog = AlertDialog.Builder(activity)
        progressDialog.setView(view)
        progressDialog.setCancelable(cancelable)

        dialog = progressDialog.create()
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable())
        dialog.show()
    }

    fun hideLoading() {
        if (this::dialog.isInitialized) {
            dialog.dismiss()
        }
    }
}