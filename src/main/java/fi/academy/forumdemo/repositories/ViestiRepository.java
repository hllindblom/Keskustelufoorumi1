package fi.academy.forumdemo.repositories;

import fi.academy.forumdemo.entities.Viesti;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ViestiRepository extends CrudRepository<Viesti, Integer> {
    

    @Query("SELECT v from Viesti v WHERE parent_viesti_id is NULL ")
    List<Viesti> haeViestitIlmanParenttia();




}
