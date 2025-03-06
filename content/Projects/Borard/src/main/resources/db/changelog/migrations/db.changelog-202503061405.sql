--liquibase formatted sql
--changeset gonçalves:202503061405
--comment: create table boards_columns

CREATE TABLE BOARDS_COLUMNS(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    `order` int NOT NULL,
    kind VARCHAR(10) NOT NULL,
    board_id BIGINT NOT  NULL,
    CONSTRAINT board__boards_columns_fk FOREIGN KEY (board_id) REFERENCES BOARDS(id) ON DELETE CASCADE,
    CONSTRAINT id_board_uk UNIQUE KEY unique_board_id_borard (board_id,`order`)
) ENGINE=InnoDB;

--rollback DROP TABLE BOARDS_COLUMNS