package com.ssafy.findyourhome.dao;

import com.ssafy.findyourhome.domain.Qna;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QnaDao {

    @Select("SELECT * FROM qna WHERE id = #{id}")
    Qna findById(int id);

    @Select("SELECT * FROM qna")
    List<Qna> findAll();

    @Insert("INSERT INTO qna (user_id, title, content) VALUES (#{userId}, #{title}, #{content})")
    void insert(Qna qna);

    @Update("UPDATE qna SET title = #{title}, content = #{content} WHERE id = #{id}")
    void update(Integer id, Qna qna);

    @Delete("DELETE FROM qna WHERE id = #{id}")
    void delete(int id);

    @Update("UPDATE qna SET views = #{views} + 1  WHERE id = #{id}")
    void increaseViews(Qna qna);
}
