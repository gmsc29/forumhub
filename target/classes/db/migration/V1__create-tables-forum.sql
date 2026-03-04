CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE cursos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL
);

CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL UNIQUE,
    mensagem TEXT NOT NULL,
    data_criacao DATETIME NOT NULL,
    status VARCHAR(50) NOT NULL,
    autor_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,
    CONSTRAINT fk_topicos_autor_id FOREIGN KEY (autor_id) REFERENCES usuarios(id),
    CONSTRAINT fk_topicos_curso_id FOREIGN KEY (curso_id) REFERENCES cursos(id)
);

CREATE TABLE respostas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensagem TEXT NOT NULL,
    topico_id BIGINT NOT NULL,
    data_criacao DATETIME NOT NULL,
    autor_id BIGINT NOT NULL,
    solucao TINYINT(1) NOT NULL DEFAULT 0,
    CONSTRAINT fk_respostas_topico_id FOREIGN KEY (topico_id) REFERENCES topicos(id),
    CONSTRAINT fk_respostas_autor_id FOREIGN KEY (autor_id) REFERENCES usuarios(id)
);