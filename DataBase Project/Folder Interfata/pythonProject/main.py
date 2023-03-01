import cx_Oracle
from tkinter import *
from tkinter import ttk

# initialize the connection object
conn = None

try:
    # create a connection object
    conn = cx_Oracle.connect("bd029", "bd029", "bd-dc.cs.tuiasi.ro:1539/orcl")
    print(conn.version)
    conn.commit()

except Exception as err:
    print('Error while connecting to db')
    print(err)

# Create an instance of Tk
window = Tk()
window.title("DB Interface")
window.geometry("655x550")

# Create a tab control that manages multiple tabs
tabsystem = ttk.Notebook(window)

# Create new tabs using Frame widget
tabAngajati = Frame(tabsystem)
tabRoluri = Frame(tabsystem)
tabManageri = Frame(tabsystem)
tabZone = Frame(tabsystem)
tabAdrese = Frame(tabsystem)
tabObservatii = Frame(tabsystem)

tabAddAngajat = Frame(tabsystem)
tabEditAngajat = Frame(tabsystem)
tabDelAngajat = Frame(tabsystem)

tabAddManager = Frame(tabsystem)
tabEditManager = Frame(tabsystem)
tabDelManager = Frame(tabsystem)

tabAddZone = Frame(tabsystem)
tabEditZone = Frame(tabsystem)
tabDelZone = Frame(tabsystem)

tabAddAdresa = Frame(tabsystem)
tabDelAdresa = Frame(tabsystem)
tabEditAdresa = Frame(tabsystem)

tabAddObs = Frame(tabsystem)
tabDelObs = Frame(tabsystem)

tabsystem.add(tabAngajati, text='Angajati')
tabsystem.add(tabRoluri, text='Tipuri_Rol')
tabsystem.add(tabManageri, text='Manageri_Depozit')
tabsystem.add(tabZone, text='Zone')
tabsystem.add(tabAdrese, text='Adrese')
tabsystem.add(tabObservatii, text='Observatii')

tabsystem.add(tabAddAngajat, text='Adauga Angajat')
tabsystem.hide(tabAddAngajat)
tabsystem.add(tabEditAngajat, text='Editeaza Angajat')
tabsystem.hide(tabEditAngajat)
tabsystem.add(tabDelAngajat, text='Sterge Angajat')
tabsystem.hide(tabDelAngajat)

tabsystem.add(tabAddManager, text='Adauga Manager')
tabsystem.hide(tabAddManager)
tabsystem.add(tabEditManager, text='Editeaza Manager')
tabsystem.hide(tabEditManager)
tabsystem.add(tabDelManager, text='Sterge Manager')
tabsystem.hide(tabDelManager)

tabsystem.add(tabAddZone, text='Adauga Zone')
tabsystem.hide(tabAddZone)
tabsystem.add(tabEditZone, text='Editeaza Zone')
tabsystem.hide(tabEditZone)
tabsystem.add(tabDelZone, text='Sterge Zone')
tabsystem.hide(tabDelZone)

tabsystem.add(tabAddAdresa, text='Adauga Adresa')
tabsystem.hide(tabAddAdresa)
tabsystem.add(tabEditAdresa, text='Editeaza Adresa')
tabsystem.hide(tabEditAdresa)
tabsystem.add(tabDelAdresa, text='Sterge Adresa')
tabsystem.hide(tabDelAdresa)

tabsystem.add(tabAddObs, text='Adauga Observatie')
tabsystem.hide(tabAddObs)
tabsystem.add(tabDelObs, text='Sterge Observatie')
tabsystem.hide(tabDelObs)

tabsystem.pack(expand=1, fill="both")


def hideTabs():
    tabsystem.hide(tabZone)
    tabsystem.hide(tabEditZone)
    tabsystem.hide(tabDelZone)
    tabsystem.hide(tabAddZone)
    tabsystem.hide(tabAngajati)
    tabsystem.hide(tabEditAngajat)
    tabsystem.hide(tabAddAngajat)
    tabsystem.hide(tabDelAngajat)
    tabsystem.hide(tabRoluri)
    tabsystem.hide(tabManageri)
    tabsystem.hide(tabDelManager)
    tabsystem.hide(tabEditManager)
    tabsystem.hide(tabAddManager)
    tabsystem.hide(tabAdrese)
    tabsystem.hide(tabAddAdresa)
    tabsystem.hide(tabDelAdresa)
    tabsystem.hide(tabObservatii)
    tabsystem.hide(tabDelObs)
    tabsystem.hide(tabAddObs)
    tabsystem.hide(tabEditAdresa)


def showAllTabs():
    tabsystem.select(tabAdrese)
    tabsystem.select(tabObservatii)
    tabsystem.select(tabAngajati)
    tabsystem.select(tabZone)
    tabsystem.select(tabManageri)
    tabsystem.select(tabRoluri)


# functii Angajati


def selectAllAngajati():
    for item in angajati.get_children():
        angajati.delete(item)
    conn.commit()
    cur = conn.cursor()
    command = 'select a.id_angajat, a.nume, a.prenume, z.id_manager, a.id_rol, a.id_zona' \
              ' from ANGAJATI a, ZONE z ' \
              'where a.id_zona = z.id_zona order by id_angajat'
    cur.execute(command)
    for result in cur:
        angajati.insert(parent='', index='end', iid=0, text='',
                        values=(result[0], result[1], result[2], result[3], result[4], result[5]))
    cur.close()


def selectNumeAngajat(nume):
    for item in angajati.get_children():
        angajati.delete(item)
    conn.commit()
    cur = conn.cursor()
    command = 'select a.id_angajat, a.nume, a.prenume, z.id_manager, a.id_rol, a.id_zona' \
              ' from ANGAJATI a, ZONE z ' \
              'where a.id_zona = z.id_zona and ' \
              'nume = \'' + nume + '\' '
    cur.execute(command)
    for result in cur:
        angajati.insert(parent='', index='end', iid=0, text='',
                        values=(result[0], result[1], result[2], result[3], result[4], result[5]))
    cur.close()


def selectJoinAdresa(street, number, cargo):
    for item in angajati.get_children():
        angajati.delete(item)
    if street != '' and number != '':
        conn.commit()
        cur = conn.cursor()
        if cargo == '' or cargo == 'nu' or cargo == 'Nu' or cargo == 'NU':
            command = 'select a.id_angajat, a.nume, a.prenume, z.id_manager, a.id_rol, a.id_zona  ' \
                      'from ANGAJATI a, ZONE z, ADRESE ad ' \
                      ' where a.id_zona = z.id_zona and ' \
                      'ad.nume_strada = \'' + street + '\' and ' \
                                                       'ad.numar_strada = ' + number + '  and ' \
                                                                                       'ad.id_zona = z.id_zona '
        if cargo == 'da' or cargo == 'DA' or cargo == 'Da':
            command = 'select a.id_angajat, a.nume, a.prenume, z1.id_manager, a.id_rol, a.id_zona  ' \
                      'from ANGAJATI a, ZONE z1, ADRESE ad, ZONE z2 ' \
                      ' where ad.nume_strada = \'' + street + '\' and ' \
                                                              'ad.numar_strada = ' + number + '  and ' \
                                                                                              'ad.id_zona = z1.id_zona and ' \
                                                                                              'z1.parinte = z2.id_zona and ' \
                                                                                              'a.id_zona = z2.id_zona '

        cur.execute(command)
        for result in cur:
            angajati.insert(parent='', index='end', iid=0, text='',
                            values=(result[0], result[1], result[2], result[3], result[4], result[5]))
        cur.close()


