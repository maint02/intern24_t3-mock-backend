select e.id, e.username, e.image_url imageUrl, e.fullname, e.last_access lastAccess, e.created_date createdDate, e.email, e.phone_number phoneNumber,
    e.skype_account skypeAccount, e.user_type userType, e.address, e.university, e.graduated_year graduatedYear , e.is_leader isLeader, e.is_manager isManager, e.reason_reject reasonReject, e.is_approved isApproved,
    e.is_actived isActived, e.birthday,
    er.role_id roleId, r.name roleName, e.department_id departmentId, d.name departmentName, e.position_id positionId, p.name positionName,e.team_id teamId, t.name teamName
from employee e
join employee_role er on e.id = er.employee_id
join role r on er.employee_id = r.id
join position p on p.id = e.position_id
join team t on t.id = e.team_id
join department d on d.id = e.department_id
where 1 = 1