package com.qzsang.retrofittest.net.bean;

import java.util.List;

public class BooksBean {

    private String author;
    private int bookclass;
    private String count;
    private int fcount;
    private int id;
    private String img;
    private List<BookBean> list;
    private String name;
    private int rcount;
    private boolean status;
    private String summary;
    private long time;
    private String errNum;
    private String errMsg;

    public boolean isStatus() {
        return status;
    }

    public String getErrNum() {
        return errNum;
    }

    public void setErrNum(String errNum) {
        this.errNum = errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setBookclass(int bookclass) {
        this.bookclass = bookclass;
    }

    public int getBookclass() {
        return bookclass;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCount() {
        return count;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public int getFcount() {
        return fcount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setList(List<BookBean> list) {
        this.list = list;
    }

    public List<BookBean> getList() {
        return list;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public int getRcount() {
        return rcount;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "BooksBean{" +
                "author='" + author + '\'' +
                ", bookclass=" + bookclass +
                ", count='" + count + '\'' +
                ", fcount=" + fcount +
                ", id=" + id +
                ", img='" + img + '\'' +
                ", list=" + list +
                ", name='" + name + '\'' +
                ", rcount=" + rcount +
                ", status=" + status +
                ", summary='" + summary + '\'' +
                ", time=" + time +
                ", errNum='" + errNum + '\'' +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}