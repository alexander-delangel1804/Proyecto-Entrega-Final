USE DBVetGo;


CREATE TABLE USUARIOS (
    id_usuario INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    correo VARCHAR(50) UNIQUE NOT NULL,
    contrasena VARCHAR(50) NOT NULL,
    telefono VARCHAR(10),
    direccion VARCHAR(100),
    ciudad VARCHAR(20) CHECK (ciudad IN ('Ciudad Madero','Tampico','Altamira')),
	rol VARCHAR(20) CHECK (rol IN ('Cliente','Veterinario')) NOT NULL
);

CREATE TABLE VETERINARIOS_INFO (
    fk_id_veterinario INT PRIMARY KEY,
    cedula_profesional VARCHAR(50) NOT NULL,
    especialidad VARCHAR(50),
    estado VARCHAR(20) CHECK (estado IN ('Disponible', 'Ocupado', 'Inactivo')) DEFAULT 'Inactivo',
    calif_promedio DECIMAL(3,2) DEFAULT 0,
    FOREIGN KEY (fk_id_veterinario) REFERENCES USUARIOS(id_usuario)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE CLIENTES_INFO (
    fk_id_cliente INT PRIMARY KEY,
    saldo DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    tarjeta_marca VARCHAR(20) NULL,  
    tarjeta_ultimos4 CHAR(4) NULL,
    tarjeta_expiracion VARCHAR(7) NULL, 
    tarjeta_token VARCHAR(255) NULL,
    fecha_registro DATETIME DEFAULT GETDATE(),

    CONSTRAINT FK_ClientesInfo_Usuarios
        FOREIGN KEY (fk_id_cliente)
        REFERENCES USUARIOS(id_usuario)
);


CREATE TABLE ADMINISTRADORES (
    id_admin INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    correo VARCHAR(50) UNIQUE NOT NULL,
    contrasena VARCHAR(50) NOT NULL,
    rol VARCHAR(20) CHECK (rol IN('Gerente', 'Coordinador', 'Soporte')) DEFAULT 'Coordinador'
);

CREATE TABLE MASCOTAS (
    id_mascota INT IDENTITY(1,1) PRIMARY KEY,
    fk_id_cliente INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    especie VARCHAR(50),
    edad INT,
	peso FLOAT,
    sexo VARCHAR(20) CHECK (sexo IN('Macho', 'Hembra')),
    FOREIGN KEY (fk_id_usuario) REFERENCES USUARIOS(id_usuario)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE SERVICIOS (
    id_servicio INT IDENTITY(1,1) PRIMARY KEY,
    nombre_servicio VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio_base DECIMAL(10,2) NOT NULL
);

CREATE TABLE CITAS (
    id_cita INT IDENTITY(1,1) PRIMARY KEY,
    fk_id_cliente INT NOT NULL,
    fk_servicio INT NOT NULL,
    fk_mascota INT NOT NULL,
    fk_id_veterinario INT,
    fecha_cita DATETIME NOT NULL,
    estado VARCHAR(20) CHECK (estado IN (
		'En espera',
		'Cancelada por cliente',
		'Asignada a veterinario',
		'En camino',
		'En servicio',
		'Completada',
		'Pagada',
		'Cancelada por veterinario'
	))
	--CHECK(estado IN ('Pendiente', 'Aceptada', 'En Proceso', 'Completada', 'No pagado', 'Cancelada')),
    detalles_cita TEXT,
    metodo_pago VARCHAR(30) NOT NULL CHECK (metodo_pago IN ('Tarjeta','Efectivo')),
    FOREIGN KEY (fk_id_cliente) REFERENCES USUARIOS(id_usuario),
    FOREIGN KEY (fk_servicio) REFERENCES SERVICIOS(id_servicio),
    FOREIGN KEY (fk_mascota) REFERENCES MASCOTAS(id_mascota),
    FOREIGN KEY (fk_id_veterinario) REFERENCES USUARIOS(id_usuario)
);

CREATE TABLE REPORTES (
    id_reporte INT IDENTITY(1,1) PRIMARY KEY,
    fk_id_veterinario INT NOT NULL,
    fk_id_cliente INT NOT NULL,
    fk_servicio INT NOT NULL,
    fk_mascota INT NOT NULL,
    fk_id_cita INT NOT NULL,
    detalles TEXT,
    observaciones TEXT,
    coste_extra DECIMAL(10,2) DEFAULT 0.00,
    evidencia_foto VARCHAR(MAX),
    FOREIGN KEY (fk_id_veterinario) REFERENCES USUARIOS(id_usuario),
    FOREIGN KEY (fk_id_cliente) REFERENCES USUARIOS(id_usuario),
    FOREIGN KEY (fk_servicio) REFERENCES SERVICIOS(id_servicio),
    FOREIGN KEY (fk_mascota) REFERENCES MASCOTAS(id_mascota),
    FOREIGN KEY (fk_id_cita) REFERENCES CITAS(id_cita)
);


CREATE TABLE PAGOS (
    id_pago INT IDENTITY(1,1) PRIMARY KEY,
	fk_cita INT NOT NULL,
    fk_servicio INT NOT NULL,
    fk_coste_extra INT NULL,
    monto_total DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    metodo_pago VARCHAR(30) NOT NULL CHECK (metodo_pago IN ('Tarjeta','Efectivo')),
    estado VARCHAR(20) NOT NULL DEFAULT 'Pendiente' CHECK (estado IN ('Pendiente','Completado','Fallido')),
    fecha_pago DATETIME DEFAULT GETDATE(),

    CONSTRAINT FK_Pagos_Servicios FOREIGN KEY (fk_servicio)
        REFERENCES SERVICIOS(id_servicio),

    CONSTRAINT FK_Pagos_Reportes FOREIGN KEY (fk_coste_extra)
        REFERENCES REPORTES(id_reporte),

	CONSTRAINT FK_Pagos_Citas FOREIGN KEY (fk_cita)
        REFERENCES CITAS(id_cita)
);

CREATE TRIGGER trigger_monto_total
ON PAGOS
AFTER INSERT, UPDATE
AS
BEGIN
    SET NOCOUNT ON;
    UPDATE P
    SET P.monto_total = ISNULL(S.precio_base, 0) + ISNULL(R.coste_extra, 0)
    FROM PAGOS P
    INNER JOIN inserted I ON P.id_pago = I.id_pago
    LEFT JOIN dbo.Servicios S ON S.id_servicio = I.fk_servicio
    LEFT JOIN dbo.Reportes R ON R.id_reporte = I.fk_coste_extra;
END;



CREATE TABLE ENCUESTAS (
    id_encuesta INT IDENTITY(1,1) PRIMARY KEY,
    fk_id_cliente INT NOT NULL,
    fk_id_veterinario INT NOT NULL,
    calificacion INT NOT NULL CHECK (calificacion BETWEEN 1 AND 5),
    comentario VARCHAR(500), 
    fecha DATETIME DEFAULT GETDATE() NOT NULL,
    FOREIGN KEY (fk_id_cliente) REFERENCES USUARIOS(id_usuario),
    FOREIGN KEY (fk_id_veterinario) REFERENCES USUARIOS(id_usuario)
);



-- Consultas --

SELECT * FROM VETERINARIOS_INFO
SELECT * FROM USUARIOS
SELECT * FROM SERVICIOS
SELECT * FROM MASCOTAS
SELECT * FROM CITAS
SELECT * FROM REPORTES
SELECT * FROM PAGOS
SELECT * FROM ENCUESTAS


use master
SP_TABLES


if OBJECT_ID('monto_total', 'TR') IS
Not null
	drop trigger monto_total;


drop table ENCUESTAS

delete from MASCOTAS where id_mascota = 5;


SELECT 
    name 
FROM sys.foreign_keys
WHERE parent_object_id = OBJECT_ID('MASCOTAS');

ALTER TABLE MASCOTAS
DROP CONSTRAINT FK__MASCOTAS__fk_id___45F365D3;

EXEC sp_rename 'MASCOTAS.fk_id_usuario', 'fk_id_cliente', 'COLUMN';


ALTER TABLE MASCOTAS
ADD CONSTRAINT FK_Mascotas_Cliente
FOREIGN KEY (fk_id_cliente)
REFERENCES USUARIOS(id_usuario)
ON UPDATE CASCADE
ON DELETE CASCADE;
