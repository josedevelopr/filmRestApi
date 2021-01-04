package com.bootcamp.tests.service.Impl;

import com.bootcamp.tests.entity.Film;
import com.bootcamp.tests.service.FilmApi;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@Log4j2
@ExtendWith(MockitoExtension.class)
public class FilmApiServiceImplTest
{   @InjectMocks
    private FilmServiceImpl service;

    @Mock
    private FilmApi filmApi;

    Film film1 = null;
    Film film2 = null;
    Film film3 = null;
    Film film4 = null;
    Film film5 = null;

    @BeforeEach
    public void initialize()
    {
         film1 = new Film("1", "atitle1", "description1", "director1", "producer1", "1999","50");
         film2 = new Film("2", "title2", "description2", "director2", "producer2", "2000","45");
         film3 = new Film("3", "title3", "description3", "director3", "producer3", "1970","78");
         film4 = new Film("4", "title4", "description4", "director4", "producer4", "1994","36");
         film5 = new Film("5", "title5", "description5", "director5", "producer5", "2011","100");

    }

    @Test
    public void getSizeWhenReleaseDateIs1998()
    {
        List<Film> listResponse = Arrays.asList(film1, film2, film3, film4, film5);
        Single<List<Film>> films = Single.just(listResponse);

        when(filmApi.getFilms()).thenReturn(films);

        Observable<Film> response = service.findAllFilmsByReleaseDate(1998);

        Long expectedSize = 3L;
        Long actualSize = response.count().blockingGet();
        assertEquals("Sizes must be equal", expectedSize, actualSize);
        response.test().assertValueCount(3);
    }

    @Test
    public void validateFirstFilmWhenFindAllOfThemWhereFilmNameStartsWith()
    {
        List<Film> listResponse = Arrays.asList(film1, film2, film3, film4, film5);
        Single<List<Film>> films = Single.just(listResponse);
        when(filmApi.getFilms()).thenReturn(films);

        TestObserver<Film> testResponse = service.findAllFilmsWhereNameStartsWith("a").test();

        testResponse.awaitTerminalEvent();

        testResponse.assertSubscribed()
                    .assertComplete()
                    .assertTerminated()
                    .assertValueCount(1)
                    .assertValue(film1);
    }
}
