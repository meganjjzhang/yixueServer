drop database if exists yixuedb;
create database yixuedb character set utf8;
use yixuedb;

drop table if exists teachers;
/* 教师信息表 */
create table teachers(
	teacher_number varchar(10) primary key,
	password varchar(20),
	teacher_name varchar(20)
	);

drop table if exists students;
/* 学生信息表 */
create table students(
	student_number varchar(10) primary key,
	password varchar(20),
	student_name varchar(20)
	/* 将学生总分列放在了scores表中 */
	);

drop table if exists courses;
/* 课程信息表 
   包括：课程编号、课堂编号、课程名、教工号、学号、上课地点、上课时间*/
create table courses(
	course_number varchar(10),
	class_number varchar(10),
	course_name varchar(30),
	teacher_number varchar(10),
	student_number varchar(10),
	student_name varchar(20),
	classroom varchar(10),
	course_time varchar(10),
	primary key(course_number,class_number,student_number),
	foreign key(teacher_number) references teachers(teacher_number) on delete cascade on update cascade,
	foreign key(student_number) references students(student_number) on delete cascade on update cascade
	);

drop table if exists scores;
/* 新添加的一个表 */
/* 学生课程成绩表 */
create table scores(
	student_number varchar(10),
	course_number varchar(10),
	class_number varchar(10),
	totalScore double default 0,
	primary key(course_number,class_number,student_number),
	foreign key(student_number) references students(student_number) on delete cascade on update cascade
);

drop table if exists questions;
/* 问答题信息表，状态0未发布，1讨论中，2结束讨论
   包括：主键id、课程编号、课堂号、主题、内容、状态、保存时间 */
create table questions(
	q_id int(11) primary key auto_increment,
	course_number varchar(10),
	class_number varchar(10),
	theme varchar(30) default null,
	content varchar(300) not null,
	status int(1) default 0,
	time datetime
	);

drop table if exists selections;
/* 选择题信息表，状态0未发布，1答题中，2结束答题 
   包括：主键id、课程编号、课堂号、主题、内容、四个选项、正确选项、各选项已选次数、分值、分钟、开始时间、状态、保存时间*/
create table selections(
	s_id int(11) primary key auto_increment,
	course_number varchar(10),
	class_number varchar(10),
	theme varchar(30) default null,
	content varchar(300) not null,
	A varchar(30) default null,
	B varchar(30) default null,
	C varchar(30) default null,
	D varchar(30) default null,
	correctChoice varchar(4) default 'A',
	AA int(1) default 0,
	BB int(1) default 0,
	CC int(1) default 0,
	DD int(1) default 0,
	score int(1) default 1,
	minutes int(1) default 45,
	startTime datetime default null,
	status int(1) default 0,
	time datetime
	);

drop table if exists records;
/* 学生记录信息表，状态0可以答题，1已答题，2表示未答题（即答题已关闭)
   包括：主键id、学号、课程编号、课堂编号、主题、内容、正确选项、我的选项、分值、我的得分、状态*/
create table records(
	r_id int(11) primary key auto_increment,
	s_id int(11),
	student_number varchar(10),
	course_number varchar(10),
	class_number varchar(10),
	theme varchar(30) default null,
	content varchar(300) not null,
	/*以下第一列为新加列，第二列改变了数据长度*/
	choice varchar(130) not null,
	correctChoice varchar(4) not null,
	myChoice varchar(4) default null,
	score int(1) default 1,
	/* 以下一列改变了数据类型 */
	myScore double default 0,
	endTime datetime default null,
	status int(1) default 0,
	foreign key(s_id) references selections(s_id) on update cascade on delete set null,
	foreign key(student_number) references students(student_number) on delete cascade on update cascade
	);