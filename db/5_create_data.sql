
--DEMO DATA 

INSERT INTO "MOCKUP_SMARTOFFICE"."ROLE" (ID, NAME) VALUES (AUTO_INCRE_SEQ_ROLE.nextval, 'MEMBER');
INSERT INTO "MOCKUP_SMARTOFFICE"."ROLE" (ID, NAME) VALUES (AUTO_INCRE_SEQ_ROLE.nextval, 'LEADER');
INSERT INTO "MOCKUP_SMARTOFFICE"."ROLE" (ID, NAME) VALUES (AUTO_INCRE_SEQ_ROLE.nextval, 'MANAGER');
INSERT INTO "MOCKUP_SMARTOFFICE"."ROLE" (ID, NAME) VALUES (AUTO_INCRE_SEQ_ROLE.nextval, 'HR');
commit;

INSERT INTO "MOCKUP_SMARTOFFICE"."POSITION" (ID, NAME) VALUES (0, null);
INSERT INTO "MOCKUP_SMARTOFFICE"."POSITION" (ID, NAME) VALUES (AUTO_INCRE_SEQ_POSITION.nextval, 'Nhân Viên');
INSERT INTO "MOCKUP_SMARTOFFICE"."POSITION" (ID, NAME) VALUES (AUTO_INCRE_SEQ_POSITION.nextval, 'Trưởng nhóm');
INSERT INTO "MOCKUP_SMARTOFFICE"."POSITION" (ID, NAME) VALUES (AUTO_INCRE_SEQ_POSITION.nextval, 'Quản lý');
INSERT INTO "MOCKUP_SMARTOFFICE"."POSITION" (ID, NAME) VALUES (AUTO_INCRE_SEQ_POSITION.nextval, 'Nhân sự');
commit;

INSERT INTO "MOCKUP_SMARTOFFICE"."DEPARTMENT" (ID, NAME, LOCATION) VALUES (0, null, null);
INSERT INTO "MOCKUP_SMARTOFFICE"."DEPARTMENT" (ID, NAME, LOCATION) VALUES (AUTO_INCRE_SEQ_DEPARTMENT.nextval, 'Tester', 'Hà Nội');
INSERT INTO "MOCKUP_SMARTOFFICE"."DEPARTMENT" (ID, NAME, LOCATION) VALUES (AUTO_INCRE_SEQ_DEPARTMENT.nextval,  'Developer', 'Hà Nội');
INSERT INTO "MOCKUP_SMARTOFFICE"."DEPARTMENT" (ID, NAME, LOCATION) VALUES (AUTO_INCRE_SEQ_DEPARTMENT.nextval,  'Human Resources', 'Hà Nội');
commit;

INSERT INTO "MOCKUP_SMARTOFFICE"."STATUS_TYPE" (ID, NAME) VALUES (AUTO_INCRE_SEQ_STATUS_TYPE.nextval, 'issue_status');
INSERT INTO "MOCKUP_SMARTOFFICE"."STATUS_TYPE" (ID, NAME) VALUES (AUTO_INCRE_SEQ_STATUS_TYPE.nextval, 'project_status');
INSERT INTO "MOCKUP_SMARTOFFICE"."STATUS_TYPE" (ID, NAME) VALUES (AUTO_INCRE_SEQ_STATUS_TYPE.nextval, 'timesheet_status');
INSERT INTO "MOCKUP_SMARTOFFICE"."STATUS_TYPE" (ID, NAME) VALUES (AUTO_INCRE_SEQ_STATUS_TYPE.nextval, 'absent_status');
commit;

