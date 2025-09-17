USE db_jpa;

INSERT INTO cursos (nomecurso, sigla) VALUES ('Ciência da Computação', 'CC');
INSERT INTO cursos (nomecurso, sigla) VALUES ('Engenharia Elétrica', 'EE');
INSERT INTO cursos (nomecurso, sigla) VALUES ('Letras', 'LET');
INSERT INTO cursos (nomecurso, sigla) VALUES ('Pedagogia', 'PED');
INSERT INTO cursos (nomecurso, sigla) VALUES ('Sistemas de Informação', 'SIN');

INSERT INTO semanas (idsemana, ativa, fim, inicio, local, nome, observacao) VALUES (1, 'S', '2024-11-01', '2024-11-01', 'Presidente Epitácio', 'Semana Epitaciana da Computação', 'Evento anual dedicado à tecnologia e inovação, a Semana Epitaciana da Computação promove palestras, workshops e networking para estudantes e profissionais de computação.');
INSERT INTO semanas (idsemana, ativa, fim, inicio, local, nome, observacao) VALUES (2, 'S', '2024-11-01', '2024-11-01', 'Presidente Epitácio', 'Semana da Tecnologia', 'Evento que reúne palestras, oficinas e debates sobre inovações e tendências tecnológicas para estudantes e profissionais da área.');
INSERT INTO semanas (idsemana, ativa, fim, inicio, local, nome, observacao) VALUES (3, 'S', '2024-11-01', '2024-11-01', 'Presidente Epitácio', 'Semana da Pegadogia', 'Evento com palestras, oficinas e debates voltados ao desenvolvimento educacional e às práticas pedagógicas para futuros e atuais educadores.');
INSERT INTO semanas (idsemana, ativa, fim, inicio, local, nome, observacao) VALUES (4, 'N', '2024-11-01', '2024-11-01', 'Presidente Epitácio', 'Semana da Diversidade', 'Evento com palestras, oficinas e diálogos para promover inclusão, respeito e valorização da diversidade em todas as suas formas.');
INSERT INTO semanas (idsemana, ativa, fim, inicio, local, nome, observacao) VALUES (5, 'N', '2024-11-01', '2024-11-01', 'Presidente Epitácio', 'Semana da Engenharia Elétrica', 'Evento com palestras, workshops e atividades práticas, explorando inovações e desafios na área de engenharia elétrica.');
INSERT INTO semanas (idsemana, ativa, fim, inicio, local, nome, observacao) VALUES (6, 'S', CURDATE() + INTERVAL 7 DAY, CURDATE(), 'Presidente Epitácio', 'Semana Da Inovação', 'Semana com eventos voltados à inovação, palestras e networking.');

INSERT INTO participantes (prontuario, cep, cidade, cpf, email, endereco, nome, senha, telefone, uf, usuario, idcursos) VALUES ('PE3013027', '12345-678', 'São Paulo', '12345678900', 'joao.silva@example.com', 'Rua das Flores, 100', 'João Silva', 'senha123', '(11) 91234-5678', 'SP', 'joaos', 1);
INSERT INTO participantes (prontuario, cep, cidade, cpf, email, endereco, nome, senha, telefone, uf, usuario, idcursos) VALUES ('PE4037819', '23456-789', 'Rio de Janeiro', '23456789001', 'maria.oliveira@example.com', 'Avenida Central, 200', 'Maria Oliveira', 'senha456', '(21) 99876-5432', 'RJ', 'mariao', 2);
INSERT INTO participantes (prontuario, cep, cidade, cpf, email, endereco, nome, senha, telefone, uf, usuario, idcursos) VALUES ('PE9284911', '34567-890', 'Belo Horizonte', '34567890123', 'carlos.santos@example.com', 'Praça das Américas, 300', 'Carlos Santos', 'senha789', '(31) 98765-4321', 'MG', 'carloss', 3);
INSERT INTO participantes (prontuario, cep, cidade, cpf, email, endereco, nome, senha, telefone, uf, usuario, idcursos) VALUES ('PE3028171', '45678-901', 'Curitiba', '45678901234', 'ana.souza@example.com', 'Rua Verde, 400', 'Ana Souza', 'senha321', '(41) 97654-3210', 'PR', 'anas', 4);
INSERT INTO participantes (prontuario, cep, cidade, cpf, email, endereco, nome, senha, telefone, uf, usuario, idcursos) VALUES ('PE3018291', '56789-012', 'Fortaleza', '56789012345', 'lucas.pereira@example.com', 'Avenida Mar, 500', 'Lucas Pereira', 'senha654', '(85) 96543-2109', 'CE', 'lucasp', 5);
INSERT INTO participantes (prontuario, cep, cidade, cpf, email, endereco, nome, senha, telefone, uf, usuario, idcursos) VALUES ('PE3015017', '12345-678', 'São Paulo', '12345678901', 'guilherme.tanaka@example.com', 'Av. Paulista, 1000', 'Guilherme Tanaka', '123', '(11) 98765-4321', 'SP', 'teste', 2);


