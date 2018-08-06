package com.mumu.bean;

import java.util.Date;

public class Theory {
    private Integer id;

    private Date created;

    private Boolean haveComment;

    private String title;

    private Integer reading;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Boolean getHaveComment() {
        return haveComment;
    }

    public void setHaveComment(Boolean haveComment) {
        this.haveComment = haveComment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getReading() {
        return reading;
    }

    public void setReading(Integer reading) {
        this.reading = reading;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}