INSERT INTO "MOCKUP_SMARTOFFICE"."STATUS" (ID, NAME, TYPE_ID) VALUES (AUTO_INCRE_SEQ_STATUS.nextval, 'New', '1');
INSERT INTO "MOCKUP_SMARTOFFICE"."STATUS" (ID, NAME, TYPE_ID) VALUES (AUTO_INCRE_SEQ_STATUS.nextval, 'In Progress', '1');
INSERT INTO "MOCKUP_SMARTOFFICE"."STATUS" (ID, NAME, TYPE_ID) VALUES (AUTO_INCRE_SEQ_STATUS.nextval, 'Resolved', '1');
INSERT INTO "MOCKUP_SMARTOFFICE"."STATUS" (ID, NAME, TYPE_ID) VALUES (AUTO_INCRE_SEQ_STATUS.nextval, 'Reopen', '1');
INSERT INTO "MOCKUP_SMARTOFFICE"."STATUS" (ID, NAME, TYPE_ID) VALUES (AUTO_INCRE_SEQ_STATUS.nextval, 'Closed', '1');
INSERT INTO "MOCKUP_SMARTOFFICE"."STATUS" (ID, NAME, TYPE_ID) VALUES (AUTO_INCRE_SEQ_STATUS.nextval, 'Next Release', '1');
INSERT INTO "MOCKUP_SMARTOFFICE"."STATUS" (ID, NAME, TYPE_ID) VALUES (AUTO_INCRE_SEQ_STATUS.nextval, 'Build', '1');
INSERT INTO "MOCKUP_SMARTOFFICE"."STATUS" (ID, NAME, TYPE_ID) VALUES (AUTO_INCRE_SEQ_STATUS.nextval, 'Task', '3');
INSERT INTO "MOCKUP_SMARTOFFICE"."STATUS" (ID, NAME, TYPE_ID) VALUES (AUTO_INCRE_SEQ_STATUS.nextval, 'Request', '3');
INSERT INTO "MOCKUP_SMARTOFFICE"."STATUS" (ID, NAME, TYPE_ID) VALUES (AUTO_INCRE_SEQ_STATUS.nextval, 'Bug', '3');
INSERT INTO "MOCKUP_SMARTOFFICE"."STATUS" (ID, NAME, TYPE_ID) VALUES (AUTO_INCRE_SEQ_STATUS.nextval, 'Feature', '3');
INSERT INTO "MOCKUP_SMARTOFFICE"."STATUS" (ID, NAME, TYPE_ID) VALUES (AUTO_INCRE_SEQ_STATUS.nextval, 'New', '2');
INSERT INTO "MOCKUP_SMARTOFFICE"."STATUS" (ID, NAME, TYPE_ID) VALUES (AUTO_INCRE_SEQ_STATUS.nextval, 'In Progress', '2');
INSERT INTO "MOCKUP_SMARTOFFICE"."STATUS" (ID, NAME, TYPE_ID) VALUES (AUTO_INCRE_SEQ_STATUS.nextval, 'Closed', '2');
INSERT INTO "MOCKUP_SMARTOFFICE"."STATUS" (ID, NAME, TYPE_ID) VALUES (AUTO_INCRE_SEQ_STATUS.nextval, 'Reopen', '2');
INSERT INTO "MOCKUP_SMARTOFFICE"."STATUS" (ID, NAME, TYPE_ID) VALUES (AUTO_INCRE_SEQ_STATUS.nextval, 'Nghỉ', '4');
INSERT INTO "MOCKUP_SMARTOFFICE"."STATUS" (ID, NAME, TYPE_ID) VALUES (AUTO_INCRE_SEQ_STATUS.nextval, 'Vắng', '4');
INSERT INTO "MOCKUP_SMARTOFFICE"."STATUS" (ID, NAME, TYPE_ID) VALUES (AUTO_INCRE_SEQ_STATUS.nextval, 'Đi muộn', '4');
INSERT INTO "MOCKUP_SMARTOFFICE"."STATUS" (ID, NAME, TYPE_ID) VALUES (AUTO_INCRE_SEQ_STATUS.nextval, 'Về sớm', '4');
commit;

insert into TEAM (id,name) values (0, null);
insert into TEAM (id,name) values (AUTO_INCRE_SEQ_TEAM.nextval, 'ITSSOL');
insert into TEAM (id,name) VALUES (AUTO_INCRE_SEQ_TEAM.nextval, 'VIETIS');
insert into TEAM (id,name) VALUES (AUTO_INCRE_SEQ_TEAM.nextval, 'Mobifone');
commit;

INSERT INTO "MOCKUP_SMARTOFFICE"."PROJECT" (id, name,status_id ) VALUES (AUTO_INCRE_SEQ_PROJECT.nextval, 'Smart Shop', 12);
INSERT INTO PROJECT (id, name,status_id ) VALUES (AUTO_INCRE_SEQ_PROJECT.nextval, 'Smart Ofice', 14);
INSERT INTO PROJECT (id, name,status_id ) VALUES (AUTO_INCRE_SEQ_PROJECT.nextval, 'Salary Web', 13);
commit;

insert into team_project (team_id,project_id,start_date,handover_date ) values (3, 2,to_date('29/1/2019','dd/MM/yyyy'), to_date('30/11/2019','dd/MM/yyyy'));
insert into team_project(team_id,project_id,start_date,handover_date ) values (2, 2,to_date('19/1/2019','dd/MM/yyyy'), to_date('30/6/2019','dd/MM/yyyy'));
insert into team_project (team_id,project_id,start_date,handover_date )values (1, 3,to_date('7/2/2019','dd/MM/yyyy'), to_date('3/8/2019','dd/MM/yyyy'));
insert into team_project (team_id,project_id,start_date,handover_date )values (1, 1,to_date('8/2/2019','dd/MM/yyyy'), to_date('17/12/2019','dd/MM/yyyy'));
insert into team_project(team_id,project_id,start_date,handover_date ) values (3, 3,to_date('20/6/2019','dd/MM/yyyy'), to_date('30/11/2019','dd/MM/yyyy'));
commit;

