package fi.academy.forumdemo.repositories;

import fi.academy.forumdemo.entities.Alue;
import fi.academy.forumdemo.entities.Viesti;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ViestiRepository extends CrudRepository<Viesti, Integer> {
    // Haetaan viestit laskevassa aikajärjestyksessä joka näytetään etusivulla.
    @Query("SELECT v from Viesti v order by v.aika desc")
    List<Viesti> ViestitAikajarjestyksessa();

    // Haetaan viestit joilla ei ole parenttia joista saadaan otsikot viestiketjuille.
    @Query("SELECT v from Viesti v WHERE parent_viesti_id is NULL and v.alue = :alue")
    List<Viesti> haeViestitIlmanParenttia(@Param("alue") Alue alue);

    // Haetaan viestit joissa hakusana esiintyy jossain kohti tekstiä.
    @Query("Select v from Viesti v where teksti like CONCAT('%',:hakusana,'%')")
    List<Viesti> haeViestitHakusanalla(@Param("hakusana")String hakusana);

    // Haetaan viestit joissa hakusana esiintyy kirjoittajan nimessä.
    @Query("select v from Viesti v where v.kirjoittaja.username LIKE CONCAT('%',:hakusana,'%')")
    List<Viesti> haeKirjoittajanViestit(@Param("hakusana")String hakusana);






}
