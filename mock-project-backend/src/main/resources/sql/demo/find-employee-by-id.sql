select e.id, e.username, e.image_url, e.password, e.fullname, e.last_access, e.created_date, e.email, e.phone_number,
e.skype_account, e.user_type, e.address, e.university, e.graduated_year, e.is_leader, e.is_manager, e.reason_reject, e.is_approved,
    e.is_actived, e.position_id, e.team_id, e.department_id, e.birthday, r.name, d.name,t.name
from employee e
join employee_role er on e.id = er.employee_id
join role r on er.employee_id = r.id
join position p on p.id = e.position_id
join team t on t.id = e.team_id
join department d on d.id = e.department_id
where 1 = 1
