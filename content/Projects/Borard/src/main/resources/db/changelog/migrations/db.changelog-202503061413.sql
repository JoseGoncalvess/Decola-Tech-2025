--liquibase formatted sql
--changeset gonçalves:202503061405
--comment: created table CARDS

CREATE TABLE CARDS(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    descripton VARCHAR(255) NOT NULL,
    `order` int NOT NULL,
    board_column_id BIGINT NOT  NULL,
    CONSTRAINT boards__columns__cards_fk FOREIGN KEY (board_column_id) REFERENCES BOARDS_COLUMNS(id) ON DELETE CASCADE
) ENGINE=InnoDB;

--rollback DROP TABLE CARDS