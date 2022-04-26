USE [master]
GO
/****** Object:  Database [BD_VIVERO]    Script Date: 18/4/2022 17:27:09 ******/
CREATE DATABASE [BD_VIVERO]
-- CONTAINMENT = NONE
-- ON  PRIMARY 
--( NAME = N'BD_VIVERO', FILENAME = N'B:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\BD_VIVERO.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
-- LOG ON 
--( NAME = N'BD_VIVERO_log', FILENAME = N'B:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\BD_VIVERO_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
-- WITH CATALOG_COLLATION = DATABASE_DEFAULT
--GO
--ALTER DATABASE [BD_VIVERO] SET COMPATIBILITY_LEVEL = 150
--GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [BD_VIVERO].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [BD_VIVERO] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [BD_VIVERO] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [BD_VIVERO] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [BD_VIVERO] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [BD_VIVERO] SET ARITHABORT OFF 
GO
ALTER DATABASE [BD_VIVERO] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [BD_VIVERO] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [BD_VIVERO] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [BD_VIVERO] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [BD_VIVERO] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [BD_VIVERO] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [BD_VIVERO] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [BD_VIVERO] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [BD_VIVERO] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [BD_VIVERO] SET  DISABLE_BROKER 
GO
ALTER DATABASE [BD_VIVERO] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [BD_VIVERO] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [BD_VIVERO] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [BD_VIVERO] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [BD_VIVERO] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [BD_VIVERO] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [BD_VIVERO] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [BD_VIVERO] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [BD_VIVERO] SET  MULTI_USER 
GO
ALTER DATABASE [BD_VIVERO] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [BD_VIVERO] SET DB_CHAINING OFF 
GO
ALTER DATABASE [BD_VIVERO] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [BD_VIVERO] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [BD_VIVERO] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [BD_VIVERO] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [BD_VIVERO] SET QUERY_STORE = OFF
GO
USE [BD_VIVERO]
GO
/****** Object:  Table [dbo].[Categoria]    Script Date: 18/4/2022 17:27:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categoria](
	[COD_CATEGORIA] [int] IDENTITY(1,1) NOT NULL,
	[NOMBRE_CATEGORIA] [varchar](30) NOT NULL,
	[DESCRIPCION] [varchar](60) NULL,
 CONSTRAINT [PK_Categoria] PRIMARY KEY CLUSTERED 
(
	[COD_CATEGORIA] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Clasificacion_Planta]    Script Date: 18/4/2022 17:27:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Clasificacion_Planta](
	[COD_CLASIFICACION] [int] IDENTITY(1,1) NOT NULL,
	[codProducto] [int] NOT NULL,
	[COD_CATEGORIA] [int] NOT NULL,
 CONSTRAINT [PK_Clasificacion_Planta] PRIMARY KEY CLUSTERED 
(
	[COD_CLASIFICACION] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cliente]    Script Date: 18/4/2022 17:27:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cliente](
	[COD_CLIENTE] [int] IDENTITY(1,1) NOT NULL,
	[ID] [varchar](30) NULL,
	[NOMBRE] [varchar](30) NULL,
	[APELLIDO1] [varchar](25) NULL,
	[TELEFONO] [varchar](20) NULL,
	[ESTADO] [bit] NULL,
 CONSTRAINT [PK_Cliente] PRIMARY KEY CLUSTERED 
(
	[COD_CLIENTE] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Detalle_Factura]    Script Date: 18/4/2022 17:27:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Detalle_Factura](
	[COD_DETALLE] [int] IDENTITY(1,1) NOT NULL,
	[COD_FACTURA] [int] NOT NULL,
	[codProducto] [int] NOT NULL,
	[cantDetalle] [int] NOT NULL,
	[TOTAL_PAGAR] [float] NULL,
	[FECHA] [date] NULL,
	[OBSERVACIONES] [varchar](60) NULL,
 CONSTRAINT [PK_Detalle_Factura] PRIMARY KEY CLUSTERED 
(
	[COD_DETALLE] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Empleado]    Script Date: 18/4/2022 17:27:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Empleado](
	[COD_EMPLEADO] [int] IDENTITY(1,1) NOT NULL,
	[ID] [varchar](30) NULL,
	[NOMBRE] [varchar](30) NULL,
	[APELLIDO1] [varchar](25) NULL,
	[TELEFONO] [varchar](20) NULL,
	[VENTAS_REALIZADAS] [int] NULL,
	[ESTADO] [bit] NULL,
	[BONO] [float] NULL,
 CONSTRAINT [PK_Empleado] PRIMARY KEY CLUSTERED 
(
	[COD_EMPLEADO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Factura]    Script Date: 18/4/2022 17:27:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Factura](
	[COD_FACTURA] [int] IDENTITY(1,1) NOT NULL,
	[COD_EMPLEADO] [int] NOT NULL,
	[COD_CLIENTE] [int] NOT NULL,
	[estado] [varchar](10) null DEFAULT 'Pendiente'
 CONSTRAINT [PK_Factura] PRIMARY KEY CLUSTERED 
(
	[COD_FACTURA] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO



/****** Object:  Table [dbo].[Productos]    Script Date: 18/4/2022 17:27:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Productos](
	[codProducto] [int] IDENTITY(1,1) NOT NULL,
	[tipoProducto] [varchar](15) NULL,
	[nombre] [varchar](30) NULL,
	[descripcion] [varchar](30) NULL,
	[precio] [money] NULL,
	[cantDisponible] [int] NULL,
 CONSTRAINT [PK_Productos] PRIMARY KEY CLUSTERED 
(
	[codProducto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Cliente] ADD  CONSTRAINT [DF_Cliente_ESTADO]  DEFAULT ((1)) FOR [ESTADO]
GO
ALTER TABLE [dbo].[Detalle_Factura] ADD  CONSTRAINT [DF_Detalle_Factura_TOTAL_PAGAR]  DEFAULT ((0)) FOR [TOTAL_PAGAR]
GO
ALTER TABLE [dbo].[Empleado] ADD  CONSTRAINT [DF_Empleado_VENTAS_REALIZADAS]  DEFAULT ((0)) FOR [VENTAS_REALIZADAS]
GO
ALTER TABLE [dbo].[Empleado] ADD  CONSTRAINT [DF_Empleado_BONO]  DEFAULT ((0)) FOR [BONO]
GO
ALTER TABLE [dbo].[Clasificacion_Planta]  WITH CHECK ADD  CONSTRAINT [FK_Clasificacion_Planta_Categoria] FOREIGN KEY([COD_CATEGORIA])
REFERENCES [dbo].[Categoria] ([COD_CATEGORIA])
GO
ALTER TABLE [dbo].[Clasificacion_Planta] CHECK CONSTRAINT [FK_Clasificacion_Planta_Categoria]
GO
ALTER TABLE [dbo].[Clasificacion_Planta]  WITH CHECK ADD  CONSTRAINT [FK_Clasificacion_Planta_Productos] FOREIGN KEY([codProducto])
REFERENCES [dbo].[Productos] ([codProducto])
GO
ALTER TABLE [dbo].[Clasificacion_Planta] CHECK CONSTRAINT [FK_Clasificacion_Planta_Productos]
GO
ALTER TABLE [dbo].[Detalle_Factura]  WITH CHECK ADD  CONSTRAINT [FK_Detalle_Factura_Factura] FOREIGN KEY([COD_FACTURA])
REFERENCES [dbo].[Factura] ([COD_FACTURA])
GO
ALTER TABLE [dbo].[Detalle_Factura] CHECK CONSTRAINT [FK_Detalle_Factura_Factura]
GO
ALTER TABLE [dbo].[Detalle_Factura]  WITH CHECK ADD  CONSTRAINT [FK_Detalle_Factura_Productos] FOREIGN KEY([codProducto])
REFERENCES [dbo].[Productos] ([codProducto])
GO
ALTER TABLE [dbo].[Detalle_Factura] CHECK CONSTRAINT [FK_Detalle_Factura_Productos]
GO
ALTER TABLE [dbo].[Factura]  WITH CHECK ADD  CONSTRAINT [FK_Factura_Cliente] FOREIGN KEY([COD_CLIENTE])
REFERENCES [dbo].[Cliente] ([COD_CLIENTE])
GO
ALTER TABLE [dbo].[Factura] CHECK CONSTRAINT [FK_Factura_Cliente]
GO
ALTER TABLE [dbo].[Factura]  WITH CHECK ADD  CONSTRAINT [FK_Factura_Empleado] FOREIGN KEY([COD_EMPLEADO])
REFERENCES [dbo].[Empleado] ([COD_EMPLEADO])
GO
ALTER TABLE [dbo].[Factura] CHECK CONSTRAINT [FK_Factura_Empleado]
GO
ALTER TABLE [dbo].[Categoria]  WITH CHECK ADD  CONSTRAINT [CK_Nombre_Categoria] CHECK  (([NOMBRE_CATEGORIA]=upper([NOMBRE_CATEGORIA]) AND ([NOMBRE_CATEGORIA]='Interiores' OR [NOMBRE_CATEGORIA]='Exteriores' OR [NOMBRE_CATEGORIA]='Aromáticas' OR [NOMBRE_CATEGORIA]='Medicinales' OR [NOMBRE_CATEGORIA]='Frutales' OR [NOMBRE_CATEGORIA]='Árboles' OR [NOMBRE_CATEGORIA]='Arbustos')))
GO
ALTER TABLE [dbo].[Categoria] CHECK CONSTRAINT [CK_Nombre_Categoria]
GO
ALTER TABLE [dbo].[Productos]  WITH CHECK ADD  CONSTRAINT [CHK_Cantidad_Plantas] CHECK  (([cantDisponible]>=(0)))
GO
ALTER TABLE [dbo].[Productos] CHECK CONSTRAINT [CHK_Cantidad_Plantas]
GO
ALTER TABLE [dbo].[Productos]  WITH CHECK ADD  CONSTRAINT [CHK_Precio_Plantas] CHECK  (([precio]>=(0)))
GO
ALTER TABLE [dbo].[Productos] CHECK CONSTRAINT [CHK_Precio_Plantas]
GO
ALTER TABLE [dbo].[Productos]  WITH CHECK ADD  CONSTRAINT [CHK_TipoProducto] CHECK  (([tipoProducto]=upper([tipoProducto]) AND ([tipoProducto]='OTRO' OR [tipoProducto]='ARTÍCULO' OR [tipoProducto]='PLANTA' OR [tipoProducto]='QUÍMICO')))
GO
ALTER TABLE [dbo].[Productos] CHECK CONSTRAINT [CHK_TipoProducto]
GO
/****** Object:  StoredProcedure [dbo].[SP_BONO]    Script Date: 18/4/2022 17:27:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE  PROCEDURE [dbo].[SP_BONO] (@COD_EMPLEADO int, @MES int, @BONO int OUT)
AS
BEGIN
DECLARE @SUMA_PAGOS int

SET @SUMA_PAGOS =( SELECT SUM(DF.TOTAL_PAGAR)
				FROM Detalle_Factura DF INNER JOIN Factura F ON DF.COD_FACTURA = F.COD_FACTURA
				WHERE (MONTH(DF.FECHA) = @MES) AND (F.COD_EMPLEADO= @COD_EMPLEADO) )
				--AND (DF.COD_FACTURA = F.COD_FACTURA)

SET @BONO = @SUMA_PAGOS * 0.05

UPDATE Empleado
SET BONO = @BONO
WHERE COD_EMPLEADO = @COD_EMPLEADO

RETURN @BONO
END
GO
/****** Object:  StoredProcedure [dbo].[SP_RESTAR_CANTIDAD]    Script Date: 18/4/2022 17:27:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE  PROCEDURE [dbo].[SP_RESTAR_CANTIDAD] (@cod int, @cantidad int)
AS
--BEGIN TRANSACTION
	BEGIN TRY	

	UPDATE Productos
	SET cantDisponible = cantDisponible- @cantidad
	WHERE codProducto= @cod
	
END TRY
BEGIN CATCH -- Si hay un error se salta todo y el ROLLBACK deshace todo lo realizado	 		
	PRINT ERROR_MESSAGE()
	--ROLLBACK TRANSACTION
END CATCH
GO
/****** Object:  StoredProcedure [dbo].[SP_RESUMIR_VENTAS]    Script Date: 18/4/2022 17:27:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE  PROCEDURE [dbo].[SP_RESUMIR_VENTAS]
	(@mes int,
	@resumen float OUT)
AS
BEGIN
	SET @resumen= (SELECT SUM(DF.TOTAL_PAGAR)
					FROM Detalle_Factura DF
					WHERE MONTH(DF.FECHA) = @mes)

END
GO
USE [master]
GO
ALTER DATABASE [BD_VIVERO] SET  READ_WRITE 
GO



-- ************************** Inserta el producto al detalle si es posible **************************
/*
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- select * from detalle_factura

CREATE OR ALTER PROCEDURE InsertarDetalleFactura(@codFactura int, 
												@codProducto int,
												@cantDetalle int,
												@totalDetalle int,
												@fecha date,
												@obser varchar(60))
as

begin
  BEGIN TRY  

	declare @estadoFactura varchar(10) = (select estado from Factura where COD_FACTURA = @codFactura)
	-- print @estadoFactura

	 if (@estadoFactura = 'Pendiente')

		begin
			
			Insert into Detalle_Factura
								(COD_FACTURA, codProducto,  cantDetalle,  TOTAL_PAGAR,   FECHA,  OBSERVACIONES)
						VALUES	(@codFactura, @codProducto, @cantDetalle, @totalDetalle, @fecha, @obser)
		
		end

  END TRY  
  BEGIN CATCH  
     
	 PRINT ERROR_MESSAGE()

  END CATCH 
end
GO
*/