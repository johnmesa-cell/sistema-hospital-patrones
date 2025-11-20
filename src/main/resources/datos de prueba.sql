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

-- ============================================================
-- DATOS DE PRUEBA: HISTORIAL CLÍNICO Y NOTAS MÉDICAS
-- ============================================================

-- =========================
-- 1. Historiales Clínicos
-- =========================
-- Cada paciente debe tener un historial clínico único
INSERT INTO historial_clinico (id_historial, id_paciente, fecha_creacion, fecha_ultima_actualizacion) VALUES
('HC001', 'P001', '2024-01-15 08:00:00', '2025-04-01 10:30:00'),
('HC002', 'P002', '2024-06-10 09:00:00', '2025-03-20 14:15:00');

-- =========================
-- 2. Notas Médicas
-- =========================
-- Notas asociadas al historial clínico de cada paciente
-- IMPORTANTE: Ajusta la estructura según tu clase NotaMedica.java

-- Si tu tabla nota_medica debe incluir los campos que usa tu código Java,
-- primero debes modificar la estructura de la tabla:

-- Opción A: Modificar la tabla para que coincida con NotaMedica.java
-- (Ejecuta esto ANTES de insertar datos)
ALTER TABLE nota_medica 
ADD COLUMN id_historial VARCHAR(50) AFTER id_nota,
ADD COLUMN tipo_nota VARCHAR(50) AFTER id_historial,
ADD COLUMN contenido_medico TEXT,
ADD COLUMN contenido_paciente TEXT,
ADD COLUMN visible_para_paciente BOOLEAN DEFAULT TRUE,
MODIFY COLUMN contenido TEXT NULL,
MODIFY COLUMN fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD CONSTRAINT fk_nota_historial 
    FOREIGN KEY (id_historial) REFERENCES historial_clinico(id_historial) ON DELETE CASCADE;

-- Ahora insertamos notas médicas con la estructura completa
INSERT INTO nota_medica (
    id_nota, 
    id_historial, 
    id_paciente,
    id_medico,
    tipo_nota,
    contenido_medico,
    contenido_paciente,
    visible_para_paciente,
    fecha
) VALUES
-- Notas para el paciente P001 (Juan Gómez)
(
    'N001', 
    'HC001', 
    'P001',
    'med001',
    'Consulta Inicial',
    'Paciente masculino de 40 años. Antecedentes: HTA diagnosticada hace 2 años, sin otros antecedentes relevantes. Examen físico: PA 145/95 mmHg, FC 78 lpm. Se indica tratamiento antihipertensivo.',
    'Primera consulta. Se encontró presión arterial elevada. Iniciará tratamiento con medicamentos.',
    TRUE,
    '2025-01-10 09:30:00'
),
(
    'N002', 
    'HC001', 
    'P001',
    'med001',
    'Control Mensual',
    'Control de seguimiento. Paciente refiere adherencia al tratamiento. PA actual 130/85 mmHg. Glucemia en ayunas 156 mg/dL. Se añade metformina al esquema terapéutico.',
    'Control mensual. La presión ha mejorado. Los niveles de azúcar están altos, se agregará medicamento.',
    TRUE,
    '2025-02-01 10:00:00'
),
(
    'N003', 
    'HC001', 
    'P001',
    'med002',
    'Interconsulta Cardiología',
    'Paciente con HTA en tratamiento. Ecocardiograma normal. Sin evidencia de hipertrofia ventricular. Se sugiere continuar con esquema actual y control en 3 meses.',
    'El especialista revisó su corazón y todo está bien. Continúe con sus medicamentos.',
    TRUE,
    '2025-02-15 14:00:00'
),
(
    'N004', 
    'HC001', 
    'P001',
    'med001',
    'Nota Confidencial',
    'Paciente reporta episodios de ansiedad relacionados con el diagnóstico. Se refiere a psicología para manejo. No se observan complicaciones cardiovasculares por el momento.',
    NULL,  -- Esta nota NO es visible para el paciente
    FALSE,
    '2025-03-01 11:30:00'
),

-- Notas para el paciente P002 (María Rodríguez)
(
    'N005', 
    'HC002', 
    'P002',
    'med002',
    'Consulta Urgencia',
    'Paciente femenina de 33 años. Acude por epigastralgia intensa de 2 días de evolución. Abdomen doloroso a palpación en epigastrio. Dx: Gastritis aguda. Se inicia tratamiento con IBP.',
    'Consulta de urgencia por dolor de estómago fuerte. Se diagnosticó gastritis y se inició tratamiento.',
    TRUE,
    '2025-03-05 16:45:00'
),
(
    'N006', 
    'HC002', 
    'P002',
    'med002',
    'Control Post-Tratamiento',
    'Paciente regresa para control. Refiere mejoría significativa del dolor. Sin nuevos episodios. Completó esquema de IBP por 14 días. Recomendaciones dietéticas entregadas.',
    'Control después del tratamiento. El dolor ha desaparecido. Se dan recomendaciones de alimentación.',
    TRUE,
    '2025-03-19 09:00:00'
),
(
    'N007', 
    'HC002', 
    'P002',
    'med001',
    'Consulta Preventiva',
    'Control preventivo anual. Examen físico sin hallazgos patológicos. Laboratorios dentro de límites normales. Vacunas al día. Se orienta sobre hábitos saludables.',
    'Consulta de control anual. Todo está en orden. Continúe con hábitos saludables.',
    TRUE,
    '2025-04-01 08:30:00'
);

-- =========================
-- 3. Verificación de datos
-- =========================
-- Consulta para verificar que todo se insertó correctamente
SELECT 
    h.id_historial,
    h.id_paciente,
    p.id_usuario,
    u.nombre AS nombre_paciente,
    COUNT(n.id_nota) AS total_notas,
    h.fecha_creacion,
    h.fecha_ultima_actualizacion
FROM historial_clinico h
LEFT JOIN paciente p ON h.id_paciente = p.id_paciente
LEFT JOIN usuario u ON p.id_usuario = u.id_usuario
LEFT JOIN nota_medica n ON h.id_historial = n.id_historial
GROUP BY h.id_historial, h.id_paciente, p.id_usuario, u.nombre, h.fecha_creacion, h.fecha_ultima_actualizacion;

-- Consulta para ver todas las notas médicas con información del paciente
SELECT 
    n.id_nota,
    n.tipo_nota,
    n.id_paciente,
    u.nombre AS nombre_paciente,
    m.nombre AS nombre_medico,
    n.visible_para_paciente,
    n.fecha
FROM nota_medica n
LEFT JOIN paciente p ON n.id_paciente = p.id_paciente
LEFT JOIN usuario u ON p.id_usuario = u.id_usuario
LEFT JOIN usuario m ON n.id_medico = m.id_usuario
ORDER BY n.fecha DESC;
