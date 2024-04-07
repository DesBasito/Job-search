--liquibase formatted sql
--changeset Abu:add_applicants_data

INSERT INTO USERS (name, surname, age, email, password, phone_number, avatar, ENABLED)
VALUES
    ('Alice', 'Johnson', 28, 'alice.johnson@example.com',
     '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '9876543210', 'avatar_alice.jpg', true),
    ('Bob', 'Anderson', 32, 'bob.anderson@example.com',
     '$2a$10$zLoPtjUjaZAcOPjEuFunnOS13swef0FFxo06ujuyobopYxwD5F/s2', '7654321987', 'avatar_bob.png', true),
    ('Eva', 'Garcia', 26, 'eva.garcia@example.com',
     '$2a$10$VGkwdmvXTqgn6yfmdZ6w7.NdMDeSYTs4JcDdySZ.yMdQ4qzGH3At.', '1122334455', 'avatar_eva.jpg', true),
    ('David', 'Brown', 29, 'david.brown@example.com',
     '$2a$10$UYXM0mgh1OLkf6r7Iq0kCe2KV/fZT/GB1SkbdddpjzXEHK8NOQomi', '763547234', 'avatar_david.jpg', true),
    ('Sophia', 'Miller', 27, 'sophia.miller@example.com',
     '$2a$10$UYXM0mgh1OLkf6r7Iq0kCe2KV/fZT/GB1SkbdddpjzXEHK8NOQomi', '237644345', 'avatar_sophia.jpg', true),
    ('Max', 'Wang', 30, 'max.wang@example.com',
     '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '9988776655', 'avatar_max.jpg', true),
    ('Lily', 'Chen', 24, 'lily.chen@example.com',
     '$2a$10$zLoPtjUjaZAcOPjEuFunnOS13swef0FFxo06ujuyobopYxwD5F/s2', '1234432156', 'avatar_lily.jpg', true),
    ('Ryan', 'Lee', 31, 'ryan.lee@example.com',
     '$2a$10$VGkwdmvXTqgn6yfmdZ6w7.NdMDeSYTs4JcDdySZ.yMdQ4qzGH3At.', '9876543210', 'avatar_ryan.jpg', true),
    ('Emily', 'Gonzalez', 28, 'emily.gonzalez@example.com',
     '$2a$10$UYXM0mgh1OLkf6r7Iq0kCe2KV/fZT/GB1SkbdddpjzXEHK8NOQomi', '7654321987', 'avatar_emily.jpg', true),
    ('Sam', 'Kim', 29, 'sam.kim@example.com',
     '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '1122334455', 'avatar_sam.jpg', true),
    ('Olivia', 'Park', 25, 'olivia.park@example.com',
     '$2a$10$zLoPtjUjaZAcOPjEuFunnOS13swef0FFxo06ujuyobopYxwD5F/s2', '9988776655', 'avatar_olivia.jpg', true),
    ('Daniel', 'Liu', 30, 'daniel.liu@example.com',
     '$2a$10$VGkwdmvXTqgn6yfmdZ6w7.NdMDeSYTs4JcDdySZ.yMdQ4qzGH3At.', '1234432156', 'avatar_daniel.jpg', true),
    ('Grace', 'Rios', 26, 'grace.rios@example.com',
     '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '9876543210', 'avatar_grace.jpg', true),
    ('Tom', 'Li', 27, 'tom.li@example.com',
     '$2a$10$zLoPtjUjaZAcOPjEuFunnOS13swef0FFxo06ujuyobopYxwD5F/s2', '7654321987', 'avatar_tom.jpg', true),
    ('Sarah', 'Wu', 29, 'sarah.wu@example.com',
     '$2a$10$VGkwdmvXTqgn6yfmdZ6w7.NdMDeSYTs4JcDdySZ.yMdQ4qzGH3At.', '1122334455', 'avatar_sarah.jpg', true);

INSERT INTO USER_AUTHORITY(user_id, authority_id)
VALUES
    ((SELECT id FROM USERS WHERE EMAIL = 'alice.johnson@example.com'),
     (SELECT id FROM AUTHORITIES WHERE ROLE = 'employee')),
    ((SELECT id FROM USERS WHERE EMAIL = 'bob.anderson@example.com'),
     (SELECT id FROM AUTHORITIES WHERE ROLE = 'employee')),
    ((SELECT id FROM USERS WHERE EMAIL = 'eva.garcia@example.com'),
     (SELECT id FROM AUTHORITIES WHERE ROLE = 'employee')),
    ((SELECT id FROM USERS WHERE EMAIL = 'david.brown@example.com'),
     (SELECT id FROM AUTHORITIES WHERE ROLE = 'employee')),
    ((SELECT id FROM USERS WHERE EMAIL = 'sophia.miller@example.com'),
     (SELECT id FROM AUTHORITIES WHERE ROLE = 'employee')),
    ((SELECT id FROM USERS WHERE EMAIL = 'max.wang@example.com'),
     (SELECT id FROM AUTHORITIES WHERE ROLE = 'employee')),
    ((SELECT id FROM USERS WHERE EMAIL = 'lily.chen@example.com'),
     (SELECT id FROM AUTHORITIES WHERE ROLE = 'employee')),
    ((SELECT id FROM USERS WHERE EMAIL = 'ryan.lee@example.com'),
     (SELECT id FROM AUTHORITIES WHERE ROLE = 'employee')),
    ((SELECT id FROM USERS WHERE EMAIL = 'emily.gonzalez@example.com'),
     (SELECT id FROM AUTHORITIES WHERE ROLE = 'employee')),
    ((SELECT id FROM USERS WHERE EMAIL = 'sam.kim@example.com'),
     (SELECT id FROM AUTHORITIES WHERE ROLE = 'employee')),
    ((SELECT id FROM USERS WHERE EMAIL = 'olivia.park@example.com'),
     (SELECT id FROM AUTHORITIES WHERE ROLE = 'employee')),
    ((SELECT id FROM USERS WHERE EMAIL = 'daniel.liu@example.com'),
     (SELECT id FROM AUTHORITIES WHERE ROLE = 'employee')),
    ((SELECT id FROM USERS WHERE EMAIL = 'grace.rios@example.com'),
     (SELECT id FROM AUTHORITIES WHERE ROLE = 'employee')),
    ((SELECT id FROM USERS WHERE EMAIL = 'tom.li@example.com'),
     (SELECT id FROM AUTHORITIES WHERE ROLE = 'employee')),
    ((SELECT id FROM USERS WHERE EMAIL = 'sarah.wu@example.com'),
     (SELECT id FROM AUTHORITIES WHERE ROLE = 'employee'));

INSERT INTO contact_types (type)
VALUES ('Telegram'),
       ('Instagram'),
       ('Facebook');

INSERT INTO categories (name)
VALUES ('Engineering'),
       ('Sales'),
       ('Human Resources'),
       ('Customer Support'),
       ('Operations'),
       ('Design'),
       ('Healthcare'),
       ('Education'),
       ('Legal'),
       ('Hospitality'),
       ('Construction'),
       ('Research');


-- Alice Johnson's Resume
INSERT INTO resumes (name, category_id, applicant_id, salary, is_active, created_date, update_date)
VALUES ('Alice Johnson Resume', (SELECT id FROM categories WHERE name = 'Software Development'),
        (SELECT id FROM USERS WHERE name = 'Alice' AND surname = 'Johnson'), 75000, 1, NOW(), NOW());

INSERT INTO contacts_info (infoValue, type_id, resume_id)
VALUES ('alice.johnson@example.com', (SELECT id FROM contact_types WHERE type = 'Email'),
        (SELECT id FROM resumes WHERE name = 'Alice Johnson Resume')),
       ('9876543210', (SELECT id FROM contact_types WHERE type = 'Phone'),
        (SELECT id FROM resumes WHERE name = 'Alice Johnson Resume')),
       ('https://www.linkedin.com/alicejohnson', (SELECT id FROM contact_types WHERE type = 'LinkedIn'),
        (SELECT id FROM resumes WHERE name = 'Alice Johnson Resume'));

INSERT INTO education_info (institution, program, resume_id, start_date, end_date, degree)
VALUES ('University of Example', 'Computer Science',
        (SELECT id FROM resumes WHERE name = 'Alice Johnson Resume'),
        '2010-09-01', '2014-06-01', 'Bachelor');

INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES ((SELECT id FROM resumes WHERE name = 'Alice Johnson Resume'), 5, 'Tech Solutions Ltd.', 'Software Developer',
        'Developed and maintained software applications');

-- Bob Anderson's Resume
INSERT INTO resumes (name, category_id, applicant_id, salary, is_active, created_date, update_date)
VALUES ('Bob Anderson Resume', (SELECT id FROM categories WHERE name = 'Finance'),
        (SELECT id FROM USERS WHERE name = 'Bob' AND surname = 'Anderson'), 85000, 1, NOW(), NOW());

INSERT INTO contacts_info (infoValue, type_id, resume_id)
VALUES ('bob.anderson@example.com', (SELECT id FROM contact_types WHERE type = 'Email'),
        (SELECT id FROM resumes WHERE name = 'Bob Anderson Resume')),
       ('7654321987', (SELECT id FROM contact_types WHERE type = 'Phone'),
        (SELECT id FROM resumes WHERE name = 'Bob Anderson Resume')),
       ('https://www.linkedin.com/bobanderson', (SELECT id FROM contact_types WHERE type = 'LinkedIn'),
        (SELECT id FROM resumes WHERE name = 'Bob Anderson Resume'));

INSERT INTO education_info (institution, program, resume_id, start_date, end_date, degree)
VALUES ('Business School', 'Finance and Accounting',
        (SELECT id FROM resumes WHERE name = 'Bob Anderson Resume'),
        '2008-09-01', '2012-06-01', 'Bachelor');

INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES ((SELECT id FROM resumes WHERE name = 'Bob Anderson Resume'), 8, 'Financial Services Inc.', 'Financial Analyst',
        'Performed financial analysis and reporting');

-- Eva Garcia's Resume
INSERT INTO resumes (name, category_id, applicant_id, salary, is_active, created_date, update_date)
VALUES ('Eva Garcia Resume', (SELECT id FROM categories WHERE name = 'Marketing'),
        (SELECT id FROM USERS WHERE name = 'Eva' AND surname = 'Garcia'), 70000, 1, NOW(), NOW());

INSERT INTO contacts_info (infoValue, type_id, resume_id)
VALUES ('eva.garcia@example.com', (SELECT id FROM contact_types WHERE type = 'Email'),
        (SELECT id FROM resumes WHERE name = 'Eva Garcia Resume')),
       ('1122334455', (SELECT id FROM contact_types WHERE type = 'Phone'),
        (SELECT id FROM resumes WHERE name = 'Eva Garcia Resume')),
       ('https://www.linkedin.com/evagarcia', (SELECT id FROM contact_types WHERE type = 'LinkedIn'),
        (SELECT id FROM resumes WHERE name = 'Eva Garcia Resume'));

INSERT INTO education_info (institution, program, resume_id, start_date, end_date, degree)
VALUES ('Marketing Academy', 'Digital Marketing',
        (SELECT id FROM resumes WHERE name = 'Eva Garcia Resume'),
        '2013-09-01', '2017-06-01', 'Bachelor');

INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES ((SELECT id FROM resumes WHERE name = 'Eva Garcia Resume'), 4, 'Online Retail Co.', 'Marketing Specialist',
        'Executed digital marketing campaigns and strategies');
-- David Brown's Resume
INSERT INTO resumes (name, category_id, applicant_id, salary, is_active, created_date, update_date)
VALUES ('David Brown Resume', (SELECT id FROM categories WHERE name = 'Engineering'),
        (SELECT id FROM USERS WHERE name = 'David' AND surname = 'Brown'), 80000, 1, NOW(), NOW());

INSERT INTO contacts_info (infoValue, type_id, resume_id)
VALUES ('david.brown@example.com', (SELECT id FROM contact_types WHERE type = 'Email'),
        (SELECT id FROM resumes WHERE name = 'David Brown Resume')),
       ('763547234', (SELECT id FROM contact_types WHERE type = 'Phone'),
        (SELECT id FROM resumes WHERE name = 'David Brown Resume')),
       ('https://www.linkedin.com/davidbrown', (SELECT id FROM contact_types WHERE type = 'LinkedIn'),
        (SELECT id FROM resumes WHERE name = 'David Brown Resume'));

INSERT INTO education_info (institution, program, resume_id, start_date, end_date, degree)
VALUES ('Engineering University', 'Mechanical Engineering',
        (SELECT id FROM resumes WHERE name = 'David Brown Resume'),
        '2010-09-01', '2014-06-01', 'Bachelor');

INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES ((SELECT id FROM resumes WHERE name = 'David Brown Resume'), 7, 'Tech Innovations Inc.', 'Mechanical Engineer',
        'Designed and developed mechanical systems');

-- Sophia Miller's Resume
INSERT INTO resumes (name, category_id, applicant_id, salary, is_active, created_date, update_date)
VALUES ('Sophia Miller Resume', (SELECT id FROM categories WHERE name = 'Design'),
        (SELECT id FROM USERS WHERE name = 'Sophia' AND surname = 'Miller'), 70000, 1, NOW(), NOW());

INSERT INTO contacts_info (infoValue, type_id, resume_id)
VALUES ('sophia.miller@example.com', (SELECT id FROM contact_types WHERE type = 'Email'),
        (SELECT id FROM resumes WHERE name = 'Sophia Miller Resume')),
       ('237644345', (SELECT id FROM contact_types WHERE type = 'Phone'),
        (SELECT id FROM resumes WHERE name = 'Sophia Miller Resume')),
       ('https://www.linkedin.com/sophiamiller', (SELECT id FROM contact_types WHERE type = 'LinkedIn'),
        (SELECT id FROM resumes WHERE name = 'Sophia Miller Resume'));

INSERT INTO education_info (institution, program, resume_id, start_date, end_date, degree)
VALUES ('Design Academy', 'Graphic Design',
        (SELECT id FROM resumes WHERE name = 'Sophia Miller Resume'),
        '2013-09-01', '2017-06-01', 'Bachelor');

INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES ((SELECT id FROM resumes WHERE name = 'Sophia Miller Resume'), 5, 'Creative Studio XYZ', 'Graphic Designer',
        'Developed visual concepts and designs for clients');

-- Max Wang's Resume
INSERT INTO resumes (name, category_id, applicant_id, salary, is_active, created_date, update_date)
VALUES ('Max Wang Resume', (SELECT id FROM categories WHERE name = 'Finance'),
        (SELECT id FROM USERS WHERE name = 'Max' AND surname = 'Wang'), 90000, 1, NOW(), NOW());

INSERT INTO contacts_info (infoValue, type_id, resume_id)
VALUES ('max.wang@example.com', (SELECT id FROM contact_types WHERE type = 'Email'),
        (SELECT id FROM resumes WHERE name = 'Max Wang Resume')),
       ('9988776655', (SELECT id FROM contact_types WHERE type = 'Phone'),
        (SELECT id FROM resumes WHERE name = 'Max Wang Resume')),
       ('https://www.linkedin.com/maxwang', (SELECT id FROM contact_types WHERE type = 'LinkedIn'),
        (SELECT id FROM resumes WHERE name = 'Max Wang Resume'));

INSERT INTO education_info (institution, program, resume_id, start_date, end_date, degree)
VALUES ('Finance School', 'Financial Management',
        (SELECT id FROM resumes WHERE name = 'Max Wang Resume'),
        '2009-09-01', '2013-06-01', 'Bachelor');

INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES ((SELECT id FROM resumes WHERE name = 'Max Wang Resume'), 8, 'Financial Services Corp.', 'Financial Advisor',
        'Provided financial advice and investment strategies');

-- Lily Chen's Resume
INSERT INTO resumes (name, category_id, applicant_id, salary, is_active, created_date, update_date)
VALUES ('Lily Chen Resume', (SELECT id FROM categories WHERE name = 'Marketing'),
        (SELECT id FROM USERS WHERE name = 'Lily' AND surname = 'Chen'), 70000, 1, NOW(), NOW());

INSERT INTO contacts_info (infoValue, type_id, resume_id)
VALUES ('lily.chen@example.com', (SELECT id FROM contact_types WHERE type = 'Email'),
        (SELECT id FROM resumes WHERE name = 'Lily Chen Resume')),
       ('1234432156', (SELECT id FROM contact_types WHERE type = 'Phone'),
        (SELECT id FROM resumes WHERE name = 'Lily Chen Resume')),
       ('https://www.linkedin.com/lilychen', (SELECT id FROM contact_types WHERE type = 'LinkedIn'),
        (SELECT id FROM resumes WHERE name = 'Lily Chen Resume'));

INSERT INTO education_info (institution, program, resume_id, start_date, end_date, degree)
VALUES ('Marketing School', 'Digital Marketing',
        (SELECT id FROM resumes WHERE name = 'Lily Chen Resume'),
        '2015-09-01', '2019-06-01', 'Bachelor');

INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES ((SELECT id FROM resumes WHERE name = 'Lily Chen Resume'), 3, 'Advertising Agency ABC', 'Marketing Coordinator',
        'Assisted in developing and implementing marketing campaigns');

-- Ryan Lee's Resume
INSERT INTO resumes (name, category_id, applicant_id, salary, is_active, created_date, update_date)
VALUES ('Ryan Lee Resume', (SELECT id FROM categories WHERE name = 'Finance'),
        (SELECT id FROM USERS WHERE name = 'Ryan' AND surname = 'Lee'), 90000, 1, NOW(), NOW());

INSERT INTO contacts_info (infoValue, type_id, resume_id)
VALUES ('ryan.lee@example.com', (SELECT id FROM contact_types WHERE type = 'Email'),
        (SELECT id FROM resumes WHERE name = 'Ryan Lee Resume')),
       ('9876543210', (SELECT id FROM contact_types WHERE type = 'Phone'),
        (SELECT id FROM resumes WHERE name = 'Ryan Lee Resume')),
       ('https://www.linkedin.com/ryanlee', (SELECT id FROM contact_types WHERE type = 'LinkedIn'),
        (SELECT id FROM resumes WHERE name = 'Ryan Lee Resume'));

INSERT INTO education_info (institution, program, resume_id, start_date, end_date, degree)
VALUES ('Finance Academy', 'Financial Management',
        (SELECT id FROM resumes WHERE name = 'Ryan Lee Resume'),
        '2010-09-01', '2014-06-01', 'Bachelor');

INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES ((SELECT id FROM resumes WHERE name = 'Ryan Lee Resume'), 8, 'Financial Consulting Firm', 'Financial Advisor',
        'Provided financial planning and investment advice to clients');

-- Emily Gonzalez's Resume
INSERT INTO resumes (name, category_id, applicant_id, salary, is_active, created_date, update_date)
VALUES ('Emily Gonzalez Resume', (SELECT id FROM categories WHERE name = 'Software Development'),
        (SELECT id FROM USERS WHERE name = 'Emily' AND surname = 'Gonzalez'), 80000, 1, NOW(), NOW());

INSERT INTO contacts_info (infoValue, type_id, resume_id)
VALUES ('emily.gonzalez@example.com', (SELECT id FROM contact_types WHERE type = 'Email'),
        (SELECT id FROM resumes WHERE name = 'Emily Gonzalez Resume')),
       ('7654321987', (SELECT id FROM contact_types WHERE type = 'Phone'),
        (SELECT id FROM resumes WHERE name = 'Emily Gonzalez Resume')),
       ('https://www.linkedin.com/emilygonzalez', (SELECT id FROM contact_types WHERE type = 'LinkedIn'),
        (SELECT id FROM resumes WHERE name = 'Emily Gonzalez Resume'));

INSERT INTO education_info (institution, program, resume_id, start_date, end_date, degree)
VALUES ('Computer Science Institute', 'Software Engineering',
        (SELECT id FROM resumes WHERE name = 'Emily Gonzalez Resume'),
        '2013-09-01', '2017-06-01', 'Bachelor');

INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES ((SELECT id FROM resumes WHERE name = 'Emily Gonzalez Resume'), 4, 'Tech Startup XYZ', 'Software Developer',
        'Developed and maintained software applications');

-- Sam Kim's Resume
INSERT INTO resumes (name, category_id, applicant_id, salary, is_active, created_date, update_date)
VALUES ('Sam Kim Resume', (SELECT id FROM categories WHERE name = 'Software Development'),
        (SELECT id FROM USERS WHERE name = 'Sam' AND surname = 'Kim'), 75000, 1, NOW(), NOW());

INSERT INTO contacts_info (infoValue, type_id, resume_id)
VALUES ('sam.kim@example.com', (SELECT id FROM contact_types WHERE type = 'Email'),
        (SELECT id FROM resumes WHERE name = 'Sam Kim Resume')),
       ('1122334455', (SELECT id FROM contact_types WHERE type = 'Phone'),
        (SELECT id FROM resumes WHERE name = 'Sam Kim Resume')),
       ('t.me/samkim', (SELECT id FROM contact_types WHERE type = 'Telegram'),
        (SELECT id FROM resumes WHERE name = 'Sam Kim Resume')),
       ('instagram.com/samkim', (SELECT id FROM contact_types WHERE type = 'Instagram'),
        (SELECT id FROM resumes WHERE name = 'Sam Kim Resume')),
       ('facebook.com/samkim', (SELECT id FROM contact_types WHERE type = 'Facebook'),
        (SELECT id FROM resumes WHERE name = 'Sam Kim Resume'));

INSERT INTO education_info (institution, program, resume_id, start_date, end_date, degree)
VALUES ('Computer Science University', 'Software Engineering',
        (SELECT id FROM resumes WHERE name = 'Sam Kim Resume'),
        '2012-09-01', '2016-06-01', 'Bachelor');

INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES ((SELECT id FROM resumes WHERE name = 'Sam Kim Resume'), 6, 'Tech Startup XYZ', 'Software Developer',
        'Developed and maintained software applications');

-- Olivia Park's Resume
INSERT INTO resumes (name, category_id, applicant_id, salary, is_active, created_date, update_date)
VALUES ('Olivia Park Resume', (SELECT id FROM categories WHERE name = 'Marketing'),
        (SELECT id FROM USERS WHERE name = 'Olivia' AND surname = 'Park'), 70000, 1, NOW(), NOW());

INSERT INTO contacts_info (infoValue, type_id, resume_id)
VALUES ('olivia.park@example.com', (SELECT id FROM contact_types WHERE type = 'Email'),
        (SELECT id FROM resumes WHERE name = 'Olivia Park Resume')),
       ('9988776655', (SELECT id FROM contact_types WHERE type = 'Phone'),
        (SELECT id FROM resumes WHERE name = 'Olivia Park Resume')),
       ('t.me/oliviapark', (SELECT id FROM contact_types WHERE type = 'Telegram'),
        (SELECT id FROM resumes WHERE name = 'Olivia Park Resume')),
       ('instagram.com/oliviapark', (SELECT id FROM contact_types WHERE type = 'Instagram'),
        (SELECT id FROM resumes WHERE name = 'Olivia Park Resume')),
       ('facebook.com/oliviapark', (SELECT id FROM contact_types WHERE type = 'Facebook'),
        (SELECT id FROM resumes WHERE name = 'Olivia Park Resume'));

INSERT INTO education_info (institution, program, resume_id, start_date, end_date, degree)
VALUES ('Marketing Institute', 'Digital Marketing',
        (SELECT id FROM resumes WHERE name = 'Olivia Park Resume'),
        '2014-09-01', '2018-06-01', 'Bachelor');

INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES ((SELECT id FROM resumes WHERE name = 'Olivia Park Resume'), 4, 'Marketing Agency ABC', 'Marketing Specialist',
        'Executed digital marketing campaigns and strategies');

-- Daniel Liu's Resume
INSERT INTO resumes (name, category_id, applicant_id, salary, is_active, created_date, update_date)
VALUES ('Daniel Liu Resume', (SELECT id FROM categories WHERE name = 'Finance'),
        (SELECT id FROM USERS WHERE name = 'Daniel' AND surname = 'Liu'), 85000, 1, NOW(), NOW());

INSERT INTO contacts_info (infoValue, type_id, resume_id)
VALUES ('daniel.liu@example.com', (SELECT id FROM contact_types WHERE type = 'Email'),
        (SELECT id FROM resumes WHERE name = 'Daniel Liu Resume')),
       ('1234432156', (SELECT id FROM contact_types WHERE type = 'Phone'),
        (SELECT id FROM resumes WHERE name = 'Daniel Liu Resume')),
       ('t.me/danielliu', (SELECT id FROM contact_types WHERE type = 'Telegram'),
        (SELECT id FROM resumes WHERE name = 'Daniel Liu Resume')),
       ('instagram.com/danielliu', (SELECT id FROM contact_types WHERE type = 'Instagram'),
        (SELECT id FROM resumes WHERE name = 'Daniel Liu Resume')),
       ('facebook.com/danielliu', (SELECT id FROM contact_types WHERE type = 'Facebook'),
        (SELECT id FROM resumes WHERE name = 'Daniel Liu Resume'));

INSERT INTO education_info (institution, program, resume_id, start_date, end_date, degree)
VALUES ('Finance School', 'Financial Management',
        (SELECT id FROM resumes WHERE name = 'Daniel Liu Resume'),
        '2010-09-01', '2014-06-01', 'Bachelor');

INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES ((SELECT id FROM resumes WHERE name = 'Daniel Liu Resume'), 8, 'Financial Services Corp.', 'Financial Analyst',
        'Performed financial analysis and reporting for investment decisions');

-- Sam Kim's Work Experience
INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES ((SELECT id FROM resumes WHERE name = 'Sam Kim Resume'), 6, 'Tech Startup XYZ', 'Software Developer',
        'Developed and maintained software applications'),
       ((SELECT id FROM resumes WHERE name = 'Sam Kim Resume'), 3, 'Software Co.', 'Junior Developer',
        'Assisted in coding and testing software modules');

-- Olivia Park's Work Experience
INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES ((SELECT id FROM resumes WHERE name = 'Olivia Park Resume'), 4, 'Marketing Agency ABC', 'Marketing Specialist',
        'Executed digital marketing campaigns and strategies'),
       ((SELECT id FROM resumes WHERE name = 'Olivia Park Resume'), 2, 'Advertising Firm XYZ', 'Marketing Intern',
        'Assisted in market research and campaign planning');

-- Daniel Liu's Work Experience
INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES ((SELECT id FROM resumes WHERE name = 'Daniel Liu Resume'), 8, 'Financial Services Corp.', 'Financial Analyst',
        'Performed financial analysis and reporting for investment decisions'),
       ((SELECT id FROM resumes WHERE name = 'Daniel Liu Resume'), 4, 'Investment Bank ABC', 'Financial Associate',
        'Assisted in structuring and executing financial transactions');

-- Sam Kim's Education
INSERT INTO education_info (institution, program, resume_id, start_date, end_date, degree)
VALUES ('Computer Science University', 'Software Engineering',
        (SELECT id FROM resumes WHERE name = 'Sam Kim Resume'),
        '2012-09-01', '2016-06-01', 'Bachelor'),
       ('Tech Institute', 'Advanced Programming',
        (SELECT id FROM resumes WHERE name = 'Sam Kim Resume'),
        '2010-09-01', '2012-06-01', 'Diploma');

-- Olivia Park's Education
INSERT INTO education_info (institution, program, resume_id, start_date, end_date, degree)
VALUES ('Marketing Institute', 'Digital Marketing',
        (SELECT id FROM resumes WHERE name = 'Olivia Park Resume'),
        '2014-09-01', '2018-06-01', 'Bachelor'),
       ('Business College', 'Marketing Management',
        (SELECT id FROM resumes WHERE name = 'Olivia Park Resume'),
        '2012-09-01', '2014-06-01', 'Associate');

-- Daniel Liu's Education
INSERT INTO education_info (institution, program, resume_id, start_date, end_date, degree)
VALUES ('Finance School', 'Financial Management',
        (SELECT id FROM resumes WHERE name = 'Daniel Liu Resume'),
        '2010-09-01', '2014-06-01', 'Bachelor'),
       ('Economics College', 'Financial Analysis',
        (SELECT id FROM resumes WHERE name = 'Daniel Liu Resume'),
        '2008-09-01', '2010-06-01', 'Associate');
