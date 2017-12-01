CREATE TABLE user  ( 
    id      	int(4) AUTO_INCREMENT NOT NULL,
    fgid    	int(4) NULL DEFAULT '0',
    sign    	varchar(200) NULL,
    avatar  	varchar(200) NULL,
    username	varchar(200) NULL,
    PRIMARY KEY(id)
)
ENGINE = InnoDB
AUTO_INCREMENT = 2
GO
CREATE TABLE v_big_group  ( 
    id       	int(4) AUTO_INCREMENT NOT NULL,
    avatar   	varchar(200) NULL,
    groupname	varchar(200) NULL,
    PRIMARY KEY(id)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
GO
CREATE TABLE v_friend_group  ( 
    id       	int(4) AUTO_INCREMENT NOT NULL,
    online   	int(4) NULL DEFAULT '0',
    groupname	varchar(200) NULL,
    PRIMARY KEY(id)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
GO
CREATE TABLE v_group  ( 
    gid      	int(4) AUTO_INCREMENT NOT NULL,
    uid      	int(4) NOT NULL,
    groupname	varchar(200) NULL,
    PRIMARY KEY(gid)
)
ENGINE = InnoDB
AUTO_INCREMENT = 3
GO
CREATE TABLE v_group_detail  ( 
    id       	int(4) AUTO_INCREMENT NOT NULL,
    uid      	int(4) NULL DEFAULT '0',
    sign     	varchar(20) NULL,
    nickname 	varchar(20) NULL,
    headphoto	varchar(200) NULL,
    gid      	int(4) NULL DEFAULT '0',
    PRIMARY KEY(id)
)
ENGINE = InnoDB
AUTO_INCREMENT = 4
GO
CREATE TABLE v_layim_group_detail  ( 
    uid	int(4) NULL DEFAULT '0',
    gid	int(4) NULL DEFAULT '0' 
    )
ENGINE = InnoDB
AUTO_INCREMENT = 0
GO
CREATE TABLE v_layim_msg_history  ( 
    id          	int(4) AUTO_INCREMENT NOT NULL,
    fromuser    	varchar(200) NULL,
    gid         	int(4) NULL DEFAULT '0',
    msg         	varchar(200) NULL,
    chattype    	varchar(200) NULL,
    create_date 	varchar(200) NULL,
    msgtype     	varchar(200) NULL,
    message_date	varchar(200) NULL,
    PRIMARY KEY(id)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
GO
