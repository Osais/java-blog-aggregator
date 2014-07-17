package aa.something.blabla.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aa.something.blabla.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

    User findByName(String name);

}