def selectJoinManager(name, prename):
    for item in angajati.get_children():
        angajati.delete(item)
    if name != '' and prename != '':
        conn.commit()
        cur = conn.cursor()
        command = 'select a.id_angajat, a.nume, a.prenume, z.id_manager, a.id_rol, a.id_zona ' \
                  'from ANGAJATI a, ZONE z, MANAGERI_DEPOZIT m ' \
                  'where a.id_zona = z.id_zona and ' \
                  'z.id_manager = m.id_manager and ' \
                  'm.nume = \'' + name + '\' and ' \
                                         'm.prenume = \'' + prename + '\''
        cur.execute(command)
        for result in cur:
            angajati.insert(parent='', index='end', iid=0, text='',
                            values=(result[0], result[1], result[2], result[3], result[4], result[5]))
        cur.close()


def selectJoinZona(zn):
    for item in angajati.get_children():
        angajati.delete(item)
    conn.commit()
    cur = conn.cursor()
    command = 'select a.id_angajat, a.nume, a.prenume, z.id_manager, a.id_rol, a.id_zona' \
              ' from ANGAJATI a, ZONE z ' \
              'where a.id_zona = z.id_zona and ' \
              'z.denumire = \'' + zn + '\''
    cur.execute(command)
    for result in cur:
        angajati.insert(parent='', index='end', iid=0, text='',
                        values=(result[0], result[1], result[2], result[3], result[4], result[5]))
    cur.close()


def insertIntoAngajat(nume, prenume, id_rol, id_zona):
    if nume != '' and prenume != '' and id_rol != '' and id_zona != '':
        conn.commit()
        cur = conn.cursor()
        command = 'insert into ANGAJATI (nume, prenume, id_rol, id_zona)' \
                  ' VALUES (\'' + nume + '\', \'' + prenume + '\', ' + id_rol + ', ' + id_zona + ')'
        cur.execute(command)
        cur.close()


def deleteNumeAngajat(nume):
    if nume != '':
        conn.commit()
        cur = conn.cursor()
        command = 'delete ANGAJATI where nume = \'' + nume + '\''
        cur.execute(command)
        cur.close()


def delPrenumeAngajat(prenume):
    if prenume != '':
        conn.commit()
        cur = conn.cursor()
        command = 'delete ANGAJATI where prenume = \'' + prenume + '\''
        cur.execute(command)
        cur.close()


def delRolAngajat(rol):
    if rol != '':
        conn.commit()
        cur = conn.cursor()
        command = 'delete ANGAJATI where id_rol = ' + rol + ''
        cur.execute(command)
        cur.close()


def deleteZonaAngajat(zona):
    if zona != '':
        conn.commit()
        cur = conn.cursor()
        command = 'delete ANGAJATI where id_zona = ' + zona + ''
        cur.execute(command)
        cur.close()


def updateZonaAngajat(angajat, zona):
    if nume != '' and zona != '':
        conn.commit()
        cur = conn.cursor()
        command = 'update ANGAJATI ' \
                  'set id_zona = ' + zona + '' \
                                            ' where id_angajat = ' + angajat + ''
        cur.execute(command)
        cur.close()


def updateRolAngajat(angajat, rol):
    if nume != '' and rol != '':
        conn.commit()
        cur = conn.cursor()
        command = 'update ANGAJATI ' \
                  'set id_rol = ' + rol + '' \
                                          ' where id_angajat = ' + angajat + ''
        cur.execute(command)
        cur.close()


# functii Zone


def selectAllZone():
    for item in zone.get_children():
        zone.delete(item)
    conn.commit()
    cur = conn.cursor()
    command = 'select * from ZONE'
    cur.execute(command)
    for result in cur:
        zone.insert(parent='', index='end', iid=0, text='',
                    values=(result[0], result[1], result[2], result[3]))
    cur.close()


def selectNumeZone(nume):
    for item in zone.get_children():
        zone.delete(item)
    conn.commit()
    cur = conn.cursor()
    command = 'select * from zone ' \
              'where denumire = \'' + nume + '\' '
    cur.execute(command)
    for result in cur:
        zone.insert(parent='', index='end', iid=0, text='',
                    values=(result[0], result[1], result[2], result[3]))
    cur.close()


def selectSelfJoin(parinte):
    for item in zone.get_children():
        zone.delete(item)
    if parinte != '':
        conn.commit()
        cur = conn.cursor()
        command = 'select z.* from ZONE z, ZONE c ' \
                  'where c.denumire = \'' + parinte + '\'' \
                                                      'and z.parinte = c.id_zona'
        cur.execute(command)
        for result in cur:
            zone.insert(parent='', index='end', iid=0, text='',
                        values=(result[0], result[1], result[2], result[3]))
        cur.close()


def insertIntoZone(denumire, parinte, id_manager):
    if denumire != '' and id_manager != '':
        conn.commit()
        cur = conn.cursor()
        command = 'insert into Zone (denumire, parinte, id_manager)' \
                  ' VALUES (\'' + denumire + '\', \'' + parinte + '\',' + id_manager + ')'
        cur.execute(command)
        cur.close()


def deleteZona(denumire):
    if denumire != '':
        conn.commit()
        cur = conn.cursor()
        command = 'delete Zone where denumire = \'' + denumire + '\''
        cur.execute(command)
        cur.close()


def updateParinteZona(parinte, denumire):
    if nume != '' and zona != '':
        conn.commit()
        cur = conn.cursor()
        command = 'update ZONE ' \
                  'set parinte = (select id_zona from ZONE where denumire = \'' + parinte + '\')' \
                                                                                            ' where denumire = \'' + denumire + '\''
        cur.execute(command)
        cur.close()


def updateManagerZona(manager, denumire):
    if nume != '' and zona != '':
        conn.commit()
        cur = conn.cursor()
        command = 'update ZONE ' \
                  'set id_manager = ' + manager + '' \
                                                  ' where denumire = \'' + denumire + '\''
        cur.execute(command)
        cur.close()


# functii Adrese


def selectAllAdrese():
    for item in adrese.get_children():
        adrese.delete(item)
    conn.commit()
    cur = conn.cursor()
    command = 'select * from ADRESE'
    cur.execute(command)
    for result in cur:
        adrese.insert(parent='', index='end', iid=0, text='',
                      values=(result[0], result[1], result[2], result[3], result[4]))
    cur.close()


