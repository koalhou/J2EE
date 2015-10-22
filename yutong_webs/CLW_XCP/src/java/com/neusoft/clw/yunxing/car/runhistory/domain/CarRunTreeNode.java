/*******************************************************************************
 * @(#)CarRunTreeNode.java 2012-11-13
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.yunxing.car.runhistory.domain;

/**
 * @author <a href="mailto:jin.p@neusoft.com">JinPeng </a>
 * @version $Revision 1.1 $ 2012-11-13 上午09:21:48
 */
public class CarRunTreeNode {
    private String id = "";

    private String name = "";

    private String level = "";

    private String pId = "";
    
    private String isParent="";
    
    private String open="";
    
    private String iconSkin="";
    
    private String dvrState = "";
    
    public String getPId() {
        return pId;
    }

    public void setPId(String id) {
        pId = id;
    }

    public String getIconSkin() {
        return iconSkin;
    }

    public void setIconSkin(String iconSkin) {
        this.iconSkin = iconSkin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getDvrState() {
        return dvrState;
    }

    public void setDvrState(String dvrState) {
        this.dvrState = dvrState;
    }
}
