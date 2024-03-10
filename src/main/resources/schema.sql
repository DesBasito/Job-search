create table if not exists users
(
    id           integer auto_increment not null primary key,
    name         text                   not null,
    surname      text                   not null,
    age          integer                not null,
    email        text                   not null,
    password     text                   not null,
    phone_number varchar(55),
    avatar       text,
    acc_type     varchar(45)            not null
);

create table if not exists contact_types
(
    id   int auto_increment not null primary key,
    type text               not null
);

create table if not exists categories
(
    id   integer AUTO_INCREMENT not null primary key,
    name text                   not null
);

create table if not exists vacancies
(
    id           integer auto_increment not null,
    name         text                   not null,
    description  text                   not null,
    category_id  integer                not null,
    salary       real                   not null,
    exp_from     integer                not null,
    exp_to       integer                not null,
    is_active    boolean                not null,
    author_id    integer,
    created_date timestamp              not null,
    update_date  timestamp              not null,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) references categories (id) on update cascade,
    FOREIGN KEY (author_id) references users (id) on update cascade
);

create table if not exists resumes
(
    id           integer auto_increment not null,
    name         text                   not null,
    category_id  integer                not null,
    applicant_id integer                not null,
    salary       real                   not null,
    is_active    boolean                not null,
    created_date timestamp              not null,
    update_date  timestamp              not null,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) references categories (id) on update cascade,
    FOREIGN KEY (applicant_id) references users (id) on update cascade
);

create table if not exists contacts_info
(
    id        integer auto_increment not null,
    infoValue text                   not null,
    type_id   integer                not null,
    resume_id integer                not null,
    PRIMARY KEY (id),
    FOREIGN KEY (type_id) references contact_types (id) on update cascade,
    FOREIGN KEY (resume_id) references resumes (id) on update cascade
);


create table if not exists responded_applicants
(
    id           integer auto_increment not null,
    vacancy_id   integer                not null,
    resume_id    integer                not null,
    confirmation boolean                not null,
    PRIMARY KEY (id),
    FOREIGN KEY (vacancy_id) references vacancies (id) on update cascade,
    FOREIGN KEY (resume_id) references resumes (id) on update cascade
);

create table if not exists education_info
(
    id          integer auto_increment not null,
    institution text                   not null,
    program     text                   not null,
    resume_id   int                    not null,
    start_date  date                   not null,
    end_date    date                   not null,
    degree      text                   not null,
    PRIMARY KEY (id),
    FOREIGN KEY (resume_id) references resumes (id) on update cascade);

create table if not exists work_experience_info
(
    id               integer auto_increment not null,
    resume_id        integer                not null,
    years            integer                not null,
    company_name     text                   not null,
    position         text                   not null,
    responsibilities text                   not null,
    PRIMARY KEY (id),
    FOREIGN KEY (resume_id) references resumes (id) on update cascade
);

create table if not exists messages
(
    id                   integer auto_increment not null,
    responded_applicants integer                not null,
    content              text                   not null,
    timestamp            timestamp              not null,
    PRIMARY KEY (id),
    FOREIGN KEY (responded_applicants) references users (id) on update cascade
);
