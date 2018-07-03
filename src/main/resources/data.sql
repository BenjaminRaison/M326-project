insert into Item (description, name, price, id) values ('Test 1 description', 'Test 1', 25.25, 1), ('Test 2 description', 'Test 2', 124.85, 2), ('Test 3 description', 'Test 3', 2457.85, 3), ('Test 4 description', 'Test 4', 14.00, 4), ('Test 5 description', 'Test 5', 5.05, 5), ('Test 6 description', 'Test 6', 82.95, 6);

insert into Item (description, name, price, id) values ('Banana', 'Test 123', 123.25, 7), ('Test 232 description', 'Test 2', 1424.85, 8), ('Test 344', 'Test 344', 244457.85, 9), (' 4 description', ' 4', 100, 10), ('Test  description', 'adsfdasf 5', 5000.05, 11), ('Test 60 description', 'Test 60', 82.95, 12);

insert into Client (address, email, firstName, password, postcode, surname, town, id) values ('Address', 'benji@localhost', 'Benji', 'pass', '1234', 'Raison', 'New York', 1), ('Address', 'jonas@localhost', 'Jonas', 'pass', '1234', 'Gredig', 'Bündnerland', 2), ('Address', 'trump@localhost', 'Donald', 'pass', '1234', 'Trump', 'Washington, DC', 3), ('banana', '123@localhost', 'Gerry', 'pass', '55555', 'Loal', 'NYC', 4);

insert into Employee (firstName, surname, type, id) values ('Jonas', 'Gredig', 0, 1), ('Tobias', 'Fatzer', 1, 2), ('Andrin', 'Kappeler  ', 2, 3), ('Peter', 'Gürteltier', 0, 4), ('Hans', 'Gut', 1, 5), ('Sara', 'Rieder  ', 2, 6);

insert into CSOrder (client_id, deliveryExpected, lastEdited, lastEditor_id, orderNumber, archived, status, id) values (1, '2018-06-24', '2018-06-22 11:30:25', 1, '123A', false, 2, 1), (1, '2018-06-24', '2018-06-22 11:30:25', 1, '123B', false,  1, 2), (2, '2018-06-24', '2018-06-22 11:30:25', 1, '124', false, 3, 3), (3, '2018-06-24', null, null, '125', false, 0, 4), (1, '2014-06-24', null, null, '999', false, 0, 5), (3, '2017-06-24', '2017-06-22 11:30:25', 2, '333', false, 2, 6), (2, '2017-03-14', '2017-04-22 11:30:25', 2, '111', false, 3, 7);

insert into CSOrder_Item (CSOrder_id, items_id) values (1,1), (1,2), (2,3), (2,4), (3,1), (4,1), (4,2), (4,4), (3,1), (3,2), (5,2), (5,3), (6,1), (6,2), (7,2), (7,3), (7,4);
