BEGIN
  FOR i IN (SELECT us.sequence_name
              FROM USER_SEQUENCES us) LOOP
    EXECUTE IMMEDIATE 'drop sequence '|| i.sequence_name ||'';
  END LOOP;
END;

drop table Adrese;
drop table Angajati;
drop table Zone;
drop table Manageri_Depozit;
drop table Observatii;
drop table Tipuri_rol;




