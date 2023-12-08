package com.example.th.article;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/list")
    public String articleList(Model model) {
        List<Article> articleList = this.articleService.articleList();
        model.addAttribute("articleList", articleList);
        return "article_list";
    }

    @GetMapping("/create")
    public String articleCreate() {
        return "article_form";
    }

    @PostMapping("/create")
    public String articleCreate(@RequestParam("subject")String subject, @RequestParam("content") String content, Model model) {
        this.articleService.create(subject, content);
        return"redirect:/article/list";
    }

    @GetMapping("/detail/{id}")
    public String articleDetail(@PathVariable("id") Integer id, Model model) {
        Article article = this.articleService.getArticle(id);
        model.addAttribute("article", article);
        return "article_detail";
    }

    @GetMapping("/modify/{id}")
    public String articleModify(@PathVariable("id") Integer id, Model model) {
        Article article = this.articleService.getArticle(id);
        model.addAttribute("article", article);
        return "article_modify";
    }

//    @PostMapping("/modify/{id}")
//    public String articleModify(@PathVariable("id") Integer id, Model model, @Valid ArticleForm articleForm) {
//        Article article = this.articleService.getArticle(id);
//        model.addAttribute("article", article);
//        this.articleService.modify(articleForm);
//    }

    @GetMapping("/delete/{id}")
    public String articleDelete(@PathVariable("id")Integer id ) {
        Article article = this.articleService.getArticle(id);
        this.articleService.delete(article);
       return "redirect:/article/list";
    }

}
