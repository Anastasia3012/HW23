package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.BestResultNotFoundException;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {

    private final String nameArticle;
    private final String textArticle;
    private final UUID id;

    public Article(String nameArticle, String textArticle, UUID id) throws BestResultNotFoundException {
        this.nameArticle = nameArticle;
        this.textArticle = textArticle;
        this.id = getId();
    }

    public String getNameArticle() {
        return nameArticle;
    }

    public String getTextArticle() {
        return textArticle;
    }

    @Override
    public String toString() {
        return "Название статьи: " + nameArticle + " Текст статьи: " + textArticle;
    }


    @JsonIgnore
    @Override
    public String searchContent() {
        return "ARTICLE";
    }

    @JsonIgnore
    @Override
    public String searchTerm() {
        return toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(nameArticle, article.nameArticle);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nameArticle);
    }

    @Override
    public UUID getId() {
        return id;
    }

}