def selectZoneAdrese(id_zona):
    for item in adrese.get_children():
        adrese.delete(item)
    conn.commit()
    cur = conn.cursor()
    command = 'select * from ADRESE where id_zona = ' + id_zona + ''
    cur.execute(command)
    for result in cur:
        adrese.insert(parent='', index='end', iid=0, text='',
                      values=(result[0], result[1], result[2], result[3], result[4]))
    cur.close()


def insertIntoAdrese(nume_strada, numar_strada, id_zona, id_obs):
    if nume_strada != '' and numar_strada != '' and id_zona != '':
        if id_obs == '':
            id_obs = 'NULL'
        conn.commit()
        cur = conn.cursor()
        command = 'insert into ADRESE (nume_strada, numar_strada, id_zona, id_observatie)' \
                  ' VALUES (\'' + nume_strada + '\', ' + numar_strada + ', ' + id_zona + ', ' + id_obs + ')'
        cur.execute(command)
        cur.close()


def setObsAdresa(idAdresa, idObs):
    if idAdresa != '':
        if idObs == '':
            idObs = 'NULL'
        print(idObs)
        conn.commit()
        cur = conn.cursor()
        command = 'update ADRESE ' \
                  ' set id_observatie = ' + idObs + ' ' \
                                                    ' where id_adresa = ' + idAdresa + ''
        cur.execute(command)
        cur.close()


def delIdAdresa(idAddr):
    if idAddr != '':
        conn.commit()
        cur = conn.cursor()
        command = 'delete ADRESE where id_adresa = ' + idAddr + ''
        cur.execute(command)
        cur.close()


# functii Observatii
def selAllObservatii():
    for item in observatii.get_children():
        observatii.delete(item)
    conn.commit()
    cur = conn.cursor()
    command = 'select * from OBSERVATII'
    cur.execute(command)
    for result in cur:
        observatii.insert(parent='', index='end', iid=0, text='',
                          values=(result[0], result[1]))
    cur.close()


def selIdObservatii(obs):
    if obs != '':
        for item in observatii.get_children():
            observatii.delete(item)
        conn.commit()
        cur = conn.cursor()
        command = 'select * from OBSERVATII where id_observatie = ' + obs + ''
        cur.execute(command)
        for result in cur:
            observatii.insert(parent='', index='end', iid=0, text='',
                              values=(result[0], result[1]))
        cur.close()


def insertIntoObservatii(desc):
    if desc != '':
        conn.commit()
        cur = conn.cursor()
        command = 'insert into OBSERVATII (descriere)' \
                  ' VALUES (\'' + desc + '\')'
        cur.execute(command)
        cur.close()


def delFromObservatii(iddObs):
    if iddObs != '':
        conn.commit()
        cur = conn.cursor()
        command = 'delete OBSERVATII where id_observatie = ' + iddObs + ''
        cur.execute(command)
        cur.close()



# functii Roluri


def selectAllRoluri():
    for item in roluri.get_children():
        roluri.delete(item)
    conn.commit()
    cur = conn.cursor()
    command = 'select * from TIPURI_ROL'
    cur.execute(command)
    for result in cur:
        roluri.insert(parent='', index='end', iid=0, text='',
                      values=(result[0], result[1]))
    cur.close()


# functii manager


def selectAllManageri():
    for item in manageri.get_children():
        manageri.delete(item)
    conn.commit()
    cur = conn.cursor()
    command = 'select * from MANAGERI_DEPOZIT'
    cur.execute(command)
    for result in cur:
        manageri.insert(parent='', index='end', iid=0, text='',
                        values=(result[0], result[1], result[2]))
    cur.close()


def selectNumeManageri(nume):
    for item in manageri.get_children():
        manageri.delete(item)
    conn.commit()
    cur = conn.cursor()
    command = 'select * from MANAGERI_DEPOZIT where nume = \'' + nume + '\''
    cur.execute(command)
    for result in cur:
        manageri.insert(parent='', index='end', iid=0, text='',
                        values=(result[0], result[1], result[2]))
    cur.close()


def selectIdManageri(id):
    for item in manageri.get_children():
        manageri.delete(item)
    conn.commit()
    cur = conn.cursor()
    command = 'select * from MANAGERI_DEPOZIT where id_manager = ' + id + ''
    cur.execute(command)
    for result in cur:
        manageri.insert(parent='', index='end', iid=0, text='',
                        values=(result[0], result[1], result[2]))
    cur.close()


def insertIntoManageri(nume, prenume):
    if nume != '' and prenume != '':
        conn.commit()
        cur = conn.cursor()
        command = 'insert into MANAGERI_DEPOZIT (nume, prenume)' \
                  ' VALUES (\'' + nume + '\', \'' + prenume + '\')'
        cur.execute(command)
        cur.close()


def deleteNumeManager(nume):
    if nume != '':
        conn.commit()
        cur = conn.cursor()
        command = 'delete MANAGERI_DEPOZIT where nume = \'' + nume + '\''
        cur.execute(command)
        cur.close()


def deletePrenumeManager(prenume):
    if prenume != '':
        conn.commit()
        cur = conn.cursor()
        command = 'delete MANAGERI_DEPOZIT where prenume = \'' + prenume + '\''
        cur.execute(command)
        cur.close()


## ANGAJATI
label1 = Label(tabAngajati, text="Tabel Angajati")
label1.grid(column=1,
            row=1,
            padx=40,
            pady=40)
angajati = ttk.Treeview(label1)
angajati.pack(padx=0, pady=40)

angajati['columns'] = ('id_angajat', 'Nume', 'Prenume', 'id_manager', 'id_rol', 'id_zona')

angajati.column("#0", width=0, stretch=NO)
angajati.column("id_angajat", anchor=CENTER, width=80)
angajati.column("Nume", anchor=CENTER, width=90)
angajati.column("Prenume", anchor=CENTER, width=160)
angajati.column("id_rol", anchor=CENTER, width=80)
angajati.column("id_zona", anchor=CENTER, width=80)
angajati.column("id_manager", anchor=CENTER, width=80)

angajati.heading("#0", text="", anchor=CENTER)
angajati.heading("id_angajat", text="id", anchor=CENTER)
angajati.heading("Nume", text="Nume", anchor=CENTER)
angajati.heading("Prenume", text="Prenume", anchor=CENTER)
angajati.heading("id_rol", text="id_rol", anchor=CENTER)
angajati.heading("id_zona", text="id_zona", anchor=CENTER)
angajati.heading("id_manager", text="id_manager", anchor=CENTER)

adresaJoin = Frame(label1)
adresaJoin.pack(expand=TRUE, fill=BOTH)

strada = Label(adresaJoin, text="Strada")
strada.grid(row=0, column=0)
nr = Label(adresaJoin, text="Nr.")
nr.grid(row=0, column=1)
cargo = Label(adresaJoin, text="Cargo?")
cargo.grid(row=0, column=2)

strada_entry = Entry(adresaJoin)
strada_entry.grid(row=1, column=0)
nr_entry = Entry(adresaJoin)
nr_entry.grid(row=1, column=1)
cargo_entry = Entry(adresaJoin)
cargo_entry.grid(row=1, column=2)

