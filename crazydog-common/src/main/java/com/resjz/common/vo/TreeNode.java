package com.resjz.common.vo;

import java.util.List;

public class TreeNode {

    private Integer id;

    private Integer parentid;

    private String catname;

    public TreeNode(Integer id, Integer parentid, String catname,List<TreeNode> children) {
        this.id = id;
        this.parentid = parentid;
        this.catname = catname;
        this.children = children;
    }

    private List<TreeNode> children;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
