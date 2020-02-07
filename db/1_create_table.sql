--CREATE TABLE

CREATE TABLE role (
    id     NUMBER(10) PRIMARY KEY,
    name   VARCHAR2(255)
);

CREATE TABLE employee_role (
    employee_id   NUMBER(10) NOT NULL,
    role_id       NUMBER(10) NOT NULL,
    PRIMARY KEY ( employee_id,
                  role_id )
);

CREATE TABLE department (
    id           NUMBER(10) PRIMARY KEY,
    name         VARCHAR2(255),
    location     VARCHAR2(255)
);

CREATE TABLE status_type (
    id     NUMBER(10) PRIMARY KEY,
    name   VARCHAR2(255)
);

CREATE TABLE status (
    id        NUMBER(10) PRIMARY KEY,
    name      VARCHAR2(255),
    type_id   NUMBER(10) NOT NULL
);

CREATE TABLE position (
    id     NUMBER(10) PRIMARY KEY,
    name   VARCHAR2(255)
);

CREATE TABLE absent (
    id                  NUMBER(10) PRIMARY KEY,
    person_absent_id    NUMBER(10),
    person_approve_id   NUMBER(10),
    number_day          NUMBER(5) ,
    reason              VARCHAR2(1000),
    status_id           NUMBER(10) NOT NULL
);

CREATE TABLE project (
    id          NUMBER(10) PRIMARY KEY,
    name        VARCHAR2(255),
    status_id   NUMBER(10) NOT NULL
);

CREATE TABLE team_project (
    team_id         NUMBER(10) NOT NULL,
    project_id      NUMBER(10) NOT NULL,
    start_date      DATE,
    handover_date   DATE,
    PRIMARY KEY ( team_id,
                  project_id )
);

CREATE TABLE team (
    id          NUMBER(10) PRIMARY KEY,
    name        varchar2(200)
);

CREATE TABLE employee_issue (
	employee_id   NUMBER(10) NOT NULL,
    issue_id       NUMBER(10) NOT NULL,
    spent_time    FLOAT(10),
    note          VARCHAR2(1000),
    status_id     NUMBER(10) NOT NULL,
	created_date  DATE,
	employee_assigned_id NUMBER(10) NOT NULL,
    PRIMARY KEY ( employee_id,
                  issue_id )  
);

CREATE TABLE issue (
    id             NUMBER(10) PRIMARY KEY,
    name           VARCHAR2(255) NOT NULL,
    start_date     DATE NOT NULL,
    due_date       NUMBER(10,2) ,
    done_percent   NUMBER(10) ,
    priority       VARCHAR2(500),
    reason         VARCHAR2(1000),
    description    VARCHAR2(500),
    type           VARCHAR2(255),
    project_id     NUMBER(10) NOT NULL,
    status_id      NUMBER(10) NOT NULL,
    employee_reported_id number(10) not null
);

CREATE TABLE employee (
    id               NUMBER(10) NOT NULL PRIMARY KEY,
    username         VARCHAR2(255) NOT NULL,
    password         VARCHAR2(255) NOT NULL,
    image_url        VARCHAR2(255),
    last_access      DATE,
    fullname         VARCHAR2(255),
    created_date     DATE,
    email            VARCHAR2(255) NOT NULL UNIQUE,
    phone_number     VARCHAR2(255),
    skype_account    VARCHAR2(255)unique,
    user_type        VARCHAR2(255) ,
    address          VARCHAR2(255),
    university      VARCHAR2(255),
    graduated_year   NUMBER(5),
    is_leader        NUMBER(2),
    is_manager       number(2),
    reason_reject    varchar2(500),
    is_approved      number(2),
    is_actived       NUMBER(2),
    position_id      NUMBER(10) NOT NULL,
    team_id          NUMBER(10) NOT NULL,
    department_id    NUMBER(10) NOT NULL,
	birthday 		 DATE 
);

CREATE TABLE issue_history (
    id                 NUMBER(10) PRIMARY KEY,
    update_date        DATE NOT NULL,
    comments            VARCHAR2(255),
    issue_change        VARCHAR2(4000),
    issue_id            NUMBER(10) NOT NULL,
    update_person_id   NUMBER(10) 
);

--ADD CONSTRAINT

ALTER TABLE employee_role ADD CONSTRAINT employee_role_role_fk FOREIGN KEY (role_id) REFERENCES role (id);
ALTER TABLE employee ADD CONSTRAINT employee_position_fk FOREIGN KEY (position_id) REFERENCES position (id);
ALTER TABLE employee_issue ADD CONSTRAINT employee_issue_employee_fk FOREIGN KEY (employee_id) REFERENCES employee (id);
ALTER TABLE status ADD CONSTRAINT status_status_type_fk FOREIGN KEY (type_id) REFERENCES status_type (id);
ALTER TABLE employee_issue ADD CONSTRAINT employee_issue_issue_fk FOREIGN KEY (issue_id) REFERENCES issue (id);
ALTER TABLE issue ADD CONSTRAINT issue_project_fk FOREIGN KEY (project_id) REFERENCES project (id);
ALTER TABLE issue_history ADD CONSTRAINT issue_history_issue_fk FOREIGN KEY (issue_id) REFERENCES issue (id);
ALTER TABLE issue ADD CONSTRAINT issue_status_fk FOREIGN KEY (status_id) REFERENCES status (id);
ALTER TABLE issue ADD CONSTRAINT issue_employee_fk FOREIGN KEY (person_created_id) REFERENCES employee (id);

ALTER TABLE project ADD CONSTRAINT project_status_fk FOREIGN KEY (status_id) REFERENCES status (id);
ALTER TABLE issue_history ADD CONSTRAINT issue_history_employee_fk FOREIGN KEY (update_person_id) REFERENCES employee (id);
ALTER TABLE team_project ADD CONSTRAINT team_project_team_fk FOREIGN KEY (team_id) REFERENCES team (id);
ALTER TABLE team_project ADD CONSTRAINT team_project_project_fk FOREIGN KEY (project_id) REFERENCES project (id);
ALTER TABLE absent ADD CONSTRAINT absent_employee_fk FOREIGN KEY (person_absent_id) REFERENCES employee (id);
ALTER TABLE absent ADD CONSTRAINT absent_employee1_fk FOREIGN KEY (person_approve_id) REFERENCES employee (id);
ALTER TABLE absent ADD CONSTRAINT absent_status_fk FOREIGN KEY (status_id) REFERENCES status (id);
ALTER TABLE employee_role ADD CONSTRAINT employee_role_employee_fk FOREIGN KEY (employee_id) REFERENCES employee (id);
ALTER TABLE employee ADD CONSTRAINT employee_department_fk FOREIGN KEY (department_id) REFERENCES department (id);
ALTER TABLE employee_issue ADD CONSTRAINT employee_issue_status_fk FOREIGN KEY (status_id) REFERENCES status (id);
ALTER TABLE employee ADD CONSTRAINT employee_team_fk FOREIGN KEY (team_id) REFERENCES team (id);