bSelJoinAdresa = Button(tabAngajati, text="Vezi",
                        command=lambda: [selectJoinAdresa(strada_entry.get(), nr_entry.get(), cargo_entry.get()),
                                         nume_entry.delete(0, END),
                                         prenume_entry.delete(0, END),
                                         zona_entry.delete(0, END),
                                         sNume_entry.delete(0, END)])

bSelJoinAdresa.place(x=420, y=365)

managerJoin = Frame(label1)
managerJoin.pack(expand=TRUE, fill=BOTH)

nume = Label(managerJoin, text="Manager (Nume)")
nume.grid(row=2, column=0)
prenume = Label(managerJoin, text="Manager (Prenume)")
prenume.grid(row=2, column=1)

nume_entry = Entry(managerJoin)
nume_entry.grid(row=3, column=0)
prenume_entry = Entry(managerJoin)
prenume_entry.grid(row=3, column=1)

bSelJoinManager = Button(tabAngajati, text="Vezi",
                         command=lambda: [selectJoinManager(nume_entry.get(), prenume_entry.get()),
                                          zona_entry.delete(0, END),
                                          strada_entry.delete(0, END),
                                          nr_entry.delete(0, END),
                                          cargo_entry.delete(0, END),
                                          sNume_entry.delete(0, END)])

bSelJoinManager.place(x=420, y=403)

zonaJoin = Frame(label1)
zonaJoin.pack(expand=TRUE, fill=BOTH)

zona = Label(zonaJoin, text="Zona (denumire)")
zona.grid(row=4, column=0)
zona_entry = Entry(zonaJoin)
zona_entry.grid(row=5, column=0)

bSelJoinZona = Button(tabAngajati, text="Vezi",
                      command=lambda: [selectJoinZona(zona_entry.get()),
                                       nume_entry.delete(0, END),
                                       prenume_entry.delete(0, END),
                                       strada_entry.delete(0, END),
                                       nr_entry.delete(0, END),
                                       cargo_entry.delete(0, END),
                                       sNume_entry.delete(0, END)])
bSelJoinZona.place(x=420, y=445)

selNameAngajat = Frame(label1)
selNameAngajat.pack(expand=TRUE, fill=BOTH)

sNume = Label(selNameAngajat, text="Nume")
sNume.grid(row=6, column=0)

sNume_entry = Entry(selNameAngajat)
sNume_entry.grid(row=7, column=0)

bSelNume = Button(tabAngajati, text="Vezi",
                  command=lambda: [selectNumeAngajat(sNume_entry.get()),
                                   nume_entry.delete(0, END),
                                   prenume_entry.delete(0, END),
                                   strada_entry.delete(0, END),
                                   nr_entry.delete(0, END),
                                   cargo_entry.delete(0, END),
                                   zona_entry.delete(0, END)])

bSelNume.place(x=420, y=487)

# buttons
bAddA = Button(tabAngajati, text="Adauga Angajat",
               command=lambda: [hideTabs(), tabsystem.select(tabAddAngajat)])
bAddA.place(x=25, y=10)
bEditA = Button(tabAngajati, text="Editeaza Angajat",
                command=lambda: [hideTabs(), tabsystem.select(tabEditAngajat)])
bEditA.place(x=125, y=10)
bDelA = Button(tabAngajati, text="Sterge Angajat",
               command=lambda: [hideTabs(), tabsystem.select(tabDelAngajat)])
bDelA.place(x=228, y=10)

bSelAllA = Button(tabAngajati, text="Vezi tot",
                  command=lambda: selectAllAngajati())
bSelAllA.place(x=430, y=10)

label7 = Label(tabAddAngajat, text="Adauga Angajati")
label7.grid(column=1,
            row=1,
            padx=40,
            pady=40)

insertAngajat = Frame(label7)
insertAngajat.pack(expand=TRUE, fill=BOTH)

nume = Label(insertAngajat, text="Nume")
nume.grid(row=0, column=0)
prenume = Label(insertAngajat, text="Prenume")
prenume.grid(row=0, column=1)
id_rol = Label(insertAngajat, text="id_rol")
id_rol.grid(row=0, column=2)
id_zona = Label(insertAngajat, text="id_zona")
id_zona.grid(row=0, column=3)

numeA_entry = Entry(insertAngajat)
numeA_entry.grid(row=1, column=0)
prenumeA_entry = Entry(insertAngajat)
prenumeA_entry.grid(row=1, column=1)
id_rol_entry = Entry(insertAngajat)
id_rol_entry.grid(row=1, column=2)
id_zona_entry = Entry(insertAngajat)
id_zona_entry.grid(row=1, column=3)

b7 = Button(tabAddAngajat, text="Adauga",
            command=lambda: [
                insertIntoAngajat(numeA_entry.get(), prenumeA_entry.get(), id_rol_entry.get(), id_zona_entry.get()),
                tabsystem.hide(tabAddAngajat), showAllTabs(), tabsystem.select(tabAngajati),
                numeA_entry.delete(0, END),
                prenumeA_entry.delete(0, END),
                id_rol_entry.delete(0, END),
                id_zona_entry.delete(0, END)])

b7.place(x=550, y=58)

b71 = Button(tabAddAngajat, text="Back",
             command=lambda: [tabsystem.hide(tabAddAngajat), showAllTabs(), tabsystem.select(tabAngajati),
                              numeA_entry.delete(0, END),
                              prenumeA_entry.delete(0, END),
                              id_rol_entry.delete(0, END),
                              id_zona_entry.delete(0, END)])

b71.place(x=557, y=100)

label8 = Label(tabEditAngajat, text="Editeaza Angajati")
label8.grid(column=1,
            row=1,
            padx=40,
            pady=40)

setZona = Frame(label8)
setZona.pack(expand=TRUE, fill=BOTH)

sZona = Label(setZona, text="Zona (id)")
sZona.grid(row=0, column=1)
setZonaById = Label(setZona, text="id_angajat")
setZonaById.grid(row=0, column=0)

sZona_entry = Entry(setZona)
sZona_entry.grid(row=1, column=1)
setZonaById_entry = Entry(setZona)
setZonaById_entry.grid(row=1, column=0)

b81 = Button(tabEditAngajat, text="Update Zona",
             command=lambda: [tabsystem.hide(tabEditAngajat), showAllTabs(), tabsystem.select(tabAngajati),
                              updateZonaAngajat(setZonaById_entry.get(), sZona_entry.get())])
b81.place(x=300, y=58)

setRol = Frame(label8)
setRol.pack(expand=TRUE, fill=BOTH)

sRol = Label(setRol, text="Rol (id)")
sRol.grid(row=0, column=1)
setRolById = Label(setRol, text="id_angajat")
setRolById.grid(row=0, column=0)

sRol_entry = Entry(setRol)
sRol_entry.grid(row=1, column=1)
setRolById_entry = Entry(setRol)
setRolById_entry.grid(row=1, column=0)

