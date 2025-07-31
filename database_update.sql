USE bd_gamelegends;

-- Tabela Cadastro
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Cadastro' AND xtype='U')
CREATE TABLE Cadastro (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    nome NVARCHAR(255),
    sobrenome NVARCHAR(255),
    email NVARCHAR(255)
);

-- Tabela CadCartao
IF EXISTS (SELECT * FROM sysobjects WHERE name='CadCartao' AND xtype='U')
DROP TABLE CadCartao;

CREATE TABLE CadCartao (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    nomeT NVARCHAR(255) NULL,
    numC NVARCHAR(255) NULL,
    validade NVARCHAR(255) NULL,
    CVV NVARCHAR(255) NULL,
    bandeira NVARCHAR(255) NULL,
    clienteId BIGINT NULL
);

-- Usuário teste
IF NOT EXISTS (SELECT * FROM Cadastro WHERE id = 1)
INSERT INTO Cadastro (nome, sobrenome, email) VALUES ('Usuario', 'Teste', 'teste@teste.com');

PRINT 'Pronto para testar cartões!';