alter table consertos add ativo boolean;

update consertos set ativo = true;

alter table consertos alter column ativo set not null;