INSERT INTO eventos (ideventos, cargahoraria, dataevento, numerovagas, qtdeinscrito, titulo, idsemana) VALUES (1, 8, '2024-11-01', 50, 0, 'Palestra sobre Inovação', 1);
INSERT INTO eventos (ideventos, cargahoraria, dataevento, numerovagas, qtdeinscrito, titulo, idsemana) VALUES (2, 4, '2024-11-02', 30, 0, 'Workshop de Programação', 1);
INSERT INTO eventos (ideventos, cargahoraria, dataevento, numerovagas, qtdeinscrito, titulo, idsemana) VALUES (3, 6, '2024-11-03', 40, 0, 'Mesa Redonda de Tecnologia', 1);
INSERT INTO eventos (ideventos, cargahoraria, dataevento, numerovagas, qtdeinscrito, titulo, idsemana) VALUES (4, 3, '2024-11-04', 25, 0, 'Café com Empreendedores', 1);
INSERT INTO eventos (ideventos, cargahoraria, dataevento, numerovagas, qtdeinscrito, titulo, idsemana) VALUES (5, 5, '2024-11-05', 20, 0, 'Seminário de Carreiras', 1);
INSERT INTO eventos (ideventos, cargahoraria, dataevento, numerovagas, qtdeinscrito, titulo, idsemana) VALUES (6, 8, '2024-11-06', 50, 0, 'Palestra sobre Sustentabilidade', 2);
INSERT INTO eventos (ideventos, cargahoraria, dataevento, numerovagas, qtdeinscrito, titulo, idsemana) VALUES (7, 4, '2024-11-07', 30, 0, 'Workshop de Design', 2);
INSERT INTO eventos (ideventos, cargahoraria, dataevento, numerovagas, qtdeinscrito, titulo, idsemana) VALUES (8, 6, '2024-11-08', 40, 0, 'Mesa Redonda sobre Educação', 2);
INSERT INTO eventos (ideventos, cargahoraria, dataevento, numerovagas, qtdeinscrito, titulo, idsemana) VALUES (9, 3, '2024-11-09', 25, 0, 'Café com Especialistas', 2);
INSERT INTO eventos (ideventos, cargahoraria, dataevento, numerovagas, qtdeinscrito, titulo, idsemana) VALUES (10, 5, '2024-11-10', 20, 0, 'Seminário de Inovação Social', 2);
INSERT INTO eventos (ideventos, cargahoraria, dataevento, numerovagas, qtdeinscrito, titulo, idsemana) VALUES (11, 8, '2024-11-11', 50, 0, 'Palestra sobre Inteligência Artificial', 3);
INSERT INTO eventos (ideventos, cargahoraria, dataevento, numerovagas, qtdeinscrito, titulo, idsemana) VALUES (12, 4, '2024-11-12', 30, 0, 'Workshop de Robótica', 3);
INSERT INTO eventos (ideventos, cargahoraria, dataevento, numerovagas, qtdeinscrito, titulo, idsemana) VALUES (13, 6, '2024-11-13', 40, 0, 'Mesa Redonda de Saúde Digital', 3);
INSERT INTO eventos (ideventos, cargahoraria, dataevento, numerovagas, qtdeinscrito, titulo, idsemana) VALUES (14, 3, '2024-11-14', 25, 0, 'Café com Cientistas', 3);
INSERT INTO eventos (ideventos, cargahoraria, dataevento, numerovagas, qtdeinscrito, titulo, idsemana) VALUES (15, 5, '2024-11-15', 20, 0, 'Seminário de Tecnologias Emergentes', 3);
INSERT INTO eventos (ideventos, cargahoraria, dataevento, numerovagas, qtdeinscrito, titulo, idsemana) VALUES (16, 8, '2024-11-16', 50, 0, 'Palestra sobre Cultura Digital', 4);
INSERT INTO eventos (ideventos, cargahoraria, dataevento, numerovagas, qtdeinscrito, titulo, idsemana) VALUES (17, 4, '2024-11-17', 30, 0, 'Workshop de Marketing Digital', 4);
INSERT INTO eventos (ideventos, cargahoraria, dataevento, numerovagas, qtdeinscrito, titulo, idsemana) VALUES (18, 6, '2024-11-18', 40, 0, 'Mesa Redonda sobre Inclusão', 4);
INSERT INTO eventos (ideventos, cargahoraria, dataevento, numerovagas, qtdeinscrito, titulo, idsemana) VALUES (19, 3, '2024-11-19', 25, 0, 'Café com Artistas', 4);
INSERT INTO eventos (ideventos, cargahoraria, dataevento, numerovagas, qtdeinscrito, titulo, idsemana) VALUES (20, 5, '2024-11-20', 20, 0, 'Seminário de Transformação Digital', 4);
INSERT INTO eventos (ideventos, cargahoraria, dataevento, numerovagas, qtdeinscrito, titulo, idsemana) VALUES (21, 8, '2024-11-21', 50, 0, 'Palestra sobre Desenvolvimento Sustentável', 5);
INSERT INTO eventos (ideventos, cargahoraria, dataevento, numerovagas, qtdeinscrito, titulo, idsemana) VALUES (22, 4, '2024-11-22', 30, 0, 'Workshop de Empreendedorismo', 5);
INSERT INTO eventos (ideventos, cargahoraria, dataevento, numerovagas, qtdeinscrito, titulo, idsemana) VALUES (23, 6, '2024-11-23', 40, 0, 'Mesa Redonda sobre Ética', 5);
INSERT INTO eventos (ideventos, cargahoraria, dataevento, numerovagas, qtdeinscrito, titulo, idsemana) VALUES (24, 3, '2024-11-24', 25, 0, 'Café com Filósofos', 5);
INSERT INTO eventos (ideventos, cargahoraria, dataevento, numerovagas, qtdeinscrito, titulo, idsemana) VALUES (25, 5, '2024-11-25', 20, 0, 'Seminário de Futuro do Trabalho', 5);

