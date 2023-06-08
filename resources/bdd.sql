-- CREATE VIEW Commande_client as 
-- SELECT cl.id as idclient , p.id as idproduit, p.libelle, p.prix, c.quantite, p.quantiteEnStock
-- FROM Commande AS c
-- JOIN Produit AS p ON p.id = c.idproduit 
-- JOIN Client as cl on c.idclient = cl.id;

select *