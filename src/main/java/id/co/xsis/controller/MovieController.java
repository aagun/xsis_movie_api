package id.co.xsis.controller;

import id.co.xsis.dao.MovieDao;
import id.co.xsis.dto.MovieDto;
import id.co.xsis.dto.ResponseDto;
import id.co.xsis.exception.MovieAlreadyExistsExceptions;
import id.co.xsis.exception.MovieNotFoundException;
import id.co.xsis.helper.Messages;
import id.co.xsis.model.Movie;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/v2/movies")
@Validated
public class MovieController {

    @Autowired
    private MovieDao movieDao;

    private static final Logger log = Logger.getLogger(MovieController.class.getName());

    @GetMapping(value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto getAllMovies() {
        Long totalRecord = movieDao.count();
        List<Movie> data = movieDao.findAll();
        return ResponseDto.ok(data, totalRecord);
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto getMovieById(
            @PathVariable("id") @NotNull(message = "Movie Id is required") Integer id) throws MovieNotFoundException {
        String msg = String.format(Messages.MOVIE_MSG_NOTFOUND, id);
        Movie movie = movieDao.findById(id).orElseThrow(() -> new MovieNotFoundException(msg));
        return ResponseDto.ok(List.of(movie));
    }

    @PatchMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto updateMovieById(
            @PathVariable("id") @NotNull(message = "Movie Id is required") Integer id,
            @RequestBody @Valid MovieDto movieDto) throws MovieNotFoundException, MovieAlreadyExistsExceptions {

        // 1. Does the movie exist?
        String msg = String.format(Messages.MOVIE_MSG_NOTFOUND, id);
        Movie movie = movieDao.findById(id).orElseThrow(() -> new MovieNotFoundException(msg));

        // 2. Validate constraint movie id
        if (!movieDto.getId().equals(id) && movieDao.existsById(movieDto.getId())) {
            String messageFormat = String.format("Data failed to update, %s", Messages.MOVIE_MSG_ALREADY_EXIST);
            throw new MovieAlreadyExistsExceptions(String.format(messageFormat, movieDto.getId()));
        }

        // 3. Do update
        movie.setId(movieDto.getId());
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setImage(movieDto.getImage());
        movie.setRating(movieDto.getRating());
        movie.setDuration(movieDto.getDuration());
        movie.setTrailerLink(movieDto.getTrailerLink());

        return doSaveUpdate(false, movie);
    }

    @DeleteMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto deleteMovieById(
            @PathVariable("id") @NotNull(message = "Id is required") Integer id) throws MovieNotFoundException {

        // 1. Does the movie exist?
        if (!movieDao.existsById(id)) {
            throw new MovieNotFoundException(String.format(Messages.MOVIE_MSG_NOTFOUND, id));
        }

        // 2. Do delete
        try {
            movieDao.deleteById(id);
        } catch (Exception e) {
            log.log(Level.SEVERE, null, e);
            return ResponseDto.err(String.format(Messages.MOVIE_MSG_FAILED, id, "delete"));
        }

        return ResponseDto.ok(String.format(Messages.MOVIE_MSG_SUCCESS, id, "deleted"));
    }

    @PostMapping(value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto saveMovie(@RequestBody Movie movie) throws MovieAlreadyExistsExceptions {
        if (movieDao.existsById(movie.getId())) {
            throw new MovieAlreadyExistsExceptions(String.format(Messages.MOVIE_MSG_ALREADY_EXIST, movie.getId()));
        }
        return doSaveUpdate(true, movie);
    }

    private ResponseDto doSaveUpdate(Boolean isSave, Movie movie) {
        try {
            movieDao.save(movie);
        } catch (Exception e) {
            log.log(Level.SEVERE, null, e);
            return ResponseDto.err(String.format(
                    Messages.MOVIE_MSG_FAILED,
                    movie.getId(),
                    isSave ? "save" : "update")
            );
        }

        return ResponseDto.ok(String.format(
                Messages.MOVIE_MSG_SUCCESS,
                movie.getId(),
                isSave ? "saved" : "updated")
        );
    }

}
