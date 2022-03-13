package au.com.nine.ArticleAPI.controllers;

import au.com.nine.ArticleAPI.exceptions.ArticleNotFoundException;
import au.com.nine.ArticleAPI.models.Article;
import au.com.nine.ArticleAPI.models.Tag;
import au.com.nine.ArticleAPI.models.TagDate;
import au.com.nine.ArticleAPI.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

/** Represents article controller
 * @author Ivan Resemkin
 * @version 1.0
 * @since 1.0
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    /**
     * @param id Articles ID.
     * @return Returns expected article if exists and HttpStatus 200.
     * @throws ArticleNotFoundException Throws custom exception when Article is not found.
     */
    @GetMapping("/articles/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable("id") long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    /**
     * @param tagName Filter by provided tag.
     * @param date Filter by provided date.
     * @return Returns summary for the day and tag.S
     * @throws ArticleNotFoundException Throws custom exception when Article is not found.
     */
    @GetMapping("/tags/{tagName}/{date}")
    public ResponseEntity<TagDate> getSummaryByDateAndTag(
            @PathVariable("tagName") String tagName, @PathVariable("date") String date) {

        TagDate tagDate = new TagDate();
        tagDate.setTag(new Tag(tagName));
        int count = 1;
        Article article;

        List<Long> articleIDs = articleRepository.getArticleIDs(tagName, date);
        for (long id : articleIDs) {
            article = articleRepository.findById(id)
                    .orElseThrow(() -> new ArticleNotFoundException(id));

                tagDate.setCount(count++);
                tagDate.getArticleIdList().add((int) id);
                tagDate.getRelated_tags().addAll(article.getTags());
        }
        return new ResponseEntity<>(tagDate, HttpStatus.OK);
    }

    /**
     * @param article JSON representation of Article.class
     * @return Returns created article and HttpStatus 201.
     */
    @PostMapping("/articles")
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {

        Article newArticle = articleRepository.save(new Article(
                article.getTitle(), article.getDate(), article.getBody(), article.getTags()));

        return new ResponseEntity<>(newArticle, HttpStatus.CREATED);
    }

    /**
     * This method automatically starts and creates one article in memory DB, just as an example.
     */
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
