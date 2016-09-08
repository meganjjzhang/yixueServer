drop database if exists yixuedb;
create database yixuedb character set utf8;
use yixuedb;

drop table if exists teachers;
/* ��ʦ��Ϣ�� */
create table teachers(
	teacher_number varchar(10) primary key,
	password varchar(20),
	teacher_name varchar(20)
	);

drop table if exists students;
/* ѧ����Ϣ�� */
create table students(
	student_number varchar(10) primary key,
	password varchar(20),
	student_name varchar(20)
	/* ��ѧ���ܷ��з�����scores���� */
	);

drop table if exists courses;
/* �γ���Ϣ�� 
   �������γ̱�š����ñ�š��γ������̹��š�ѧ�š��Ͽεص㡢�Ͽ�ʱ��*/
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
/* ����ӵ�һ���� */
/* ѧ���γ̳ɼ��� */
create table scores(
	student_number varchar(10),
	course_number varchar(10),
	class_number varchar(10),
	totalScore double default 0,
	primary key(course_number,class_number,student_number),
	foreign key(student_number) references students(student_number) on delete cascade on update cascade
);

drop table if exists questions;
/* �ʴ�����Ϣ��״̬0δ������1�����У�2��������
   ����������id���γ̱�š����úš����⡢���ݡ�״̬������ʱ�� */
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
/* ѡ������Ϣ��״̬0δ������1�����У�2�������� 
   ����������id���γ̱�š����úš����⡢���ݡ��ĸ�ѡ���ȷѡ���ѡ����ѡ��������ֵ�����ӡ���ʼʱ�䡢״̬������ʱ��*/
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
/* ѧ����¼��Ϣ��״̬0���Դ��⣬1�Ѵ��⣬2��ʾδ���⣨�������ѹر�)
   ����������id��ѧ�š��γ̱�š����ñ�š����⡢���ݡ���ȷѡ��ҵ�ѡ���ֵ���ҵĵ÷֡�״̬*/
create table records(
	r_id int(11) primary key auto_increment,
	s_id int(11),
	student_number varchar(10),
	course_number varchar(10),
	class_number varchar(10),
	theme varchar(30) default null,
	content varchar(300) not null,
	/*���µ�һ��Ϊ�¼��У��ڶ��иı������ݳ���*/
	choice varchar(130) not null,
	correctChoice varchar(4) not null,
	myChoice varchar(4) default null,
	score int(1) default 1,
	/* ����һ�иı����������� */
	myScore double default 0,
	endTime datetime default null,
	status int(1) default 0,
	foreign key(s_id) references selections(s_id) on update cascade on delete set null,
	foreign key(student_number) references students(student_number) on delete cascade on update cascade
	);