package com.sstudio.submissionbajetpackpro.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.sstudio.submissionbajetpackpro.data.MovieTvRepository
import com.sstudio.submissionbajetpackpro.data.source.local.entity.MovieEntity
import com.sstudio.submissionbajetpackpro.utils.DataDummy
import com.sstudio.submissionbajetpackpro.vo.Resource
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvRepository: MovieTvRepository

    @Mock
    private lateinit var observer: Observer<Resource<List<MovieEntity>>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieTvRepository)
    }

    @Test
    fun testGetMovies() {
        val dataMovies = Resource.success(DataDummy.generateDummyMovies())
        val movies = MutableLiveData<Resource<List<MovieEntity>>>()
        movies.value = dataMovies

        Mockito.`when`(movieTvRepository.getAllMovie()).thenReturn(movies)
        val movieEntities = viewModel.listMovie?.value?.data
        Mockito.verify(movieTvRepository).getAllMovie()
        Assert.assertNotNull(movieEntities)
        Assert.assertEquals(5, movieEntities?.size)

        viewModel.listMovie?.observeForever(observer)
        Mockito.verify(observer).onChanged(dataMovies)

    }
}