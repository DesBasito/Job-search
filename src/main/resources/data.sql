INSERT INTO users (name, surname, age, email, password, phone_number, avatar, acc_type)
VALUES ('John', 'Doe', 30, 'john.doe@example.com', 'password123', '+1234567890', 'avatar.jpg', 'regular'),
       ('Jane', 'Smith', 25, 'jane.smith@example.com', 'password456', '+1987654321', 'avatar.png', 'premium'),
       ('Michael', 'Johnson', 35, 'michael.johnson@example.com', 'password789', '+1122334455', NULL, 'regular');

INSERT INTO contact_types (type)
VALUES ('Email'),
       ('Phone'),
       ('LinkedIn');

INSERT INTO categories (name)
VALUES ('Software Development'),
       ('Marketing'),
       ('Finance');

INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_date)
VALUES ('Software Engineer', 'Description for software engineer vacancy', 1, 70000, 3, 5, 1, 1, NOW(), NOW()),
       ('Marketing Manager', 'Description for marketing manager vacancy', 2, 60000, 5, 7, 1, 2, NOW(), NOW()),
       ('Financial Analyst', 'Description for financial analyst vacancy', 3, 80000, 3, 6, 1, 1, NOW(), NOW());

INSERT INTO resumes (name, category_id, applicant_id, salary, is_active, created_date, update_date)
VALUES ('John Doe Resume', 1, 1, 70000, 1, NOW(), NOW()),
       ('Jane Smith Resume', 2, 2, 60000, 1, NOW(), NOW()),
       ('Michael Johnson Resume', 3, 3, 80000, 1, NOW(), NOW());

INSERT INTO contacts_info (infoValue, type_id, resume_id)
VALUES ('john.doe@example.com', 1, 1),
       ('+1234567890', 2, 1),
       ('https://www.linkedin.com/johndoe', 3, 1);

INSERT INTO responded_applicants (vacancy_id, resume_id, confirmation)
VALUES (1, 1, 1),
       (2, 2, 1),
       (3, 3, 0);

INSERT INTO education_info (institution, program, resume_id, start_date, end_date, degree)
VALUES ('University of Example', 'Computer Science', 1, '2010-09-01', '2014-06-01', 'Bachelor'),
       ('College of Marketing', 'Marketing Management', 2, '2012-09-01', '2016-06-01', 'Bachelor'),
       ('Institute of Finance', 'Finance and Economics', 3, '2008-09-01', '2012-06-01', 'Bachelor');

INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES (1, 6, 'Tech Company Inc.', 'Software Engineer', 'Responsible for developing web applications'),
       (2, 5, 'Marketing Agency Ltd.', 'Marketing Manager', 'Developing marketing strategies and campaigns'),
       (3, 8, 'Finance Firm LLC', 'Financial Analyst', 'Conducting financial analysis and reporting');

INSERT INTO messages (responded_applicants, content, timestamp)
VALUES (1, 'Thank you for applying.', NOW()),
       (2, 'We are interested in your application. Can you come for an interview?', NOW()),
       (3, 'Unfortunately, your qualifications do not match our requirements.', NOW());