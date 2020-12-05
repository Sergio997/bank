create table card (
  id int8 not null,
  create_date timestamp,
  update_date timestamp,
  number_card varchar(60),
  email varchar(60),
  first_name varchar(60),
  second_name varchar(60),
  password varchar(255),
  balance float,
  role varchar(20),
  active boolean,
  primary key (id)
);

create table verification_token(
    id int8 not null,
    token varchar(255),
    created_date timestamp,
    card_id int8,
    primary key (id)
);

alter table verification_token
    add constraint FKh4ce7wcayhe962fxj2gd8bwq01 foreign key (card_id) references card;