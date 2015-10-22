package com.neusoft.clw.common.util.tree;

import java.util.ArrayList;

import com.neusoft.clw.yw.qx.entimanage.ds.EnterpriseResInfo;
import com.neusoft.clw.yw.qx.rolemanage.ds.ModuleResInfo;

public class TreeHtmlShow {
    /*
     * 权限角色树-可被操作
     */
    public static String getModuleAll(ArrayList < ModuleResInfo > res) {
        StringBuffer strb = new StringBuffer("");
        try {
            ModuleResInfo tempres = null;
            ModuleResInfo tempres1 = null;
            String module_id = "";
            String module_name = "";
            int left_num = 0;
            int right_num = 0;
            int treeleve = 0;// 层数用于计算用
            int treeleve1 = 0;// 用于判断节点类型
            String module_parent = "";
            String module_uri = "";
            if (res.size() == 0) {
                return "";
            }
            // 最高及别数 1-宇通 2-各个系统(管理、车联网、宇通杯) 3-其他
            int toptreeleve = Integer.parseInt(res.get(0).getTreeleve());
            int treelevebefore = toptreeleve - 1;

            for (int i = 0; i < res.size(); i++) {
                tempres = (ModuleResInfo) res.get(i);
                module_id = tempres.getModule_id();
                module_name = tempres.getModule_name();
                left_num = Integer.parseInt(tempres.getLeft_num());
                right_num = Integer.parseInt(tempres.getRight_num());
                treeleve = Integer.parseInt(tempres.getTreeleve());
                module_parent = tempres.getModule_parent();
                module_uri = tempres.getModule_uri();

                if (i == 0) {
                    //
                } else {
                    // 同级别，封闭上标签
                    if (treeleve == treelevebefore) {
                        strb.append("</li>\n");
                    }
                    // 该节点是上节点的子节点
                    else if (treeleve > treelevebefore) {
                        strb.append("\n<ul>\n");
                    }
                    // 该节点是高级节点
                    else if (treeleve < treelevebefore) {
                        for (int j = 0; j < (treelevebefore - treeleve); j++) {
                            strb.append("</ul>\n</li>\n");
                        }
                    }
                }
                /*
                 * <li>SPAN
                 */
                strb.append("<li id=\"tree_li_id_").append(module_id).append(
                        "\" r_num=\"").append(right_num).append("\" p_id=\"")
                        .append(module_parent);
                // strb.append("\" ><SPAN");
                if (i == 0) {
                    if (left_num + 1 == right_num) {
                        strb.append("\" class=\"node-last\">");
                    } else {
                        strb.append("\" class=\"plus-only\">");
                    }
                } else if (i + 1 >= res.size()) {
                    /*
                     * 最后一个节点肯定是L型节点
                     */
                    strb.append("\" class=\"node-last\">");
                } else {
                    if (left_num + 1 == right_num) {
                        /*
                         * 如果是叶子节点，就要判断是否是L型节点
                         */
                        tempres1 = (ModuleResInfo) res.get(i + 1);
                        treeleve1 = Integer.parseInt(tempres1.getTreeleve());
                        // 下一个节点是同级节点，则该节点是一般节点，其他为L型节点
                        if (treeleve == treeleve1) {
                            strb.append("\" class=\"node\">");
                        } else {
                            strb.append("\" class=\"node-last\">");
                        }
                    } else {
                        strb.append("\" class=\"plus\">");
                    }
                }

                strb
                        .append("<input id=\"tree_box_id_")
                        .append(module_id)
                        .append(
                                "\" name=\"roleTreeCheckBox\"  type=\"checkbox\" left_num=\"")
                        .append(left_num).append("\"  right_num=\"").append(
                                right_num).append("\" module_id=\"").append(
                                module_id).append(
                                "\"  onclick=\"treeCheckBox(this)\" ");
                // disabled
                // if (right_num != left_num + 1) {
                // strb.append("disabled");
                // }
                strb.append(" /><SPAN>").append(module_name).append("</SPAN>");

                treelevebefore = treeleve;
            }
            strb.append("</li>");
            for (int j = 0; j < (treelevebefore - toptreeleve); j++) {
                strb.append("\n</ul>\n</li>");
            }
            strb.append("\n");
        } catch (Throwable e) {
            strb = new StringBuffer("");
        } finally {
            return strb.toString();
        }
    }

