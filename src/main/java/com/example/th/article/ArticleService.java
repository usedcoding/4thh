package com.example.th.article;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> articleList() {
        return this.articleRepository.findAll();
    }

    public Article getArticle(Integer id) {
        Optional<Article> article = this.articleRepository.findById(id);
        return article.get();
    }

    public void create(String subject, String content) {
        Article article = new Article();
        article.setSubject(subject);
        article.setContent(content);
        article.setCreateDate(LocalDateTime.now());

        this.articleRepository.save(article);
    }

    public void modify(Article article, ArticleForm articleForm) {
        article.setSubject(articleForm.getSubject());
        article.setContent(articleForm.getContent());
        article.setCreateDate(LocalDateTime.now());

        this.articleRepository.save(article);

    }

    public void delete(Article article) {
        this.articleRepository.delete(article);
    }
}
