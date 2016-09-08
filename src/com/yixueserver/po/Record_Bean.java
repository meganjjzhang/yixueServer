package com.yixueserver.po;

public class Record_Bean {

	private String theme;
	private String content;
	private String choice;
	private String correctChoice;
	private String myChoice;
	private int score;
	private int myScore;
	private int status;
	
	public Record_Bean() {
		super();
	}

	public Record_Bean(String theme, String content, String choice,
			String correctChoice, String myChoice, int score, int myScore,
			int status) {
		super();
		this.theme = theme;
		this.content = content;
		this.choice = choice;
		this.correctChoice = correctChoice;
		this.myChoice = myChoice;
		this.score = score;
		this.myScore = myScore;
		this.status = status;
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

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getCorrectChoice() {
		return correctChoice;
	}

	public void setCorrectChoice(String correctChoice) {
		this.correctChoice = correctChoice;
	}

	public String getMyChoice() {
		return myChoice;
	}

	public void setMyChoice(String myChoice) {
		this.myChoice = myChoice;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getMyScore() {
		return myScore;
	}

	public void setMyScore(int myScore) {
		this.myScore = myScore;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
