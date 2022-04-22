--Andrés Villalobos
--27/3/22
use BD_VIVERO
GO

-- =============================================
-- Trigger1
-- Description:	Se ejecuta antes de ingresar un registro, pregunta si la cantidad ingresada en el detalle factura
-- esta disponible en sus respectivas tablas, si es así continua con el proceso.
-- =============================================
CREATE OR ALTER TRIGGER PERMITIR_CANTIDADES
   ON  Detalle_Factura
   INSTEAD OF INSERT, UPDATE
AS 
BEGIN TRY 	
BEGIN TRANSACTION

--Si hay herramientas o productos ingresados en la factura
IF ((SELECT cantDetalle FROM inserted) > 0)

--Pregunta si hay suficientes en el inventario de las herramientas o productos
	IF ((SELECT cantDetalle FROM inserted) <= (SELECT cantDisponible FROM inserted INNER JOIN  Productos P ON P.codProducto= inserted.codProducto
																WHERE inserted.codProducto = P.codProducto))			
			BEGIN
			BEGIN TRANSACTION
				INSERT INTO Detalle_Factura
				SELECT COD_FACTURA, codProducto,cantDetalle, TOTAL_PAGAR, FECHA,OBSERVACIONES FROM inserted

				--Se llama al SP para actualizar la tabla y se declaran y se enván los parámetros
				DECLARE @parametro1 int
				SET @parametro1  =  (SELECT codProducto FROM inserted)
				
				DECLARE @parametro2 int
				SET @parametro2 =(SELECT cantDetalle FROM inserted)
				EXEC SP_RESTAR_CANTIDAD @parametro1,@parametro2
			COMMIT TRANSACTION
			END
	ELSE	
	BEGIN
		RAISERROR('MENSAJE: No hay articulos suficientes',16,1)
		ROLLBACK TRANSACTION 
		PRINT ERROR_MESSAGE();
	END
--Si hay plantas ingresadas en la factura
COMMIT TRANSACTION
END TRY
	BEGIN CATCH 	
		PRINT ERROR_MESSAGE();
	END CATCH
GO


-- =============================================
-- Trigger2
-- Description:	Se ejecuta antes de ACTUALIZAR un registro, pregunta si la cantidad ingresada en el detalle factura
-- esta disponible en sus respectivas tablas, si es así continua con el proceso.
--**** Se hicieron 2 triggers iguales para no hacer muchos procesos y saber si actualiza o no
-- =============================================
--CREATE OR ALTER TRIGGER PERMITIR_CANTIDADES
--   ON  Detalle_Factura
--   INSTEAD OF UPDATE
--AS 
--BEGIN TRY 	
--BEGIN TRANSACTION

----Si hay herramientas o productos ingresados en la factura
--IF ((SELECT CANTIDAD_HERRAMIENTAS_PROD FROM inserted) > 0)

----Pregunta si hay suficientes en el inventario de las herramientas o productos
--	IF ((SELECT CANTIDAD_HERRAMIENTAS_PROD FROM inserted) <= (SELECT CANTIDAD_DISPONIBLE FROM inserted INNER JOIN  Herramienta_Producto HP ON HP.COD_HERRAMIENTA_PROD = inserted.COD_HERRAMIENTA_PROD
--																WHERE inserted.COD_HERRAMIENTA_PROD = HP.COD_HERRAMIENTA_PROD))			
--			BEGIN
--			BEGIN TRANSACTION
--				INSERT INTO Detalle_Factura
--				SELECT COD_FACTURA, COD_HERRAMIENTA_PROD,CANTIDAD_HERRAMIENTAS_PROD, COD_PLANTA, CANTIDAD_PLANTAS,TOTAL_PAGAR, FECHA, OBSERVACIONES FROM inserted

