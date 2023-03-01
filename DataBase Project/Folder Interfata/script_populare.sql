--Tipuri_rol
insert into TIPURI_ROL (tip_rol) VALUES ('Sofer');
insert into TIPURI_ROL (tip_rol) VALUES ('Ajutor sofer');
insert into TIPURI_ROL (tip_rol) VALUES ('Manipulator lift');

--Manageri_depozit
insert into MANAGERI_DEPOZIT (nume, prenume) VALUES ('Bleoju', 'Andrei');
insert into MANAGERI_DEPOZIT (nume, prenume) VALUES ('Petre', 'Costel');
insert into MANAGERI_DEPOZIT (nume, prenume) VALUES ('Isac', 'Ion-Gheorghe');
insert into MANAGERI_DEPOZIT (nume, prenume) VALUES ('Sava', 'Ovidiu');
insert into MANAGERI_DEPOZIT (nume, prenume) VALUES ('Nechita', 'Alexandru-Mihai');

--Zone
--zone cargo (nu au parinte)
insert into ZONE (denumire, parinte, id_manager) VALUES ('Cargo 1', null, 1);
insert into ZONE (denumire, parinte, id_manager) VALUES ('Cargo 2', null, 2);
insert into ZONE (denumire, parinte, id_manager) VALUES ('Cargo 3', null, 3);
insert into ZONE (denumire, parinte, id_manager) VALUES ('Cargo 4', null, 4);
insert into ZONE (denumire, parinte, id_manager) VALUES ('Cargo 5', null, 5);
--zone mici (au parinte)
insert into ZONE (denumire, parinte, id_manager) VALUES ('Zona 1', 1, 5);
insert into ZONE (denumire, parinte, id_manager) VALUES ('Zona 2', 1, 5);
insert into ZONE (denumire, parinte, id_manager) VALUES ('Zona 3', 1, 5);
insert into ZONE (denumire, parinte, id_manager) VALUES ('Zona 4', 1, 5);
insert into ZONE (denumire, parinte, id_manager) VALUES ('Zona 5', 2, 1);
insert into ZONE (denumire, parinte, id_manager) VALUES ('Zona 6', 2, 1);
insert into ZONE (denumire, parinte, id_manager) VALUES ('Zona 7', 2, 1);
insert into ZONE (denumire, parinte, id_manager) VALUES ('Zona 8', 2, 1);
insert into ZONE (denumire, parinte, id_manager) VALUES ('Zona 9', 3, 2);
insert into ZONE (denumire, parinte, id_manager) VALUES ('Zona 10', 3, 2);
insert into ZONE (denumire, parinte, id_manager) VALUES ('Zona 11', 3, 2);
insert into ZONE (denumire, parinte, id_manager) VALUES ('Zona 12', 3, 2);
insert into ZONE (denumire, parinte, id_manager) VALUES ('Zona 13', 4, 4);
insert into ZONE (denumire, parinte, id_manager) VALUES ('Zona 14', 4, 4);
insert into ZONE (denumire, parinte, id_manager) VALUES ('Zona 15', 4, 4);
insert into ZONE (denumire, parinte, id_manager) VALUES ('Zona 16', 4, 4);
insert into ZONE (denumire, parinte, id_manager) VALUES('Zona 17', 5, 3);
insert into ZONE (denumire, parinte, id_manager) VALUES('Zona 18', 5, 3);
insert into ZONE (denumire, parinte, id_manager) VALUES('Zona 19', 5, 3);
insert into ZONE (denumire, parinte, id_manager) VALUES('Zona 20', 5, 3);

--Angajati
--Cargo 1
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Dumitrascu', 'Mihai-Andrei', 2, 1);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Pintilie', 'Razvan-Nicolae', 1, 1);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Ciobanu', 'Stefan', 3, 1);
--Cargo 2
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Arhip', 'Alexandru', 3, 2);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Savu', 'Bogdan', 1, 2);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Mihnea', 'Ion-Lucian', 2, 2);
--Cargo 3
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Budu', 'Daniel', 3, 3);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Hritcu', 'Vladut', 1, 3);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Galben', 'Vlad', 2, 3);
--Cargo 4
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Buzila', 'Andrei-Mihai', 3, 4);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Mereuta', 'Constantin', 2, 4);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Hagi', 'Ianis', 1, 4);
-- Cargo 5
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Ciubotaru', 'Stefan', 1, 5);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Pasa', 'David', 2, 5);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Selicu', 'Ionut-Dumitru', 3, 5);
--zone mici (doar soferi)
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Munteanu', 'Paul-Adrian', 1, 6);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Mihailescu', 'Mircea', 1, 7);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Iancu', 'Ivan', 1, 8);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Moisescu', 'Hector-Petru', 1, 9);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Negoita', 'Florin-Razvan', 1, 10);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Tomescu', 'Dorel', 1, 11);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Stoica', 'Calin', 1, 12);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Gheorghiu', 'Cezar-Gabriel', 1, 13);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Nitu', 'Aurel', 1, 14);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Pop', 'Iustin-Lucian', 1, 15);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Marin', 'Damian', 1, 16);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Voinea', 'Rafael-Rares', 1, 17);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Murarasu', 'Sabin-Ionel', 1, 18);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Ionita', 'Cosmin', 1, 19);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Diaconescu', 'Eric', 1, 20);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Ciobanu', 'Matei-Sorin', 1, 21);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Rusu', 'Tudor', 1, 22);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Ifrim', 'Sebastian', 1, 23);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Dabija', 'Victor', 1, 24);
insert into ANGAJATI (nume, prenume, id_rol, id_zona) VALUES ('Mocanu Iustin', 'David-Viorel', 1, 25);

