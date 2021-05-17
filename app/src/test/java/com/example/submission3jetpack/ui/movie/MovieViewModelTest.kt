package com.example.submission3jetpack.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.submission3jetpack.data.MoviesTvData
import com.example.submission3jetpack.utils.DataDummy
import com.example.submission3jetpack.data.source.MovieTvShowRepository
import com.example.submission3jetpack.ui.viewmodels.MovieViewModel
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
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get: Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvShowRepository: MovieTvShowRepository

    @Mock private lateinit var observer: Observer<List<MoviesTvData>>

    @Before
    fun setUp()
    {
        viewModel = MovieViewModel(movieTvShowRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = DataDummy.getMovies()
        val moviesLiveData = MutableLiveData<List<MoviesTvData>>()
        moviesLiveData.value = dummyMovies

        `when`(movieTvShowRepository.getMovies()).thenReturn(moviesLiveData)
        val movieEntities = viewModel.getMovies().value
        verify(movieTvShowRepository).getMovies()
        assertNotNull(movieEntities)
        assertEquals(20, movieEntities?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}