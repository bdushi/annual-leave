INSERT INTO user (username, last_name, first_name, password, email, address, phone, employment_date, enable)
    VALUES
    ('brunodushi', 'Bruno', 'Dushi', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'brunodushi@gmail.com', 'Tirane', '699897887', '2020-01-22T13:38:56.793276+02:00', true),
    ('supervisor', 'Supervisor', 'Supervisor', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'supervisor@supervisor.com', 'Tirane', '699897887', CURRENT_TIMESTAMP(), true),
    ('finance', 'Finance', 'Finance', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'finance@finance.com', 'Tirane', '699897887', CURRENT_TIMESTAMP(), true),
    ('admin', 'Admin', 'Admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin@admin.com', 'Tirane', '699897887', CURRENT_TIMESTAMP(), true);

INSERT INTO authority (authority, description)
    VALUES
    ('ADMIN', 'Administrator'),
    ('EMPLOYEE', 'Punonjes'),
    ('SUPERVISOR', 'Supervizor'),
    ('FINANCE', 'Financa');

INSERT INTO user_authorities (user_id, authorities_id)
    VALUES
    (1, 2),
    (2, 3),
    (3, 4),
    (4, 1);

INSERT INTO leave_types(types, description)
    VALUES
    ('DAYS_OFF', 'days off'),
    ('VACATION', 'vacations'),
    ('COMPENSATION', 'compensation');