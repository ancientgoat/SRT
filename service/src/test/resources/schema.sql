-- =============================================================================
--
-- =============================================================================
DROP TABLE IF EXISTS PARENT_TABLE CASCADE;
DROP TABLE IF EXISTS CHILD_TABLE CASCADE;

CREATE TABLE IF NOT EXISTS PARENT_TABLE
( Id            BIGSERIAL
, Name          VARCHAR(256)
, Name_Given    VARCHAR(256)
, Name_Family   VARCHAR(256)
--
, CONSTRAINT PARENT_TABLE_PK PRIMARY KEY (Id)
);

CREATE TABLE IF NOT EXISTS CHILD_TABLE
( Id          BIGSERIAL
, Parent_Id   BIGINT
, Name        VARCHAR(256)
, Name_Given    VARCHAR(256)
, Name_Family   VARCHAR(256)
--
, CONSTRAINT CHILD_TABLE_PK PRIMARY KEY (Id)
, CONSTRAINT CHILD_TABLE__PARENT_ID_FK
  FOREIGN KEY ( Parent_Id )
  REFERENCES PARENT_TABLE ( Id )
);

INSERT INTO PARENT_TABLE ( Id, Name, Name_Given, Name_Family ) VALUES
 ( 1, 'Parent_AB', 'Parent_AB_02', 'Parent_AB_03')
,( 2, 'Parent_CD', 'Parent_CD_02', 'Parent_CD_03')
;

INSERT INTO CHILD_TABLE ( Id, Parent_Id, Name, Name_Given, Name_Family ) VALUES
 ( 1, 1, 'Alfred', 'Alfred_02', 'Alfred_03')
,( 2, 1, 'Betty', 'Betty_02', 'Betty_03')
,( 3, 2, 'Carl', 'Carl_02', 'Carl_03')
,( 4, 2, 'Dan', 'Dan_02', 'Dan_03')
;
