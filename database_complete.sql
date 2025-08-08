USE bd_gamelegends;

-- Tabela Cadastro (Usuários)
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Cadastro' AND xtype='U')
CREATE TABLE Cadastro (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    nome NVARCHAR(255) NOT NULL,
    sobrenome NVARCHAR(255) NOT NULL,
    cpf NVARCHAR(14) NOT NULL,
    datanascimento DATE NOT NULL,
    email NVARCHAR(255) NOT NULL UNIQUE,
    telefone NVARCHAR(15) NOT NULL,
    senha NVARCHAR(255) NOT NULL,
    usuario NVARCHAR(50) NOT NULL DEFAULT 'Cliente' -- ADM ou Cliente
);

-- Tabela Avaliacao (Comentários/Reviews)
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Avaliacao' AND xtype='U')
CREATE TABLE Avaliacao (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    comentario NVARCHAR(MAX) NOT NULL,
    estrelas INT NOT NULL CHECK (estrelas >= 1 AND estrelas <= 5),
    nomeJogo NVARCHAR(255) NOT NULL,
    nomeUsuario NVARCHAR(255) NOT NULL,
    dataAvaliacao NVARCHAR(10) NOT NULL
);

-- Tabela CadCartao (Cartões de Crédito)
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='CadCartao' AND xtype='U')
CREATE TABLE CadCartao (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    nomeT NVARCHAR(255) NOT NULL,
    numC NVARCHAR(19) NOT NULL,
    validade NVARCHAR(7) NOT NULL,
    CVV NVARCHAR(4) NOT NULL,
    bandeira NVARCHAR(50) NOT NULL,
    clienteId BIGINT NOT NULL,
    FOREIGN KEY (clienteId) REFERENCES Cadastro(id)
);

-- Tabela Doacao
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Doacao' AND xtype='U')
CREATE TABLE Doacao (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    valor INT NOT NULL,
    fk_Cliente_ID BIGINT NOT NULL,
    cartaoId NVARCHAR(255) NOT NULL,
    FOREIGN KEY (fk_Cliente_ID) REFERENCES Cadastro(id)
);

-- Tabela Projetos
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='projetos' AND xtype='U')
CREATE TABLE projetos (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    nomeProjeto NVARCHAR(255) NOT NULL UNIQUE,
    descricao NVARCHAR(500) NOT NULL,
    dataInicio NVARCHAR(255) NOT NULL,
    tecnologias NVARCHAR(255) NOT NULL,
    genero NVARCHAR(255) NOT NULL,
    statusProjeto NVARCHAR(255),
    foto VARBINARY(MAX)
);

-- Tabela Fatura
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Fatura' AND xtype='U')
CREATE TABLE Fatura (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    valor DECIMAL(10,2) NOT NULL,
    dataVencimento DATE NOT NULL,
    status NVARCHAR(50) NOT NULL DEFAULT 'Pendente',
    clienteId BIGINT NOT NULL,
    FOREIGN KEY (clienteId) REFERENCES Cadastro(id)
);

-- Inserir usuário administrador padrão
IF NOT EXISTS (SELECT * FROM Cadastro WHERE email = 'admin@gamelegends.com')
INSERT INTO Cadastro (nome, sobrenome, cpf, datanascimento, email, telefone, senha, usuario) 
VALUES ('Admin', 'Sistema', '000.000.000-00', '1990-01-01', 'admin@gamelegends.com', '(11) 99999-9999', 'admin123', 'ADM');

-- Inserir usuário cliente padrão
IF NOT EXISTS (SELECT * FROM Cadastro WHERE email = 'cliente@teste.com')
INSERT INTO Cadastro (nome, sobrenome, cpf, datanascimento, email, telefone, senha, usuario) 
VALUES ('Cliente', 'Teste', '111.111.111-11', '1995-05-15', 'cliente@teste.com', '(11) 88888-8888', 'cliente123', 'Cliente');

-- Inserir algumas avaliações de exemplo
IF NOT EXISTS (SELECT * FROM Avaliacao WHERE nomeJogo = 'Happy Cat Tavern')
BEGIN
    INSERT INTO Avaliacao (comentario, estrelas, nomeJogo, nomeUsuario, dataAvaliacao) 
    VALUES ('Jogo muito divertido! Adoro a mecânica de digitação.', 5, 'Happy Cat Tavern', 'Cliente Teste', '2024-01-15');
    
    INSERT INTO Avaliacao (comentario, estrelas, nomeJogo, nomeUsuario, dataAvaliacao) 
    VALUES ('Bom jogo, mas poderia ter mais níveis.', 4, 'Happy Cat Tavern', 'Admin Sistema', '2024-01-16');
    
    INSERT INTO Avaliacao (comentario, estrelas, nomeJogo, nomeUsuario, dataAvaliacao) 
    VALUES ('Excelente para treinar digitação!', 5, 'Happy Cat Tavern', 'Cliente Teste', '2024-01-17');
END

PRINT 'Banco de dados configurado com sucesso!';
PRINT 'Usuário Admin: admin@gamelegends.com / admin123';
PRINT 'Usuário Cliente: cliente@teste.com / cliente123';