b82 = Button(tabEditAngajat, text="Update Rol",
             command=lambda: [tabsystem.hide(tabEditAngajat), showAllTabs(), tabsystem.select(tabAngajati),
                              updateRolAngajat(setRolById_entry.get(), sRol_entry.get())])
b82.place(x=304, y=100)

b83 = Button(tabEditAngajat, text="Back",
             command=lambda: [tabsystem.hide(tabEditAngajat), showAllTabs(), tabsystem.select(tabAngajati)])
b83.place(x=318, y=140)

label9 = Label(tabDelAngajat, text="Sterge Angajati")
label9.grid(column=1,
            row=1,
            padx=40,
            pady=40)

stergeNume = Frame(label9)
stergeNume.pack(expand=TRUE, fill=BOTH)

delNume = Label(stergeNume, text="Nume")
delNume.grid(row=0, column=0)

delNume_entry = Entry(stergeNume)
delNume_entry.grid(row=1, column=0)

b91 = Button(tabDelAngajat, text="Sterge",
             command=lambda: [tabsystem.hide(tabDelAngajat), showAllTabs(), tabsystem.select(tabAngajati),
                              deleteNumeAngajat(delNume_entry.get()),
                              delZona_entry.delete(0, END),
                              delPrenume_entry.delete(0, END),
                              delRol_entry.delete(0, END)])
b91.place(x=180, y=58)

stergePrenume = Frame(label9)
stergePrenume.pack(expand=TRUE, fill=BOTH)

delPrenume = Label(stergePrenume, text="Prenume")
delPrenume.grid(row=0, column=0)

delPrenume_entry = Entry(stergePrenume)
delPrenume_entry.grid(row=1, column=0)

b92 = Button(tabDelAngajat, text="Sterge",
             command=lambda: [tabsystem.hide(tabDelAngajat), showAllTabs(), tabsystem.select(tabAngajati),
                              delPrenumeAngajat(delPrenume_entry.get()),
                              delZona_entry.delete(0, END),
                              delNume_entry.delete(0, END),
                              delRol_entry.delete(0, END)
                              ])
b92.place(x=180, y=100)

stergeId_rol = Frame(label9)
stergeId_rol.pack(expand=TRUE, fill=BOTH)

delRol = Label(stergeId_rol, text="id_rol")
delRol.grid(row=0, column=0)

delRol_entry = Entry(stergeId_rol)
delRol_entry.grid(row=1, column=0)

b93 = Button(tabDelAngajat, text="Sterge",
             command=lambda: [tabsystem.hide(tabDelAngajat), showAllTabs(), tabsystem.select(tabAngajati),
                              delRolAngajat(delRol_entry.get()),
                              delZona_entry.delete(0, END),
                              delPrenume_entry.delete(0, END),
                              delNume_entry.delete(0, END)
                              ])
b93.place(x=180, y=140)

stergeId_zona = Frame(label9)
stergeId_zona.pack(expand=TRUE, fill=BOTH)

delZona = Label(stergeId_zona, text="id_zona")
delZona.grid(row=0, column=0)

delZona_entry = Entry(stergeId_zona)
delZona_entry.grid(row=1, column=0)

b94 = Button(tabDelAngajat, text="Sterge",
             command=lambda: [tabsystem.hide(tabDelAngajat), showAllTabs(), tabsystem.select(tabAngajati),
                              deleteZonaAngajat(delZona_entry.get()),
                              delNume_entry.delete(0, END),
                              delPrenume_entry.delete(0, END),
                              delRol_entry.delete(0, END)
                              ])
b94.place(x=180, y=180)

b95 = Button(tabDelAngajat, text="Back",
             command=lambda: [tabsystem.hide(tabDelAngajat), showAllTabs(), tabsystem.select(tabAngajati)])
b95.place(x=183, y=220)

## ROLURI
label2 = Label(tabRoluri, text="Tabel Roluri")
label2.grid(column=1,
            row=1,
            padx=40,
            pady=40)
roluri = ttk.Treeview(label2)
roluri.pack(padx=0, pady=40)

roluri['columns'] = ('id_rol', 'tip_rol')

roluri.column("#0", width=0, stretch=NO)
roluri.column("id_rol", anchor=CENTER, width=80)
roluri.column("tip_rol", anchor=CENTER, width=490)

roluri.heading("#0", text="", anchor=CENTER)
roluri.heading("id_rol", text="id", anchor=CENTER)
roluri.heading("tip_rol", text="Rol", anchor=CENTER)

selectAllRoluri()

## MANAGERI
label3 = Label(tabManageri, text="Tabel Manageri")
label3.grid(column=1,
            row=1,
            padx=40,
            pady=40)
manageri = ttk.Treeview(label3)
manageri.pack(padx=0, pady=40)

manageri['columns'] = ('id_manager', 'Nume', 'Prenume')

manageri.column("#0", width=0, stretch=NO)
manageri.column("id_manager", anchor=CENTER, width=80)
manageri.column("Nume", anchor=CENTER, width=90)
manageri.column("Prenume", anchor=CENTER, width=160)

manageri.heading("#0", text="", anchor=CENTER)
manageri.heading("id_manager", text="id", anchor=CENTER)
manageri.heading("Nume", text="Nume", anchor=CENTER)
manageri.heading("Prenume", text="Prenume", anchor=CENTER)

bSelAllM = Button(tabManageri, text="Vezi tot",
                  command=lambda: selectAllManageri())
bSelAllM.place(x=430, y=10)

selNameManager = Frame(label3)
selNameManager.pack(expand=TRUE, fill=BOTH)

sNumeM = Label(selNameManager, text="Nume")
sNumeM.grid(row=6, column=0)

sNumeM_entry = Entry(selNameManager)
sNumeM_entry.grid(row=7, column=0)

bSelNumeM = Button(tabManageri, text="Vezi",
                   command=lambda: [selectNumeManageri(sNumeM_entry.get()),
                                    sIdM_entry.delete(0, END)])

bSelNumeM.place(x=180, y=365)

selIdManager = Frame(label3)
selIdManager.pack(expand=TRUE, fill=BOTH)

sIdM = Label(selIdManager, text="Id")
sIdM.grid(row=6, column=0)

sIdM_entry = Entry(selIdManager)
sIdM_entry.grid(row=7, column=0)

bSelIdM = Button(tabManageri, text="Vezi",
                 command=lambda: [selectIdManageri(sIdM_entry.get()),
                                  sNumeM_entry.delete(0, END)])

bSelIdM.place(x=180, y=405)

# buttons
bAddM = Button(tabManageri, text="Adauga Manager",
               command=lambda: [hideTabs(), tabsystem.select(tabAddManager)])
bAddM.place(x=25, y=10)
bDelM = Button(tabManageri, text="Sterge Manager",
               command=lambda: [hideTabs(), tabsystem.select(tabDelManager)])
bDelM.place(x=132, y=10)

label77 = Label(tabAddManager, text="Adauga Manager")
label77.grid(column=1,
             row=1,
             padx=40,
             pady=40)