--				--Se llama al SP para actualizar la tabla y se declaran y se enván los parámetros
--				DECLARE @parametro1 int
--				SET @parametro1  =  (SELECT COD_HERRAMIENTA_PROD FROM inserted)
--				DECLARE @parametro2 int
--				SET @parametro2 =(SELECT CANTIDAD_HERRAMIENTAS_PROD FROM inserted)
--				EXEC SP_RESTAR_CANTIDAD_HerrProd @parametro1,@parametro2
--			COMMIT TRANSACTION
--			END
--	ELSE	
--	BEGIN
--		RAISERROR('MENSAJE: No hay articulos suficientes',16,1)
--		ROLLBACK TRANSACTION 
--		PRINT ERROR_MESSAGE();
--	END
----Si hay plantas ingresadas en la factura
--COMMIT TRANSACTION

--IF ((SELECT CANTIDAD_PLANTAS FROM inserted) > 0)
--	IF ((SELECT CANTIDAD_PLANTAS FROM inserted) <= (SELECT CANTIDAD_DISPONIBLE FROM inserted INNER JOIN Planta P ON P.COD_PLANTA= inserted.COD_PLANTA
--																WHERE inserted.COD_PLANTA= P.COD_PLANTA))
--			--Se inserta todo si hay disponibles			
--			BEGIN			
--			BEGIN TRANSACTION
--				--INSERT INTO Detalle_Factura
--				--SELECT inserted.COD_PLANTA FROM inserted INNER JOIN Detalle_Factura DF ON inserted.COD_DETALLE = DF.COD_DETALLE
--				--WHERE DF.COD_DETALLE = inserted.COD_DETALLE		
--				UPDATE Detalle_Factura
--				SET CANTIDAD_PLANTAS= (SELECT CANTIDAD_PLANTAS FROM inserted )
--				WHERE COD_DETALLE =(select COD_DETALLE from inserted)
				
--				--Se llama al SP para actualizar la tabla y se declaran y se enván los parámetros
--				DECLARE @parametro3 int
--				SET @parametro3  =  (SELECT COD_PLANTA FROM inserted)
--				DECLARE @parametro4 int
--				SET @parametro4 =(SELECT CANTIDAD_PLANTAS FROM inserted)
--				EXEC SP_RESTAR_CANTIDAD_PLANTA @parametro3,@parametro4
--			COMMIT TRANSACTION
--			END
--	ELSE
--	BEGIN
--		RAISERROR('MENSAJE: No hay plantas suficientes',16,1)		
--		ROLLBACK TRANSACTION 
--		PRINT ERROR_MESSAGE();
--	END
--END TRY
--	BEGIN CATCH 	
--	print 'test'
--		PRINT ERROR_MESSAGE();
--	END CATCH
--GO


--=========================================
--Procedimiento Almacenado para saber el resumen del mes especificado
--=========================================
--CREATE OR ALTER PROCEDURE SP_RESUMIR_VENTAS
--	(@mes int,
--	@resumen float OUT)
--AS
--BEGIN
--	SET @resumen= (SELECT SUM(DF.TOTAL_PAGAR)
--					FROM Detalle_Factura DF
--					WHERE MONTH(DF.FECHA) = @mes)

--END
--GO


--=========================================
--Este Procedimiento Almacenado será llamado cuando el usuario quiera saber el bono de un empleado
--solo con consultar y enviando su id (empleado) y el mes que quiere saber se le devolverá su bono
--solución: suma de las ventas que hizo un empleado según el mes de la fecha y esa suma multiplicarlo por 0.05
--=========================================
--CREATE OR ALTER PROCEDURE SP_BONO (@COD_EMPLEADO int, @MES int, @BONO int OUT)
--AS
--BEGIN
--DECLARE @SUMA_PAGOS int

--SET @SUMA_PAGOS =( SELECT SUM(DF.TOTAL_PAGAR)
--				FROM Detalle_Factura DF INNER JOIN Factura F ON DF.COD_FACTURA = F.COD_FACTURA
--				WHERE (MONTH(DF.FECHA) = @MES) AND (F.COD_EMPLEADO= @COD_EMPLEADO) )
--				--AND (DF.COD_FACTURA = F.COD_FACTURA)

--SET @BONO = @SUMA_PAGOS * 0.05

--UPDATE Empleado
--SET BONO = @BONO
--WHERE COD_EMPLEADO = @COD_EMPLEADO

--RETURN @BONO
--END
--GO