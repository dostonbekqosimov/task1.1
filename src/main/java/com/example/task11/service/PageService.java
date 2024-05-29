package com.example.task11.service;


import com.example.task11.entity.PageReactionDTO;
import com.example.task11.entity.PageTranslation;

import org.springframework.beans.factory.annotation.Autowired;

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


    public List<PageTranslation> getAllPages(Locale locale) {

        String language = locale.getLanguage();

        String sql = "SELECT p.id, p.imageurl, p.userreaction, p.storyid, t.language, t.title, t.content, t.buttontext FROM pages p" +
                     " JOIN page_translations t ON p.id = t.pageid WHERE t.language = ?";


        return jdbcTemplate.query(sql, new PageTranslationRowMapper(), language);
    }


    public void updatePageReaction(Long pageId, Locale locale, PageReactionDTO reactionDTO) {

        String reaction = reactionDTO.getReaction();

        String sql = "UPDATE pages SET userreaction = ? WHERE id = ?";
        jdbcTemplate.update(sql, reaction, pageId);
    }


    private static class PageTranslationRowMapper implements RowMapper<PageTranslation> {

        @Override
        public PageTranslation mapRow(ResultSet rs, int rowNum) throws SQLException {
            PageTranslation pageTranslation = new PageTranslation();
            pageTranslation.setId(rs.getLong("id"));
            pageTranslation.setPageId(rs.getLong("id"));
            pageTranslation.setLanguage(rs.getString("language"));
            pageTranslation.setTitle(rs.getString("title"));
            pageTranslation.setContent(rs.getString("content"));
            pageTranslation.setImageUrl(rs.getString("imageurl"));
            pageTranslation.setButtonText(rs.getString("buttontext"));
            pageTranslation.setUserReaction(rs.getString("userreaction"));
            pageTranslation.setStoryId(rs.getLong("storyid"));
            return pageTranslation;
        }
    }


}