    /*
     * 权限角色树-不可被操作
     */
    public static String getModuleNoUsed(ArrayList < ModuleResInfo > res) {
        StringBuffer strb = new StringBuffer("");
        try {
            ModuleResInfo tempres = null;
            ModuleResInfo tempres1 = null;
            String module_id = "";
            String module_name = "";
            int left_num = 0;
            int right_num = 0;
            int treeleve = 0;// 层数用于计算用
            int treeleve1 = 0;// 用于判断节点类型
            String module_parent = "";
            String module_uri = "";
            if (res.size() == 0) {
                return "";
            }
            // 最高及别数 1-宇通 2-各个系统(管理、车联网、宇通杯) 3-其他
            int toptreeleve = Integer.parseInt(res.get(0).getTreeleve());
            int treelevebefore = toptreeleve - 1;

            for (int i = 0; i < res.size(); i++) {
                tempres = (ModuleResInfo) res.get(i);
                module_id = tempres.getModule_id();
                module_name = tempres.getModule_name();
                left_num = Integer.parseInt(tempres.getLeft_num());
                right_num = Integer.parseInt(tempres.getRight_num());
                treeleve = Integer.parseInt(tempres.getTreeleve());
                module_parent = tempres.getModule_parent();
                module_uri = tempres.getModule_uri();

                if (i == 0) {
                    //
                } else {
                    // 同级别，封闭上标签
                    if (treeleve == treelevebefore) {
                        strb.append("</li>\n");
                    }
                    // 该节点是上节点的子节点
                    else if (treeleve > treelevebefore) {
                        strb.append("\n<ul>\n");
                    }
                    // 该节点是高级节点
                    else if (treeleve < treelevebefore) {
                        for (int j = 0; j < (treelevebefore - treeleve); j++) {
                            strb.append("</ul>\n</li>\n");
                        }
                    }
                }
                /*
                 * <li>SPAN
                 */
                strb.append("<li id=\"tree_li_id_").append(module_id).append(
                "\" r_num=\"").append(right_num).append("\" p_id=\"")
                .append(module_parent);
                // strb.append("\" ><SPAN");
                if (i == 0) {
                    if (left_num + 1 == right_num) {
                        strb.append("\" class=\"node-last\">");
                    } else {
                        strb.append("\" class=\"plus-only\">");
                    }
                } else if (i + 1 >= res.size()) {
                    /*
                     * 最后一个节点肯定是L型节点
                     */
                    strb.append("\" class=\"node-last\">");
                } else {
                    if (left_num + 1 == right_num) {
                        /*
                         * 如果是叶子节点，就要判断是否是L型节点
                         */
                        tempres1 = (ModuleResInfo) res.get(i + 1);
                        treeleve1 = Integer.parseInt(tempres1.getTreeleve());
                        // 下一个节点是同级节点，则该节点是一般节点，其他为L型节点
                        if (treeleve == treeleve1) {
                            strb.append("\" class=\"node\">");
                        } else {
                            strb.append("\" class=\"node-last\">");
                        }
                    } else {
                        strb.append("\" class=\"plus\">");
                    }
                }
                strb.append("<input id=\"tree_box_id_")
                        .append(module_id)
                        .append(
                                "\" name=\"roleTreeCheckBox\"  type=\"checkbox\" left_num=\"")
                        .append(left_num).append("\"  right_num=\"").append(
                                right_num).append("\" module_id=\"").append(
                                module_id).append(
                                "\"  onclick=\"treeCheckBox(this)\" ");
                // disabled
                // if (right_num != left_num + 1) {
                strb.append("disabled");
                // }
                strb.append(" /><SPAN>").append(module_name).append("</SPAN>");

                treelevebefore = treeleve;
            }
            strb.append("</li>");
            for (int j = 0; j < (treelevebefore - toptreeleve); j++) {
                strb.append("\n</ul>\n</li>");
            }
            strb.append("\n");
        } catch (Throwable e) {
            strb = new StringBuffer("");
        } finally {
            return strb.toString();
        }
    }