INSERT INTO admins (login, senha) VALUES ('admin', '123');

INSERT INTO organizadores (idorganizador, nome, idSemana, idCurso) VALUES (1, 'Ana Souza', 1, 1);
INSERT INTO organizadores (idorganizador, nome, idSemana, idCurso) VALUES (2, 'Lucas Pereira', 1, 1);
INSERT INTO organizadores (idorganizador, nome, idSemana, idCurso) VALUES (3, 'Maria Oliveira', 2, 2);
INSERT INTO organizadores (idorganizador, nome, idSemana, idCurso) VALUES (4, 'Carlos Santos', 2, 2);
INSERT INTO organizadores (idorganizador, nome, idSemana, idCurso) VALUES (5, 'João Silva', 3, 3);
INSERT INTO organizadores (idorganizador, nome, idSemana, idCurso) VALUES (6, 'Lucas Pereira', 3, 3);
INSERT INTO organizadores (idorganizador, nome, idSemana, idCurso) VALUES (7, 'Maria Oliveira', 4, 4);
INSERT INTO organizadores (idorganizador, nome, idSemana, idCurso) VALUES (8, 'João Silva', 4, 4);
INSERT INTO organizadores (idorganizador, nome, idSemana, idCurso) VALUES (9, 'Ana Souza', 5, 5);
INSERT INTO organizadores (idorganizador, nome, idSemana, idCurso) VALUES (10, 'Carlos Santos', 5, 5);


