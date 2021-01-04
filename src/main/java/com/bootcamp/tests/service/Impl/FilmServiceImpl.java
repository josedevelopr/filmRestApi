package com.bootcamp.tests.service.Impl;

import com.bootcamp.tests.entity.Film;
import com.bootcamp.tests.service.FilmApi;
import com.bootcamp.tests.service.FilmService;
import io.reactivex.Observable;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static io.reactivex.schedulers.Schedulers.io;

@Log4j2
@RequiredArgsConstructor
@Service
public class FilmServiceImpl implements FilmService
{   private final FilmApi filmApi;

    @Override
    public Observable<Film> findAll()
    {
        return filmApi.getFilms().toObservable().flatMapIterable(list -> list);
    }

    @Override
    public Observable<Film> findAllFilmsByReleaseDate(int year)
    {
        return filmApi.getFilms()
                      .toObservable()
                      .flatMapIterable(list -> list)
                      .filter(f -> Integer.parseInt(f.getRelease_date()) > year);
    }

    @Override
    public Observable<Film> findAllFilmsWhereNameStartsWith(String startsWith)
    {
        return filmApi.getFilms()
                .toObservable()
                .flatMapIterable(list -> list)
                .filter(film -> film.getTitle().startsWith(startsWith))
                .concatMap(f -> Observable.just(f).delay(5, TimeUnit.SECONDS))
                .subscribeOn(io());
    }
}
