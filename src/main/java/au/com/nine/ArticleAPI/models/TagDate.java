package au.com.nine.ArticleAPI.models;

import java.util.*;

/** Represents TagDate model as the response for GET /tags/{tagName}/{date} endpoint.
 * @author Ivan Resemkin
 * @version 1.0
 * @since 1.0
 */
public class TagDate {

    Tag tag;
    int count;
    List<Integer> articleIdList = new ArrayList<>();
    Set<Tag> related_tags = new HashSet<>();
    String other;

    public TagDate() {
    }

    public Tag getTag() {
        return tag;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Integer> getArticleIdList() {
        return articleIdList;
    }

    public Set<Tag> getRelated_tags() {
        return related_tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagDate tagDate = (TagDate) o;
        return count == tagDate.count && Objects.equals(tag, tagDate.tag) && Objects.equals(articleIdList, tagDate.articleIdList) && Objects.equals(related_tags, tagDate.related_tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag, count, articleIdList, related_tags);
    }
}
