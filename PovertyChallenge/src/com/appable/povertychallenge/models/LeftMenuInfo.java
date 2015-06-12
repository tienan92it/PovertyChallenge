package com.appable.povertychallenge.models;

public class LeftMenuInfo {

	private String title;
	private int icon;
	private int hightLightIcon;
	private boolean isSelected;
	private boolean isShowRedBubble;
	public LeftMenuInfo() {

	}

	public LeftMenuInfo(String title, int icon, int hightLightIcon) {
		this.title = title;
		this.icon = icon;
		this.hightLightIcon = hightLightIcon;
	}

	public boolean isShowRedBubble() {
		return isShowRedBubble;
	}

	public void setShowRedBubble(boolean isShowRedBubble) {
		this.isShowRedBubble = isShowRedBubble;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public int getHightLightIcon() {
		return hightLightIcon;
	}

	public void setHightLightIcon(int hightLightIcon) {
		this.hightLightIcon = hightLightIcon;
	}

}
