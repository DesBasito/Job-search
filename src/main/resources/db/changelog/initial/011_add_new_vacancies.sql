--liquibase formatted sql
--changeset Abu:add_vacancies_data
INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_date)
VALUES
    ('Marketing Manager', 'Description for marketing manager vacancy',
     (SELECT id FROM categories WHERE name = 'Marketing' ), 60000, 5, 7, 1,
     (SELECT id FROM USERS WHERE email = 'peter.wilson@example.com' ), NOW(), NOW()),

    ('Sales Executive', 'Description for sales executive vacancy',
     (SELECT id FROM categories WHERE name = 'Sales' ), 65000, 4, 6, 1,
     (SELECT id FROM USERS WHERE email = 'sophie.roberts@example.com' ), NOW(), NOW()),

    ('Financial Analyst', 'Description for financial analyst vacancy',
     (SELECT id FROM categories WHERE name = 'Finance'  ), 70000, 3, 5, 1,
     (SELECT id FROM USERS WHERE email = 'david.harris@example.com'  ), NOW(), NOW()),

    ('HR Manager', 'Description for HR manager vacancy',
     (SELECT id FROM categories WHERE name = 'Human Resources'), 70000, 3, 5, 1,
     (SELECT id FROM USERS WHERE email = 'emma.allen@example.com'), NOW(), NOW()),

    ('Customer Support Specialist', 'Description for customer support specialist vacancy',
     (SELECT id FROM categories WHERE name = 'Customer Support'), 60000, 2, 4, 1,
     (SELECT id FROM USERS WHERE email = 'tom.carter@example.com'), NOW(), NOW()),

    ('Operations Manager', 'Description for operations manager vacancy',
     (SELECT id FROM categories WHERE name = 'Operations'), 75000, 5, 8, 1,
     (SELECT id FROM USERS WHERE email = 'olivia.king@example.com'), NOW(), NOW()),

    ('UX/UI Designer', 'Description for UX/UI designer vacancy',
     (SELECT id FROM categories WHERE name = 'Design'), 70000, 3, 6, 1,
     (SELECT id FROM USERS WHERE email = 'jack.scott@example.com'), NOW(), NOW()),

    ('Nurse', 'Description for nurse vacancy',
     (SELECT id FROM categories WHERE name = 'Healthcare'), 65000, 2, 4, 1,
     (SELECT id FROM USERS WHERE email = 'lucy.turner@example.com'), NOW(), NOW()),

    ('Math Teacher', 'Description for math teacher vacancy',
     (SELECT id FROM categories WHERE name = 'Education'), 60000, 3, 5, 1,
     (SELECT id FROM USERS WHERE email = 'ben.miller@example.com'), NOW(), NOW()),

    ('Legal Counsel', 'Description for legal counsel vacancy',
     (SELECT id FROM categories WHERE name = 'Legal'), 80000, 5, 7, 1,
     (SELECT id FROM USERS WHERE email = 'natalie.moore@example.com'), NOW(), NOW()),

    ('Hotel Manager', 'Description for hotel manager vacancy',
     (SELECT id FROM categories WHERE name = 'Hospitality'), 70000, 4, 6, 1,
     (SELECT id FROM USERS WHERE email = 'mark.clark@example.com'), NOW(), NOW()),

    ('Construction Engineer', 'Description for construction engineer vacancy',
     (SELECT id FROM categories WHERE name = 'Construction'), 75000, 5, 8, 1,
     (SELECT id FROM USERS WHERE email = 'laura.young@example.com'), NOW(), NOW()),

    ('Research Scientist', 'Description for research scientist vacancy',
     (SELECT id FROM categories WHERE name = 'Research'), 90000, 7, 10, 1,
     (SELECT id FROM USERS WHERE email = 'chris.hill@example.com'), NOW(), NOW()),

    ('Event Coordinator', 'Description for event coordinator vacancy',
     (SELECT id FROM categories WHERE name = 'Hospitality'), 60000, 3, 5, 1,
     (SELECT id FROM USERS WHERE email = 'rachel.wright@example.com'), NOW(), NOW()),

    ('Civil Rights Attorney', 'Description for civil rights attorney vacancy',
     (SELECT id FROM categories WHERE name = 'Legal'), 95000, 6, 8, 1,
     (SELECT id FROM USERS WHERE email = 'kevin.adams@example.com'), NOW(), NOW());
