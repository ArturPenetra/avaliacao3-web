create table consertos(

    id bigint not null auto_increment,
    data_entrada varchar(10),
    data_saida varchar(10),

    mecanico_nome varchar(100) not null,
    mecanico_anos_experiencia int,

    veiculo_marca varchar(50) not null,
    veiculo_modelo varchar(50) not null,
    veiculo_ano varchar(4) not null,

    primary key(id)

);
