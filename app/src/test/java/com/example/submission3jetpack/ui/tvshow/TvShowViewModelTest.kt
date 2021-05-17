package com.example.submission3jetpack.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.submission3jetpack.data.MoviesTvData
import com.example.submission3jetpack.utils.DataDummy
import com.example.submission3jetpack.data.source.MovieTvShowRepository
import com.example.submission3jetpack.ui.viewmodels.TvShowViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get: Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvShowRepository: MovieTvShowRepository

    @Mock
    private lateinit var observer: Observer<List<MoviesTvData>>

    @Before
    fun setUp()
    {
        viewModel = TvShowViewModel(movieTvShowRepository)
    }

    @Test
    fun getTvShows()
    {
        val dummyTvShows = DataDummy.getTvShows()
        val tvShowsLiveData = MutableLiveData<List<MoviesTvData>>()
        tvShowsLiveData.value = dummyTvShows

        `when` (movieTvShowRepository.getTvShows()).thenReturn(tvShowsLiveData)
        val tvShowsEntity = viewModel.getTvShows().value
        verify(movieTvShowRepository).getTvShows()
        assertNotNull(tvShowsEntity)
        assertEquals(20, tvShowsEntity?.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)



    }
}