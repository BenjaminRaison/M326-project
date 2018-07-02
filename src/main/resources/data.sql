insert into Item (description, name, price, id) values
  ('Test 1 description', 'Test 1', 25.25, 1),
  ('Test 2 description', 'Test 2', 124.85, 2),
  ('Test 3 description', 'Test 3', 2457.85, 3),
  ('Test 4 description', 'Test 4', 14.00, 4),
  ('Test 5 description', 'Test 5', 5.05, 5),
  ('Test 6 description', 'Test 6', 82.95, 6);

insert into Item (description, name, price, id) values
  ('Banana', 'Test 123', 123.25, 7),
  ('Test 232 description', 'Test 2', 1424.85, 8),
  ('Test 344', 'Test 344', 244457.85, 9),
  (' 4 description', ' 4', 100, 10),
  ('Test  description', 'adsfdasf 5', 5000.05, 11),
  ('Test 60 description', 'Test 60', 82.95, 12);

insert into Client (address, email, firstName, password, postcode, surname, town, id) values
  ('Address', 'benji@localhost', 'Benji', 'pass', '1234', 'Raison', 'New York', 1),
  ('Address', 'jonas@localhost', 'Jonas', 'pass', '1234', 'Gredig', 'Bündnerland', 2),
  ('Address', 'trump@localhost', 'Donald', 'pass', '1234', 'Trump', 'Washington, DC', 3),
  ('banana', '123@localhost', 'Gerry', 'pass', '55555', 'Loal', 'NYC', 4);

insert into Employee (firstName, surname, type, id) values
  ('Jonas', 'Gredig', 0, 1),
  ('Tobias', 'Fatzer', 1, 2),
  ('Andrin', 'Kappeler  ', 2, 3),
  ('Peter', 'Gürteltier', 0, 4),
  ('Hans', 'Gut', 1, 5),
  ('Sara', 'Rieder  ', 2, 6);


insert into CSOrder (client_id, deliveryExpected, lastEdited, lastEditor_id, orderNumber, status, id) values
  (1, '2018-06-24', '2018-06-22 11:30:25', 1, '123A', 0, 1),
  (1, '2018-06-24', '2018-06-22 11:30:25', 1, '123B', 0, 2),
  (2, '2018-06-24', '2018-06-22 11:30:25', 1, '124', 0, 3),
  (3, '2018-06-24', '2018-06-22 11:30:25', 2, '125', 1, 4);

insert into CSOrder_Item (CSOrder_id, items_id) values
  (1,1),
  (1,2),
  (2,3),
  (2,4),
  (3,1),
  (4,1),
  (4,2),
  (4,4),
  (3,1),
  (44,33),
  (3,2);
