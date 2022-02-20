package repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MainRepository {
    private final Connection connection;

    public MainRepository() throws SQLException, IOException {
        connection = Connector.getConnection();
    }

    public void createTablesInDataBase() throws SQLException {
        try (Statement statement = connection.createStatement();) {
            String sql = "create table if not exists homework"
                + "("
                + "    id       serial primary key,"
                + "    task     varchar(256) not null,"
                + "    deadline timestamp    not null"
                + ");";
            statement.executeQuery(sql);

            sql = "create table if not exists resource"
                + "("
                + "    id   serial primary key,"
                + "    name varchar(256) not null,"
                + "    type varchar(5)   not null,"
                + "    data varchar(512) not null"
                + ");";
            statement.executeQuery(sql);

            sql = "create table if not exists contacts"
                + "("
                + "    id    serial primary key,"
                + "    email varchar(256) unique,"
                + "    phone varchar(256) unique"
                + ");";
            statement.executeQuery(sql);

            sql = "create table if not exists person"
                + "("
                + "    id             serial primary key,"
                + "    first_name      varchar(256) not null,"
                + "    last_name       varchar(256) not null,"
                + "    contact_id      integer      not null,"
                + "    git_hub_nickname varchar(256) not null,"
                + "    role           varchar(10)  not null,"
                + "    constraint fk_contacts"
                + "        foreign key (contact_id)"
                + "            references contacts (id)"
                + "            on delete cascade"
                + ");";
            statement.executeQuery(sql);

            sql = "create table if not exists lection"
                + "("
                + "    id           serial primary key,"
                + "    name         varchar(256) not null,"
                + "    describe     varchar(512) not null,"
                + "    resource_id    integer      not null,"
                + "    person_id       integer      not null,"
                + "    homework_id    integer      not null,"
                + "    creation_date timestamp default current_timestamp,"
                + "    constraint fk_resources"
                + "        foreign key (resource_id)"
                + "            references resource (id)"
                + "            on delete cascade,"
                + "    constraint fk_person"
                + "        foreign key (person_id)"
                + "            references person (id)"
                + "            on delete cascade,"
                + "    constraint fk_homeworks"
                + "        foreign key (homework_id)"
                + "            references homework (id)"
                + "            on delete cascade"
                + ");";
            statement.executeQuery(sql);

            sql = "create table if not exists course"
                + "("
                + "    id           serial primary key,"
                + "    name         varchar(256) not null,"
                + "    lection_id    integer      not null,"
                + "    student_id       integer      not null,"
                + "    constraint fk_lections"
                + "        foreign key (lection_id)"
                + "            references lection (id)"
                + "            on delete cascade,"
                + "    constraint fk_students"
                + "        foreign key (student_id)"
                + "            references person (id)"
                + "            on delete cascade"
                + ");";
            statement.executeQuery(sql);
        }
    }

    public void insertDataToTablesInDataBase() throws SQLException {
        try (Statement statement = connection.createStatement();) {
            String sql = "insert into contacts (email, phone)"
                + "values ('person1@gmail.com', '+380962314658'),"
                + "('person2@gmail.com', '+380966354658'),"
                + "('persob3@gmail.com', '+380962317896');";
            statement.executeQuery(sql);

            sql = "insert into homework (task, deadline)"
                + "values ('task1', '2022-11-02 18:00'),"
                + "('task2', '2022-11-09 18:00'),"
                + "('task3', '2022-12-09 18:00');";
            statement.executeQuery(sql);

            sql = "insert into resource (name, type, data)"
                + "values ('resource1', 'URL', 'data1'),"
                + "('resource2', 'BOOK', 'data2'),"
                + "('resource3', 'VIDEO', 'data3');";
            statement.executeQuery(sql);

            sql = "insert into person (first_name, last_name, contact_id, git_hub_nickname, role)"
                + "values ('first_name1', 'last_name1', 1, 'nick_name1', 'TEACHER'),"
                + "('first_name2', 'last_name2', 2, 'nick_name2', 'STUDENT'),"
                + "('first_name3', 'last_name3', 3, 'nick_name2', 'STUDENT');";
            statement.executeQuery(sql);

            sql = "insert into lection (name, describe, resource_id, person_id, homework_id)"
                + "values ('lection1', 'describe1', 1, 1, 1),"
                + "('lection2', 'describe2', 2, 1, 2),"
                + "('lection3', 'describe3', 3, 1, 3);";
            statement.executeQuery(sql);

            sql = "insert into course (name, lection_id, student_id)"
                + "values ('course1', 1, 2),"
                + "('course2', 2, 2),"
                + "('course3', 3, 3);";
            statement.executeQuery(sql);
        }
    }
}