    /*
     * 企业组织关系树_全部点让选择
     */
    public static String getEnterpriseAllClick(
            ArrayList < EnterpriseResInfo > res) {

        StringBuffer strb = new StringBuffer("");
        try {
            EnterpriseResInfo tempres = null;
            EnterpriseResInfo tempres1 = null;
            String enterprise_id = "";
            String short_name = "";
            int left_num = 0;
            int right_num = 0;
            int treeleve = 0;// 层数用于计算用
            int treeleve1 = 0;// 用于判断节点类型
            String parent_id = "";
            String concate_cr_flag = "";
            if (res.size() == 0) {
                return "";
            }
            // 最高及别数 1-宇通 2-各个系统(管理、车联网、宇通杯) 3-其他
            int toptreeleve = Integer.parseInt(res.get(0).getTreeleve());
            int treelevebefore = toptreeleve - 1;

            for (int i = 0; i < res.size(); i++) {
                tempres = (EnterpriseResInfo) res.get(i);
                enterprise_id = tempres.getEnterprise_id();
                short_name = tempres.getShort_name();
                left_num = Integer.parseInt(tempres.getLeft_num());
                right_num = Integer.parseInt(tempres.getRight_num());
                treeleve = Integer.parseInt(tempres.getTreeleve());
                parent_id = tempres.getParent_id();
                concate_cr_flag = tempres.getConcate_cr_flag();

                if (i == 0) {
                    //
                } else {
                    // 同级别，封闭上标签
                    if (treeleve == treelevebefore) {
                        strb.append("</li>\n");
                    }
                    // 该节点是上节点的子节点
                    else if (treeleve > treelevebefore) {
                        strb.append("\n<ul>\n");
                    }
                    // 该节点是高级节点
                    else if (treeleve < treelevebefore) {
                        for (int j = 0; j < (treelevebefore - treeleve); j++) {
                            strb.append("</ul>\n</li>\n");
                        }
                    }
                }
                /*
                 * <li>SPAN
                 */
                strb.append("<li id=\"tree_li_id_").append(enterprise_id)
                        .append("\" r_num=\"").append(right_num).append(
                                "\" p_id=\"").append(parent_id);
                // strb.append("\" ><SPAN");
                if (i == 0) {
                    if (left_num + 1 == right_num) {
                        strb.append("\" class=\"node-last\"><SPAN");
                    } else {
                        strb.append("\" class=\"plus-only\"><SPAN");
                    }
                } else if (i + 1 >= res.size()) {
                    /*
                     * 最后一个节点肯定是L型节点
                     */
                    strb.append("\" class=\"node-last\"><SPAN");
                } else {
                    if (left_num + 1 == right_num) {
                        /*
                         * 如果是叶子节点，就要判断是否是L型节点
                         */
                        tempres1 = (EnterpriseResInfo) res.get(i + 1);
                        treeleve1 = Integer.parseInt(tempres1.getTreeleve());
                        // 下一个节点是同级节点，则该节点是一般节点，其他为L型节点
                        if (treeleve == treeleve1) {
                            strb.append("\" class=\"node\"><SPAN");
                        } else {
                            strb.append("\" class=\"node-last\"><SPAN");
                        }
                    } else {
                        strb.append("\" class=\"plus\"><SPAN");
                    }
                }

                // 样式
                if (treeleve == toptreeleve) {
                    strb.append(" class=\"liGongsi\" ");
                } else if (left_num + 1 == right_num) {
                    strb.append(" class=\"liChedui\" ");
                } else {
                    strb.append(" class=\"liFengongsi\" ");
                }

                // 字段
                strb.append(" onclick=\"clickEnter(this)\" left_num=\"")
                        .append(left_num).append("\" right_num=\"").append(
                                right_num).append("\" id=\"").append(
                                enterprise_id).append("\" concate_cr_flag=\"")
                        .append(concate_cr_flag).append("\" short_name=\"")
                        .append(short_name).append("\" ");
                strb.append(" />").append(short_name).append("</SPAN>");

                treelevebefore = treeleve;
            }
            strb.append("</li>");
            for (int j = 0; j < (treelevebefore - toptreeleve); j++) {
                strb.append("\n</ul>\n</li>");
            }
            strb.append("\n");
        } catch (Throwable e) {
            strb = new StringBuffer("");
        } finally {
            return strb.toString();
        }
    }

