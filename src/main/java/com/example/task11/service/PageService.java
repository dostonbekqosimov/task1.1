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
public class PageService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Page> getAllPages() {
        String sql = "SELECT * FROM pages";
        return jdbcTemplate.query(sql, new PageRowMapper());
    }



    private static class PageRowMapper implements RowMapper<Page>{

        @Override
        public Page mapRow(ResultSet rs, int rowNum) throws SQLException {
            Page page = new Page();
            page.setId(rs.getLong("id"));
            page.setTitle(rs.getString("title"));
            page.setContent(rs.getString("content"));
            return page;
        }
    }
}
