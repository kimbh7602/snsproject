create database snsdb;

use snsdb;

create table users (
	user_id varchar(100) primary key,
    password varchar(100) not null,
    tel varchar(100),
	email varchar(100) unique,
    interest varchar(1000),
    dislike varchar(1000),
    description varchar(1000),
    timestamp datetime,
    status boolean default true
);

create table contents (
	content_id int primary key auto_increment,
    share_cid int,
    content_title varchar(100),
    content_val varchar(1000),
    user_id varchar(100),
    timestamp datetime,
    content_like int default 0,
    hashtag varchar(1000),
    user_like boolean,
	dislike int default 0,
    location_name varchar(100),
    lat double,
    lng double
);

create table user_like (
	content_id int,
    user_id varchar(100),
    timestamp datetime
);

alter table user_like add constraint pk_userLike primary key (content_id, user_id);

create table comments (
	comment_id int primary key auto_increment,
    re_comment_id int,
	content_id int,
    user_id varchar(100),
    target_id varchar(100),
    comment_val varchar(1000)
);

create table scraps (
	content_id int,
    user_id varchar(100)
);

alter table scraps add constraint pk_scraps primary key (content_id, user_id);

create table follow (
	follow_id varchar(100),
    follower_id varchar(100)
);

alter table follow add constraint pk_follow primary key (follow_id, follower_id);

create table user_dm (
	dm_id int primary key auto_increment,
    user_id varchar(100),
    other_id varchar(100),
    recent_message varchar(1000)
);

create table direct_message (
	dm_id int,
	send_id varchar(100),
    receive_id varchar(100),
    message varchar(1000),
    timestamp datetime,
    read_check boolean
);

alter table direct_message add constraint pk_direct_message primary key (dm_id, timestamp);

create table content_image (
	content_id int,
    image_name varchar(100),
    image_url varchar(1000),
    filter varchar(100)
);

create table user_report (
	content_id int,
    user_id int
);

alter table user_report add constraint pk_user_report primary key (content_id, user_id);

create table notice (
	calendarId varchar(100),
    category varchar(100),
    id varchar(100) primary key,
    title varchar(1000),
    location varchar(100),
    isAllDay boolean,
    start_date datetime,
    end_date datetime,
    start varchar(100),
    end varchar(100)
);

alter table content_image add constraint pk_content_image primary key (content_id, image_name);

create table notification (
	notification_id int primary key auto_increment,
    user_id varchar(100),
    target_user_id varchar(100),
    category varchar(100)
);
