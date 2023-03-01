-- Generated by Oracle SQL Developer Data Modeler 22.2.0.165.1149
--   at:        2022-12-01 22:16:16 EET
--   site:      Oracle Database 11g
--   type:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE adrese (
    id_adresa     NUMBER(4) NOT NULL,
    nume_strada   VARCHAR2(50) NOT NULL,
    numar_strada  NUMBER(3) NOT NULL,
    id_zona       NUMBER(2) NOT NULL,
    id_observatie NUMBER(3)
);

ALTER TABLE adrese ADD CONSTRAINT adrese_id_adresa_ck CHECK ( id_adresa > 0 );

ALTER TABLE adrese
    ADD CONSTRAINT adrese_nume_strada_ck CHECK ( REGEXP_LIKE ( nume_strada,
                                                               '^[A-Za-z]+((\s)?(-)?([A-Za-z])+)*$' ) );

ALTER TABLE adrese ADD CONSTRAINT adrese_numar_strada_ck CHECK ( numar_strada > 0 );

CREATE UNIQUE INDEX adrese__idx ON
    adrese (
        id_observatie
    ASC );

ALTER TABLE adrese ADD CONSTRAINT adrese_pk PRIMARY KEY ( id_adresa );

ALTER TABLE adrese ADD CONSTRAINT adrese_numenumar_uk UNIQUE ( nume_strada,
                                                               numar_strada );

CREATE TABLE angajati (
    id_angajat NUMBER(3) NOT NULL,
    nume       VARCHAR2(15) NOT NULL,
    prenume    VARCHAR2(25) NOT NULL,
    id_rol     NUMBER(1) NOT NULL,
    id_zona    NUMBER(2) NOT NULL
);

ALTER TABLE angajati ADD CONSTRAINT angajati_id_angajat_ck CHECK ( id_angajat > 0 );

ALTER TABLE angajati
    ADD CONSTRAINT angajati_nume_ck CHECK ( REGEXP_LIKE ( nume,
                                                          '^[A-Za-z]+((\s)?(-)?([A-Za-z])+)*$' ) );

ALTER TABLE angajati
    ADD CONSTRAINT angajati_prenume_ck CHECK ( REGEXP_LIKE ( prenume,
                                                             '^[A-Za-z]+((\s)?(-)?([A-Za-z])+)*$' ) );

ALTER TABLE angajati ADD CONSTRAINT angajati_pk PRIMARY KEY ( id_angajat );

CREATE TABLE manageri_depozit (
    id_manager NUMBER(1) NOT NULL,
    nume       VARCHAR2(15) NOT NULL,
    prenume    VARCHAR2(25) NOT NULL
);

ALTER TABLE manageri_depozit ADD CONSTRAINT manageri_depozit_id_manager_ck CHECK ( id_manager > 0 );

ALTER TABLE manageri_depozit
    ADD CONSTRAINT manageri_depozit_nume_ck CHECK ( REGEXP_LIKE ( nume,
                                                                  '^[A-Za-z]+((\s)?(-)?([A-Za-z])+)*$' ) );

ALTER TABLE manageri_depozit
    ADD CONSTRAINT manageri_depozit_prenume_ck CHECK ( REGEXP_LIKE ( prenume,
                                                                     '^[A-Za-z]+((\s)?(-)?([A-Za-z])+)*$' ) );

ALTER TABLE manageri_depozit ADD CONSTRAINT manageri_depozit_pk PRIMARY KEY ( id_manager );

CREATE TABLE observatii (
    id_observatie NUMBER(3) NOT NULL,
    descriere     VARCHAR2(100) NOT NULL
);

ALTER TABLE observatii ADD CONSTRAINT observatii_id_observatie CHECK ( id_observatie > 0 );

ALTER TABLE observatii
    ADD CONSTRAINT observatii_descriere_ck CHECK ( length(descriere) BETWEEN 1 AND 100 );

ALTER TABLE observatii ADD CONSTRAINT observatii_pk PRIMARY KEY ( id_observatie );

CREATE TABLE tipuri_rol (
    id_rol  NUMBER(1) NOT NULL,
    tip_rol VARCHAR2(17) NOT NULL
);

ALTER TABLE tipuri_rol ADD CONSTRAINT tipuri_rol_id_rol_ck CHECK ( id_rol > 0 );

