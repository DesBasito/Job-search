--liquibase formatted sql
--changeset Abu:add_initial_data

insert into AUTHORITIES(role)
VALUES ('employer'),
       ('employee'),
       ('admin'),
       ('guest');

INSERT INTO USERS (name, surname, age, email, password, phone_number, avatar, ENABLED,ROLE_ID)
VALUES /* qwe */('John', 'Doe', 30, 'john.doe@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '1234567890', 'avatar.jpg', true,(SELECT id from AUTHORITIES where ROLE = 'employee')),
       /* password456 */('Jane', 'Smith', 25, 'jane.smith@example.com', '$2a$10$zLoPtjUjaZAcOPjEuFunnOS13swef0FFxo06ujuyobopYxwD5F/s2', '1987654321', 'avatar.png', true,(select id from AUTHORITIES where ROLE = 'employer')),
       /* password123 */('Michael', 'Johnson', 35, 'michael.johnson@example.com', '$2a$10$VGkwdmvXTqgn6yfmdZ6w7.NdMDeSYTs4JcDdySZ.yMdQ4qzGH3At.', '1122334455', NULL, true,(select id from AUTHORITIES where role = 'admin')),
       /* qwerty */('John', 'Jacobson', 30, 'johny@example.com', '$2a$10$UYXM0mgh1OLkf6r7Iq0kCe2KV/fZT/GB1SkbdddpjzXEHK8NOQomi', '763547234', 'avatar4.jpg', true,(select id from AUTHORITIES where role = 'guest'));



insert into USER_AUTHORITY(user_id, authority_id)
values ((SELECT id FROM USERS WHERE EMAIL = 'john.doe@example.com'),
        (SELECT id FROM AUTHORITIES WHERE ROLE = 'employee')),
        ((SELECT id FROM USERS WHERE EMAIL = 'johny@example.com'),
        (SELECT id FROM AUTHORITIES WHERE ROLE = 'guest')),
        ((SELECT id FROM USERS WHERE EMAIL = 'michael.johnson@example.com'),
         (SELECT id FROM AUTHORITIES WHERE ROLE = 'admin')),
       ((SELECT id FROM USERS WHERE EMAIL = 'jane.smith@example.com'),
        (SELECT id FROM AUTHORITIES WHERE ROLE = 'employer'));

INSERT INTO contact_types (type)
VALUES ('Email'),
       ('Phone'),
       ('LinkedIn');

INSERT INTO categories (name)
VALUES ('Software Development'),
       ('Marketing'),
       ('Finance'),
       ('Bio Engineer');

INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date,
                       update_date)
VALUES ('Software Engineer', 'Description for software engineer vacancy',
        (SELECT id FROM categories WHERE name = 'Software Development'), 70000, 3, 5, 1,
        (SELECT id FROM USERS WHERE name = 'John' AND surname = 'Doe'), NOW(), NOW()),
       ('Marketing Manager', 'Description for marketing manager vacancy',
        (SELECT id FROM categories WHERE name = 'Marketing'), 60000, 5, 7, 1,
        (SELECT id FROM USERS WHERE name = 'Jane' AND surname = 'Smith'), NOW(), NOW()),
       ('Financial Analyst', 'Description for financial analyst vacancy',
        (SELECT id FROM categories WHERE name = 'Finance'), 80000, 3, 6, 1,
        (SELECT id FROM USERS WHERE name = 'John' AND surname = 'Doe'), NOW(), NOW()),
       ('Data base architecture', 'Description for architecture vacancy',
        (SELECT id FROM categories WHERE name = 'Software Development'), 67000, 2, 4, 1,
        (SELECT id FROM USERS WHERE name = 'Michael' AND surname = 'Johnson'), '2023-12-01 17:45:18',
        '2024-02-12 14:27:46');

INSERT INTO resumes (name, category_id, applicant_id, salary, is_active, created_date, update_date)
VALUES ('John Doe Resume', (SELECT id FROM categories WHERE name = 'Software Development'),
        (SELECT id FROM USERS WHERE name = 'John' AND surname = 'Doe'), 70000, 1, NOW(), NOW()),
       ('Jane Smith Resume', (SELECT id FROM categories WHERE name = 'Marketing'),
        (SELECT id FROM USERS WHERE name = 'Jane' AND surname = 'Smith'), 60000, 1, NOW(), NOW()),
       ('Michael Johnson Resume', (SELECT id FROM categories WHERE name = 'Finance'),
        (SELECT id FROM USERS WHERE name = 'Michael' AND surname = 'Johnson'), 80000, 1, NOW(), NOW()),
       ('John Doe Resume of php dev-op', (SELECT id FROM categories WHERE name = 'Software Development'),
        (SELECT id FROM USERS WHERE name = 'John' AND surname = 'Doe'), 70000, 1, '2024-01-23 12:34:09',
        '2024-03-02 18:12:39');

