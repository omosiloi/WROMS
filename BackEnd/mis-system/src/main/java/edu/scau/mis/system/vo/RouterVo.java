package edu.scau.mis.system.vo;

import java.util.List;

public class RouterVo {
    /**
     * 路由名字
     */
    private String name;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 是否隐藏路由，当设置 true 的时候该路由不会再侧边栏出现
     */
    private boolean hidden;

    /**
     * 重定向地址，当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
     */
    private String redirect;


    /**
     * 组件地址
     */
    private String component;

    /**
     * 其他元素
     */
    private MetaVo meta;

    /**
     * 子路由
     */
    private List<RouterVo> children;

    public void setChildren(List<RouterVo> children) {
        this.children = children;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void setMeta(MetaVo meta) {
        this.meta = meta;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public List<RouterVo> getChildren() {
        return children;
    }

    public MetaVo getMeta() {
        return meta;
    }

    public String getComponent() {
        return component;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getRedirect() {
        return redirect;
    }
}
