package au.com.nine.ArticleAPI.exceptions;
/** Represents Article model
 * @author Ivan Resemkin
 * @version 1.0
 * @since 1.0
 */
public class ArticleNotFoundException extends RuntimeException {

    /**
     *
     * @param id ID of the article that not found.
     */
    public ArticleNotFoundException(Long id) {
        super("Article id={" + id + "} not found");
    }
}
