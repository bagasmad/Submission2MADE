package com.example.submission3jetpack.ui.detail

import com.example.submission3jetpack.ui.viewmodels.DetailViewModel
import com.example.submission3jetpack.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel

    //data movies
    private val dummyMovie = DataDummy.getMovies()[0]

    //data tv shows
    private val dummyShow = DataDummy.getTvShows()[0]

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
    }


    @Test
    fun getMoviesData() {
        //test untuk data movie
        viewModel.setData(dummyMovie)
        val detailData = viewModel.getData()
        assertNotNull(detailData)
        assertEquals(dummyMovie.original_title, detailData.original_title)
        assertEquals(dummyMovie.overview, detailData.overview)
        assertEquals(dummyMovie.original_language, detailData.original_language)
        assertEquals(dummyMovie.popularity, detailData.popularity, 0.0)
        assertEquals(dummyMovie.vote_average, detailData.vote_average, 0.0)
        assertEquals(dummyMovie.vote_count, detailData.vote_count)
        //test untuk data shows

    }

    @Test
    fun getTvShowsData(){
        viewModel.setData(dummyShow)
        val detailData = viewModel.getData()
        assertNotNull(detailData)
        assertEquals(dummyShow.original_title, detailData.original_title)
        assertEquals(dummyShow.overview, detailData.overview)
        assertEquals(dummyShow.original_language, detailData.original_language)
        assertEquals(dummyShow.popularity, detailData.popularity, 0.0)
        assertEquals(dummyShow.vote_average, detailData.vote_average, 0.0)
        assertEquals(dummyShow.vote_count, detailData.vote_count)

    }
}