--VIUALIZARE

--vizualizare integrala
select * from adrese order by id_adresa;
select * from observatii order by id_observatie;
select * from zone order by id_zona;
select * from manageri_depozit order by id_manager;
select * from angajati order by id_angajat;
select * from tipuri_rol order by id_rol;

--cine este responsabil de livrarea unui colet la adresa Strada Dragos-Voda nr 3?
select concat(concat(an.nume,' '), an.prenume) as Angajat, 
concat(concat(ad.nume_strada, ' '), concat('nr. ', ad.numar_strada)) as Adresa,
z.denumire as Zona
from ANGAJATI an, ADRESE ad, ZONE z
where ad.nume_strada = 'Strada Dragos-Voda' 
and ad.numar_strada = 3 
and ad.id_zona = z.id_zona 
and an.id_zona = z.id_zona;

--cine este responsabil de livrarea unui colet la adresa Strada Teilor nr 21 daca este livrat pe cargo?
select concat(concat(an.nume,' '), an.prenume) as Angajat, 
t.tip_rol, z2.denumire as Zona
from ANGAJATI an, ADRESE ad, ZONE z1, ZONE z2, TIPURI_ROL t
where ad.nume_strada = 'Strada Teilor' 
and ad.numar_strada = 21
and ad.id_zona = z1.id_zona 
and an.id_zona = z2.id_zona
and z1.parinte = z2.id_zona
and an.id_rol = t.id_rol
order by an.id_rol;

--sa se afiseze observatiile pentru zonele cargo
select Z2.denumire, o.descriere
from ZONE z1, ZONE z2, ADRESE ad, OBSERVATII o
where z1.parinte = z2.id_zona and 
ad.id_zona = z1.id_zona and
o.id_observatie = ad.id_observatie and
ad.id_observatie is not null
order by z2.id_zona;

--sa se afiseze cate adrese cu observatie are fiecare zona cargo
select z2.denumire as Zona, sum(case 
                            when ad.id_observatie is null then 0 
                            else 1
                        end) as "Numar observatii"
from ZONE z1, ZONE z2, ADRESE ad
where z1.parinte = z2.id_zona and 
ad.id_zona = z1.id_zona
group by z2.denumire;

--ce manager are manipulatorul lift de pe cargo 3
select concat(concat(m.nume, ' '), m.prenume) as Manager, concat(an.nume, concat(' ', an.prenume)) as Angajat
from ANGAJATI an, MANAGERI_DEPOZIT m, ZONE z, TIPURI_ROL t
where z.denumire = 'Cargo 3' and
z.id_zona = an.id_zona and
z.id_manager = m.id_manager and
an.id_rol = t.id_rol and
t.tip_rol = 'Manipulator lift';


--ADAUGARE

--Zone
insert into ZONE (denumire, parinte, id_manager) VALUES ('Zona 25', 1, 1);

--Angajati
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Dubei', 'Vlad-Adrian', 1, 26);

--Observatii
insert into OBSERVATII (descriere) VALUES ('Semafor nou in prima intersectie');

--Adrese
insert into ADRESE (nume_strada, numar_strada, id_zona, id_observatie) VALUES 
('Strada Ocatav Onicescu', 7, 8, (Select id_observatie from OBSERVATII where descriere = 'Semafor nou in prima intersectoe'));

--MODIFICARE

--Angajati
update ANGAJATI 
set id_rol = 1
where nume = 'Ciobanu'  and id_zona = 1;

update ANGAJATI 
set id_rol = 3
where nume = 'Pintilie'  and id_zona = 1;

update ANGAJATI 
set id_zona = 24
where id_zona = 25;

update ANGAJATI 
set id_zona = 25
where id_zona = 24 and nume = 'Dabija';

--Manageri_Depozit
update MANAGERI_DEPOZIT
SET
id_manager = CASE 
            WHEN id_manager = 1 THEN 5 
            WHEN id_manager = 5 THEN 1
            else id_manager end;
                
--Adrese
update ADRESE
set
id_zona = 23
where nume_strada = 'Aleea Ciucas' and numar_strada = 230;

update ADRESE
set id_observatie = null
where nume_strada = 'Aleea Plevnei' and numar_strada = 30;

--Observatii
update OBSERVATII
set descriere = 'Trafic blocat'
where id_observatie = 6;

--Tipuri_Rol
update TIPURI_ROL
set
id_rol = CASE 
        WHEN id_rol = 1 THEN 3
        WHEN id_rol = 3 THEN 1
        else id_rol end;
            
--Zone
update ZONE
set id_manager = 2
where denumire = 'Zona 18';

--STERGERE

--stergerea adreselor dintr-o zona in vederea adaugarii viitoare in alta zona; (ex: contopirea a doua zone)
delete from ADRESE where id_zona = 25;
--stergerea angajatilor care livrau pe acea zona
delete from ANGAJATI where id_zona = 25;
--stergerea propriu-zisa a zonei
delete from ZONE where denumire = 'Zona 20';
--stergerea unei observatii (ex: a fost rezolvat incidentul ce a cauzat acea observatie)
delete from OBSERVATII where id_observatie = 2;

--VALIDARE

--Tipuri_rol
insert into TIPURI_ROL (tip_rol) VALUES ('Sofer de masina mare');

--Manageri_depozit
insert into MANAGERI_DEPOZIT (nume, prenume) VALUES ('Bleoju8', 'Andrei');
insert into MANAGERI_DEPOZIT (nume, prenume) VALUES ('Ion', 'Mihai-Alexandru-Ignat-Matei');

--Zone
--zone cargo (nu au parinte)
insert into ZONE (denumire, parinte, id_manager) VALUES ('Cargo 1', null, 0);
insert into ZONE (denumire, parinte, id_manager, id_observatie) VALUES ('Cargo 2', null, 1, 20);

--zone mici (au parinte)
insert into ZONE (denumire, parinte, id_manager) VALUES ('Zona 1', 100, 5);

--Angajati
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Dumitrascu7', 'Mihai-Andrei', 2, 1);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Munteanu', 'Paul-Adrian', 6, 6);

--Adrese
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Eternitate', 0, 6);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Mihai Eminescu', 45, 101);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada8 Mihai Eminescu', 45, 44);

--Observatii
insert into OBSERVATII (descriere) VALUES ('O noua groapa la intrarea pe pod, urmata de un accident in prima intersectie, rezultand un trafic ingreunat;');





