package com.example.movieviewerr.repository

//import androidx.paging.rxjava2.RxPagingSource
//import androidx.paging.rxjava3.RxPagingSource
//
//
//class PagingDataSource() : RxPagingSource<Int, Movies.Movie>() {
//
//    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Movies.Movie>> {
//        val position = params.key ?: 1
//
//        return service.popularMovieRx(apiKey, position, locale.language)
//            .subscribeOn(Schedulers.io())
//            .map { mapper.transform(it, locale) }
//            .map { toLoadResult(it, position) }
//            .onErrorReturn { LoadResult.Error(it) }
//    }
//
//    private fun toLoadResult(data: Movies, position: Int): LoadResult<Int, Movies.Movie> {
//        return LoadResult.Page(
//            data = data.movies,
//            prevKey = if (position == 1) null else position - 1,
//            nextKey = if (position == data.total) null else position + 1
//        )
//    }
//}