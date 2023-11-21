INSERT INTO cuenta VALUES(1, 'pepito@email.com', '$2a$10$ZDgVbOn7oVm2f69v9zDsEuLJBEJrfGdTdkTaQOe3NgvtLnhCRO25G');
INSERT INTO cuenta VALUES(2, 'juanita@email.com', '$2a$10$ZDgVbOn7oVm2f69v9zDsEuLJBEJrfGdTdkTaQOe3NgvtLnhCRO25G');
INSERT INTO cuenta VALUES(3, 'luis@email.com', '$2a$10$ZDgVbOn7oVm2f69v9zDsEuLJBEJrfGdTdkTaQOe3NgvtLnhCRO25G');
INSERT INTO cuenta VALUES(4, 'david@email.com', '$2a$10$ZDgVbOn7oVm2f69v9zDsEuLJBEJrfGdTdkTaQOe3NgvtLnhCRO25G');
INSERT INTO cuenta VALUES(5, 'oscar@email.com', '$2a$10$ZDgVbOn7oVm2f69v9zDsEuLJBEJrfGdTdkTaQOe3NgvtLnhCRO25G');
INSERT INTO cuenta VALUES(6, 'pepito1@email.com', '$2a$10$v9bRP81voDgpQC9DgGfuPubWzZjkedAMsrXt3ZYd2Ixw.s5UY/7K2');
INSERT INTO cuenta VALUES(7, 'juanita1@email.com', '$2a$10$v9bRP81voDgpQC9DgGfuPubWzZjkedAMsrXt3ZYd2Ixw.s5UY/7K2');
INSERT INTO cuenta VALUES(8, 'lui1s@email.com', '$2a$10$v9bRP81voDgpQC9DgGfuPubWzZjkedAMsrXt3ZYd2Ixw.s5UY/7K2');
INSERT INTO cuenta VALUES(9, 'david1@email.com', '$2a$10$v9bRP81voDgpQC9DgGfuPubWzZjkedAMsrXt3ZYd2Ixw.s5UY/7K2');
INSERT INTO cuenta VALUES(10, 'oscar1@email.com', '$2a$10$v9bRP81voDgpQC9DgGfuPubWzZjkedAMsrXt3ZYd2Ixw.s5UY/7K2');


INSERT INTO paciente VALUES('24567234', 1, 1, 'Pepito Perez', '5454545', 'url_foto', 'Sinalergias', 1, 1, '1990-01-01', 1);
INSERT INTO paciente VALUES('18635123', 1, 1, 'Juanita Lopez', '4564545', 'url_foto', 'Sinalergias', 1, 1, '1990-01-01', 2);
INSERT INTO paciente VALUES('18635124', 1, 1, 'Pedro Martinez', '7896789', 'url_foto', 'Sin alergias', 1, 1, '1995-03-15', 3);
INSERT INTO paciente VALUES('18635125', 1, 1, 'Maria Rodriguez', '9876543', 'url_foto', 'Sin alergias', 1, 1, '1988-07-22', 4);
INSERT INTO paciente VALUES('18635126', 1, 1, 'Luis Gonzalez', '5432109', 'url_foto', 'Sin alergias', 1, 1, '2000-11-10', 5);
INSERT INTO paciente VALUES('18635127', 1, 1, 'Laura Gonzalez', '5432108', 'url_foto', 'Sin alergias', 1, 1, '2000-11-10', 11);

INSERT INTO Medico VALUES('254875324', 1, 1, 'Juan', '3257486512', 'url_foto', 1, 6);
INSERT INTO Medico VALUES('257845695', 1, 1, 'Andres Camilo', '3215784695', 'url_foto', 1, 7);
INSERT INTO Medico VALUES('547852169', 1, 1, 'Diego Alexander', '5478453321', 'url_foto', 1, 8);
INSERT INTO Medico VALUES('754862315', 1, 1, 'Andres Esteban', '3215744862', 'url_foto', 1, 9);
INSERT INTO Medico VALUES('458756321', 1, 1, 'Andres Felipe', '3102574865', 'url_foto', 1, 10);


INSERT INTO Cita VALUES (1, 3, '2023-10-25 10:20:00', '2023-10-28 11:20:00', 'Baricela', 6, 1);
INSERT INTO Cita VALUES (2, 3, '2023-10-25 10:35:00', '2023-10-29 15:40:00', 'Caries', 7, 2);
INSERT INTO Cita VALUES (3, 3, '2023-10-26 14:15:00', '2023-10-30 09:30:00', 'Limpieza dental', 8, 3);
INSERT INTO Cita VALUES (4, 0, '2023-10-27 11:30:00', '2023-10-31 10:25:00', 'Extracción de muelas', 9, 4);
INSERT INTO Cita VALUES (5, 0, '2023-10-28 16:45:00', '2023-11-01 14:15:00', 'Revision general', 10, 5);
INSERT INTO Cita VALUES (6, 0, '2023-12-28 16:45:00', '2023-11-01 14:15:00', 'Revision general', 10, 5);
INSERT INTO Cita VALUES (7, 0, '2023-12-29 16:45:00', '2023-11-01 14:15:00', 'Revision general', 8, 3);
INSERT INTO Cita VALUES (8, 0, '2023-12-30 16:45:00', '2023-11-01 14:15:00', 'Revision general', 8, 4);


INSERT INTO dia_libre VALUES (1, '2023-11-01', 6);
INSERT INTO dia_libre VALUES (2, '2023-11-01', 7);
INSERT INTO dia_libre VALUES (3, '2023-11-01', 8);
INSERT INTO dia_libre VALUES (4, '2023-11-01', 9);
INSERT INTO dia_libre VALUES (5, '2023-11-01', 10);

INSERT INTO atencion_cita VALUES (1,'hola','quemas','chao',1);

INSERT INTO PQRS (codigo, codigo_estado, estado, fecha_creacion, motivo, tipo, cita_codigo)
VALUES (1, 1, 1, '2023-11-08 15:30:00', 'Consulta general', 'Queja', 1);
