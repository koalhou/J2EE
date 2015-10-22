package com.neusoft.clw.yw.qx.entimanage.ds;

public class EnterpriseResInfo {
    private String enterprise_id = "";

    private String short_name = "";

    private String left_num = "";

    private String right_num = "";

    private String treeleve = "";

    private String parent_id = "";

    private String concate_cr_flag = "";

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setEnterprise_id(String enterprise_id) {
        this.enterprise_id = enterprise_id;
    }

    public String getEnterprise_id() {
        return enterprise_id;
    }

    public void setLeft_num(String left_num) {
        this.left_num = left_num;
    }

    public String getLeft_num() {
        return left_num;
    }

    public void setRight_num(String right_num) {
        this.right_num = right_num;
    }

    public String getRight_num() {
        return right_num;
    }

    public void setTreeleve(String treeleve) {
        this.treeleve = treeleve;
    }

    public String getTreeleve() {
        return treeleve;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setConcate_cr_flag(String concate_cr_flag) {
        this.concate_cr_flag = concate_cr_flag;
    }

    public String getConcate_cr_flag() {
        return concate_cr_flag;
    }
}
