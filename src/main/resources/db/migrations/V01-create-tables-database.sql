-- Criar tabela usuário
CREATE TABLE usuario (
    id SERIAL PRIMARY key,
    login VARCHAR(50) NOT null,
    senha VARCHAR(255) NOT null
);

-- Criar tabela skills
CREATE TABLE skills (
    id SERIAL PRIMARY key,
    imagem VARCHAR(2000) not null,
    nome VARCHAR(100) NOT null,
    descricao varchar(100) not null
);

-- criar tabela associação skills
create table associacao_skills(
	id SERIAL primary key,
	level_skill INT NOT null,
	usuario_id INT REFERENCES usuario(id),
	skills_id INT REFERENCES skills(id)
);

insert into table usuario(login, senha) 
VALUES ('admin', '$2a$12$j0FRQeLVH66ehEYmYevETO8bNF.qffBm6ufk9z77NATTvFHDpEID6');