-- Disable foreign key checks to delete data
SET FOREIGN_KEY_CHECKS = 0;

-- Clear tables
TRUNCATE TABLE usuario;
TRUNCATE TABLE skills;
TRUNCATE TABLE associacao_skills;

-- Reset auto-increment counters (if needed)
ALTER TABLE usuario AUTO_INCREMENT = 1;
ALTER TABLE skills AUTO_INCREMENT = 1;
ALTER TABLE associacao_skills AUTO_INCREMENT = 1;

-- Insert test data for Usuario table
INSERT INTO usuario (login, senha) VALUES
('admin', '$2a$12$j0FRQeLVH66ehEYmYevETO8bNF.qffBm6ufk9z77NATTvFHDpEID6'), -- senha: 123
('user2', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W'); -- senha: senha2

-- Insert test data for Skills table
INSERT INTO skills (nome, descricao, imagem) VALUES
('Java', 'Linguagem de programação Java', 'java.png'),
('Spring Boot', 'Framework para construção de aplicações em Spring Boot', 'spring.png'),
('JavaScript', 'Linguagem de script para páginas web', 'javascript.png'),
('React', 'Biblioteca JavaScript para construção de interfaces de usuário', 'react.png'),
('Python', 'Linguagem de programação Python', 'python.png');

-- Insert test data for AssociacaoSkill table
INSERT INTO associacao_skills (level_skill, usuario_id, skills_id) VALUES
(3, 1, 1), -- user1 tem habilidade Java com nível 3
(4, 1, 2), -- user1 tem habilidade Spring Boot com nível 4
(2, 1, 3), -- user1 tem habilidade JavaScript com nível 2
(5, 2, 2), -- user2 tem habilidade Spring Boot com nível 5
(3, 2, 4), -- user2 tem habilidade React com nível 3
(4, 2, 5); -- user2 tem habilidade Python com nível 4

-- Enable foreign key checks
SET FOREIGN_KEY_CHECKS = 1;
