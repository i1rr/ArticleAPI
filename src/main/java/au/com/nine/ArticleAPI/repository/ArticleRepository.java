package au.com.nine.ArticleAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import au.com.nine.ArticleAPI.models.Article;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
/** Represents article repository
 * @author Ivan Resemkin
 * @version 1.0
 * @since 1.0
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {

    /**
     *
     * @param tagName Injecting tag name into query.
     * @param date Injecting date into query.
     * @return Returns id of the article that matches filtered query.
     */
    @Query(value = "select ar.id FROM articles AS ar " +
            "INNER JOIN article_tags AS at ON ar.id = at.article_id " +
            "INNER JOIN tags AS t ON t.id = at.tag_id " +
            "WHERE t.name = ?1 AND ar.date = ?2 " +
            "GROUP BY ar.id", nativeQuery = true)
    List<Long> getArticleId(String tagName, String date);
}
