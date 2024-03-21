# Write your MySQL query statement below（患某种疾病的患者）

select patient_id, patient_name, conditions
from Patients
where conditions regexp '^DIAB1' or conditions regexp ' DIAB1'
;
