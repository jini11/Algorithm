-- 코드를 입력하세요
# SELECT animal_id, name, if(sex_upon_intake like "%Neutered%" or sex_upon_intake like "%Spayed%", 'O', 'X') as 중성화
# from animal_ins
# order by animal_id;

-- 동물의 아이디, 이름, 중성화 여부
-- 아이디 순으로 조회

SELECT ANIMAL_ID, NAME, IF(SEX_UPON_INTAKE LIKE '%Neutered%' OR SEX_UPON_INTAKE LIKE '%Spayed%', 'O', 'X') 중성화
FROM ANIMAL_INS
ORDER BY ANIMAL_ID;