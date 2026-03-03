package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ArtistRepository extends CrudRepository<Artist,Integer> {
    @Query("select a from Artist a where :keyword is null or " +
            "lower(a.artistName) like lower(concat('%', :keyword, '%')) or " +
            "lower(a.bio) like lower(concat('%', :keyword, '%')) or " +
            "lower(a.genre) like lower(concat('%', :keyword, '%')) or " +
            "lower(a.portfolio) like lower(concat('%', :keyword, '%'))")
    List<Artist> findByFilter(@Param("keyword") String keyword);
}
