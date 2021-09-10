package com.example.movieviewerr.ui.view.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieviewerr.data.MoviesData
import com.example.movieviewerr.data.Result
import com.example.movieviewerr.repository.Resource
import com.example.movieviewerr.repository.AppRepository
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appRepository: AppRepository
):ViewModel() {

    private var internetDisposable: Disposable? = null
    private val compositeDisposable = CompositeDisposable()
    val moviesData = MutableLiveData<Resource<List<Result>>>()
    private var allowLoading = true

    fun listenForMovies(){

         internetDisposable = ReactiveNetwork.observeInternetConnectivity()
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe { isConnectedToInternet ->

                 when(isConnectedToInternet){

                     true -> {
                         if (allowLoading){

                             fetchMovies()
                         }
                     }

                     false -> {
                         moviesData.postValue(Resource.error("No Internet", null))
                         allowLoading = true
                     }
                 }
             }

    }

    private fun fetchMovies() {
        allowLoading = false
        moviesData.postValue(Resource.loading(null))

        compositeDisposable.add(
            appRepository.requestMovies()
                .subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
                .subscribe({ movies ->
                    //Temporary get only 1 page

                    moviesData.postValue(Resource.success(movies.results))
                }, {
                    moviesData.postValue(Resource.error("Couldn't fetch the data: ${it.message}", null))
                    allowLoading = true

                })
        )
    }



}