INSERT INTO users (name, surname, age, email, password, phone_number, avatar, acc_type)
VALUES ('John', 'Doe', 30, 'john.doe@example.com', 'password123', '1234567890', 'avatar.jpg', 'employer'),
       ('Jane', 'Smith', 25, 'jane.smith@example.com', 'password456', '1987654321', 'avatar.png', 'employee'),
       ('Michael', 'Johnson', 35, 'michael.johnson@example.com', 'password789', '1122334455', NULL, 'employer');

INSERT INTO users (name, surname, age, email, password, phone_number, avatar, acc_type)
VALUES ('John', 'Jakobson', 30, 'johny@example.com', 'qwerty', '763547234', 'avatar4.jpg', 'employee');

INSERT INTO contact_types (type)
VALUES ('Email'),
       ('Phone'),
       ('LinkedIn');

INSERT INTO categories (name)
VALUES ('Software Development'),
       ('Marketing'),
       ('Finance');

INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date,
                       update_date)
VALUES ('Software Engineer', 'Description for software engineer vacancy',
        (SELECT id FROM categories WHERE name = 'Software Development'), 70000, 3, 5, 1,
        (SELECT id FROM users WHERE name = 'John' AND surname = 'Doe'), NOW(), NOW()),
       ('Marketing Manager', 'Description for marketing manager vacancy',
        (SELECT id FROM categories WHERE name = 'Marketing'), 60000, 5, 7, 1,
        (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith'), NOW(), NOW()),
       ('Financial Analyst', 'Description for financial analyst vacancy',
        (SELECT id FROM categories WHERE name = 'Finance'), 80000, 3, 6, 1,
        (SELECT id FROM users WHERE name = 'John' AND surname = 'Doe'), NOW(), NOW());

INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date,
                       update_date)
VALUES ('Data base architecture', 'Description for architecture vacancy',
        (SELECT id FROM categories WHERE name = 'Software Development'), 67000, 2, 4, 1,
        (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Jakobson'), '2023-12-01 17:45:18',
        '2024-02-12 14:27:46');

INSERT INTO resumes (name, category_id, applicant_id, salary, is_active, created_date, update_date)
VALUES ('John Doe Resume', (SELECT id FROM categories WHERE name = 'Software Development'),
        (SELECT id FROM users WHERE name = 'John' AND surname = 'Doe'), 70000, 1, NOW(), NOW()),
       ('Jane Smith Resume', (SELECT id FROM categories WHERE name = 'Marketing'),
        (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith'), 60000, 1, NOW(), NOW()),
       ('Michael Johnson Resume', (SELECT id FROM categories WHERE name = 'Finance'),
        (SELECT id FROM users WHERE name = 'Michael' AND surname = 'Johnson'), 80000, 1, NOW(), NOW());

INSERT INTO resumes (name, category_id, applicant_id, salary, is_active, created_date, update_date)
VALUES ('John Doe Resume of php dev-op', (SELECT id FROM categories WHERE name = 'Software Development'),
        (SELECT id FROM users WHERE name = 'John' AND surname = 'Doe'), 70000, 1, '2024-01-23 12:34:09',
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
