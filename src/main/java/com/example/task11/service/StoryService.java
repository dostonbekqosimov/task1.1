package com.example.task11.service;


import com.example.task11.entity.Page;
import com.example.task11.entity.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class StoryService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Story> getAllStories() {
        String sql = "SELECT * FROM stories";
        return jdbcTemplate.query(sql, new StoryRowMapper());
    }

    private static class StoryRowMapper implements RowMapper<Story> {

        @Override
        public Story mapRow(ResultSet rs, int rowNum) throws SQLException {
            Story story = new Story();
            story.setId(rs.getLong("id"));
            story.setTitle(rs.getString("title"));
            story.setImage(rs.getString("image"));
            return story;
        }
    }
}
