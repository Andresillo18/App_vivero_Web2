--Andr�s Villalobos
--27/3/22
USE [BD_VIVERO]
GO

INSERT INTO Productos
           (
		   tipoProducto,
		   nombre,
		   descripcion,
		   precio,
		   cantDisponible)
     VALUES
           ('Planta','Lirio Azul','Planta Hermosa',15000 ,3 ),
		   ('Planta','Bromelia','Color c�lido',20000,5),
		   ('Planta','Manzanilla','Medicinal',20000,10),
		   ('Planta','Hierbabuena','Tambi�n considerada medicinal',5000,1),		   
		   ('Planta','Pingo de oro','Tiene espinas',10000,15);
GO


INSERT INTO [dbo].[Categoria]
           ([NOMBRE_CATEGORIA]
           ,[DESCRIPCION])
     VALUES
           ('Interiores'
           ,'Todo tipo de plantas de interior'),	--1	  
		     ('exteriores'
           ,'Todo tipo de plantas de exteriores'),--2
		     ('arom�ticas'
           ,'Todo tipo de plantas de arom�ticas'),--3
		     ('medicinales'
           ,'Todo tipo de plantas de medicinales'),--4
		     ('frutales'
           ,'Todo tipo de frutales'),--5
		     ('�rboles'
           ,'Todo tipo de �rboles'),--6
		     ('arbustos'
           ,'Todo tipo de arbustos');--7
GO


INSERT INTO [dbo].[Clasificacion_Planta]
           ([codProducto]
           ,[COD_CATEGORIA])
     VALUES
           (1,2), (2,1), (3,4), (5,6);
GO


INSERT INTO [dbo].[Empleado]
           ([ID]
           ,[NOMBRE]
           ,[APELLIDO1]
           ,[TELEFONO]
           ,[VENTAS_REALIZADAS]
           ,[ESTADO]
           ,[BONO])
     VALUES
           ('118080627','Andres','Villalobos',88807673,'','',''),
		   ('207890853','Redwin','Valverde',86639700,'','',''),
		   ('118888800','Juan','Vargas',5555555,'1','',''),
		   ('245155515','Mar�a','Carranza',22444445,'','',''),
		   ('425552222','Lucas','Carballo',5558888,'','','');
GO


INSERT INTO [dbo].[Cliente]
           ([ID]
           ,[NOMBRE]
           ,[APELLIDO1]
           ,[TELEFONO])
     VALUES
           ('','Pablo','',''),
		   ('22555547','Juliana','','');
GO


INSERT INTO [dbo].[Factura]
           ([COD_EMPLEADO]
           ,[COD_CLIENTE])
     VALUES
           (1,1),
		   (1,2);
GO

--select * from Detalle_Factura
--select * from Productos
INSERT INTO [dbo].[Detalle_Factura]
           ([COD_FACTURA]
           ,[codProducto]
           ,[cantDetalle]
           ,[TOTAL_PAGAR]
           ,[FECHA]
           ,[OBSERVACIONES])
     VALUES
           (1,1,1,2000,'2022-3-28','')
GO