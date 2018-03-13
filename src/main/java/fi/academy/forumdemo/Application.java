package fi.academy.forumdemo;

import fi.academy.forumdemo.entities.Alue;
import fi.academy.forumdemo.entities.Viesti;
import fi.academy.forumdemo.repositories.AlueRepository;
import fi.academy.forumdemo.repositories.ViestiRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
    CommandLineRunner alusta(ViestiRepository vr, AlueRepository ar){
	    return args -> {
            Alue yleinen = new Alue("Yleinen");
            ar.save(yleinen);

            Alue java = new Alue("Java");
            ar.save(java);

            Alue javascript = new Alue("JavaScript");
            ar.save(javascript);

            Alue spring = new Alue("Spring Framework");
            ar.save(spring);

            List<Viesti> yleisetViestit = new ArrayList<>();
            List<Viesti> javaViestit = new ArrayList<>();
            List<Viesti> javaScriptViestit = new ArrayList<>();
            List<Viesti> springViestit = new ArrayList<>();

            Viesti v = new Viesti("Koodaaminen on kivaa", "allu", "moi mun mielestä koodaaminen on joskus ihan mukavaa");
            v.setAlue(yleinen);
	        vr.save(v);
	        yleisetViestit.add(v);

	        Viesti v2 = new Viesti("Samu on äijä!", "allu", "puhutaan tällä langalla miten kova jätkä samu on");
	        v2.setAlue(yleinen);
	        vr.save(v2);
	        yleisetViestit.add(v2);

	        Viesti v3 = new Viesti("buffered readeristä", "pikku-allu", "voisko joku kertoo mulle miten helvetissä buffered reader toimii");
	        v3.setAlue(java);
	        vr.save(v3);
	        javaViestit.add(v3);

	        Viesti v4 = new Viesti("onko javaScript vaikeeta?", "allu p", "en oo hirveesti koodannu javascriptii. Onks se vaikeeta?");
            v4.setAlue(javascript);
            vr.save(v4);
            javaScriptViestit.add(v4);

            Viesti v5 = new Viesti("springiii!!", "allu", "tää on jokin viesti liittyen springiin");
            v5.setAlue(spring);
            vr.save(v5);
            springViestit.add(v5);

	        yleinen.setViestit(yleisetViestit);
	        ar.save(yleinen);

	        java.setViestit(javaViestit);
	        ar.save(java);

	        javascript.setViestit(javaScriptViestit);
	        ar.save(javascript);

	        spring.setViestit(springViestit);
	        ar.save(spring);
        };
    }
}
