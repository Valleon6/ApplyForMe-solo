drop database if exists APPLY_FOR_ME;
CREATE DATABASE APPLY_FOR_ME;

CREATE TABLE IF NOT EXISTS APPLIER(
    id bigserial NOT NULL,

    user_id INT NOT NUll,
    professional_id INT NOT NUll,
    primary key (id)
);

CREATE TABLE IF NOT EXISTS PROFESSIONAL(
  id bigserial not null,
  available_for_interview BOOL NOT NULL DEFAULT 'f',

  user_id int not null,
  primary key (id)
);

CREATE TABLE PROFESSIONAL_ATTACHMENT(
    id bigserial NOT NULL,
    passport_link VARCHAR(400) NOT NULL,
    resume_link VARCHAR(400) NOT NULL,
    cover_letter_link VARCHAR(400) NOT NULL,
    created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

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

Create Table IF NOT EXISTS MEMBER(
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

CREATE TABLE IF NOT EXISTS JOB_SUBMISSION(
    id bigserial not null,
    job_title varchar(200),
    job_link varchar(300) not null,
    other_comment text not null,
    created_on timestamp not null,
    updated_on timestamp not null,

    professional_id int not null,
    applier_id int not null,
    primary key (id)
);

CREATE TABLE IF NOT EXISTS MEMBER_ROLE(
    id bigserial not null,
    title varchar(200) not null,

    user_id int not null,
    primary key (id)
);

ALTER TABLE APPLIER
    ADD CONSTRAINT applier_user_id
        FOREIGN KEY (user_id)
            REFERENCES MEMBER(id);

ALTER TABLE APPLIER
    ADD CONSTRAINT applier_professional_id
        FOREIGN KEY (professional_id)
            REFERENCES PROFESSIONAL (id);

ALTER TABLE PROFESSIONAL
    ADD CONSTRAINT professional_user_id
        FOREIGN KEY (user_id)
            REFERENCES MEMBER (id);

ALTER TABLE PROFESSIONAL_ATTACHMENT
    ADD CONSTRAINT attachment_professional_id
        FOREIGN KEY (professional_id)
            REFERENCES PROFESSIONAL (id);

ALTER TABLE PROFESSIONAL_METADATA
    ADD CONSTRAINT metadata_professional_id
        FOREIGN KEY (professional_id)
            REFERENCES PROFESSIONAL (id);

ALTER TABLE JOB_SUBMISSION
    ADD CONSTRAINT job_submission_professional_id
        FOREIGN KEY (professional_id)
            REFERENCES PROFESSIONAL (id);

ALTER TABLE JOB_SUBMISSION
    ADD CONSTRAINT job_submission_applier_id
        FOREIGN KEY (applier_id)
            REFERENCES APPLIER (id);

ALTER TABLE MEMBER_ROLE
    ADD CONSTRAINT member_role_user_id
        FOREIGN KEY (user_id)
            REFERENCES MEMBER (id);