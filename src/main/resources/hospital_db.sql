CREATE DATABASE IF NOT EXISTS hospitaldb;
USE hospitaldb;

-- Tabla para usuarios (médicos, pacientes, administradores)
CREATE TABLE usuario (
    id_usuario VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    tipo ENUM('medico', 'paciente', 'administrador') NOT NULL,
    contrasena VARCHAR(255) NOT NULL
);

-- Tabla pacientes (puede estar relacionada al usuario también si quieres)
CREATE TABLE paciente (
    id_paciente VARCHAR(50) PRIMARY KEY,
    id_usuario VARCHAR(50),
    fecha_nacimiento DATE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE SET NULL
);

-- Tabla diagnósticos
CREATE TABLE diagnostico (
    id_diagnostico VARCHAR(50) PRIMARY KEY,
    id_paciente VARCHAR(50),
    descripcion TEXT,
    codigo_cie10 VARCHAR(20),
    descripcion_tecnica TEXT,
    explicacion_paciente TEXT,
    estado VARCHAR(50),
    FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente) ON DELETE CASCADE
);

-- Tabla tratamientos
CREATE TABLE tratamiento (
    id_tratamiento VARCHAR(50) PRIMARY KEY,
    id_diagnostico VARCHAR(50),
    descripcion TEXT,
    fecha_inicio DATE,
    fecha_fin DATE,
    estado VARCHAR(50),
    FOREIGN KEY (id_diagnostico) REFERENCES diagnostico(id_diagnostico) ON DELETE CASCADE
);

-- Tabla citas
CREATE TABLE cita (
    id_cita VARCHAR(50) PRIMARY KEY,
    id_paciente VARCHAR(50),
    fecha DATETIME,
    medico VARCHAR(100),
    estado VARCHAR(50),
    FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente) ON DELETE CASCADE
);

-- Tabla de histotial clinico
CREATE TABLE IF NOT EXISTS historial_clinico (
    id_historial VARCHAR(50) PRIMARY KEY,
    id_paciente VARCHAR(50) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_ultima_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente)
);

CREATE TABLE IF NOT EXISTS nota_medica (
    id_nota VARCHAR(50) PRIMARY KEY,
    id_paciente VARCHAR(50) NOT NULL,
    id_medico VARCHAR(50),
    contenido TEXT,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente)
);


-- Índices para mejorar búsqueda
CREATE INDEX idx_usuario_tipo ON usuario(tipo);
CREATE INDEX idx_diagnostico_paciente ON diagnostico(id_paciente);
CREATE INDEX idx_tratamiento_diagnostico ON tratamiento(id_diagnostico);
CREATE INDEX idx_cita_paciente ON cita(id_paciente);