INSERT INTO responsaveis (idresponsaveis, ideventos, prontuario) VALUES (1, 1, 'PE3015017');
INSERT INTO responsaveis (idresponsaveis, ideventos, prontuario) VALUES (2, 1, 'PE4037819');
INSERT INTO responsaveis (idresponsaveis, ideventos, prontuario) VALUES (3, 2, 'PE9284911');
INSERT INTO responsaveis (idresponsaveis, ideventos, prontuario) VALUES (4, 6, 'PE3018291');
INSERT INTO responsaveis (idresponsaveis, ideventos, prontuario) VALUES (5, 3, 'PE3013027');
INSERT INTO responsaveis (idresponsaveis, ideventos, prontuario) VALUES (1, 3, 'PE3018291');
INSERT INTO responsaveis (idresponsaveis, ideventos, prontuario) VALUES (2, 4, 'PE4037819');
INSERT INTO responsaveis (idresponsaveis, ideventos, prontuario) VALUES (3, 4, 'PE9284911');
INSERT INTO responsaveis (idresponsaveis, ideventos, prontuario) VALUES (4, 5, 'PE3028171');
INSERT INTO responsaveis (idresponsaveis, ideventos, prontuario) VALUES (5, 5, 'PE3015017');

INSERT INTO matriculas (idmatriculas, data, status, ideventos, prontuario) VALUES (1, '2024-11-27', 'P', 1, 'PE3013027')
INSERT INTO matriculas (idmatriculas, data, status, ideventos, prontuario) VALUES (2, '2024-11-27', 'P', 2, 'PE3013027')
INSERT INTO matriculas (idmatriculas, data, status, ideventos, prontuario) VALUES (3, '2024-11-27', 'F', 3, 'PE3013027')
INSERT INTO matriculas (idmatriculas, data, status, ideventos, prontuario) VALUES (4, '2024-11-27', 'F', 4, 'PE3013027')
INSERT INTO matriculas (idmatriculas, data, status, ideventos, prontuario) VALUES (5, '2024-11-27', 'A', 5, 'PE3013027')
INSERT INTO matriculas (idmatriculas, data, status, ideventos, prontuario) VALUES (6, '2024-11-27', 'A', 6, 'PE3013027')

INSERT INTO presencas (idpresenca, data, qtdehoras, idmatriculas) VALUES (1, '2024-11-27', 2, 1)
INSERT INTO presencas (idpresenca, data, qtdehoras, idmatriculas) VALUES (2, '2024-11-27', 2, 1)
INSERT INTO presencas (idpresenca, data, qtdehoras, idmatriculas) VALUES (3, '2024-11-27', 2, 1)
INSERT INTO presencas (idpresenca, data, qtdehoras, idmatriculas) VALUES (4, '2024-11-27', 2, 1)
INSERT INTO presencas (idpresenca, data, qtdehoras, idmatriculas) VALUES (1, '2024-11-27', 2, 2)
INSERT INTO presencas (idpresenca, data, qtdehoras, idmatriculas) VALUES (2, '2024-11-27', 2, 2)


