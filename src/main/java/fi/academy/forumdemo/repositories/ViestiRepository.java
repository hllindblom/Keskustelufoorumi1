package fi.academy.forumdemo.repositories;

import fi.academy.forumdemo.entities.Alue;
import fi.academy.forumdemo.entities.Viesti;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ViestiRepository extends CrudRepository<Viesti, Integer> {



    @Query("SELECT v from Viesti v WHERE parent_viesti_id is NULL and v.alue = :alue")
    List<Viesti> haeViestitIlmanParenttia(@Param("alue") Alue alue);




}
