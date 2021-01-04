package com.bootcamp.tests.service;

import com.bootcamp.tests.entity.Film;
import io.reactivex.Observable;

public interface FilmService
{
    Observable<Film> findAll();
    Observable<Film> findAllFilmsByReleaseDate(int year);
    Observable<Film> findAllFilmsWhereNameStartsWith(String startsWith);
}
