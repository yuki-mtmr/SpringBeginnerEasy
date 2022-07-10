package com.example.demo.dao;

import com.example.demo.entity.Inquiry;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class InquiryDaoImpl implements InquiryDao{

    private final JdbcTemplate jdbcTemplate;


    @Override
    public void insertInquiry(Inquiry inquiry) {
        jdbcTemplate.update("INSERT INTO inquiry(name, email, contents, created) VALUES(?, ?, ?, ?)",
                inquiry.getName(), inquiry.getEmail(), inquiry.getContents(), inquiry.getCreated());
    }

    @Override
    public int updateInquiry(Inquiry inquiry) {
        return jdbcTemplate.update("UPDATE inquiry SET name = ?, email = ?, contents = ? WHERE id = ?",
                inquiry.getName(), inquiry.getEmail(), inquiry.getContents(), inquiry.getId());
    }

    @Override
    public List<Inquiry> getAll() {
        String sql = "SELECT id, name, email, contents, created FROM inquiry";
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
        List<Inquiry> list = new ArrayList<Inquiry>();
        for(Map<String, Object> result : resultList) {
            Inquiry inquiry = new Inquiry();
            inquiry.setId((int)result.get("id"));
            inquiry.setName((String)result.get("name"));
            inquiry.setEmail((String)result.get("email"));
            inquiry.setContents((String)result.get("contents"));
            inquiry.setCreated(((Timestamp)result.get("created")).toLocalDateTime());
            list.add(inquiry);
        }
        return list;
    }
}
