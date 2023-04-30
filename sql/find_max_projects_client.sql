SELECT client.name, COUNT(project.id) AS project_count
FROM client
JOIN project ON client.id = project.client_id
GROUP BY client.id
HAVING COUNT(project.id) = (
   SELECT COUNT (id) AS project_count
   FROM project
   GROUP BY client_id
   ORDER BY project_count DESC
   LIMIT 1
);