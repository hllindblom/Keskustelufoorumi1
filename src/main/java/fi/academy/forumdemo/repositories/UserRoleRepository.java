package fi.academy.forumdemo.repositories;

import fi.academy.forumdemo.entities.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, String> {
    UserRole findByRole(String role);
}
