-- ================================================
-- Template generated from Template Explorer using:
-- Create Trigger (New Menu).SQL
--
-- Use the Specify Values for Template Parameters 
-- command (Ctrl-Shift-M) to fill in the parameter 
-- values below.
--
-- See additional Create Trigger templates for more
-- examples of different Trigger statements.
--
-- This block of comments will not be included in
-- the definition of the function.
-- ================================================
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE OR ALTER TRIGGER SumarDelete
   ON  Detalle_Factura
   for delete
AS 
BEGIN TRY 	
BEGIN TRANSACTION
	
	declare @cant int = (select cantDetalle from deleted);
	declare @cod int = (select codProducto from deleted);

	update Productos
	set cantDisponible = cantDisponible + @cant
	where codProducto = @cod

COMMIT TRANSACTION
END TRY
	BEGIN CATCH 	
		PRINT ERROR_MESSAGE();
	END CATCH
GO
