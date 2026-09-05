

CREATE DATABASE auquemiadb;

CREATE TABLE tutor (
    IDTutor serial,
    nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(15) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    endereco VARCHAR(150) NOT NULL,
    PRIMARY KEY(IDTutor)
);

CREATE TABLE veterinario (
    IDVeterinario serial,
    nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(15) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    especialidade VARCHAR(30),
    crmv VARCHAR(15) NOT NULL,
    horas_trabalhadas INTEGER,
    PRIMARY KEY(IDVeterinario)
);

CREATE TABLE animal (
    IDAnimal serial,
    IDTutor INTEGER,
    nome VARCHAR(120) NOT NULL,
    especie VARCHAR(30) NOT NULL,
    raca VARCHAR(60) NOT NULL,
    idade VARCHAR(60) NOT NULL,
    PRIMARY KEY(IDAnimal),
    FOREIGN KEY (IDTutor) REFERENCES tutor(IDTutor)
);

CREATE TABLE consulta (
    IDConsulta serial,
    IDVeterinario INTEGER,
    IDAnimal INTEGER,
    data TIMESTAMP NOT NULL,
    motivo VARCHAR(60) NOT NULL,
    temperatura DECIMAL(9,2),
    diagnostico VARCHAR(60) NOT NULL,
    status VARCHAR(30) NOT NULL,
    valor DECIMAL(9,2) NOT NULL,
    PRIMARY KEY(IDConsulta),
    FOREIGN KEY(IDVeterinario) REFERENCES veterinario(IDVeterinario),
    FOREIGN KEY(IDAnimal) REFERENCES animal(IDAnimal)
);

CREATE TABLE medicamento (
    IDMedicamento serial,
    IDConsulta INTEGER,
    nome VARCHAR(60) NOT NULL,
    formato VARCHAR(30) NOT NULL,
    fabricante VARCHAR(60) NOT NULL,
    data_vencimento VARCHAR(12) NOT NULL,
    PRIMARY KEY(IDMedicamento),
    FOREIGN KEY (IDConsulta) REFERENCES consulta(IDConsulta)
);
