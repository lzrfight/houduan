package com.bishe.houduan.dao;

import com.bishe.houduan.pojo.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardDAO extends JpaRepository<Board,Integer>
{
    List<Board> findAll();
}
