CREATE TABLE bodega (
    id          NUMBER NOT NULL,
    nombre      VARCHAR2(255 BYTE) NOT NULL,
    tamanio     NUMBER NOT NULL,
    sucursal_id NUMBER NOT NULL
);

ALTER TABLE bodega ADD CONSTRAINT bodega_pk PRIMARY KEY ( id );
ALTER TABLE bodega ADD CONSTRAINT tamaniobod_ck CHECK ( tamanio > 0 );

CREATE TABLE categoria (
    codigo                    NUMBER NOT NULL,
    nombre                    VARCHAR2(255 BYTE) NOT NULL,
    descripcion               VARCHAR2(255 BYTE) NOT NULL,
    caracteristicasalmacenaje VARCHAR2(255 BYTE) NOT NULL
);

ALTER TABLE categoria ADD CONSTRAINT categoriacodigo_pk PRIMARY KEY ( codigo );
ALTER TABLE categoria ADD CONSTRAINT categorianombre_uk UNIQUE ( nombre );

CREATE TABLE ciudad (
    codigo NUMBER NOT NULL,
    nombre VARCHAR2(255 BYTE) NOT NULL
);

ALTER TABLE ciudad ADD CONSTRAINT ciudad_pk PRIMARY KEY ( codigo );
ALTER TABLE ciudad ADD CONSTRAINT ciudadnombre_uk UNIQUE ( nombre );
ALTER TABLE ciudad ADD CONSTRAINT ciudadnombre_ck CHECK (nombre IN ('Bogota', 'Medellin', 'Cali', 'Barranquilla', 'Cartagena', 'Santa Marta', 'Cucuta', 'Villavicencio', 'Pereira', 'Manizales', 'Ibague', 'Bucaramanga', 'Pasto', 'Neiva', 'Popayan', 'Monteria', 'Valledupar', 'Armenia', 'Riohacha', 'Tunja', 'Quibdo', 'Sincelejo', 'Yopal', 'Mocoa', 'Puerto Carreno', 'San Andres', 'Leticia', 'Buenaventura'));

CREATE TABLE empaque (
    id             NUMBER NOT NULL,
    volumenempaque NUMBER NOT NULL,
    pesoempaque    NUMBER NOT NULL
);

ALTER TABLE empaque ADD CONSTRAINT empaque_pk PRIMARY KEY ( id );
ALTER TABLE empaque ADD CONSTRAINT volumen_ck CHECK ( volumenempaque > 0 );
ALTER TABLE empaque ADD CONSTRAINT peso_ck CHECK ( pesoempaque > 0 );

CREATE TABLE ordencompra (
    id            NUMBER NOT NULL,
    fechaesperada DATE NOT NULL,
    fechacreacion DATE NOT NULL,
    estado        VARCHAR2(255 BYTE) NOT NULL,
    sucursal_id   NUMBER NOT NULL,
    proveedor_nit NUMBER NOT NULL
);

ALTER TABLE ordencompra ADD CONSTRAINT ordencompra_pk PRIMARY KEY ( id );
ALTER TABLE ordencompra ADD CONSTRAINT ordenestado_ck CHECK ( estado IN ('Vigente', 'Entregada', 'Anulada') );
ALTER TABLE ordencompra ADD CONSTRAINT ordenfecha_ck2 CHECK ( fechaesperada > fechacreacion );

CREATE TABLE producto (
    codigobarras         NUMBER NOT NULL,
    nombre               VARCHAR2(255 BYTE) NOT NULL,
    costobodega          NUMBER NOT NULL,
    costounidad          NUMBER NOT NULL,
    presentacion         VARCHAR2(255 BYTE) NOT NULL,
    cantidadpresentacion NUMBER NOT NULL,
    unidadmedida          VARCHAR2(255 BYTE) NOT NULL,
    fechaexpiracion      DATE NOT NULL,
    categoria_codigo     NUMBER NOT NULL,
    empaque_id           NUMBER NOT NULL
);

ALTER TABLE producto ADD CONSTRAINT producto_pk PRIMARY KEY ( codigobarras );
ALTER TABLE producto ADD CONSTRAINT costobodega_ck CHECK ( costobodega > 0 );
ALTER TABLE producto ADD CONSTRAINT costounidad_ck CHECK ( costounidad > 0 );
ALTER TABLE producto ADD CONSTRAINT productocantidad_ck CHECK ( cantidadpresentacion > 0 );

CREATE TABLE productobodega (
    costopromedio         NUMBER NOT NULL,
    cantidadproducto      NUMBER NOT NULL,
    nivelreorden          NUMBER NOT NULL,
    capacidadproducto     NUMBER NOT NULL,
    producto_codigobarras NUMBER NOT NULL,
    bodega_id             NUMBER NOT NULL
);

ALTER TABLE productobodega ADD CONSTRAINT productobodega_pk PRIMARY KEY ( producto_codigobarras,
                                                                    bodega_id );

CREATE TABLE productocompra (
    precioacordado        NUMBER NOT NULL,
    cantidad              NUMBER NOT NULL,
    ordencompra_id        NUMBER NOT NULL,
    producto_codigobarras NUMBER NOT NULL
);