--INSERT INTO "MOCKUP_SMARTOFFICE"."EMPLOYEE" (ID, USERNAME, 
--PASSWORD,image_url,last_access, FULLNAME,created_date, EMAIL, PHONE_NUMBER, SKYPE_ACCOUNT, USER_TYPE, ADDRESS, UNIVERSITY, GRADUATED_YEAR, IS_ACTIVED, POSITION_ID, TEAM_ID, DEPARTMENT_ID,IS_MANAGER, IS_LEADER, BIRTHDAY) 
--VALUES (AUTO_INCRE_SEQ_EMPLOYEE.nextval, 'dungdq96', '123456', null, to_date('29/12/2019', 'dd/MM/yyyy'),'Đỗ Quang Dũng',to_date('19/5/2019','dd/MM/yyyy'),
--'dungdq96@gmail.com', '0986198276', 'dungdq96', 'Nhân viên', 'Hà Nội', 'PTIT', '2019', '1', '2', '2', '2','0','1',to_date('30/12/1997','dd/MM/yyyy'));
--
--INSERT INTO "MOCKUP_SMARTOFFICE"."EMPLOYEE" (ID, USERNAME, 
--PASSWORD,image_url,last_access, FULLNAME,created_date, EMAIL, PHONE_NUMBER, SKYPE_ACCOUNT, USER_TYPE, ADDRESS, UNIVERSITY, GRADUATED_YEAR, IS_ACTIVED, POSITION_ID, TEAM_ID, DEPARTMENT_ID,IS_MANAGER, IS_LEADER, BIRTHDAY) 
--VALUES (AUTO_INCRE_SEQ_EMPLOYEE.nextval, 'tranvancuong97', '123456', null, to_date('31/12/2019', 'dd/MM/yyyy'),'Trần Văn Cường',to_date('9/5/2019','dd/MM/yyyy'),
--'tranvancuong97@gmail.com', '0976182712', 'trancuong97', 'Nhân viên', 'Hà Nội', 'Freelancer', null, '1', '2', '1', '3','0','1',to_date('12/12/1999','dd/MM/yyyy'));
--
--INSERT INTO "MOCKUP_SMARTOFFICE"."EMPLOYEE" (ID, USERNAME, 
--PASSWORD,image_url,last_access, FULLNAME,created_date, EMAIL, PHONE_NUMBER, SKYPE_ACCOUNT, USER_TYPE, ADDRESS, UNIVERSITY, GRADUATED_YEAR, IS_ACTIVED, POSITION_ID, TEAM_ID, DEPARTMENT_ID,IS_MANAGER, IS_LEADER, BIRTHDAY) 
--VALUES (AUTO_INCRE_SEQ_EMPLOYEE.nextval, 'dttthao21', '123456', null, to_date('10/12/2019', 'dd/MM/yyyy'),'Đặng Thị Thu Thảo',to_date('7/7/2019','dd/MM/yyyy'),
--'dttthao@yahoo.com', '0985261726', 'dtthao', 'Học việc', 'Thái Bình', 'HNU', '2018', '1', '4', '2', '3','0','0',to_date('1/6/1997','dd/MM/yyyy'));
--
--INSERT INTO "MOCKUP_SMARTOFFICE"."EMPLOYEE" (ID, USERNAME, 
--PASSWORD,image_url,last_access, FULLNAME,created_date, EMAIL, PHONE_NUMBER, SKYPE_ACCOUNT, USER_TYPE, ADDRESS, UNIVERSITY, GRADUATED_YEAR, IS_ACTIVED, POSITION_ID, TEAM_ID, DEPARTMENT_ID,IS_MANAGER, IS_LEADER, BIRTHDAY) 
--VALUES (AUTO_INCRE_SEQ_EMPLOYEE.nextval, 'dinhquang', '123456', null, to_date('17/12/2019', 'dd/MM/yyyy'),'Trịnh Đình Quang',to_date('19/10/2017','dd/MM/yyyy'),
--'dinhquang@itsol.com', '0981267271', 'dinhquang1', 'Nhân viên', 'Hà Nội', 'HUST', '2018', '1', '3', '2', '1','1','1',to_date('8/12/1993','dd/MM/yyyy'));
--
--INSERT INTO "MOCKUP_SMARTOFFICE"."EMPLOYEE" (ID, USERNAME, 
--PASSWORD,image_url,last_access, FULLNAME,created_date, EMAIL, PHONE_NUMBER, SKYPE_ACCOUNT, USER_TYPE, ADDRESS, UNIVERSITY, GRADUATED_YEAR, IS_ACTIVED, POSITION_ID, TEAM_ID, DEPARTMENT_ID,IS_MANAGER, IS_LEADER, BIRTHDAY) 
--VALUES (AUTO_INCRE_SEQ_EMPLOYEE.nextval, 'tonthang1', '123456', null, to_date('8/12/2019', 'dd/MM/yyyy'),'Tôn Đức Thắng',to_date('19/11/2017','dd/MM/yyyy'),
--'tonthang1@itsol.com', '036928127', 'tonthang32', 'Nhân viên', 'Hà Nội', 'PTIT', '2018', '1', '3', '1', '3','1','0',to_date('9/8/1999','dd/MM/yyyy'));
--
--INSERT INTO "MOCKUP_SMARTOFFICE"."EMPLOYEE" (ID, USERNAME, 
--PASSWORD,image_url,last_access, FULLNAME,created_date, EMAIL, PHONE_NUMBER, SKYPE_ACCOUNT, USER_TYPE, ADDRESS, UNIVERSITY, GRADUATED_YEAR, IS_ACTIVED, POSITION_ID, TEAM_ID, DEPARTMENT_ID,IS_MANAGER, IS_LEADER, BIRTHDAY) 
--VALUES (AUTO_INCRE_SEQ_EMPLOYEE.nextval, 'dangan9', '123456', null, to_date('10/10/2018', 'dd/MM/yyyy'),'Đặng Thế An',to_date('20/12/2018','dd/MM/yyyy'),
--'dangan@gmail.com', '037827192', 'dangan93', 'Nhân viên', 'Hà Nội', 'HUS', '2017', '1', '1', '2', '2','0','0',to_date('18/11/1993','dd/MM/yyyy'));
--
--INSERT INTO "MOCKUP_SMARTOFFICE"."EMPLOYEE" (ID, USERNAME, 
--PASSWORD,image_url,last_access, FULLNAME,created_date, EMAIL, PHONE_NUMBER, SKYPE_ACCOUNT, USER_TYPE, ADDRESS, UNIVERSITY, GRADUATED_YEAR, IS_ACTIVED, POSITION_ID, TEAM_ID, DEPARTMENT_ID,IS_MANAGER, IS_LEADER, BIRTHDAY) 
--VALUES (AUTO_INCRE_SEQ_EMPLOYEE.nextval, 'ngkhanhvan1', '654321', null, to_date('8/8/2019', 'dd/MM/yyyy'),'Nguyễn Khánh Vân',to_date('9/5/2019','dd/MM/yyyy'),
--'ngkhvan@yahoo.com', '0897672516', 'nkvan98', 'Thực tập', 'Hà Giang', 'VNU', '2018', '0', '4', '3', '1','0','0',to_date('19/1/1999','dd/MM/yyyy'));

