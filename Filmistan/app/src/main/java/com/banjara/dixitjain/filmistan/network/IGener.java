package com.banjara.dixitjain.filmistan.network;

import com.banjara.dixitjain.filmistan.model.MusicModel;
import com.banjara.dixitjain.filmistan.model.Gener;
import com.banjara.dixitjain.filmistan.model.MovieResult;
import com.banjara.dixitjain.filmistan.model.MovieInfoModel;
import com.banjara.dixitjain.filmistan.model.MovieVideo;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface IGener {

    @GET("genre/movie/list?api_key=1834b2bf127bfb836148d8e028af5d68&language=en-US")
    Single<Gener> getGenerData();

    @GET("discover/movie?api_key=1834b2bf127bfb836148d8e028af5d68&language=en-US&sort_by=primary_release_date.desc&include_adult=false&include_video=true")
    Observable<MovieResult> getMovies(@Query("with_genres") String gener, @Query("primary_release_date.lte") String value, @Query("page") Integer page);

    @GET("movie/{movie_id}/videos?api_key=1834b2bf127bfb836148d8e028af5d68&language=en-US")
    Observable<MovieVideo> getTrailor(@Path("movie_id") int id );

    @GET("2.0/?method=chart.gettopartists&api_key=5e0dc76d3777e154748331a8e0b7d5ce&format=json")
    Observable<MusicModel> getTracks(@Query("page") Integer page);

    @GET("movie/upcoming?api_key=1834b2bf127bfb836148d8e028af5d68&language=en-US&page=1")
    Observable<MovieResult> getUpcomingMovies();

    @GET("movie/{movie_id}?api_key=1834b2bf127bfb836148d8e028af5d68&append_to_response=credits")
    Observable<MovieInfoModel> getMovieInfo(@Path("movie_id") int id);

}
