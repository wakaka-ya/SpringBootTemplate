package com.wakaka.dao.pojo;

import java.util.List;

public class CheckedPMenu {
	private List<CheckedCMenu> children;
	private String icon;
	private Boolean spread;
	private String title;


	public List<CheckedCMenu> getChildren() {
		return children;
	}
	public void setChildren(List<CheckedCMenu> children) {
		this.children = children;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Boolean getSpread() {
		return spread;
	}
	public void setSpread(Boolean spread) {
		this.spread = spread;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
