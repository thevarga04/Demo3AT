CREATE TABLE DOCUMENT_TYPE (
  ID              NUMBER              GENERATED ALWAYS as IDENTITY ( START WITH 1 INCREMENT BY 1 ) PRIMARY KEY,
  NAME            VARCHAR2(32)        NOT NULL UNIQUE,
  DETAILS         VARCHAR2(1024)
);

-- @ManyToOne Doc & Type              Any Document can be of 1 type only
CREATE TABLE DOCUMENT (
  ID              NUMBER              GENERATED ALWAYS as IDENTITY ( START WITH 1 INCREMENT BY 1 ) PRIMARY KEY,
  DOCUMENT_TYPE   NUMBER              CONSTRAINT document_type REFERENCES DOCUMENT_TYPE( ID ) ON DELETE CASCADE,
  NAME            VARCHAR2(32)        NOT NULL UNIQUE,
  DETAILS         VARCHAR2(1024)
);

CREATE TABLE SIGNATURE (
  ID              NUMBER              GENERATED ALWAYS as IDENTITY ( START WITH 1 INCREMENT BY 1 ) PRIMARY KEY,
  NAME            VARCHAR2(32)        NOT NULL UNIQUE,
  DETAILS         VARCHAR2(1024)
);


-- @OneToMany Company & Contracts     Bidirectional relationship
CREATE TABLE COMPANY (
  ID              NUMBER              GENERATED ALWAYS as IDENTITY ( START WITH 1 INCREMENT BY 1 ) PRIMARY KEY,
  NAME            VARCHAR2(32)        NOT NULL UNIQUE,
  DETAILS         VARCHAR2(1024)
);

-- @ManyToMany Contract & Documents   Any Contract may contains multiple documents
-- @ManyToMany Contract & Signatures  Any Contract may be signed by multiple signatures
CREATE TABLE CONTRACT (
  ID              NUMBER              GENERATED ALWAYS as IDENTITY ( START WITH 1 INCREMENT BY 1 ) PRIMARY KEY,
  COMPANY         NUMBER              CONSTRAINT contract_company REFERENCES COMPANY( ID ) ON DELETE CASCADE,
  NAME            VARCHAR2(32)        NOT NULL UNIQUE,
  DETAILS         VARCHAR2(1024)
);