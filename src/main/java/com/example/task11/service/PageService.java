package com.example.task11.service;

import com.example.task11.entity.Page;
import com.example.task11.entity.PageReactionDTO;
import com.example.task11.entity.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

@Service
public class PageService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Page> getAllPages(Locale locale){

        String tableName = getTableName(locale);
        String sql = "SELECT * FROM " + tableName;
        return jdbcTemplate.query(sql, new PageRowMapper());
    }


    public void updatePageReaction(Long pageId, Locale locale, PageReactionDTO reactionDTO) {

        String tableName = getTableName(locale);
        String reaction = reactionDTO.getReaction();

        String sql = "UPDATE " + tableName + " SET user_reaction = ? WHERE id = ?";
        jdbcTemplate.update(sql, reaction, pageId);
    }

//    public List<Page> getAllPagesByStoryId(Long storyId, Locale locale) {
//        String tableName = getTableName(locale);
//        String sql = "SELECT * FROM " + tableName + " WHERE story_id = ?";
//        return jdbcTemplate.query(sql, new PageRowMapper(), storyId);
//    }

    private static String getTableName(Locale locale) {

        String language = locale.getLanguage();
        return switch (language) {
            case "en" -> "pages";
            case "ru" -> "pages_ru";
            default -> "pages_uz";
        };
    }


    private static class PageRowMapper implements RowMapper<Page> {

        @Override
        public Page mapRow(ResultSet rs, int rowNum) throws SQLException {
            Page page = new Page();
            page.setId(rs.getLong("id"));
            page.setTitle(rs.getString("title"));
            page.setContent(rs.getString("content"));
            page.setImageUrl(rs.getString("image_url"));
            page.setButtonText(rs.getString("button_text"));
            page.setUserReaction(rs.getString("user_reaction"));
            page.setStoryId(rs.getLong("story_id"));
            return page;
        }
    }


}
