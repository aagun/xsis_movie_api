package id.co.xsis.dao;

import id.co.xsis.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDao extends JpaRepository<Movie, Integer> {
    Boolean deleteMovieById(Integer id);
}
