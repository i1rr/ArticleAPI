package au.com.nine.ArticleAPI.exceptions;

public class ArticleNotFoundException extends RuntimeException {

    public ArticleNotFoundException(Long id) {
        super("Article id={" + id + "} not found");
    }
}
