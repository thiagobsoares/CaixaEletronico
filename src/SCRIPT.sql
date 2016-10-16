create database CaixaEletronico;

use CaixaEletronico;

CREATE TABLE conta (
  numeroConta int(11) NOT NULL,
  numeroAgencia int(11) NOT NULL,
  saldo decimal(15,2) NOT NULL,
  cliente varchar(255) NOT NULL,
  administrador tinyint(1) DEFAULT '0',
  senha int(6) NOT NULL,
  PRIMARY KEY (numeroConta,numeroAgencia)
);

INSERT INTO conta VALUES (123123,1234,1000000.00,'Joãozinho da Petrobras',0,123456);
INSERT INTO conta VALUES (123456,1234,0.00,'Zé da Laranja',0,123456);
INSERT INTO conta VALUES (222333,2222,500.00,'Jose Fernando',0,123456);


create table RegistroDeOperacao(
	numeroDocumento integer primary key auto_increment,
	numeroConta integer not null,
    numeroAgencia integer not null,
    dataLancamento date,
    tipoOperacao varchar(50) not null,
    tipoLancamento varchar(50) not null,
    valorDaOperacao decimal(10,2) not null,
    FOREIGN KEY ( numeroConta, numeroAgencia ) REFERENCES Conta (numeroConta, numeroAgencia)
);