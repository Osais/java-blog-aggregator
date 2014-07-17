package aa.something.blabla.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import aa.something.blabla.entity.Blog;
import aa.something.blabla.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{
    
    List<Item> findByBlog(Blog blog, Pageable pageable);
    
    Item findByBlogAndLink(Blog blog, String link);

}