insertManager = Frame(label77)
insertManager.pack(expand=TRUE, fill=BOTH)

numeMan = Label(insertManager, text="Nume")
numeMan.grid(row=0, column=0)
prenumeMan = Label(insertManager, text="Prenume")
prenumeMan.grid(row=0, column=1)

numeMan_entry = Entry(insertManager)
numeMan_entry.grid(row=1, column=0)
prenumeMan_entry = Entry(insertManager)
prenumeMan_entry.grid(row=1, column=1)

b771 = Button(tabAddManager, text="Adauga",
              command=lambda: [
                  insertIntoManageri(numeMan_entry.get(), prenumeMan_entry.get()),
                  tabsystem.hide(tabAddManager), showAllTabs(), tabsystem.select(tabManageri),
                  numeMan_entry.delete(0, END),
                  prenumeMan_entry.delete(0, END)])
b771.place(x=430, y=58)

b772 = Button(tabAddManager, text="Back",
              command=lambda: [
                  tabsystem.hide(tabAddManager), showAllTabs(), tabsystem.select(tabManageri),
                  numeMan_entry.delete(0, END),
                  prenumeMan_entry.delete(0, END)])
b772.place(x=435, y=100)

label99 = Label(tabDelManager, text="Sterge Manager")
label99.grid(column=1,
             row=1,
             padx=40,
             pady=40)
stergeNumeMan = Frame(label99)
stergeNumeMan.pack(expand=TRUE, fill=BOTH)

delNumeMan = Label(stergeNumeMan, text="Nume")
delNumeMan.grid(row=0, column=0)

delNumeMan_entry = Entry(stergeNumeMan)
delNumeMan_entry.grid(row=1, column=0)

b991 = Button(tabDelManager, text="Sterge",
              command=lambda: [tabsystem.hide(tabDelManager), showAllTabs(), tabsystem.select(tabManageri),
                               deleteNumeManager(delNumeMan_entry.get()),
                               delPrenumeMan_entry.delete(0, END)])
b991.place(x=180, y=58)

stergePrenumeMan = Frame(label99)
stergePrenumeMan.pack(expand=TRUE, fill=BOTH)

delPrenumeMan = Label(stergePrenumeMan, text="Prenume")
delPrenumeMan.grid(row=0, column=0)

delPrenumeMan_entry = Entry(stergePrenumeMan)
delPrenumeMan_entry.grid(row=1, column=0)

b992 = Button(tabDelManager, text="Sterge",
              command=lambda: [tabsystem.hide(tabDelManager), showAllTabs(), tabsystem.select(tabManageri),
                               deletePrenumeManager(delPrenumeMan_entry.get()),
                               delNumeMan_entry.delete(0, END)])
b992.place(x=180, y=100)

b995 = Button(tabDelManager, text="Back",
              command=lambda: [tabsystem.hide(tabDelManager), showAllTabs(), tabsystem.select(tabManageri)])
b995.place(x=184, y=140)

## ZONE
label4 = Label(tabZone, text="Tabel Zone")
label4.grid(column=1,
            row=1,
            padx=40,
            pady=40)
zone = ttk.Treeview(label4)
zone.pack(padx=0, pady=40)

zone['columns'] = ('id_zona', 'Denumire', 'Parinte', 'id_manager')

zone.column("#0", width=0, stretch=NO)
zone.column("id_zona", anchor=CENTER, width=80)
zone.column("Denumire", anchor=CENTER, width=80)
zone.column("Parinte", anchor=CENTER, width=80)
zone.column("id_manager", anchor=CENTER, width=80)

zone.heading("#0", text="", anchor=CENTER)
zone.heading("id_zona", text="id", anchor=CENTER)
zone.heading("Denumire", text="Denumire", anchor=CENTER)
zone.heading("Parinte", text="Parinte", anchor=CENTER)
zone.heading("id_manager", text="id_manager", anchor=CENTER)

bSelAllZ = Button(tabZone, text="Vezi tot",
                  command=lambda: selectAllZone())
bSelAllZ.place(x=430, y=10)

selDenumire = Frame(label4)
selDenumire.pack(expand=TRUE, fill=BOTH)

selDen = Label(selDenumire, text="Nume")
selDen.grid(row=6, column=0)

selDen_entry = Entry(selDenumire)
selDen_entry.grid(row=7, column=0)

bSelDen = Button(tabZone, text="Vezi",
                 command=lambda: [selectNumeZone(selDen_entry.get()),
                                  selPar_entry.delete(0, END)])

bSelDen.place(x=180, y=365)

selParinte = Frame(label4)
selParinte.pack(expand=TRUE, fill=BOTH)

selPar = Label(selParinte, text="Parinte")
selPar.grid(row=6, column=0)

selPar_entry = Entry(selParinte)
selPar_entry.grid(row=7, column=0)

bSelPar = Button(tabZone, text="Vezi",
                 command=lambda: [selectSelfJoin(selPar_entry.get()),
                                  selDen_entry.delete(0, END)])

bSelPar.place(x=180, y=405)

# buttons
bAddZ = Button(tabZone, text="Adauga Zona",
               command=lambda: [hideTabs(), tabsystem.select(tabAddZone)])
bAddZ.place(x=25, y=10)
bEditZ = Button(tabZone, text="Editeaza Zona",
                command=lambda: [hideTabs(), tabsystem.select(tabEditZone)])
bEditZ.place(x=125, y=10)
bDelZ = Button(tabZone, text="Sterge Zona",
               command=lambda: [hideTabs(), tabsystem.select(tabDelZone)])
bDelZ.place(x=225, y=10)

label777 = Label(tabAddZone, text="Adauga Zona")
label777.grid(column=1,
              row=1,
              padx=40,
              pady=40)
insertZona = Frame(label777)
insertZona.pack(expand=TRUE, fill=BOTH)

denumire = Label(insertZona, text="Denumire")
denumire.grid(row=0, column=0)
parinte = Label(insertZona, text="Parinte")
parinte.grid(row=0, column=1)
id_managerZ = Label(insertZona, text="id_manager")
id_managerZ.grid(row=0, column=2)

denumire_entry = Entry(insertZona)
denumire_entry.grid(row=1, column=0)
parinte_entry = Entry(insertZona)
parinte_entry.grid(row=1, column=1)
id_managerZ_entry = Entry(insertZona)
id_managerZ_entry.grid(row=1, column=2)

b7771 = Button(tabAddZone, text="Adauga",
               command=lambda: [
                   insertIntoZone(denumire_entry.get(), parinte_entry.get(), id_managerZ_entry.get()),
                   tabsystem.hide(tabAddZone), showAllTabs(), tabsystem.select(tabZone),
                   denumire_entry.delete(0, END),
                   parinte_entry.delete(0, END),
                   id_managerZ_entry.delete(0, END)])

b7771.place(x=550, y=58)

