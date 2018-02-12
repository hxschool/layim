
CREATE TABLE chat_friend  ( 
	id 	int(4) NOT NULL,
	uid	varchar(64) NOT NULL,
	fid	varchar(64) NOT NULL,
	PRIMARY KEY(id)
)
;

CREATE TABLE chat_group  ( 
	id       	varchar(64) NOT NULL,
	avatar   	varchar(200) NULL,
	groupname	varchar(200) NULL,
	PRIMARY KEY(id)
)
;


CREATE TABLE chat_msg_history  ( 
	id          	int(4) AUTO_INCREMENT NOT NULL,
	fromuser    	varchar(200) NULL,
	gid         	varchar(64) NULL DEFAULT '0',
	msg         	varchar(200) NULL,
	chattype    	varchar(200) NULL,
	create_date 	varchar(200) NULL,
	msgtype     	varchar(200) NULL,
	message_date	varchar(200) NULL,
	PRIMARY KEY(id)
)

;


CREATE TABLE chat_user  ( 
	id      	varchar(64) NOT NULL,
	fgid    	int(4) NULL DEFAULT '0',
	sign    	varchar(200) NULL,
	avatar  	varchar(200) NULL,
	username	varchar(200) NULL,
	PRIMARY KEY(id)
)

;
CREATE TABLE chat_user_group  ( 
	uid	varchar(64) NULL DEFAULT '0',
	gid	varchar(64) NULL DEFAULT '0' 
	)
	;