    /*
     * 企业组织关系树_子节点让选择
     */
    public static String getEnterpriseChildClick(
            ArrayList < EnterpriseResInfo > res) {

        StringBuffer strb = new StringBuffer("");
        try {
            EnterpriseResInfo tempres = null;
            EnterpriseResInfo tempres1 = null;
            String enterprise_id = "";
            String short_name = "";
            int left_num = 0;
            int right_num = 0;
            int treeleve = 0;// 层数用于计算用
            int treeleve1 = 0;// 用于判断节点类型
            String parent_id = "";
            String concate_cr_flag = "";
            if (res.size() == 0) {
                return "";
            }
            // 最高及别数 1-宇通 2-各个系统(管理、车联网、宇通杯) 3-其他
            int toptreeleve = Integer.parseInt(res.get(0).getTreeleve());
            int treelevebefore = toptreeleve - 1;

            for (int i = 0; i < res.size(); i++) {
                tempres = (EnterpriseResInfo) res.get(i);
                enterprise_id = tempres.getEnterprise_id();
                short_name = tempres.getShort_name();
                left_num = Integer.parseInt(tempres.getLeft_num());
                right_num = Integer.parseInt(tempres.getRight_num());
                treeleve = Integer.parseInt(tempres.getTreeleve());
                parent_id = tempres.getParent_id();
                concate_cr_flag = tempres.getConcate_cr_flag();

                if (i == 0) {
                    //
                } else {
                    // 同级别，封闭上标签
                    if (treeleve == treelevebefore) {
                        strb.append("</li>\n");
                    }
                    // 该节点是上节点的子节点
                    else if (treeleve > treelevebefore) {
                        strb.append("\n<ul>\n");
                    }
                    // 该节点是高级节点
                    else if (treeleve < treelevebefore) {
                        for (int j = 0; j < (treelevebefore - treeleve); j++) {
                            strb.append("</ul>\n</li>\n");
                        }
                    }
                }
                /*
                 * <li>SPAN
                 */
                strb.append("<li id=\"tree_li_id_").append(enterprise_id)
                        .append("\" r_num=\"").append(right_num).append(
                                "\" p_id=\"").append(parent_id);
                // strb.append("\" ><SPAN");
                if (i == 0) {
                    if (left_num + 1 == right_num) {
                        strb.append("\" class=\"node-last\"><SPAN");
                    } else {
                        strb.append("\" class=\"plus-only\"><SPAN");
                    }
                } else if (i + 1 >= res.size()) {
                    /*
                     * 最后一个节点肯定是L型节点
                     */
                    strb.append("\" class=\"node-last\"><SPAN");
                } else {
                    if (left_num + 1 == right_num) {
                        /*
                         * 如果是叶子节点，就要判断是否是L型节点
                         */
                        tempres1 = (EnterpriseResInfo) res.get(i + 1);
                        treeleve1 = Integer.parseInt(tempres1.getTreeleve());
                        // 下一个节点是同级节点，则该节点是一般节点，其他为L型节点
                        if (treeleve == treeleve1) {
                            strb.append("\" class=\"node\"><SPAN");
                        } else {
                            strb.append("\" class=\"node-last\"><SPAN");
                        }
                    } else {
                        strb.append("\" class=\"plus\"><SPAN");
                    }
                }

                // 样式
                if (treeleve == toptreeleve) {
                    strb.append(" class=\"liGongsi_nc\" ");
                    strb
                    .append(" left_num=\"")
                    .append(left_num).append("\" right_num=\"")
                    .append(right_num).append("\" id=\"").append(
                            enterprise_id).append("\" ");
                } else if (left_num + 1 == right_num) {
                    strb.append(" class=\"liChedui\" ");
                    strb.append(" onclick=\"clickEnter(this)\" left_num=\"")
                            .append(left_num).append("\" right_num=\"").append(
                                    right_num).append("\" id=\"").append(
                                    enterprise_id).append(
                                    "\" concate_cr_flag=\"").append(
                                    concate_cr_flag).append("\" short_name=\"")
                            .append(short_name).append("\" ");
                } else {
                    strb.append(" class=\"liFengongsi_nc\" ");
                    strb
                    .append(" left_num=\"")
                    .append(left_num).append("\" right_num=\"")
                    .append(right_num).append("\" id=\"").append(
                            enterprise_id).append("\" ");
                }

                strb.append(" />").append(short_name).append("</SPAN>");

                treelevebefore = treeleve;
            }
            strb.append("</li>");
            for (int j = 0; j < (treelevebefore - toptreeleve); j++) {
                strb.append("\n</ul>\n</li>");
            }
            strb.append("\n");
        } catch (Throwable e) {
            strb = new StringBuffer("");
        } finally {
            return strb.toString();
        }
    }

