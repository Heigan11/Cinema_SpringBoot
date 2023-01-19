insert into halls (seats) VALUES
    (
        '100'
    );
insert into movies (title, year, restriction, description) VALUES
    (
        'The Terminator',
        '1984',
        '16',
        'The Terminator is a 1984 American science fiction action film directed by James Cameron.'
    );

INSERT INTO chat_user (avatar_id, is_confirmed, email, first_name, last_name, password, phone_number, role, verification_id)
values ('0', true,'admin@gmail.com', 'Admin', 'Adminov',
        '$2a$10$BEsEiXoQwpqVQmseTtprWORMl.j.XZWqT6N69Sj0qC6eA2KcSmLFS', '+7(777)7777777', 'ADMIN', 'randomUUID');

INSERT INTO chat_user (avatar_id, is_confirmed, email, first_name, last_name, password, phone_number, role, verification_id)
values ('0', true,'user@gmail.com', 'User', 'Userov',
        '$2a$10$BEsEiXoQwpqVQmseTtprWORMl.j.XZWqT6N69Sj0qC6eA2KcSmLFS', '+7(777)7777777', 'USER', 'randomUUID');

-- insert into springboot.users (first_name, last_name, email, phone_number, password, role, status, avatar, is_non_locked, is_non_enable, fail_attempts)
-- VALUES ('b', 'last_name', 'ADMIN', 'phone_number', '$2a$10$BDRuCkP91uTk2KfREKCSDuzrbZN/xRbchODFdqTlKNWSjQMSUKXHq', 'ADMIN', 'CONFIRMED', null, 'true', 'true', 0);

-- insert into springboot.authority (authorities_id, name) VALUES ('1', 'USER');
-- insert into springboot.users_authorities (user_id, authorities_id) VALUES ('35', '1');

-- insert into springboot.users (first_name, last_name, email, phone_number, password, role, status, avatar, is_non_locked, is_non_enable, fail_attempts)
-- VALUES ('b', 'last_name', 'ADMIN', 'phone_number', '$2a$10$BDRuCkP91uTk2KfREKCSDuzrbZN/xRbchODFdqTlKNWSjQMSUKXHq', 'ADMIN', 'CONFIRMED', null, 'true', 'true', 0);

-- update springboot.users
-- set role='ADMIN' where user_id='187'