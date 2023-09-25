CREATE DATABASE giftappdb
GO

-- USE master
-- GO
-- ALTER DATABASE giftappdb SET SINGLE_USER WITH ROLLBACK IMMEDIATE
-- GO
-- DROP DATABASE giftappdb
-- GO

USE giftappdb
GO

-- Create tables
CREATE TABLE account (
    uname VARCHAR(20) NOT NULL,
    passwd VARCHAR(50) NOT NULL,
    acctype INT NOT NULL, -- 0: admin | 1: nomal

    PRIMARY KEY(uname)
)
GO

CREATE TABLE accountinfo (
    uname VARCHAR(20) REFERENCES dbo.account(uname),
    disname NVARCHAR(100) NOT NULL,
    uaddress NTEXT,
    phonenum INT NOT NULL,
	email VARCHAR(200),

	PRIMARY KEY(uname)
)
GO

CREATE TABLE item (
	cid INT IDENTITY(1,1),
	iname NVARCHAR(100) NOT NULL,
	idescription NTEXT NOT NULL,
	thumbnailimg NVARCHAR(200) NOT NULL DEFAULT 'https://www.apple.com/ac/structured-data/images/open_graph_logo.png?201810040211',
	price INT NOT NULL DEFAULT 0,

	activestate INT NOT NULL DEFAULT 1, -- 1: on

	PRIMARY KEY(cid)
)
GO

CREATE TABLE giftpack (
	cid INT IDENTITY(1,1),
	gname NVARCHAR(100) NOT NULL,
	gdescription NTEXT NOT NULL,
	thumbnailimg NVARCHAR(200) NOT NULL DEFAULT 'https://www.apple.com/ac/structured-data/images/open_graph_logo.png?201810040211',
	price INT NOT NULL DEFAULT 0,
	averagestar INT NOT NULL DEFAULT 0, -- from 1 to 5

	activestate INT NOT NULL DEFAULT 1, -- 1: on

	PRIMARY KEY(cid)
)
GO

CREATE TABLE giftpackitems (
	cid INT IDENTITY(1,1),
	giftpackid INT REFERENCES dbo.giftpack(cid) NOT NULL,
	itemid INT REFERENCES dbo.item(cid) NOT NULL,

	PRIMARY KEY(cid)
)
GO

CREATE TABLE giftpackcomments (
	cid INT IDENTITY(1,1),
	giftpackid INT REFERENCES dbo.giftpack(cid) NOT NULL,
	uname VARCHAR(20) REFERENCES dbo.account(uname) NOT NULL,
	content NTEXT NOT NULL,

	PRIMARY KEY(cid)
)
GO

CREATE TABLE gevaluate (
	cid INT REFERENCES dbo.giftpack(cid),
	uname VARCHAR(20) UNIQUE REFERENCES dbo.account(uname),
	star INT NOT NULL, -- from 1 to 5

	PRIMARY KEY(cid, uname)
)
GO

CREATE TABLE addon (
	cid INT IDENTITY(1,1),
	aname VARCHAR(20) NOT NULL,
	adescription NTEXT NOT NULL,
	price INT NOT NULL DEFAULT 0,

	PRIMARY KEY(cid)
)
GO

CREATE TABLE gorder (
	cid INT IDENTITY(1,1),
	uname VARCHAR(20) REFERENCES dbo.account(uname) NOT NULL,
	giftpackid INT REFERENCES dbo.giftpack(cid) NOT NULL,
	receiveaddress NTEXT NOT NULL,
	phonenumber INT NOT NULL,
	dateorder DATETIME NOT NULL,
	datearrive DATETIME NOT NULL,
	greetingcardcontent NTEXT NOT NULL DEFAULT 'Have a nice day!',

	PRIMARY KEY(cid)
)
GO

CREATE TABLE gorderaddons (
	cid INT IDENTITY(1,1),
	gorderid INT REFERENCES dbo.gorder(cid) NOT NULL,
	addonid INT REFERENCES dbo.addon(cid) NOT NULL,
	content NTEXT,

	PRIMARY KEY(cid)
)
GO

CREATE TABLE orderprocess (
	gorderid INT REFERENCES dbo.gorder(cid),
	pstate NTEXT NOT NULL,
	pdelivered INT NOT NULL DEFAULT 0,
	pcanceled INT NOT NULL DEFAULT 0,

	PRIMARY KEY(gorderid)
)
GO