    /*
     * 企业组织关系树_叶子节点让选择
     */
    public static String getEnterpriseLastChildClick(
            ArrayList < EnterpriseResInfo > res) {

        StringBuffer strb = new StringBuffer("");
        try {
            EnterpriseResInfo tempres = null;
            EnterpriseResInfo tempres1 = null;
            String enterprise_id = "";
            String short_name = "";
            int left_num = 0;
            int right_num = 0;
            int treeleve = 0;// 层数用于计算用
            int treeleve1 = 0;// 用于判断节点类型
            String parent_id = "";
            String concate_cr_flag = "";
            if (res.size() == 0) {
                return "";
            }
            // 最高及别数 1-宇通 2-各个系统(管理、车联网、宇通杯) 3-其他
            int toptreeleve = Integer.parseInt(res.get(0).getTreeleve());
            int treelevebefore = toptreeleve - 1;

            for (int i = 0; i < res.size(); i++) {
                tempres = (EnterpriseResInfo) res.get(i);
                enterprise_id = tempres.getEnterprise_id();
                short_name = tempres.getShort_name();
                left_num = Integer.parseInt(tempres.getLeft_num());
                right_num = Integer.parseInt(tempres.getRight_num());
                treeleve = Integer.parseInt(tempres.getTreeleve());
                parent_id = tempres.getParent_id();
                concate_cr_flag = tempres.getConcate_cr_flag();

                if (i == 0) {
                    //
                } else {
                    // 同级别，封闭上标签
                    if (treeleve == treelevebefore) {
                        strb.append("</li>\n");
                    }
                    // 该节点是上节点的子节点
                    else if (treeleve > treelevebefore) {
                        strb.append("\n<ul>\n");
                    }
                    // 该节点是高级节点
                    else if (treeleve < treelevebefore) {
                        for (int j = 0; j < (treelevebefore - treeleve); j++) {
                            strb.append("</ul>\n</li>\n");
                        }
                    }
                }
                /*
                 * <li>SPAN
                 */
                strb.append("<li id=\"tree_li_id_").append(enterprise_id)
                        .append("\" r_num=\"").append(right_num).append(
                                "\" p_id=\"").append(parent_id);
                // strb.append("\" ><SPAN");
                if (i == 0) {
                    if (left_num + 1 == right_num) {
                        strb.append("\" class=\"node-last\"><SPAN");
                    } else {
                        strb.append("\" class=\"plus-only\"><SPAN");
                    }
                } else if (i + 1 >= res.size()) {
                    /*
                     * 最后一个节点肯定是L型节点
                     */
                    strb.append("\" class=\"node-last\"><SPAN");
                } else {
                    if (left_num + 1 == right_num) {
                        /*
                         * 如果是叶子节点，就要判断是否是L型节点
                         */
                        tempres1 = (EnterpriseResInfo) res.get(i + 1);
                        treeleve1 = Integer.parseInt(tempres1.getTreeleve());
                        // 下一个节点是同级节点，则该节点是一般节点，其他为L型节点
                        if (treeleve == treeleve1) {
                            strb.append("\" class=\"node\"><SPAN");
                        } else {
                            strb.append("\" class=\"node-last\"><SPAN");
                        }
                    } else {
                        strb.append("\" class=\"plus\"><SPAN");
                    }
                }

                // 样式
                if (treeleve == toptreeleve) {
                    if (left_num + 1 == right_num) {
                        strb.append(" class=\"liGongsi_nc\" ");
                        strb
                                .append(
                                        " onclick=\"clickEnter(this)\" left_num=\"")
                                .append(left_num).append("\" right_num=\"")
                                .append(right_num).append("\" id=\"").append(
                                        enterprise_id).append(
                                        "\" concate_cr_flag=\"").append(
                                        concate_cr_flag).append(
                                        "\" short_name=\"").append(short_name)
                                .append("\" ");
                    } else {
                        strb.append(" class=\"liGongsi_nc\" ");
                        strb
                        .append(" left_num=\"")
                        .append(left_num).append("\" right_num=\"")
                        .append(right_num).append("\" id=\"").append(
                                enterprise_id).append("\" ");
                    }

                } else if (left_num + 1 == right_num) {
                    strb.append(" class=\"liChedui\" ");
                    strb.append(" onclick=\"clickEnter(this)\" left_num=\"")
                            .append(left_num).append("\" right_num=\"").append(
                                    right_num).append("\" id=\"").append(
                                    enterprise_id).append(
                                    "\" concate_cr_flag=\"").append(
                                    concate_cr_flag).append("\" short_name=\"")
                            .append(short_name).append("\" ");
                } else {
                    strb.append(" class=\"liFengongsi_nc\" ");
                    strb
                    .append(" left_num=\"")
                    .append(left_num).append("\" right_num=\"")
                    .append(right_num).append("\" id=\"").append(
                            enterprise_id).append("\" ");
                }

                strb.append(" />").append(short_name).append("</SPAN>");

                treelevebefore = treeleve;
            }
            strb.append("</li>");
            for (int j = 0; j < (treelevebefore - toptreeleve); j++) {
                strb.append("\n</ul>\n</li>");
            }
            strb.append("\n");
        } catch (Throwable e) {
            strb = new StringBuffer("");
        } finally {
            return strb.toString();
        }
    }
}
