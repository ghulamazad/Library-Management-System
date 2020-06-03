package com.ghulam.utility.css;

public enum AlertType {
	INTO("alert-info"), DANGER("alert-danger"), SUCCESS("alert-success"), WARNNING("alert-warnning");

	private String CSSClassname;

	AlertType(String className) {
		CSSClassname = className;
	}

	@Override
	public String toString() {
		return CSSClassname;
	}
}