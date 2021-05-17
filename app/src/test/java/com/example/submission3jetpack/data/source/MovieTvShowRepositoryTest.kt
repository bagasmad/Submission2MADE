package com.example.submission3jetpack.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.submission3jetpack.utils.DataDummy
import com.example.submission3jetpack.data.source.remote.RemoteDataSource
import com.example.submission3jetpack.utils.LiveDataTestUtil
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.mock
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.doAnswer

class MovieTvShowRepositoryTest {
    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieTvShowRepository = FakeMovieTvShowRepository(remote)
    private val movieResponse = DataDummy.generateRemoteMovieData()
    private val tvResponse = DataDummy.generateRemoteTvShowData()


    @Test
    fun getMovies() {
        doAnswer{invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadDataCallback)
                .onAllDataReceived(movieResponse)
            null
        }.`when`(remote).getMovies(any())
        val movieEntities = LiveDataTestUtil.getValue(movieTvShowRepository.getMovies())
        verify(remote).getMovies(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponse.results.size, movieEntities.size)
    }

    @Test
    fun getTvShows() {
        doAnswer{invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadDataCallback)
                .onAllDataReceived(tvResponse)
            null
        }.`when`(remote).getTvShow(any())
        val tvShowEntities = LiveDataTestUtil.getValue(movieTvShowRepository.getTvShows())
        verify(remote).getTvShow(any())
        assertNotNull(tvShowEntities)
        assertEquals(movieResponse.results.size, tvShowEntities.size)
    }
}