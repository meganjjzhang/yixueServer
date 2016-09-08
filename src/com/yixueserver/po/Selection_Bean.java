package com.yixueserver.po;

public class Selection_Bean {

	private int s_id;
	private String theme;
	private String content;
	private String A = null;
	private String B = null;
	private String C = null;
	private String D = null;
	private String correctChoice;
	private int AA = 0;
	private int BB = 0;
	private int CC = 0;
	private int DD = 0;
	private int score;
	private int minutes;
	private long startTime;
	private int status;
	private String time;
	
	public Selection_Bean() {
		super();
	}

	public Selection_Bean(int s_id, String theme, String content, String a,
			String b, String c, String d, String correctChoice, int aA, int bB,
			int cC, int dD, int score, int minutes, long startTime, int status,
			String time) {
		super();
		this.s_id = s_id;
		this.theme = theme;
		this.content = content;
		A = a;
		B = b;
		C = c;
		D = d;
		this.correctChoice = correctChoice;
		AA = aA;
		BB = bB;
		CC = cC;
		DD = dD;
		this.score = score;
		this.minutes = minutes;
		this.startTime = startTime;
		this.status = status;
		this.time = time;
	}

	public int getS_id() {
		return s_id;
	}

	public void setS_id(int s_id) {
		this.s_id = s_id;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getA() {
		return A;
	}

	public void setA(String a) {
		A = a;
	}

	public String getB() {
		return B;
	}

	public void setB(String b) {
		B = b;
	}

	public String getC() {
		return C;
	}

	public void setC(String c) {
		C = c;
	}

	public String getD() {
		return D;
	}

	public void setD(String d) {
		D = d;
	}

	public String getCorrectChoice() {
		return correctChoice;
	}

	public void setCorrectChoice(String correctChoice) {
		this.correctChoice = correctChoice;
	}

	public int getAA() {
		return AA;
	}

	public void setAA(int aA) {
		AA = aA;
	}

	public int getBB() {
		return BB;
	}

	public void setBB(int bB) {
		BB = bB;
	}

	public int getCC() {
		return CC;
	}

	public void setCC(int cC) {
		CC = cC;
	}

	public int getDD() {
		return DD;
	}

	public void setDD(int dD) {
		DD = dD;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}