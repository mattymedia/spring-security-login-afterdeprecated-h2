INSERT INTO clientes(full_name, email, create_at) VALUES('Maikol Garzon', 'maikol@email.com', '2022-08-19');
INSERT INTO clientes(full_name, email, create_at) VALUES('Luisa Jimenez', 'luisa@email.com', '2022-08-18');

INSERT INTO roles(role_name) VALUES('ROLE_USER');
INSERT INTO roles(role_name) VALUES('ROLE_ADMIN');

INSERT INTO usuarios(username, password) VALUES('admin', '$2a$12$NCQiTtw8tECbFPCJ0x21E.0iDeIapnK3wj24SJFDA3w.gHSNgzn4i');
INSERT INTO usuarios(username, password) VALUES('maikol', 'maikol123');

INSERT INTO usuario_roles(usuario_id, role_id) VALUES(1,1);
INSERT INTO usuario_roles(usuario_id, role_id) VALUES(1,2);
INSERT INTO usuario_roles(usuario_id, role_id) VALUES(2,2);