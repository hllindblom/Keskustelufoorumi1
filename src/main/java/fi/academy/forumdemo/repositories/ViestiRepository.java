package fi.academy.forumdemo.repositories;

import fi.academy.forumdemo.entities.Viesti;
import org.springframework.data.repository.CrudRepository;

public interface ViestiRepository extends CrudRepository<Viesti, Integer> {


}
