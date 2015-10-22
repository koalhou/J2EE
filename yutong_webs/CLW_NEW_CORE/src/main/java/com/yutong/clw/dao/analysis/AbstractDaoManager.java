package com.yutong.clw.dao.analysis;

import org.springframework.jdbc.core.JdbcTemplate;

public class AbstractDaoManager {
    protected String name = "AbstractDaoManager";

    protected JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
