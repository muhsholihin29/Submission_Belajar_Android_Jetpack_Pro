package com.sstudio.submissionbajetpackpro.core.data.source.remote.api

import com.sstudio.submissionbajetpackpro.core.data.source.remote.response.MovieResponse
import com.sstudio.submissionbajetpackpro.core.data.source.remote.response.TvResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): MovieResponse.Result

    @GET("discover/movie")
    suspend fun getDiscoverMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
        @Query("page") page: Int,
        @Query("release_date.gte") dateGte: String,
        @Query("release_date.lte") dateLte: String,
        @Query("vote_count.gte") voteCountGte: Int,
        @Query("with_cast") cast: String,
        @Query("with_genres") genre: String,
        @Query("with_original_language") region: String
    ): MovieResponse

    @GET("tv/popular")
    suspend fun getPopularTv(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): TvResponse

    @GET("tv/on_the_air")
    suspend fun getOnAirTv(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): TvResponse

    @GET("tv/airing_today")
    suspend fun getAiringTodayTv(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): TvResponse

    @GET("tv/top_rated")
    suspend fun getTopRatedTv(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): TvResponse

    @GET("tv/{movie_id}")
    suspend fun getTvDetail(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): TvResponse.Result

    @GET("discover/tv")
    suspend fun getDiscoverTv(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
        @Query("page") page: Int,
        @Query("air_date.gte") dateGte: String,
        @Query("air_date.lte") dateLte: String,
        @Query("vote_count.gte") voteCountGte: Int,
        @Query("with_genres") genre: String,
        @Query("with_original_language") region: String
    ): MovieResponse

    @GET("search/movie")
    suspend fun getSearchMovie(
        @Query("query") query: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): MovieResponse

    @GET("search/tv")
    suspend fun getSearchTv(
        @Query("query") query: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): TvResponse
}