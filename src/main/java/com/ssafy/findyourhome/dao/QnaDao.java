package com.ssafy.findyourhome.dao;

import com.ssafy.findyourhome.domain.Qna;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Mapper
public interface QnaDao {


    @Select("SELECT * FROM qna WHERE id = #{id}")
    @Results(id = "qnaResultMap", value = {
            @Result(property = "userId", column = "user_id")
    })
    Qna findById(int id);

    @Select("SELECT * FROM qna")
    @ResultMap("qnaResultMap")
    List<Qna> findAll();

    @Insert("INSERT INTO qna (user_id, title, content) VALUES (#{userId}, #{title}, #{content})")
    void insert(Qna qna);

    @Update("UPDATE qna SET title = #{title}, content = #{content} WHERE id = #{id}")
    void update(Qna qna);

    @Update("UPDATE qna SET views = #{views} + 1  WHERE id = #{id}")
    void increaseViews(int qna);

    @Delete("DELETE FROM qna WHERE id = #{id}")
    void delete(int id);

    @Delete("DELETE FROM qna")
    void deleteAll();
}