-- Nhập tạm cho đỡ lỗi
--INSERT INTO "MOCKUP_SMARTOFFICE"."EMPLOYEE" (ID, USERNAME, 
--PASSWORD,image_url,last_access, FULLNAME,created_date, EMAIL, PHONE_NUMBER, SKYPE_ACCOUNT, USER_TYPE, ADDRESS, UNIVERSITY, GRADUATED_YEAR, IS_ACTIVED, POSITION_ID, TEAM_ID, DEPARTMENT_ID,IS_MANAGER, IS_LEADER, BIRTHDAY) 
--VALUES (AUTO_INCRE_SEQ_EMPLOYEE.nextval, 'daothang98', '654321', null, to_date('7/2/2019', 'dd/MM/yyyy'),'Đào Đức Thắng',to_date('19/1/2019','dd/MM/yyyy'),
--'daothang@outlook.com', '036928127', 'daothang8', 'Sinh viên', 'Hà Nội', 'HUST', '2019', '0', '1', '3', '1','0','0',to_date('30/5/1998','dd/MM/yyyy'));
--commit;
--
--
--INSERT INTO "MOCKUP_SMARTOFFICE"."EMPLOYEE_ROLE" (EMPLOYEE_ID, ROLE_ID) VALUES ('1', '1');
--INSERT INTO "MOCKUP_SMARTOFFICE"."EMPLOYEE_ROLE" (EMPLOYEE_ID, ROLE_ID) VALUES ('2', '1');
--INSERT INTO "MOCKUP_SMARTOFFICE"."EMPLOYEE_ROLE" (EMPLOYEE_ID, ROLE_ID) VALUES ('3', '1');
--INSERT INTO "MOCKUP_SMARTOFFICE"."EMPLOYEE_ROLE" (EMPLOYEE_ID, ROLE_ID) VALUES ('4', '1');
--INSERT INTO "MOCKUP_SMARTOFFICE"."EMPLOYEE_ROLE" (EMPLOYEE_ID, ROLE_ID) VALUES ('5', '1');
--INSERT INTO "MOCKUP_SMARTOFFICE"."EMPLOYEE_ROLE" (EMPLOYEE_ID, ROLE_ID) VALUES ('6', '1');
--INSERT INTO "MOCKUP_SMARTOFFICE"."EMPLOYEE_ROLE" (EMPLOYEE_ID, ROLE_ID) VALUES ('7', '1');
--INSERT INTO "MOCKUP_SMARTOFFICE"."EMPLOYEE_ROLE" (EMPLOYEE_ID, ROLE_ID) VALUES ('8', '1');
--INSERT INTO "MOCKUP_SMARTOFFICE"."EMPLOYEE_ROLE" (EMPLOYEE_ID, ROLE_ID) VALUES ('1', '2');
--INSERT INTO "MOCKUP_SMARTOFFICE"."EMPLOYEE_ROLE" (EMPLOYEE_ID, ROLE_ID) VALUES ('2', '2');
--INSERT INTO "MOCKUP_SMARTOFFICE"."EMPLOYEE_ROLE" (EMPLOYEE_ID, ROLE_ID) VALUES ('4', '2');
--INSERT INTO "MOCKUP_SMARTOFFICE"."EMPLOYEE_ROLE" (EMPLOYEE_ID, ROLE_ID) VALUES ('4', '3');
--INSERT INTO "MOCKUP_SMARTOFFICE"."EMPLOYEE_ROLE" (EMPLOYEE_ID, ROLE_ID) VALUES ('5', '3');
--INSERT INTO "MOCKUP_SMARTOFFICE"."EMPLOYEE_ROLE" (EMPLOYEE_ID, ROLE_ID) VALUES ('3', '4');
--INSERT INTO "MOCKUP_SMARTOFFICE"."EMPLOYEE_ROLE" (EMPLOYEE_ID, ROLE_ID) VALUES ('7', '4');
--commit;



