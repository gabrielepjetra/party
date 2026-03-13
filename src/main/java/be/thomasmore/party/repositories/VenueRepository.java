package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VenueRepository extends CrudRepository<Venue,Integer> {
    @Query("SELECT v FROM Venue v WHERE (:minCapacity IS NULL OR :minCapacity <= v.capacity) and " +
            "(:maxCapacity IS NULL OR :maxCapacity >= v.capacity) and " +
            "(:maxDistance IS NULL OR :maxDistance >= v.distanceFromPublicTransportInKm) and " +
            "(:filterFood IS NULL OR :filterFood = v.foodProvided) and " +
            "(:filterIndoor IS NULL OR :filterIndoor = v.indoor) and " +
            "(:filterOutdoor IS NULL OR :filterOutdoor = v.outdoor)")
    List<Venue> findBYFilter(@Param("minCapacity") Integer minCapacity,
                             @Param("maxCapacity")  Integer maxCapacity,
                             @Param("maxDistance") Double maxDistance,
                             @Param("filterFood") Boolean filterFood,
                             @Param("filterIndoor") Boolean filterIndoor,
                             @Param("filterOutdoor")  Boolean filterOutdoor);
}
