INSERT INTO user (username, first_name, last_name, password, email, address, phone, employment_date, enable)
    VALUES
    ('brunodushi', 'Bruno', 'Dushi', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'brunodushi@gmail.com', 'Tirane', '699897887', '2020-01-22T13:38:56.793276+02:00', true),
    ('jonadushi', 'Jona', 'Dushi', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'jonadushi@gmail.com', 'Tirane', '699897887', '2020-01-22T13:38:56.793276+02:00', true),
    ('supervisor', 'Supervisor', 'Supervisor', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'supervisor@supervisor.com', 'Tirane', '699897887', CURRENT_TIMESTAMP(), true),
    ('finance', 'Finance', 'Finance', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'finance@finance.com', 'Tirane', '699897887', CURRENT_TIMESTAMP(), true),
    ('admin', 'Admin', 'Admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin@admin.com', 'Tirane', '699897887', CURRENT_TIMESTAMP(), true);

--INSERT INTO authority (authority, description)
--    VALUES
--    ('ADMIN', 'Administrator'),
--    ('EMPLOYEE', 'Punonjes'),
--    ('SUPERVISOR', 'Supervizor'),
--    ('FINANCE', 'Financa');

INSERT INTO authority (authority, description)
    VALUES
    ('0', 'Administrator'),
    ('1', 'Employee'),
    ('2', 'Supervisor'),
    ('3', 'Finance');

INSERT INTO user_authorities (user_id, authorities_id)
    VALUES
    (1, 2),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 1);

--CREATE TYPE leave_type AS ENUM (
--    'DAYS_OFF',
--    'VACATION',
--    'COMPENSATION'
--)

INSERT INTO leave_types(types, description)
    VALUES
    ('0', 'days off'),
    ('1', 'vacations'),
    ('2', 'compensation');

