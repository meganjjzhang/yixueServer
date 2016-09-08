package com.yixueserver.po;

public class Course_Bean {
	
	private String course_number;
	private String class_number;
	private String course_name;
	private String classroom;
	private String course_time;
	
	public Course_Bean() {
		super();
	}
	
	public Course_Bean(String course_number, String class_number,
			String course_name, String classroom, String course_time) {
		super();
		this.course_number = course_number;
		this.class_number = class_number;
		this.course_name = course_name;
		this.classroom = classroom;
		this.course_time = course_time;
	}

	public String getCourse_number() {
		return course_number;
	}

	public void setCourse_number(String course_number) {
		this.course_number = course_number;
	}

	public String getClass_number() {
		return class_number;
	}

	public void setClass_number(String class_number) {
		this.class_number = class_number;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getClassroom() {
		return classroom;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	public String getCourse_time() {
		return course_time;
	}

	public void setCourse_time(String course_time) {
		this.course_time = course_time;
	}
}
