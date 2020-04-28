package com.bishe.houduan.service;

import com.bishe.houduan.dao.BoardDAO;
import com.bishe.houduan.pojo.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardDAO boardDAO;
    public List<Board> find()
    {
       return boardDAO.findAll();
    }
    public void add(Board board)
    {
        boardDAO.save(board);
    }
}
