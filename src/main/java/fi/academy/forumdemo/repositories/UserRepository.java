package fi.academy.forumdemo.repositories;

import fi.academy.forumdemo.entities.User;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
