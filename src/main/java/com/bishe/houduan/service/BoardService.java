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
    public List<Board> list() {
        return boardDAO.findAll();
    }

    public Board findById(int id) {
        return boardDAO.findById(id);
    }

    public void addOrUpdate(Board board) {

        boardDAO.save(board);
    }

    public void delete(int id) {

        boardDAO.deleteById(id);
    }

}
