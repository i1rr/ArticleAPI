package au.com.nine.ArticleAPI.advices;

import au.com.nine.ArticleAPI.exceptions.ArticleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Represents error handler of ArticleNotFoundException
 * @author Ivan Resemkin
 * @version 1.0
 * @since 1.0
 */

@ControllerAdvice
public class ArticleNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ArticleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String articleNotFoundHandler(ArticleNotFoundException exception) {
        return exception.getMessage();
    }
}
