package aa.something.blabla.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aa.something.blabla.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

    Role findByName(String name);

}
