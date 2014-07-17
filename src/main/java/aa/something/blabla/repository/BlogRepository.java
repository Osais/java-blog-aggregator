package aa.something.blabla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aa.something.blabla.entity.Blog;
import aa.something.blabla.entity.User;

public interface BlogRepository extends JpaRepository<Blog, Integer>{
    
    List<Blog> findByUser(User user);

}
