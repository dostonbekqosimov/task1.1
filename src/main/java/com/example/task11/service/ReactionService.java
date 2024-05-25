package com.example.task11.service;

import com.example.task11.entity.Reaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Service
public class ReactionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void reactToPage(Long pageId, Reaction reaction) {

        Optional<Reaction> existingReaction = findReactionByPageId(pageId);
        if (existingReaction.isPresent()) {
            String sql = "UPDATE reactions SET reaction_type = ? WHERE page_id = ?";
            jdbcTemplate.update(sql, reaction.getReactionType(), pageId);
        } else {
            String sql = "INSERT INTO reactions (page_id, reaction_type) VALUES (?, ?)";
            jdbcTemplate.update(sql, pageId, reaction.getReactionType());
        }

    }

    private Optional<Reaction> findReactionByPageId(Long pageId) {
        String sql = "SELECT * FROM reactions WHERE page_id = ?";
        return jdbcTemplate.query(sql, new Object[]{pageId}, new ReactionRowMapper()).stream().findFirst();
    }

    private static class ReactionRowMapper implements RowMapper<Reaction> {

        @Override
        public Reaction mapRow(ResultSet rs, int rowNum) throws SQLException {

            Reaction reaction = new Reaction();

            reaction.setId(rs.getLong("id"));
            reaction.setPageId(rs.getLong("page_id"));
            reaction.setReactionType(rs.getString("reaction_type"));
            return reaction;
        }
    }
}
