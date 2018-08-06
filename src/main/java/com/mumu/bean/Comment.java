package com.mumu.bean;

public class Comment {
    private Integer id;

    private Integer theoryId;

    private String studentId;

    private Boolean carefullyChosen;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTheoryId() {
        return theoryId;
    }

    public void setTheoryId(Integer theoryId) {
        this.theoryId = theoryId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public Boolean getCarefullyChosen() {
        return carefullyChosen;
    }

    public void setCarefullyChosen(Boolean carefullyChosen) {
        this.carefullyChosen = carefullyChosen;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}