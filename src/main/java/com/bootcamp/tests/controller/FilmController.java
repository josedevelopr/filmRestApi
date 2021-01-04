package com.bootcamp.tests.controller;

import com.bootcamp.tests.entity.Film;
import com.bootcamp.tests.service.Impl.FilmServiceImpl;
import io.reactivex.Observable;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/film")
public class FilmController
{   @Autowired
    private final FilmServiceImpl service;

    @GetMapping("/releaseDate/{year}")
    public Observable<Film> getFilmsByReleaseDate(@PathVariable int year)
    {
        return service.findAllFilmsByReleaseDate(year);
    }

    @GetMapping
    public Observable<Film> getAll()
    {
        return service.findAll();
    }
}