CREATE TABLE productoperecedero (
    producto_codigobarras     NUMBER NOT NULL,
    fechavencimiento DATE
);

CREATE UNIQUE INDEX productoperecedero__idx ON
    productoperecedero (
        producto_codigobarras
    ASC );

ALTER TABLE productoperecedero ADD CONSTRAINT productoperecedero_pk PRIMARY KEY ( codigobarras );

CREATE TABLE proveedor (
    nit             NUMBER NOT NULL,
    nombre          VARCHAR2(255 BYTE) NOT NULL,
    direccion       VARCHAR2(255 BYTE) NOT NULL,
    nombrepersona   VARCHAR2(255 BYTE) NOT NULL,
    telefonopersona NUMBER NOT NULL
);

ALTER TABLE proveedor ADD CONSTRAINT proveedor_pk PRIMARY KEY ( nit );
ALTER TABLE proveedor ADD CONSTRAINT proveedortelefono_ck CHECK ( telefonopersona > 0 );
ALTER TABLE proveedor ADD CONSTRAINT proveedortelefono_uk  UNIQUE ( telefonopersona );

CREATE TABLE recepcion (
    id             NUMBER NOT NULL,
    fecharecepcion DATE NOT NULL,
    bodega_id      NUMBER NOT NULL,
    ordencompra_id NUMBER NOT NULL
);

CREATE UNIQUE INDEX recepcionproducto__idx ON
    recepcion (
        ordencompra_id
    ASC );

ALTER TABLE recepcion ADD CONSTRAINT recepcion_pk PRIMARY KEY ( id );

CREATE TABLE productoproveedor (
    producto_codigobarras NUMBER NOT NULL,
    proveedor_nit         NUMBER NOT NULL
);

ALTER TABLE productoproveedor ADD CONSTRAINT productoproveedor_pk PRIMARY KEY ( producto_codigobarras,
                                                                    proveedor_nit );

CREATE TABLE sucursal (
    id            NUMBER NOT NULL,
    nombre        VARCHAR2(255 BYTE) NOT NULL,
    tamanio       NUMBER NOT NULL,
    direccion     VARCHAR2(255 BYTE) NOT NULL,
    ciudad_codigo NUMBER NOT NULL
);

ALTER TABLE sucursal ADD CONSTRAINT sucursal_pk PRIMARY KEY ( id );
ALTER TABLE sucursal ADD CONSTRAINT sucursaltamanio_ck CHECK ( tamanio > 0 );

ALTER TABLE bodega
    ADD CONSTRAINT bodega_sucursal_fk FOREIGN KEY ( sucursal_id )
        REFERENCES sucursal ( id );

ALTER TABLE ordencompra
    ADD CONSTRAINT ordencompra_proveedor_fk FOREIGN KEY ( proveedor_nit )
        REFERENCES proveedor ( nit );

ALTER TABLE ordencompra
    ADD CONSTRAINT ordencompra_sucursal_fk FOREIGN KEY ( sucursal_id )
        REFERENCES sucursal ( id );

ALTER TABLE producto
    ADD CONSTRAINT producto_categoria_fk FOREIGN KEY ( categoria_codigo )
        REFERENCES categoria ( codigo );

ALTER TABLE producto
    ADD CONSTRAINT producto_empaque_fk FOREIGN KEY ( empaque_id )
        REFERENCES empaque ( id );

ALTER TABLE productobodega
    ADD CONSTRAINT productobodega_bodega_fk FOREIGN KEY ( bodega_id )
        REFERENCES bodega ( id );

ALTER TABLE productobodega
    ADD CONSTRAINT productobodega_producto_fk FOREIGN KEY ( producto_codigobarras )
        REFERENCES producto ( codigobarras );

ALTER TABLE productocompra
    ADD CONSTRAINT productocompra_ordencompra_fk FOREIGN KEY ( ordencompra_id )
        REFERENCES ordencompra ( id );

ALTER TABLE productocompra
    ADD CONSTRAINT productocompra_producto_fk FOREIGN KEY ( producto_codigobarras )
        REFERENCES producto ( codigobarras );

ALTER TABLE productoperecedero
    ADD CONSTRAINT productoperecedero_producto_fk FOREIGN KEY ( producto_codigobarras )
        REFERENCES producto ( codigobarras );

ALTER TABLE recepcion
    ADD CONSTRAINT recepcion_orden_fk FOREIGN KEY ( ordencompra_id )
        REFERENCES ordencompra ( id );

ALTER TABLE recepcion
    ADD CONSTRAINT recepcion_bodega_fk FOREIGN KEY ( bodega_id )
        REFERENCES bodega ( id );

ALTER TABLE productoproveedor
    ADD CONSTRAINT pp_producto_fk FOREIGN KEY ( producto_codigobarras )
        REFERENCES producto ( codigobarras );

ALTER TABLE productoproveedor
    ADD CONSTRAINT pp_proveedor_fk FOREIGN KEY ( proveedor_nit )
        REFERENCES proveedor ( nit );

ALTER TABLE sucursal
    ADD CONSTRAINT sucursal_ciudad_fk FOREIGN KEY ( ciudad_codigo )
        REFERENCES ciudad ( codigo );
