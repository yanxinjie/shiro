package com.yxj.shiro.domain;

import java.io.Serializable;

/**
 * @author 严新捷
 * @Type Permission.java
 * @Desc
 * @date 2020/9/13 14:29
 */
public class Permission implements Serializable {

    private int id;

    private String name;

    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
