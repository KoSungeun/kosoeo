package com.study.android.booklog;

public class Author {
    String authorID;
    String job;
    String name;

    public Author(String authorID, String job, String name) {
        this.authorID = authorID;
        this.job = job;
        this.name = name;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
