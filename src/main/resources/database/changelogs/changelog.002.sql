create table card_transaction (
    id int8 not null,
    create_date timestamp,
    update_date timestamp,
    balance float,
    withdrawn_cash float,
    card_id int8,
    primary key (id),
    FOREIGN KEY (card_id) REFERENCES card_transaction(id)
);