INSERT INTO leave(comment, create_date, description, end_date, leave_types_id, requested_by_id, start_date)
    VALUES
    ('Comment Debug', CURRENT_TIMESTAMP(), 'Description Debug', '2020-10-30T11:38:56.793276Z', 1, 1, '2020-10-22T11:38:56.793276Z'),
    ('Pushimet Dimerore', CURRENT_TIMESTAMP(), 'Kerkes Per Leve Vjetore', '2020-11-30T11:38:56.793276Z', 1, 1, '2020-11-22T11:38:56.793276Z'),
    ('Pushimet Dimerore', CURRENT_TIMESTAMP(), 'Kerkes Per Leve Vjetore', '2020-11-30T11:38:56.793276Z', 1, 1, '2020-11-22T11:38:56.793276Z'),
    ('Pushimet Dimerore', CURRENT_TIMESTAMP(), 'Kerkes Per Leve Vjetore', '2020-11-30T11:38:56.793276Z', 1, 1, '2020-11-22T11:38:56.793276Z'),
    ('Pushimet Dimerore', CURRENT_TIMESTAMP(), 'Kerkes Per Leve Vjetore', '2020-11-30T11:38:56.793276Z', 1, 1, '2020-11-22T11:38:56.793276Z'),
    ('Pushimet Dimerore', CURRENT_TIMESTAMP(), 'Kerkes Per Leve Vjetore', '2020-11-30T11:38:56.793276Z', 1, 1, '2020-11-22T11:38:56.793276Z'),
    ('Pushimet Dimerore', CURRENT_TIMESTAMP(), 'Kerkes Per Leve Vjetore', '2020-11-30T11:38:56.793276Z', 1, 1, '2020-11-22T11:38:56.793276Z'),
    ('Pushimet Dimerore', CURRENT_TIMESTAMP(), 'Kerkes Per Leve Vjetore', '2020-11-30T11:38:56.793276Z', 1, 1, '2020-11-22T11:38:56.793276Z'),
    ('Pushimet Dimerore', CURRENT_TIMESTAMP(), 'Kerkes Per Leve Vjetore', '2020-11-30T11:38:56.793276Z', 1, 1, '2020-11-22T11:38:56.793276Z'),
    ('Pushimet Dimerore', CURRENT_TIMESTAMP(), 'Kerkes Per Leve Vjetore', '2020-11-30T11:38:56.793276Z', 1, 1, '2020-11-22T11:38:56.793276Z'),
    ('Pushimet Dimerore', CURRENT_TIMESTAMP(), 'Kerkes Per Leve Vjetore', '2020-11-30T11:38:56.793276Z', 1, 1, '2020-11-22T11:38:56.793276Z'),
    ('Pushimet Dimerore', CURRENT_TIMESTAMP(), 'Kerkes Per Leve Vjetore', '2020-11-30T11:38:56.793276Z', 1, 1, '2020-11-22T11:38:56.793276Z'),
    ('Pushimet Dimerore', CURRENT_TIMESTAMP(), 'Kerkes Per Leve Vjetore', '2020-11-30T11:38:56.793276Z', 1, 1, '2020-11-22T11:38:56.793276Z'),
    ('Pushimet Dimerore', CURRENT_TIMESTAMP(), 'Kerkes Per Leve Vjetore', '2020-11-30T11:38:56.793276Z', 1, 1, '2020-11-22T11:38:56.793276Z'),
    ('Pushimet Dimerore', CURRENT_TIMESTAMP(), 'Kerkes Per Leve Vjetore', '2020-11-30T11:38:56.793276Z', 1, 1, '2020-11-22T11:38:56.793276Z'),
    ('Pushimet Dimerore', CURRENT_TIMESTAMP(), 'Kerkes Per Leve Vjetore', '2020-11-30T11:38:56.793276Z', 1, 1, '2020-11-22T11:38:56.793276Z'),
    ('Pushimet Dimerore', CURRENT_TIMESTAMP(), 'Kerkes Per Leve Vjetore', '2020-11-30T11:38:56.793276Z', 1, 1, '2020-11-22T11:38:56.793276Z'),
    ('Pushimet Dimerore', CURRENT_TIMESTAMP(), 'Kerkes Per Leve Vjetore', '2020-11-30T11:38:56.793276Z', 1, 1, '2020-11-22T11:38:56.793276Z'),
    ('Pushimet Dimerore', CURRENT_TIMESTAMP(), 'Kerkes Per Leve Vjetore', '2020-11-30T11:38:56.793276Z', 1, 1, '2020-11-22T11:38:56.793276Z'),
    ('Pushimet Dimerore', CURRENT_TIMESTAMP(), 'Kerkes Per Leve Vjetore', '2020-11-30T11:38:56.793276Z', 1, 1, '2020-11-22T11:38:56.793276Z'),
    ('Pushimet Dimerore', CURRENT_TIMESTAMP(), 'Kerkes Per Leve Vjetore', '2020-11-30T11:38:56.793276Z', 1, 1, '2020-11-22T11:38:56.793276Z'),
    ('Pushimet Dimerore', CURRENT_TIMESTAMP(), 'Kerkes Per Leve Vjetore', '2020-11-30T11:38:56.793276Z', 1, 1, '2020-11-22T11:38:56.793276Z'),
    ('Pushimet Dimerore', CURRENT_TIMESTAMP(), 'Kerkes Per Leve Vjetore', '2020-11-30T11:38:56.793276Z', 1, 1, '2020-11-22T11:38:56.793276Z'),
    ('Pushimet Dimerore', CURRENT_TIMESTAMP(), 'Kerkes Per Leve Vjetore', '2020-11-30T11:38:56.793276Z', 1, 1, '2020-11-22T11:38:56.793276Z');

INSERT INTO approved(approved_date, comment, approved, approved_by_id)
    VALUES
    ('2020-11-26T11:38:56.793276Z' , 'Chack Project Schedule', false, 3),
    ('2020-11-28T11:38:56.793276Z' , 'Not Approced', true, 3),
    (CURRENT_TIMESTAMP() , 'Approced', true, 3);

INSERT INTO leave_approved(leave_id, approved_id)
    VALUES
    (1, 1),
    (1, 2),
    (1, 3);
