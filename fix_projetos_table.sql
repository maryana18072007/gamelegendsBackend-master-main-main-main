USE bd_gamelegends;

-- Remover tabela projetos antiga se existir
IF EXISTS (SELECT * FROM sysobjects WHERE name='projetos' AND xtype='U')
DROP TABLE projetos;

-- Criar tabela projetos com estrutura correta
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

PRINT 'Tabela projetos criada com sucesso!';