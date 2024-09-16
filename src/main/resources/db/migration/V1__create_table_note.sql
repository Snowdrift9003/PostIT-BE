CREATE TABLE note
(
    id          UUID                        NOT NULL,
    create_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    update_date TIMESTAMP WITHOUT TIME ZONE,
    content     VARCHAR(255)                NOT NULL,
    CONSTRAINT pk_note PRIMARY KEY (id)
);
