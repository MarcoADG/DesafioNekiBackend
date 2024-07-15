insert into table usuario(login, senha) 
VALUES
('admin', '$2a$12$j0FRQeLVH66ehEYmYevETO8bNF.qffBm6ufk9z77NATTvFHDpEID6'),
('user2', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W');
	   
INSERT INTO skills (nome, descricao, imagem) VALUES
('Java', 'Linguagem de programação Java', 'java.png'),
('Spring Boot', 'Framework para construção de aplicações em Spring Boot', 'spring.png'),
('JavaScript', 'Linguagem de script para páginas web', 'javascript.png'),
('React', 'Biblioteca JavaScript para construção de interfaces de usuário', 'react.png'),
('Python', 'Linguagem de programação Python', 'python.png'),
('Bootstrap', 'descrição de Bootstrap', 'Bootstrap.png'),
('Ingles', 'descrição de Ingles', 'Ingles.png'),
('Zod', 'descrição de Zod', 'Zod.png'),
('AWS', 'descrição de AWS', 'AWS.png'),
('Azure', 'descrição de Azure', 'Azure.png'),
('Ubunto', 'descrição de Ubunto', 'Ubunto.png'),
('Springtools', 'descrição de Springtools', 'Springtools.png'),
('PostgreSQL', 'descrição de PostgreSQL', 'PostgreSQL.png'),
('MySQL', 'descrição de MySQL', 'MySQL.png'),
;

-- Insert test data for AssociacaoSkill table
INSERT INTO associacao_skills (level_skill, usuario_id, skills_id) VALUES
(3, 1, 1), -- user1 tem habilidade Java com nível 3
(4, 1, 2), -- user1 tem habilidade Spring Boot com nível 4
(2, 1, 3), -- user1 tem habilidade JavaScript com nível 2
(5, 2, 2), -- user2 tem habilidade Spring Boot com nível 5
(3, 2, 4), -- user2 tem habilidade React com nível 3
(4, 2, 5), -- user2 tem habilidade Python com nível 4
(50, 1, 6),
(100,1,7),
(20,1,8),
(35,1,9),
(40,1,10),
(27,1,11),
(71,1,12),
(99,1,13),
(1,1,14);