ALTER TABLE tipuri_rol
    ADD CONSTRAINT tipuri_rol_tip_rol_ck CHECK ( length(tip_rol) BETWEEN 1 AND 17 );

ALTER TABLE tipuri_rol ADD CONSTRAINT tipuri_rol_pk PRIMARY KEY ( id_rol );

ALTER TABLE tipuri_rol ADD CONSTRAINT tipuri_rol_tip_rol_uk UNIQUE ( tip_rol );

CREATE TABLE zone (
    id_zona    NUMBER(2) NOT NULL,
    denumire   VARCHAR2(20) NOT NULL,
    parinte    NUMBER(2),
    id_manager NUMBER(1) NOT NULL
);

ALTER TABLE zone ADD CONSTRAINT zone_id_zona_ck CHECK ( id_zona > 0 );

ALTER TABLE zone
    ADD CONSTRAINT zone_denumire_ck CHECK ( length(denumire) BETWEEN 1 AND 20 );

ALTER TABLE zone ADD CONSTRAINT zone_parinte_ck CHECK ( parinte > 0 );

ALTER TABLE zone ADD CONSTRAINT zone_pk PRIMARY KEY ( id_zona );

ALTER TABLE zone ADD CONSTRAINT zone_denumire_uk UNIQUE ( denumire );

ALTER TABLE adrese
    ADD CONSTRAINT id_zona_adresa_fk FOREIGN KEY ( id_zona )
        REFERENCES zone ( id_zona );

ALTER TABLE zone
    ADD CONSTRAINT manager_id_zona_fk FOREIGN KEY ( id_manager )
        REFERENCES manageri_depozit ( id_manager );

ALTER TABLE adrese
    ADD CONSTRAINT observatie_adresa_fk FOREIGN KEY ( id_observatie )
        REFERENCES observatii ( id_observatie );

ALTER TABLE angajati
    ADD CONSTRAINT roluri_angajati_fk FOREIGN KEY ( id_rol )
        REFERENCES tipuri_rol ( id_rol );

ALTER TABLE angajati
    ADD CONSTRAINT zona_angajat_fk FOREIGN KEY ( id_zona )
        REFERENCES zone ( id_zona );

CREATE SEQUENCE adrese_id_adresa_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER adrese_id_adresa_trg BEFORE
    INSERT ON adrese
    FOR EACH ROW
    WHEN ( new.id_adresa IS NULL )
BEGIN
    :new.id_adresa := adrese_id_adresa_seq.nextval;
END;
/

CREATE SEQUENCE angajati_id_angajat_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER angajati_id_angajat_trg BEFORE
    INSERT ON angajati
    FOR EACH ROW
    WHEN ( new.id_angajat IS NULL )
BEGIN
    :new.id_angajat := angajati_id_angajat_seq.nextval;
END;
/

CREATE SEQUENCE manageri_depozit_id_manager START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER manageri_depozit_id_manager BEFORE
    INSERT ON manageri_depozit
    FOR EACH ROW
    WHEN ( new.id_manager IS NULL )
BEGIN
    :new.id_manager := manageri_depozit_id_manager.nextval;
END;
/

CREATE SEQUENCE observatii_id_observatie_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER observatii_id_observatie_trg BEFORE
    INSERT ON observatii
    FOR EACH ROW
    WHEN ( new.id_observatie IS NULL )
BEGIN
    :new.id_observatie := observatii_id_observatie_seq.nextval;
END;
/

CREATE SEQUENCE tipuri_rol_id_rol_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER tipuri_rol_id_rol_trg BEFORE
    INSERT ON tipuri_rol
    FOR EACH ROW
    WHEN ( new.id_rol IS NULL )
BEGIN
    :new.id_rol := tipuri_rol_id_rol_seq.nextval;
END;
/

CREATE SEQUENCE zone_id_zona_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER zone_id_zona_trg BEFORE
    INSERT ON zone
    FOR EACH ROW
    WHEN ( new.id_zona IS NULL )
BEGIN
    :new.id_zona := zone_id_zona_seq.nextval;
END;
/



-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                             6
-- CREATE INDEX                             1
-- ALTER TABLE                             30
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           6
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          6
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0