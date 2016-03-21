-- =============================================================================
--
-- =============================================================================
DROP TABLE IF EXISTS CHILD_TABLE CASCADE;

CREATE TABLE IF NOT EXISTS CHILD_TABLE
(
  Id    BIGSERIAL
, Name  VARCHAR(256)
--
, CONSTRAINT CHILD_TABLE_PK PRIMARY KEY (Id)
);

