package com.bishe.houduan.controller;

import com.bishe.houduan.dao.BoardDAO;
import com.bishe.houduan.pojo.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardController {
    @Autowired
    BoardDAO boardDAO;
    @GetMapping("/api/board")
    public List<Board> list() throws Exception
    {
        return boardDAO.findAll();
    }

    @PostMapping("/api/addboard")
    public void add(Board board)throws Exception
    {
        boardDAO.save(board);
    }
}
