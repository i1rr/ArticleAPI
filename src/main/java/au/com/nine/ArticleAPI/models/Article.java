package au.com.nine.ArticleAPI.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "body")
    private String body;

    @ManyToMany(
            cascade = {CascadeType.PERSIST
            , CascadeType.MERGE
            , CascadeType.REFRESH
            , CascadeType.DETACH})
    @JoinTable(name = "article_tags"
            , joinColumns = @JoinColumn(name = "article_id")
            , inverseJoinColumns = @JoinColumn(name = "tag_id"))

    private List<Tag> tags = new ArrayList<>();

    public Article() {
    }

    public Article(String title, LocalDate date, String body, List<Tag> tags) {
        this.title = title;
        this.date = date;
        this.body = body;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getBody() {
        return body;
    }

    public List<Tag> getTags() {
        return tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id == article.id && Objects.equals(title, article.title) && Objects.equals(date, article.date) && Objects.equals(body, article.body) && Objects.equals(tags, article.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, date, body, tags);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", body='" + body + '\'' +
                ", tags=" + tags +
                '}';
    }
}