INSERT INTO contacts_info (infoValue, type_id, resume_id)
VALUES ('john.doe@example.com', (SELECT id FROM contact_types WHERE type = 'Email'),
        (SELECT id FROM resumes WHERE name = 'John Doe Resume')),
       ('+1234567890', (SELECT id FROM contact_types WHERE type = 'Phone'),
        (SELECT id FROM resumes WHERE name = 'John Doe Resume')),
       ('https://www.linkedin.com/johndoe', (SELECT id FROM contact_types WHERE type = 'LinkedIn'),
        (SELECT id FROM resumes WHERE name = 'John Doe Resume'));

INSERT INTO responded_applicants (vacancy_id, resume_id, confirmation)
VALUES ((SELECT id FROM vacancies WHERE name = 'Software Engineer'),
        (SELECT id FROM resumes WHERE name = 'John Doe Resume'), 1),
       ((SELECT id FROM vacancies WHERE name = 'Marketing Manager'),
        (SELECT id FROM resumes WHERE name = 'Jane Smith Resume'), 1),
       ((SELECT id FROM vacancies WHERE name = 'Financial Analyst'),
        (SELECT id FROM resumes WHERE name = 'Michael Johnson Resume'), 0);

INSERT INTO responded_applicants (vacancy_id, resume_id, confirmation)
VALUES ((SELECT id FROM vacancies WHERE name = 'Data base architecture'),
        (SELECT id FROM resumes WHERE name = 'John Doe Resume of php dev-op'), 1);

INSERT INTO responded_applicants (vacancy_id, resume_id, confirmation)
VALUES ((SELECT id FROM vacancies WHERE name = 'Software Engineer'),
        (SELECT id FROM resumes WHERE name = 'John Doe Resume of php dev-op'), 1);

INSERT INTO responded_applicants (vacancy_id, resume_id, confirmation)
VALUES ((SELECT id FROM vacancies WHERE name = 'Data base architecture'),
        (SELECT id FROM resumes WHERE name = 'John Doe Resume of php dev-op'), 1);



INSERT INTO education_info (institution, program, resume_id, start_date, end_date, degree)
VALUES ('University of Example', 'Computer Science', (SELECT id FROM resumes WHERE name = 'John Doe Resume'),
        '2010-09-01', '2014-06-01', 'Bachelor'),
       ('College of Marketing', 'Marketing Management', (SELECT id FROM resumes WHERE name = 'Jane Smith Resume'),
        '2012-09-01', '2016-06-01', 'Bachelor'),
       ('Institute of Finance', 'Finance and Economics', (SELECT id FROM resumes WHERE name = 'Michael Johnson Resume'),
        '2008-09-01', '2012-06-01', 'Bachelor');

INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES ((SELECT id FROM resumes WHERE name = 'John Doe Resume'), 6, 'Tech Company Inc.', 'Software Engineer',
        'Responsible for developing web applications'),
       ((SELECT id FROM resumes WHERE name = 'Jane Smith Resume'), 5, 'Marketing Agency Ltd.', 'Marketing Manager',
        'Developing marketing strategies and campaigns'),
       ((SELECT id FROM resumes WHERE name = 'Michael Johnson Resume'), 8, 'Finance Firm LLC', 'Financial Analyst',
        'Conducting financial analysis and reporting');

INSERT INTO messages (responded_applicants, content, timestamp)
VALUES ((SELECT id
         FROM responded_applicants
         WHERE vacancy_id = (SELECT id FROM vacancies WHERE name = 'Software Engineer')
           AND resume_id = (SELECT id FROM resumes WHERE name = 'John Doe Resume')), 'Thank you for applying.', NOW()),
       ((SELECT id
         FROM responded_applicants
         WHERE vacancy_id = (SELECT id FROM vacancies WHERE name = 'Marketing Manager')
           AND resume_id = (SELECT id FROM resumes WHERE name = 'Jane Smith Resume')),
        'We are interested in your application. Can you come for an interview?', NOW()),
       ((SELECT id
         FROM responded_applicants
         WHERE vacancy_id = (SELECT id FROM vacancies WHERE name = 'Financial Analyst')
           AND resume_id = (SELECT id FROM resumes WHERE name = 'Michael Johnson Resume')),
        'Unfortunately, your qualifications do not match our requirements.', NOW());