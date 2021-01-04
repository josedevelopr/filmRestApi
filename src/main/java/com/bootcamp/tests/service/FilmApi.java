package com.bootcamp.tests.service;

import com.bootcamp.tests.entity.Film;
import io.reactivex.Single;
import org.springframework.web.bind.annotation.PathVariable;
import retrofit2.http.GET;

import java.util.List;

public interface FilmApi
{
    @GET("/films")
    Single<List<Film>> getFilms();

    @GET("/films/{id}")
    Single<Film> getFilmById(@PathVariable(value = "id") String idFilm);
}
