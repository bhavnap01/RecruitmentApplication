drop database recruitmentDB;
create database recruitmentDB;
use recruitmentDB;
commit;
create table employee_role_master
(
	role_id int primary key auto_increment,
    designation varchar(30) check (length(designation) > 0)
);
commit;

create table project_master
(
	project_id int,
    name varchar(20) not null,
    total_budget double not null default 0,
    primary key auto_increment(project_id),
    check (length(name) > 0),
    check (total_budget > 0)
);
commit;
create table address_master
(
	address_id int,
    address_line_1 varchar(50) not null,
    address_line_2 varchar(50) not null,
    city varchar(50) not null,
    state varchar(30) not null,
    country varchar(30) not null,
    primary key auto_increment(address_id),
	check (length(address_line_1) > 0),
    check (length(address_line_2) > 0),
    check (length(city) > 0),
    check (length(state) > 0),
    check (length(country) > 0)
);
commit;

create table employee_master
(
	employee_id int,
    first_name varchar(20) not null,
    last_name varchar(20) not null,
	address_id int,
	project_id int,
    role_id int,
    email varchar(30) not null,
    password varchar(30) not null,
    active int not null,
    salary double not null,
    primary key auto_increment(employee_id),
    foreign key (address_id) references address_master(address_id),
    foreign key (project_id) references project_master(project_id),
    foreign key (role_id) references employee_role_master(role_id),
    check (length(first_name) > 0),
    check (length(last_name) > 0),
    check (salary > 0)
);
commit;

create table job_request_master
(
	job_request_id int,
    number_of_resources_required int not null,
    project_id int,
    employee_id int,
    status varchar(20) not null,
    primary key auto_increment(job_request_id),
    foreign key (project_id) references project_master(project_id),
    foreign key (employee_id) references employee_master(employee_id),
    check (number_of_resources_required > 0)
);

create table candidate_master
(
	candidate_id int,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    job_request_id int,
    primary_skill varchar(30),
    secondary_skill varchar(30),
    highest_qualification varchar(30),
    resume blob,
    photo blob,
    status varchar(20),
    primary key auto_increment(candidate_id),
    foreign key (job_request_id) references job_request_master(job_request_id),
    check (length(first_name) > 0),
    check (length(last_name) > 0)    
);

create table assessment_master
(
	assessment_id int,
    job_request_id int,
    candidate_id int,
    aptitude_test_score int not null,
    communication_test_score int not null,
    group_discussion_score int not null,
    technical_test_score int not null,
    overall_score int not null,
    status varchar(20) not null,
    primary key auto_increment(assessment_id),
    foreign key (job_request_id) references job_request_master(job_request_id),
    foreign key (candidate_id) references candidate_master(candidate_id),
    check ((aptitude_test_score) >= 0),
    check ((communication_test_score) >= 0),
    check ((group_discussion_score) >= 0),
    check ((technical_test_score) >= 0),
    check ((overall_score) >= 0)
);

commit;
insert into employee_role_master values(0,'Developer');

select * from employee_role_master;
