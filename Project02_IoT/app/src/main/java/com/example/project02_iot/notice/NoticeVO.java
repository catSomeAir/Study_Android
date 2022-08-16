package com.example.project02_iot.notice;

public class NoticeVO {
    String title, content, writer, writedate;

    public NoticeVO(String title, String content, String writer, String writedate) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.writedate = writedate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getWritedate() {
        return writedate;
    }

    public void setWritedate(String writedate) {
        this.writedate = writedate;
    }


}
