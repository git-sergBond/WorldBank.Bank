---------------
---  CLIENT ---
---------------
CREATE SEQUENCE SEQ_client_01
    MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE TABLE client (
    id NUMBER(19) DEFAULT SEQ_CLIENT_01.nextval NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    birthday DATE NOT NULL,
    address VARCHAR(255) NOT NULL,
    mobile_plone VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    PRIMARY KEY (id)
);
CREATE INDEX IDX_client_01 ON client (first_name);
CREATE INDEX IDX_client_02 ON client (middle_name);
CREATE INDEX IDX_client_03 ON client (last_name);
CREATE INDEX IDX_client_04 ON client (birthday);

---------------
--- ACCOUNT ---
---------------
CREATE SEQUENCE SEQ_account_01
    MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE TABLE account (
    id NUMBER(19) DEFAULT SEQ_account_01.nextval NOT NULL,
    client_id NUMBER(19) NOT NULL,
    date_open DATE NOT NULL,
    date_close DATE NOT NULL,
    CONSTRAINT FK_account_client_01
        FOREIGN KEY (client_id) REFERENCES client(id),
    PRIMARY KEY (id)
);
CREATE INDEX IDX_account_01 ON account (client_id);

---------------
--- BALANCE ---
---------------
CREATE SEQUENCE SEQ_balance_01
    MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE TABLE balance (
    id NUMBER(19) DEFAULT SEQ_balance_01.nextval NOT NULL,
    account_id NUMBER(19) NOT NULL,
    balance NUMBER(38) NOT NULL,
    currency_type VARCHAR(255) NOT NULL,
    CONSTRAINT FK_balance_account_01
        FOREIGN KEY (account_id) REFERENCES account (id),
    PRIMARY KEY (id)
);
CREATE INDEX IDX_balance_01 ON balance(account_id);

-----------------------
--- PAYMENT REQUEST ---
-----------------------
CREATE SEQUENCE SEQ_payment_request_01
    MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE TABLE payment_request (
  id NUMBER(19) DEFAULT SEQ_payment_request_01.nextval NOT NULL,
  client_id NUMBER(19) NOT NULL,
  account_from_id NUMBER(19) NOT NULL,
  account_to_id NUMBER(19) NOT NULL,
  amount NUMBER(19) NOT NULL,
  currency_type VARCHAR(255) NOT NULL,
  request_date TIMESTAMP NOT NULL,
  payment_template_id NUMBER(19) NOT NULL,
  CONSTRAINT FK_payment_request_client_01
      FOREIGN KEY (client_id) REFERENCES client (id),
  CONSTRAINT FK_payment_request_account_01
      FOREIGN KEY (account_from_id) REFERENCES account (id),
  CONSTRAINT FK_payment_request_account_02
      FOREIGN KEY (account_to_id) REFERENCES account (id),
  PRIMARY KEY (id)
);
CREATE INDEX IDX_payment_request_01 ON payment_request(client_id);

------------------------
--- PAYMENT TEMPLATE ---
------------------------
CREATE SEQUENCE SEQ_payment_template_01
    MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE TABLE payment_template (
  id NUMBER(19) DEFAULT SEQ_payment_template_01.nextval NOT NULL,
  client_id NUMBER(19) NOT NULL,
  account_from_id NUMBER(19) NOT NULL,
  account_to_id NUMBER(19) NOT NULL,
  amount NUMBER(19) NOT NULL,
  currency_type VARCHAR(255) NOT NULL,
  CONSTRAINT FK_paymnt_template_client_01
      FOREIGN KEY (client_id) REFERENCES client (id),
  CONSTRAINT FK_paymnt_template_account_01
      FOREIGN KEY (account_from_id) REFERENCES account (id),
  CONSTRAINT FK_paymnt_template_account_02
      FOREIGN KEY (account_to_id) REFERENCES account (id),
  PRIMARY KEY (id)
);
CREATE INDEX IDX_payment_template_01 ON payment_template(client_id);

ALTER TABLE payment_request
    ADD CONSTRAINT FK_paymnt_req_paymnt_templ_01
        FOREIGN KEY (payment_template_id)
            REFERENCES payment_template (id);

------------------------
--- PAYMENT STATUS -----
------------------------
CREATE SEQUENCE SEQ_payment_status_01
    MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE TABLE payment_status (
    id NUMBER(19) DEFAULT SEQ_payment_status_01.nextval NOT NULL,
    payment_request_id NUMBER(19) NOT NULL,
    account_from_id NUMBER(19) NOT NULL,
    account_to_id NUMBER(19) NOT NULL,
    amount NUMBER(19) NOT NULL,
    currency_type VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    transaction_id NUMBER(19) NOT NULL,
    date_change_status TIMESTAMP NOT NULL,
    CONSTRAINT FK_payment_status_account_01
        FOREIGN KEY (account_from_id) REFERENCES account (id),
    CONSTRAINT FK_payment_status_account_02
        FOREIGN KEY (account_to_id) REFERENCES account (id),
    CONSTRAINT FK_pay_status_pay_request_01
        FOREIGN KEY (payment_request_id) REFERENCES payment_request(id),
    PRIMARY KEY (id)
);
CREATE INDEX IDX_payment_status_01 ON payment_status(payment_request_id);
CREATE INDEX IDX_payment_status_02 ON payment_status(account_from_id);


------------------------
--- TRANSACTION --------
------------------------
CREATE SEQUENCE SEQ_transaction_01
    MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE TABLE transaction (
  id NUMBER(19) DEFAULT SEQ_transaction_01.nextval NOT NULL,
  account_from_id NUMBER(19) NOT NULL,
  account_to_id NUMBER(19) NOT NULL,
  amount NUMBER(19) NOT NULL,
  currency_type VARCHAR(255) NOT NULL,
  is_success NUMBER(1) NOT NULL,
  date_transaction TIMESTAMP NOT NULL,
  CONSTRAINT FK_transaction_account_01
      FOREIGN KEY (account_from_id) REFERENCES account (id),
  CONSTRAINT FK_transaction_account_02
      FOREIGN KEY (account_to_id) REFERENCES account (id),
  PRIMARY KEY (id)
);

ALTER TABLE payment_status ADD
    CONSTRAINT FK_payment_status_transaction_01
        FOREIGN KEY (transaction_id)
            REFERENCES transaction (id);

------------------------
--- TRANSACTION --------
------------------------
CREATE SEQUENCE SEQ_transaction_AUDIT_01
    MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE TABLE payment_status_AUDIT (
    id NUMBER(19) DEFAULT SEQ_transaction_AUDIT_01.nextval NOT NULL,
    payment_status_id NUMBER(19) NOT NULL,
    date_event TIMESTAMP NOT NULL,
    event_type VARCHAR(255) NOT NULL,
    --- COPY OF FIELDS FOR AUDIT ---
    payment_request_id NUMBER(19) NOT NULL,
    account_from_id NUMBER(19) NOT NULL,
    account_to_id NUMBER(19) NOT NULL,
    amount NUMBER(19) NOT NULL,
    currency_type VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    transaction_id NUMBER(19) NOT NULL,
    date_change_status TIMESTAMP NOT NULL,
    CONSTRAINT FK_pay_stat_AUD_pay_stat_01
        FOREIGN KEY (payment_status_id) REFERENCES payment_status (id),
    PRIMARY KEY (id)
);