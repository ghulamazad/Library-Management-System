package com.ghulam.utility.css;

public enum Icon {
	INFO("fa-info-circle"), CHECK("fa-check"), WARNNING("fa-warning"), CROSS("fa-times-circle");

	private String iconName;

	private Icon(String iconName) {
		this.iconName = iconName;
	}

	@Override
	public String toString() {
		return iconName;
	}
}