b7772 = Button(tabAddZone, text="Back",
               command=lambda: [tabsystem.hide(tabAddZone), showAllTabs(), tabsystem.select(tabZone),
                                numeA_entry.delete(0, END),
                                prenumeA_entry.delete(0, END),
                                id_rol_entry.delete(0, END),
                                id_zona_entry.delete(0, END)])

b7772.place(x=557, y=100)

label888 = Label(tabEditZone, text="Editeaza Zona")
label888.grid(column=1,
              row=1,
              padx=40,
              pady=40)
setParinte = Frame(label888)
setParinte.pack(expand=TRUE, fill=BOTH)

sDenum = Label(setParinte, text="Zona (denumire)")
sDenum.grid(row=0, column=0)
sParByDenum = Label(setParinte, text="Parinte (denumire)")
sParByDenum.grid(row=0, column=1)

sDenum_entry = Entry(setParinte)
sDenum_entry.grid(row=1, column=0)
sParByDenum_entry = Entry(setParinte)
sParByDenum_entry.grid(row=1, column=1)

b8881 = Button(tabEditZone, text="Update Parinte",
               command=lambda: [tabsystem.hide(tabEditZone), showAllTabs(), tabsystem.select(tabZone),
                                updateParinteZona(sParByDenum_entry.get(), sDenum_entry.get())])
b8881.place(x=300, y=58)

setManager = Frame(label888)
setManager.pack(expand=TRUE, fill=BOTH)

sDenumm = Label(setManager, text="Zona (denumire)")
sDenumm.grid(row=0, column=0)
setMngByDenum = Label(setManager, text="Manager (id)")
setMngByDenum.grid(row=0, column=1)

sDenumm_entry = Entry(setManager)
sDenumm_entry.grid(row=1, column=0)
setMngByDenum_entry = Entry(setManager)
setMngByDenum_entry.grid(row=1, column=1)

b8882 = Button(tabEditZone, text="Update Manager",
               command=lambda: [tabsystem.hide(tabEditZone), showAllTabs(), tabsystem.select(tabZone),
                                updateManagerZona(setMngByDenum_entry.get(), sDenumm_entry.get())])
b8882.place(x=300, y=100)

b8883 = Button(tabEditZone, text="Back",
               command=lambda: [tabsystem.hide(tabEditZone), showAllTabs(), tabsystem.select(tabZone)])
b8883.place(x=318, y=140)

label999 = Label(tabDelZone, text="Sterge Zona")
label999.grid(column=1,
              row=1,
              padx=40,
              pady=40)

stergeDenumire = Frame(label999)
stergeDenumire.pack(expand=TRUE, fill=BOTH)

delDenum = Label(stergeDenumire, text="Denumire")
delDenum.grid(row=0, column=0)

delDenum_entry = Entry(stergeDenumire)
delDenum_entry.grid(row=1, column=0)

b9991 = Button(tabDelZone, text="Sterge",
               command=lambda: [tabsystem.hide(tabDelZone), showAllTabs(), tabsystem.select(tabZone),
                                deleteZona(delDenum_entry.get()),
                                delDenum_entry.delete(0, END)])
b9991.place(x=184, y=60)

b9992 = Button(tabDelZone, text="Back",
               command=lambda: [tabsystem.hide(tabDelZone), showAllTabs(), tabsystem.select(tabZone),
                                deleteNumeAngajat(delNume_entry.get()),
                                delDenum_entry.delete(0, END)])
b9992.place(x=188, y=100)

## ADRESE
label5 = Label(tabAdrese, text="Tabel Adrese")
label5.grid(column=1,
            row=1,
            padx=40,
            pady=40)
adrese = ttk.Treeview(label5)
adrese.pack(padx=0, pady=40)

adrese['columns'] = ('id_adresa', 'Nume_Strada', 'Nr', 'id_zona', 'id_observatie')

adrese.column("#0", width=0, stretch=NO)
adrese.column("id_adresa", anchor=CENTER, width=80)
adrese.column("Nume_Strada", anchor=CENTER, width=180)
adrese.column("Nr", anchor=CENTER, width=80)
adrese.column("id_zona", anchor=CENTER, width=80)
adrese.column("id_observatie", anchor=CENTER, width=80)

adrese.heading("#0", text="", anchor=CENTER)
adrese.heading("id_adresa", text="id", anchor=CENTER)
adrese.heading("Nume_Strada", text="Nume_Strada", anchor=CENTER)
adrese.heading("Nr", text="Nr.", anchor=CENTER)
adrese.heading("id_zona", text="id_zona", anchor=CENTER)
adrese.heading("id_observatie", text="id_observatie", anchor=CENTER)

bSelAllAd = Button(tabAdrese, text="Vezi tot",
                   command=lambda: selectAllAdrese())
bSelAllAd.place(x=430, y=10)

selectByZona = Frame(label5)
selectByZona.pack(expand=TRUE, fill=BOTH)

zonaID = Label(selectByZona, text="Zona (id)")
zonaID.grid(row=2, column=0)

zonaID_entry = Entry(selectByZona)
zonaID_entry.grid(row=3, column=0)

bSelByZ = Button(tabAdrese, text="Vezi",
                 command=lambda: [selectZoneAdrese(zonaID_entry.get())])

bSelByZ.place(x=175, y=365)

# buttons
bAddAd = Button(tabAdrese, text="Adauga Adresa",
                command=lambda: [hideTabs(), tabsystem.select(tabAddAdresa)])
bAddAd.place(x=25, y=10)
bEditZ = Button(tabAdrese, text="Editeaza Adresa",
                command=lambda: [hideTabs(), tabsystem.select(tabEditAdresa)])
bEditZ.place(x=125, y=10)
bDelAd = Button(tabAdrese, text="Sterge Adresa",
                command=lambda: [hideTabs(), tabsystem.select(tabDelAdresa)])
bDelAd.place(x=225, y=10)

label7777 = Label(tabAddAdresa, text="Adauga Adresa")
label7777.grid(column=1,
               row=1,
               padx=40,
               pady=40)
insertAdresa = Frame(label7777)
insertAdresa.pack(expand=TRUE, fill=BOTH)

numeStrada = Label(insertAdresa, text="Nume Strada")
numeStrada.grid(row=0, column=0)
nrStrada = Label(insertAdresa, text="Nr. ")
nrStrada.grid(row=0, column=1)
idZonaAd = Label(insertAdresa, text="id_zona")
idZonaAd.grid(row=0, column=2)
idObsAd = Label(insertAdresa, text="id_observatie")
idObsAd.grid(row=0, column=3)

numeStrada_entry = Entry(insertAdresa)
numeStrada_entry.grid(row=1, column=0)
nrStrada_entry = Entry(insertAdresa)
nrStrada_entry.grid(row=1, column=1)
idZonaAd_entry = Entry(insertAdresa)
idZonaAd_entry.grid(row=1, column=2)
idObsAd_entry = Entry(insertAdresa)
idObsAd_entry.grid(row=1, column=3)

