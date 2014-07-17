package aa.something.blabla.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import aa.something.blabla.entity.Blog;
import aa.something.blabla.entity.Item;
import aa.something.blabla.entity.User;
import aa.something.blabla.exception.RssException;
import aa.something.blabla.repository.BlogRepository;
import aa.something.blabla.repository.ItemRepository;
import aa.something.blabla.repository.UserRepository;

@Service
public class BlogService {
    
    @Autowired
    private BlogRepository blogRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private RssService rssService;
    
    public void saveItems(Blog blog) {
        try {
            /*List<Item> items = rssService.getItems(blog.getUrl());
            for (Item item : items) {
                Item savedItem = itemRepository.findByBlogAndLink(blog, item.getLink());
                if (savedItem == null) {
                    item.setBlog(blog);
                    itemRepository.save(item);
                }
            }*/
            List<Item> items = rssService.getItems(blog.getUrl());
            for (Item item : items) {
            item.setBlog(blog);
            itemRepository.save(item);
            }
        } catch (RssException e) {
            e.printStackTrace();
        }
    }
    
    // 1h=60*60*1000
    @Scheduled(fixedDelay = 3600000)
    public void reloadBlogs() {
        List<Blog> blogs = blogRepository.findAll();
        for (Blog blog : blogs) {
            saveItems(blog);
        }
    }

    public void save(Blog blog, String name) {
        User user = userRepository.findByName(name);
        blog.setUser(user);
        blogRepository.save(blog);
        saveItems(blog);
    }

    @PreAuthorize("#blog.user.name == authentication.name or hasRole('ROLE_ADMIN')")
    public void delete(@P("blog") Blog blog) {
        blogRepository.delete(blog);
    }

    public Blog findOne(int id) {
        return blogRepository.findOne(id);
    }

}
