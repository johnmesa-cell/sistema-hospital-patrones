USE hospitaldb;

-- =========================
-- 1. Usuarios de prueba
-- =========================
-- IMPORTANTE: aquí se asumen contraseñas ya hasheadas (reemplaza HASH_xxx
-- por el valor real generado con SeguridadUtil.hashPassword en tu app Java).

INSERT INTO usuario (id_usuario, nombre, email, tipo, contrasena) VALUES
('med001', 'Dra. Ana López',    'ana.lopez@hospital.com',    'medico',        '15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225'),
('med002', 'Dr. Carlos Pérez',  'carlos.perez@hospital.com', 'medico',        '15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225'),
('pac001', 'Juan Gómez',        'juan.gomez@correo.com',     'paciente',      '15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225'),
('pac002', 'María Rodríguez',   'maria.rodriguez@correo.com','paciente',      '15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225'),
('admin1', 'Administrador SGCI','admin@hospital.com',        'administrador', '15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225');

-- =========================
-- 2. Pacientes de prueba
-- =========================
INSERT INTO paciente (id_paciente, id_usuario, fecha_nacimiento) VALUES
('P001', 'pac001', '1985-03-15'),
('P002', 'pac002', '1992-07-28');

-- =========================
-- 3. Diagnósticos de prueba
-- =========================
INSERT INTO diagnostico (
    id_diagnostico,
    id_paciente,
    descripcion,
    codigo_cie10,
    descripcion_tecnica,
    explicacion_paciente,
    estado
) VALUES
('D001', 'P001',
 'Hipertensión arterial esencial (primaria)',
 'I10',
 'Presión arterial sistólica y/o diastólica elevada de forma sostenida, sin causa secundaria identificable.',
 'Tiene la presión alta de forma crónica, se controlará con medicamentos y cambios en el estilo de vida.',
 'activo'),

('D002', 'P001',
 'Diabetes mellitus tipo 2 controlada',
 'E11.9',
 'Trastorno metabólico crónico con hiperglucemia por resistencia a la insulina y secreción insuficiente.',
 'Tiene azúcar alta en la sangre, debe seguir la dieta y los medicamentos para mantenerla controlada.',
 'activo'),

('D003', 'P002',
 'Gastritis aguda',
 'K29.0',
 'Inflamación aguda de la mucosa gástrica, probablemente de origen infeccioso o por fármacos.',
 'Inflamación del estómago que causa dolor y ardor; se dará tratamiento para aliviar los síntomas.',
 'resuelto');

-- =========================
-- 4. Tratamientos de prueba
-- =========================
INSERT INTO tratamiento (
    id_tratamiento,
    id_diagnostico,
    descripcion,
    fecha_inicio,
    fecha_fin,
    estado
) VALUES
('T001', 'D001',
 'Tratamiento antihipertensivo con IECA + recomendaciones de dieta baja en sal y ejercicio aeróbico 30 min/día.',
 '2025-01-10', NULL, 'activo'),

('T002', 'D002',
 'Metformina 850 mg cada 12 horas + plan nutricional hipocalórico + control mensual de HbA1c.',
 '2025-02-01', NULL, 'activo'),

('T003', 'D003',
 'Inhibidor de bomba de protones por 14 días + evitar AINEs + dieta fraccionada.',
 '2025-03-05', '2025-03-19', 'finalizado');

-- =========================
-- 5. Citas de prueba
-- =========================
-- Nota: el campo "medico" en la tabla cita es texto (nombre del médico),
-- no el id_usuario, según tu script de creación.
INSERT INTO cita (
    id_cita,
    id_paciente,
    fecha,
    medico,
    estado
) VALUES
('C001', 'P001', '2025-04-01 09:00:00', 'Dra. Ana López',   'programada'),
('C002', 'P001', '2025-04-15 09:00:00', 'Dra. Ana López',   'completada'),
('C003', 'P002', '2025-04-02 10:30:00', 'Dr. Carlos Pérez', 'programada');
