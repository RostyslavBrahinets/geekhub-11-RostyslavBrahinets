package repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseRepository {
    private void runSql(String sql) throws SQLException, IOException {
        try (
            Connection connection = Connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.execute();
        }
    }

    public void createTablesInDataBase() throws SQLException, IOException {
        String sql = "create table if not exists course"
            + "("
            + "    id           serial primary key,"
            + "    name         varchar(256) not null"
            + ");";
        runSql(sql);

        sql = "create table if not exists person"
            + "("
            + "    id             serial primary key,"
            + "    first_name      varchar(256) not null,"
            + "    last_name       varchar(256) not null,"
            + "    git_hub_nickname varchar(256) not null,"
            + "    role           varchar(10)  not null,"
            + "    course_id integer not null,"
            + "    constraint fk_course"
            + "        foreign key (course_id)"
            + "            references course (id)"
            + "            on delete cascade"
            + ");";
        runSql(sql);

        sql = "create table if not exists contacts"
            + "("
            + "    id    serial primary key,"
            + "    email varchar(256) unique,"
            + "    phone varchar(256) unique,"
            + "    person_id integer not null unique,"
            + "    constraint fk_person"
            + "        foreign key (person_id)"
            + "            references person (id)"
            + "            on delete cascade"
            + ");";
        runSql(sql);

        sql = "create table if not exists lection"
            + "("
            + "    id           serial primary key,"
            + "    name         varchar(256) not null,"
            + "    describe     varchar(512) not null,"
            + "    lecturer_id     integer not null,"
            + "    creation_date timestamp default current_timestamp,"
            + "    course_id integer not null,"
            + "    constraint fk_lecturer"
            + "        foreign key (lecturer_id)"
            + "            references person (id)"
            + "            on delete cascade,"
            + "    constraint fk_course"
            + "        foreign key (course_id)"
            + "            references course (id)"
            + "            on delete cascade"
            + ");";
        runSql(sql);

        sql = "create table if not exists resource"
            + "("
            + "    id   serial primary key,"
            + "    name varchar(256) not null,"
            + "    type varchar(5)   not null,"
            + "    data varchar(512) not null,"
            + "    lection_id integer not null,"
            + "    constraint fk_lection"
            + "        foreign key (lection_id)"
            + "            references lection (id)"
            + "            on delete cascade"
            + ");";
        runSql(sql);

        sql = "create table if not exists homework"
            + "("
            + "    id       serial primary key,"
            + "    task     varchar(256) not null,"
            + "    deadline timestamp    not null,"
            + "    lection_id integer not null,"
            + "    constraint fk_lection"
            + "        foreign key (lection_id)"
            + "            references lection (id)"
            + "            on delete cascade"
            + ");";
        runSql(sql);
    }

    public void insertDataToTablesInDataBase() throws SQLException, IOException {
        String sql = "insert into course (name)"
            + "values ('GeekHub 11')";
        runSql(sql);

        sql = "insert into person (first_name, last_name, git_hub_nickname, role, course_id)"
            + "values"
            + "('Yaroslav', 'Brahinets', 'ybrahinets', 'TEACHER', 1),"
            + "('Bohdan', 'Cherniak', 'bcherniak', 'TEACHER', 1),"
            + "('Volodymyr', 'Vedula', 'vvedula', 'TEACHER', 1),"
            + "('Vladyslav', 'Nikolenko', 'vnikolenko', 'TEACHER', 1),"
            + "('Vladyslav', 'Rudas', 'vrudas', 'TEACHER', 1),"
            + "('Rostyslav', 'Brahinets', 'rbrahinets', 'STUDENT', 1);";
        runSql(sql);

        sql = "insert into contacts (email, phone, person_id)"
            + "values"
            + "('yb@gmail.com', '+380933333333', 1),"
            + "('bc@gmail.com', '+380966666666', 2),"
            + "('vv@gmail.com', '+380699999999', 3),"
            + "('vn@gmail.com', '+380733333333', 4),"
            + "('vr@gmail.com', '+380666666666', 5),"
            + "('rostyslav.brahinets@gmail.com', '+380961801387', 6);";
        runSql(sql);

        sql = "insert into lection (name, describe, creation_date, lecturer_id, course_id)"
            + "values"
            + "('Intro', 'Lection about start in Java', '2021-11-02', 1, 1),"
            + "('Basics', 'Lection about Java', '2021-11-09', 2, 1),"
            + "('Object-oriented Programming', 'Lection about OOP', '2021-11-16', 3, 1),"
            + "('Error Propagation and Handling', 'Lection about Exceptions', '2021-11-23', 4, 1),"
            + "('Code Testing (Unit)', 'Lection about JUnit', '2021-11-30', 5, 1);";
        runSql(sql);

        sql = "insert into resource (name, type, data, lection_id)"
            + "values"
            + "('InteliJ IDEA', 'URL', 'https://www.jetbrains.com/ru-ru/idea/', 1),"
            + "('Java Core', 'URL', 'https://docs.oracle.com/en/java/', 2),"
            + "('OOP', 'URL', 'https://www.w3schools.com/java/java_oop.asp', 3),"
            + "('Exceptions', 'URL', 'https://www.w3schools.com/java/java_try_catch.asp', 4),"
            + "('JUnit', 'URL', 'https://junit.org/junit5/', 5);";
        runSql(sql);

        sql = "insert into homework (task, deadline, lection_id)"
            + "values"
            + "('task1', '2021-11-06 23:59', 1),"
            + "('task2', '2021-11-13 23:59', 2),"
            + "('task2', '2021-11-20 23:59', 3),"
            + "('task2', '2021-11-27 23:59', 4),"
            + "('task3', '2021-12-04 23:59', 5);";
        runSql(sql);
    }
}
