package com.demo.ppjokedemo.model;

public class Destination {

    private boolean isFragment;
    private boolean asStarter;
    private boolean needLogin;
    private String className;
    private String pageUrl;
    private int id;

    public boolean isFragment() {
        return isFragment;
    }

    public void setFragment(boolean fragment) {
        isFragment = fragment;
    }

    public boolean isAsStarter() {
        return asStarter;
    }

    public void setAsStarter(boolean asStarter) {
        this.asStarter = asStarter;
    }

    public boolean isNeedLogin() {
        return needLogin;
    }

    public void setNeedLogin(boolean needLogin) {
        this.needLogin = needLogin;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Destination{" +
                "isFragment=" + isFragment +
                ", asStarter=" + asStarter +
                ", needLogin=" + needLogin +
                ", className='" + className + '\'' +
                ", pageUrl='" + pageUrl + '\'' +
                ", id=" + id +
                '}';
    }
}
