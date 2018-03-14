package fi.academy.forumdemo.repositories;

import fi.academy.forumdemo.entities.Alue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AlueRepository extends CrudRepository<Alue, String> {
    @Query("SELECT v from Alue v where nimi= :nimi")
    Alue haeAlue(@Param("nimi") String nimi);


}
