-- Campo para exclusao logica
alter table consertos add ativo boolean;

-- Registros ja existentes passam a ser considerados ativos
update consertos set ativo = true;

-- A partir de agora o campo e obrigatorio
alter table consertos alter column ativo set not null;
