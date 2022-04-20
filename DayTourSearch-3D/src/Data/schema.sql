CREATE TABLE DAYTRIPS (
    dayTripId INT NOT NULL,
    title VARCHAR(32) NOT NULL,
    price INT NOT NULL,
    duration INT NOT NULL,
    dateStart VARCHAR(10) NOT NULL,
    startTime VARCHAR(5) NOT NULL,
    availableSeats INT NOT NULL,
    languageSpoken VARCHAR(32),
    location VARCHAR(32) NOT NULL,
    activity VARCHAR(100),
    description VARCHAR(200),
    dateAdded VARCHAR(10) NOT NULL,
    PRIMARY KEY(dayTripId)
    );

CREATE TABLE CUSTOMERS (
    customerId INT NOT NULL,
    password VARCHAR(32) NOT NULL,
    username VARCHAR(32) NOT NULL,
    PRIMARY KEY(customerId)
);

CREATE TABLE BOOKINGS (
    customerId INT NOT NULL,
    dayTripId INT NOT NULL,
    numberOfGuests INT NOT NULL,
    PRIMARY KEY(customerId, dayTripId),
    FOREIGN KEY(customerId) REFERENCES CUSTOMERS(customerId),
    FOREIGN KEY(dayTripId) REFERENCES DAYTRIPS(dayTripId)
);

CREATE TABLE REVIEWS (
    customerId INT NOT NULL,
    dayTripId INT NOT NULL,
    rating INT,
    review VARCHAR(200),
    PRIMARY KEY(customerId,dayTripId),
    FOREIGN KEY(customerId) REFERENCES CUSTOMERS(customerId),
    FOREIGN KEY(dayTripId) REFERENCES DAYTRIPS(dayTripId)
);

INSERT INTO CUSTOMERS VALUES(1,'123','Sverrir');
INSERT INTO CUSTOMERS VALUES(2,'456','Ragnheiður');
INSERT INTO CUSTOMERS VALUES(3,'789','Valgeir');

INSERT INTO DAYTRIPS VALUES(1,'Eyjafjallajökull',10000,7,'2021-06-22','08:00',10,'íslenska','S','Fjallganga',
    'Spennandi fjallganga á Eyjafjallajökull.','2022-03-21');
INSERT INTO DAYTRIPS VALUES(2,'Kaldbakur',7500,5,'2022-04-15','09:00',20,'íslenska','N','Fjallganga',
    'Fjallganga á Kaldbak í Eyjafirði.','2022-03-21');
INSERT INTO DAYTRIPS VALUES(3,'Kárahnjúkavirkun',5000,5,'2022-07-10','10:00',20,'enska','A','Fjallganga',
    'Gengið upp að hinni víðsfrægu Kárahnjúkavirkjun.','2022-03-21');
INSERT INTO DAYTRIPS VALUES(4,'Látrabjarg',2500,3,'2022-08-02','12:00',25,'enska','V','Fjallganga',
    'Gengið á vestasta odda íslands.','2022-03-21');
INSERT INTO DAYTRIPS VALUES(5,'Grímsey',5000,10,'2022-07-10','08:30',20,'íslenska','N','Sigling',
    'Siglt í Grímsey þar sem fólk hefur 3 tíma til að skoða sig um.','2022-03-21');
INSERT INTO DAYTRIPS VALUES(6, 'Silfra', 12000, 2, '2022-05-16','10:30', 20, 'enska', 'S', 'Köfun',
    'Stórskemmtileg köfunarferð í Silfru', '2022-03-31');
INSERT INTO DAYTRIPS VALUES(7, 'Vestmannaeyjar', 8000, 12, '2022-06-23','12:00', 5, 'íslenska', 'S', 'Sigling',
    'Útivistarferð til Vestmannaeyja','2022-03-31');
INSERT INTO DAYTRIPS VALUES(8, 'Hvalaskoðun', 9000, 4, '2022-04-22','15:00', 10, 'enska', 'N', 'Sigling',
    'Alvöru hvalaskoðun frá Húsavík', '2022-03-31');
INSERT INTO DAYTRIPS VALUES(9, 'Skíðastuð í Oddsskarði', 5000, 8, '2022-04-02','12:30', 15, 'íslenska', 'A', 'Skíði',
    'Spennandi Skíðaferð í Oddsskarði', '2022-03-31');
INSERT INTO DAYTRIPS VALUES(10, 'Látrabjarg', 3000, 2, '2022-07-15','13:00', 20, 'enska','V', 'Fjallganga',
    'Almennileg fjallganga á Látrabjargi', '2022-03-31');
INSERT INTO DAYTRIPS VALUES(11, 'Flatey', 7500, 10, '2022-06-16','11:00', 4, 'íslenska', 'V', 'Sigling',
    'Sigling út í Flatey þar sem þú getur eytt deginum', '2022-03-31');

INSERT INTO BOOKINGS VALUES(1,1,2);
INSERT INTO BOOKINGS VALUES(1,2,4);
INSERT INTO BOOKINGS VALUES(1,5,6);
INSERT INTO BOOKINGS VALUES(2,3,2);
INSERT INTO BOOKINGS VALUES(2,5,2);
INSERT INTO BOOKINGS VALUES(2,6,1);
INSERT INTO BOOKINGS VALUES(3,8,2);
INSERT INTO BOOKINGS VALUES(3,2,3);
INSERT INTO BOOKINGS VALUES(3,4,2);

INSERT INTO REVIEWS VALUES(1,1,2,'Sá ekki eldgos eins og mér var lofað!!');
INSERT INTO REVIEWS VALUES(2,5,5,'Grímsey er náttúruperla, mæli eindregið með þessari ferð.');
INSERT INTO REVIEWS VALUES(3,8,4,'Góð ferð en átti í smá vandræðum með ölduganginn.');






