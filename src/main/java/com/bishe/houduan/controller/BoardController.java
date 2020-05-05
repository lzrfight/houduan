package com.bishe.houduan.controller;

import com.bishe.houduan.pojo.Board;
import com.bishe.houduan.result.Result;
import com.bishe.houduan.result.ResultFactory;
import com.bishe.houduan.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
public class BoardController {
    @Autowired
    BoardService boardService;

    @PostMapping("api/admin/content/article")
    public Result saveArticle(@RequestBody @Valid Board article) {
        System.out.println(article);
        Date currentDate = new Date(System.currentTimeMillis());
        article.setArticleDate(currentDate);
        boardService.addOrUpdate(article);
        return ResultFactory.buildSuccessResult("保存成功");
    }

    @GetMapping("/api/article")
    public Result listArticles() {
        return ResultFactory.buildSuccessResult(boardService.list());
    }

    @GetMapping("/api/article/{id}")
    public Result getOneArticle(@PathVariable("id") int id) {
        System.out.println(boardService.findById(id));
        return ResultFactory.buildSuccessResult(boardService.findById(id));
    }

    @DeleteMapping("/api/admin/content/article/{id}")
    public Result deleteArticle(@PathVariable("id") int id) {
        boardService.delete(id);
        return ResultFactory.buildSuccessResult("删除成功");
    }
}