b77771 = Button(tabAddAdresa, text="Adauga",
                command=lambda: [
                    insertIntoAdrese(numeStrada_entry.get(), nrStrada_entry.get(), idZonaAd_entry.get(),
                                     idObsAd_entry.get()),
                    tabsystem.hide(tabAddAdresa), showAllTabs(), tabsystem.select(tabAdrese),
                    numeStrada_entry.delete(0, END),
                    nrStrada_entry.delete(0, END),
                    idZonaAd_entry.delete(0, END),
                    idObsAd_entry.delete(0, END)])

b77771.place(x=550, y=58)

b77772 = Button(tabAddAdresa, text="Back",
                command=lambda: [tabsystem.hide(tabAddAdresa), showAllTabs(), tabsystem.select(tabAdrese),
                                 numeStrada_entry.delete(0, END),
                                 nrStrada_entry.delete(0, END),
                                 idZonaAd_entry.delete(0, END),
                                 idObsAd_entry.delete(0, END)])

b77772.place(x=557, y=100)

label8888 = Label(tabEditAdresa, text="Editeaza Adresa")
label8888.grid(column=1,
               row=1,
               padx=40,
               pady=40)
idWithObs = Frame(label8888)
idWithObs.pack(expand=TRUE, fill=BOTH)

idStrada = Label(idWithObs, text="Adresa (id)")
idStrada.grid(row=0, column=0)
idObsStr = Label(idWithObs, text="Observatie (id)")
idObsStr.grid(row=0, column=1)

idStrada_entry = Entry(idWithObs)
idStrada_entry.grid(row=1, column=0)
idObsStr_entry = Entry(idWithObs)
idObsStr_entry.grid(row=1, column=1)

b88881 = Button(tabEditAdresa, text="Modifica Observatie",
                command=lambda: [
                    setObsAdresa(idStrada_entry.get(), idObsStr_entry.get()),
                    tabsystem.hide(tabEditAdresa), showAllTabs(), tabsystem.select(tabAdrese),
                    idStrada_entry.delete(0, END),
                    idObsStr_entry.delete(0, END)])

b88881.place(x=330, y=58)

b88882 = Button(tabEditAdresa, text="Back",
                command=lambda: [tabsystem.hide(tabEditAdresa), showAllTabs(), tabsystem.select(tabAdrese),
                                 idStrada_entry.delete(0, END),
                                 idObsStr_entry.delete(0, END)])

b88882.place(x=357, y=100)

label9999 = Label(tabDelAdresa, text="Sterge Adresa")
label9999.grid(column=1,
               row=1,
               padx=40,
               pady=40)
idStrSters = Frame(label9999)
idStrSters.pack(expand=TRUE, fill=BOTH)

idStradaDel = Label(idStrSters, text="Adresa (id)")
idStradaDel.grid(row=0, column=0)

idStradaDel_entry = Entry(idStrSters)
idStradaDel_entry.grid(row=1, column=0)
b9999 = Button(tabDelAdresa, text="Sterge Adresa",
               command=lambda: [
                   delIdAdresa(idStradaDel_entry.get()),
                   tabsystem.hide(tabDelAdresa), showAllTabs(), tabsystem.select(tabAdrese),
                   idStradaDel_entry.delete(0, END)])
b9999.place(x=330, y=58)

## OBSERVATII
label6 = Label(tabObservatii, text="Tabel Observatii")
label6.grid(column=1,
            row=1,
            padx=40,
            pady=40)
observatii = ttk.Treeview(label6)
observatii.pack(padx=0, pady=40)

observatii['columns'] = ('id_observatie', 'Descriere')

observatii.column("#0", width=0, stretch=NO)
observatii.column("id_observatie", anchor=CENTER, width=80)
observatii.column("Descriere", anchor=CENTER, width=180)

observatii.heading("#0", text="", anchor=CENTER)
observatii.heading("id_observatie", text="id", anchor=CENTER)
observatii.heading("Descriere", text="Descriere", anchor=CENTER)

bSelAllObs = Button(tabObservatii, text="Vezi tot",
                    command=lambda: selAllObservatii())
bSelAllObs.place(x=430, y=10)

selObsId = Frame(label6)
selObsId.pack(expand=TRUE, fill=BOTH)

obsId = Label(selObsId, text="Obs (id)")
obsId.grid(row=2, column=0)

obsId_entry = Entry(selObsId)
obsId_entry.grid(row=3, column=0)

bSelByObs = Button(tabObservatii, text="Vezi",
                   command=lambda: [selIdObservatii(obsId_entry.get())])

bSelByObs.place(x=175, y=365)

# buttons
bAddAd = Button(tabObservatii, text="Adauga Observatie",
                command=lambda: [hideTabs(), tabsystem.select(tabAddObs)])
bAddAd.place(x=25, y=10)
bDelAd = Button(tabObservatii, text="Sterge Observatie",
                command=lambda: [hideTabs(), tabsystem.select(tabDelObs)])
bDelAd.place(x=145, y=10)

label77777 = Label(tabAddObs, text="Adauga Observatie")
label77777.grid(column=1,
                row=1,
                padx=40,
                pady=40)
insertObservatie = Frame(label77777)
insertObservatie.pack(expand=TRUE, fill=BOTH)

descObs = Label(insertObservatie, text="Descriere")
descObs.grid(row=0, column=0)

descObs_entry = Entry(insertObservatie)
descObs_entry.grid(row=1, column=0)

b777771 = Button(tabAddObs, text="Adauga",
                 command=lambda: [
                     insertIntoObservatii(descObs_entry.get()),
                     tabsystem.hide(tabAddObs), showAllTabs(), tabsystem.select(tabObservatii),
                     descObs_entry.delete(0, END)])
b777771.place(x=430, y=58)

b777772 = Button(tabAddObs, text="Back",
                 command=lambda: [
                     tabsystem.hide(tabAddObs), showAllTabs(), tabsystem.select(tabObservatii),
                     descObs_entry.delete(0, END)])
b777772.place(x=435, y=100)

label99999 = Label(tabDelObs, text="Sterge Observatie")
label99999.grid(column=1,
                row=1,
                padx=40,
                pady=40)
deleteFromObs = Frame(label99999)
deleteFromObs.pack(expand=TRUE, fill=BOTH)

obsIdd = Label(deleteFromObs, text="Observatie (id)")
obsIdd.grid(row=0, column=0)

obsIdd_entry = Entry(deleteFromObs)
obsIdd_entry.grid(row=1, column=0)

b777771 = Button(tabDelObs, text="Sterge",
                 command=lambda: [
                     delFromObservatii(obsIdd_entry.get()),
                     tabsystem.hide(tabDelObs), showAllTabs(), tabsystem.select(tabObservatii),
                     obsIdd_entry.delete(0, END)])
b777771.place(x=430, y=58)

b777772 = Button(tabDelObs, text="Back",
                 command=lambda: [
                     tabsystem.hide(tabDelObs), showAllTabs(), tabsystem.select(tabObservatii),
                     obsIdd_entry.delete(0, END)])
b777772.place(x=435, y=100)

if __name__ == '__main__':
    window.mainloop()
    # close the connection
    conn.close()
    print('ok')
