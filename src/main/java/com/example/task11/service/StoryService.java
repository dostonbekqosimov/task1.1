package com.example.task11.service;



import com.example.task11.entity.StoryTranslation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

@Service
public class StoryService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<StoryTranslation> getAllStories(Locale locale) {
        String language = locale.getLanguage();
        String sql = "SELECT s.id, s.image, st.title, st.language FROM stories s " +
                     "JOIN story_translations st ON s.id = st.storyid WHERE st.language = ?";
        return jdbcTemplate.query(sql, new StoryTranslationRowMapper(), language);
    }


    private static class StoryTranslationRowMapper implements RowMapper<StoryTranslation> {

        @Override
        public StoryTranslation mapRow(ResultSet rs, int rowNum) throws SQLException {
            StoryTranslation storyTranslation = new StoryTranslation();
            storyTranslation.setId(rs.getLong("id"));
            storyTranslation.setStoryId(rs.getLong("id"));
            storyTranslation.setLanguage(rs.getString("language"));
            storyTranslation.setTitle(rs.getString("title"));
            storyTranslation.setImage(rs.getString("image"));
            return storyTranslation;
        }
    }
}
