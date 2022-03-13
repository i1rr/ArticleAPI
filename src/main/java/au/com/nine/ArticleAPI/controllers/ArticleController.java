package au.com.nine.ArticleAPI.controllers;

import au.com.nine.ArticleAPI.exceptions.ArticleNotFoundException;
import au.com.nine.ArticleAPI.models.Article;
import au.com.nine.ArticleAPI.models.Tag;
import au.com.nine.ArticleAPI.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/articles/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable("id") long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @GetMapping("/tags/{tagName}/{date}")
    public ResponseEntity<List<Article>> findByDateAndTag(
            @PathVariable("tagName") String tagName, @PathVariable("date") String date) {

        List<Article> articleList = new ArrayList<>();
        List<Long> articles = articleRepository.getArticleId(tagName, date);
        for (long i : articles) {
            articleList.add(articleRepository.findById(i)
                    .orElseThrow(() -> new ArticleNotFoundException(i)));
        }
        return new ResponseEntity<>(articleList, HttpStatus.OK);
    }

    @PostMapping("/articles")
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {

        Article newArticle = articleRepository.save(new Article(
                article.getTitle(), article.getDate(), article.getBody(), article.getTags()));

        return new ResponseEntity<>(newArticle, HttpStatus.CREATED);
    }

    @PostConstruct
    public void dbInit() {
        Tag tag1 = new Tag("Java");
        Tag tag2 = new Tag("Developer");
        Tag tag3 = new Tag("Nine");

        String title = "New employee Ivan";
        LocalDate date = LocalDate.now();
        String body = "Dear team, please welcome our new employee Ivan";

        Article article = new Article(title, date, body, List.of(tag1, tag2, tag3));

        articleRepository.save(article);
    }
}
