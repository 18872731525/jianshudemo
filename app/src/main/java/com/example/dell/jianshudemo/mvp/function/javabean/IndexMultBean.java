package com.example.dell.jianshudemo.mvp.function.javabean;

import java.util.List;

/**
 * 作者：wl on 2017/9/21 17:22
 * 邮箱：wangl@ixinyongjia.com
 */
public class IndexMultBean {
    private List<Mail> mail;
    private List<Article> articles;
    private List<Video> video;

    public List<Mail> getMail() {
        return mail;
    }

    public void setMail(List<Mail> mail) {
        this.mail = mail;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Video> getVideo() {
        return video;
    }

    public void setVideo(List<Video> video) {
        this.video = video;
    }
}