--Adrese
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Eternitate', 1, 6);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Eternitate', 25, 6);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Dragos-Voda', 3, 6);

insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Aleea Vasile Petrescu', 78, 7);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Trecatoarea Oitelor', 1, 7);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Bulevardul Chimiei', 19, 7);

insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Bulevardul Chimiei', 104, 8);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Bulevardul Chimiei', 245, 8);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Tepes Voda', 3, 8);

insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Sfantul Lazar', 18, 9);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Zimbrului', 27, 9);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Melodiei', 56, 9);

insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Zimbrului', 178, 10);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Palat', 26, 10);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Palat', 78, 10);

insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Tanasescu', 15, 11);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Aleea Tanasescu', 56, 11);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Iancu Bacalu', 79, 11);

insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Buznea', 321, 12);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Morilor', 123, 12);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Stradela Iancilor', 10, 12);

insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Lotrului', 56, 13);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Pantelimon Halipa', 56, 13);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Holboca', 23, 13);

insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Soseaua Barnova', 67, 14);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Ciocoarei', 45, 14);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Bulevardul Caramidei', 13, 14);

insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Bulevardul Caramidei', 145, 15);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Voinesti', 89, 15);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Arinis', 245, 15);

insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Radu Mihnea', 30, 16);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Haznalei', 320, 16);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Stadion', 54, 16);

insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Stradela Stadion', 46, 17);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Aleea Ciucas', 230, 17);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Smardan', 32, 17);

insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Smardan', 123, 18);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Albinelor', 302, 18);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Aleea Plevnei', 30, 18);

insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Soseaua Nicolina', 24, 19);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Soseaua Nicolina', 25, 19);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Unirii', 765, 19);

insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Nicolina', 20, 20);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Dimitrie Cantemir', 43, 20);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Aleea Izvor', 23, 20);

insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Garoafei', 198, 21);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Garoafei', 30, 21);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Zmeurei', 32, 21);

insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Aleea Stan Mircea', 89, 22);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Teilor', 21, 22);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Mestecanis', 102, 22);

insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Mestecanic', 269, 23);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Aleea Zorilor', 23, 23);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Dealul Craitelor',56, 23);

insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Bisericii', 78, 24);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Bisericii', 123, 24);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Aleea Romana', 90, 24);

insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Aleea Ion Creanga', 40, 25);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Stradela Mihai Eminescu', 75, 25);
insert into ADRESE (nume_strada, numar_strada, id_zona) VALUES ('Strada Mircea Bravo', 94, 25);

--Observatii
insert into OBSERVATII (descriere) VALUES ('Lucrari in desfasurare');
insert into OBSERVATII (descriere) VALUES ('Accident in centrul giratoriu');
insert into OBSERVATII (descriere) VALUES ('Trafic blocat');
insert into OBSERVATII (descriere) VALUES ('Accident pe trecerea de pietoni');
insert into OBSERVATII (descriere) VALUES ('Filtre radar prezente');
insert into OBSERVATII (descriere) VALUES ('Accident la intrarea pe pod');
insert into OBSERVATII (descriere) VALUES ('Se circula cu dificultate');

--Adaugare observatii la adrese
update Adrese
set id_observatie = 1
where id_adresa = 4;

update Adrese
set id_observatie = 2
where id_adresa = 60;

update Adrese
set id_observatie = 3
where id_adresa = 13;

update Adrese
set id_observatie = 4
where id_adresa = 56;

update Adrese
set id_observatie = 5
where id_adresa = 39;

update Adrese
set id_observatie = 6
where id_adresa = 25;

update Adrese
set id_observatie = 7
where id_adresa = 42;


