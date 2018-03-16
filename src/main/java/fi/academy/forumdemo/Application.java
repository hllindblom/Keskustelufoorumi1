package fi.academy.forumdemo;

import fi.academy.forumdemo.configurations.UserServiceImpl;
import fi.academy.forumdemo.entities.Alue;
import fi.academy.forumdemo.entities.User;
import fi.academy.forumdemo.entities.UserRole;
import fi.academy.forumdemo.entities.Viesti;
import fi.academy.forumdemo.repositories.AlueRepository;
import fi.academy.forumdemo.repositories.UserRepository;
import fi.academy.forumdemo.repositories.UserRoleRepository;
import fi.academy.forumdemo.repositories.ViestiRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
    CommandLineRunner alusta(ViestiRepository vr, AlueRepository ar, UserRepository ur, UserRoleRepository urr, BCryptPasswordEncoder bcpe){
	    return args -> {
            UserServiceImpl usi = new UserServiceImpl();
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

            UserRole user = new UserRole("user");
            urr.save(user);
            UserRole admin = new UserRole("admin");
            urr.save(admin);
            User allu = new User("allu",  bcpe.encode("allu"), "allu@mail.com", "kuvausteksti", 1, user);
            ur.save(allu);
            User sami = new User("sami", bcpe.encode("sami"), "sami@mail.com", "kuvausteksti",1, user);
            ur.save(sami);
            User tiina = new User("tiina", bcpe.encode("tiina"), "tiina@mail.com", "kuvausteksti",1, user);
            ur.save(tiina);
            User hannaleena = new User("hannaleena", bcpe.encode("hannaleena"), "hannaleena@mail.com", "uusi Java, Java, Spring, SQL, JS", 1, user);
            ur.save(hannaleena);
            User adminUser = new User("admin", bcpe.encode("admin"), 1, admin);
            ur.save(adminUser);
            User anonyymi = new User("anonyymi", bcpe.encode("anonyymi"), 1, user);
            ur.save(anonyymi);


            Viesti v = new Viesti("Onko koodamisessa mitään järkeä?", allu, "moi mun mielestä koodaaminen on joskus ihan mukavaa puuhastelua. Mitä mieltä muut on?");
            v.setAlue(yleinen);
	        vr.save(v);
	        yleisetViestit.add(v);

	        Viesti v2 = new Viesti("Samu on äijä!", allu, "puhutaan tällä langalla miten kova jätkä samu on");
	        v2.setAlue(yleinen);
	        vr.save(v2);
	        yleisetViestit.add(v2);

	        Viesti v3 = new Viesti("buffered readeristä", tiina, "voisko joku kertoo mulle miten helvetissä buffered reader toimii?");
	        v3.setAlue(java);
	        vr.save(v3);
	        javaViestit.add(v3);

	        Viesti v4 = new Viesti("onko javaScript vaikeeta?", tiina, "en oo hirveesti koodannu javascriptii. Onks se vaikeeta?");
            v4.setAlue(javascript);
            vr.save(v4);
            javaScriptViestit.add(v4);

            Viesti v5 = new Viesti("Spring", hannaleena, "Tänne voitaisiin hei koota vähän käteviä tippejä liittyen Springiin!");
            v5.setAlue(spring);
            vr.save(v5);
            springViestit.add(v5);

            Viesti v6 = new Viesti(sami, "springi on kyllä aika hauskaa.");
            v6.setAlue(spring);
            v6.setParent(v5);
            vr.save(v6);
            springViestit.add(v6);

            Viesti v7 = new Viesti(sami, "tosi hienoja tietokantoja saa tehtyä tällä Spring datalla");
            v7.setAlue(spring);
            v7.setParent(v5);
            vr.save(v7);
            springViestit.add(v7);

            Viesti v8 = new Viesti("application.properties", anonyymi, "kerätään tänne application.properties best practices");
            v8.setAlue(spring);
            vr.save(v8);
            springViestit.add(v8);

            Viesti v9 = new Viesti(anonyymi, "mä käytän yleensä spring.jpa.hibernate.ddl-auto=create-drop -määritystä");
            v9.setAlue(spring);
            v9.setParent(v8);
            vr.save(v9);
            springViestit.add(v9);

            Viesti v10 = new Viesti(anonyymi, "No jaa.");
            v10.setAlue(yleinen);
            v10.setParent(v2);
            vr.save(v10);
            yleisetViestit.add(v10);

            Viesti v11 = new Viesti(anonyymi, "mäkään en tiedä kyl yhtään, java 8 all the way!");
            v11.setAlue(java);
            v11.setParent(v3);
            vr.save(v11);
            javaViestit.add(v11);

            Viesti v12 = new Viesti(hannaleena, "javascriptin kirjottaminen on helppoo kuin heinänteko kun se osaa");
            v12.setAlue(javascript);
            v12.setParent(v4);
            vr.save(v12);
            javaScriptViestit.add(v12);

            Viesti v13 = new Viesti("Java beans",sami,"Tykkään Java pavuista nestemäisessä muodossa. Entäs te?");
            v13.setAlue(java);
            vr.save(v13);
            javaViestit.add(v13);

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
