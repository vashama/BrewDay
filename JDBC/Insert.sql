CREATE FUNCTION MyInsert(_sno integer, _eid integer, _sd date, _ed date, _sid integer, _status boolean)
  RETURNS void AS
  $BODY$
      BEGIN
        INSERT INTO app_for_leave(sno, eid, sd, ed, sid, status)
        VALUES(_sno, _eid, _sd, _ed, _sid, _status);
      END;
  $BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
  
CREATE PROCEDURE dbo.uspGetAddress @City nvarchar(30)
AS
SET NOCOUNT ON
SELECT * 
FROM Person.Address
WHERE City = @City
PRINT @@ROWCOUNT
GO

CREATE PROCEDURE dbo.uspGetAddressCount @City nvarchar(30), @AddressCount int OUT
AS
SELECT @AddressCount = count(*) 
FROM AdventureWorks.Person.Address 
WHERE City = @City