--INSERT INTO "MOCKUP_SMARTOFFICE"."ISSUE" (ID, NAME, start_date, due_date, DONE_PERCENT, PRIORITY, REASON, DESCRIPTION, TYPE, PROJECT_ID, STATUS_ID) 
--VALUES (AUTO_INCRE_SEQ_ISSUE.nextval, 'Không tự động chuyển trang ',to_date('19/6/2019','dd/MM/yyyy'),'2.5', '80', 'Trung bình', 'Chưa tìm ra', 'Khi bấm vào số trang ở thanh chuyển trang thì không có hành động nào xảy ra', 'Bug', '2', '2');
--
--INSERT INTO "MOCKUP_SMARTOFFICE"."ISSUE" (ID, NAME, start_date, due_date, DONE_PERCENT, PRIORITY, REASON, DESCRIPTION, TYPE, PROJECT_ID, STATUS_ID) 
--VALUES (AUTO_INCRE_SEQ_ISSUE.nextval, 'Dịch sai từ tiếng Anh sang tiếng Việt ',to_date('16/10/2019','dd/MM/yyyy'),'0.5', '90', 'Dễ', 'Do chị Google lỗi', 'Issue được dịch thành "giấy ăn"', 'Bug', '1', '3');
--
--INSERT INTO "MOCKUP_SMARTOFFICE"."ISSUE" (ID, NAME, start_date, due_date, DONE_PERCENT, PRIORITY, REASON, DESCRIPTION, TYPE, PROJECT_ID, STATUS_ID) 
--VALUES (AUTO_INCRE_SEQ_ISSUE.nextval, 'Không nhận được thông báo khi đăng ký thành công tài khoản ',to_date('10/7/2019','dd/MM/yyyy'),'3.0', '80', 'Khó', 'Chưa tìm ra', 'Khi đăng ký thành công tài khoản phải có thông báo đến email đã đăng ký', 'Bug', '2', '3');
--commit;

--insert into issue_history(id, update_date,comments, issue_change, task_id, update_person_id) 
--values (AUTO_INCRE_SEQ_ISSUE_HIS.nextval, to_date('26/12/2019','dd/MM/yyyy'), null, 'done_percent:80-100; status:In Progress-Done; ')
