SQL

create database CaixaEletronico;

use CaixaEletronico;

create table Conta(
	numeroConta integer not null,
    numeroAgencia integer not null,
    saldo decimal(15,2) not null,
	primary key(numeroConta,numeroAgencia)
);

create table RegistroDeOperacao(
	numeroDocumento integer primary key auto_increment,
	numeroConta integer not null,
    numeroAgencia integer not null,
    dataLancamento varchar(20) not null,
    tipoOperacao varchar(50) not null,
    tipoLancamento varchar(1) not null,
    valorDaOperacao decimal(10,2) not null,
    FOREIGN KEY ( numeroConta, numeroAgencia ) REFERENCES Conta (numeroConta, numeroAgencia)
);

INSERT INTO Conta (numeroConta, numeroAgencia, saldo) VALUES (01, 01 , 5000000);
