package com.bishe.houduan.dao;

import com.bishe.houduan.pojo.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardDAO extends JpaRepository<Board,Integer>
{
    Board findById(int id);
}
