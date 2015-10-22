package com.neusoft.clw.sysmanage.datamanage.routemanage.domain;

public class EnterpriseTreeInfo {
    private String id = "";

    private String name = "";

    private String level = "";

    private String pId = "";
    
    private String isParent="";
    
    private String open="";
    
    private String iconSkin="";
    
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
	
}
