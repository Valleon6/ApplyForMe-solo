drop database if exists APPLY_FOR_ME;
CREATE DATABASE APPLY_FOR_ME;

CREATE TABLE IF NOT EXISTS APPLIER(
    id bigserial NOT NULL,

    user_id INT NOT NUll,
    professional_id INT NOT NUll,
    primary key (id)
);

CREATE TABLE PROFESSIONAL(
    id bigserial NOT NULL,
    passport_link VARCHAR(400) NOT NULL,
    resume_link VARCHAR(400) NOT NULL,
    cover_letter_link VARCHAR(400) NOT NULL,
    created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP NOT NULL DEFAULT CURRENT_DATE,
    professional_id INT NOT NULL,

    PRIMARY KEY (id)
);


CREATE TABLE PROFESSIONAL_METADATA(
    id bigserial not null,
    salary_range varchar(30) not null,
    country_of_residence varchar(150) not null,
    nationality varchar(150) not null,
    preferred_job_title varchar(150) not null,
    job_seniority varchar(150) not null,
    desired_job_title varchar(150) not null,
    industry varchar(150) not null,
    years_of_experience varchar(50) not null,
    linkedin_link varchar(300) null,
    other_link_1 varchar(300),
    other_link_2 varchar(300) null,
    hobbies varchar(300) not null,
    other_comment text null,
    other_skills text null,
    created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_on timestamp not null default current_timestamp,

    professional_id INT not null,
    primary key (id)


);

Create Table MEMBER(
    ID BIGSERIAL NOT NULL,
    FIRST_NAME VARCHAR(40) NOT NULL,
    LAST_NAME VARCHAR(40) NOT NULL,
    NATIONALITY VARCHAR(150) NOT NULL,
    DATE_OF_BIRTH DATE NOT NULL  DEFAULT CURRENT_DATE,
    JOB_TITLE VARCHAR(150) NOT NULL,
    EMAIL_ADDRESS VARCHAR(150) NOT NULL,
    PASSWORD TEXT NOT NULL,
    ACTIVE BOOL NOT NULL DEFAULT 't',
    created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATED_ON TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ID)

);