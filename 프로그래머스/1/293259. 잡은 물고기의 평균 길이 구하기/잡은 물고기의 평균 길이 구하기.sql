-- 잡은 물고기 평균 길이 AVERAGE_LENGTH, 2째자리
-- 10이하 물고기는 10cm로 취급

SELECT ROUND(AVG(LENGTH), 2) AS AVERAGE_LENGTH
FROM (
    SELECT
        CASE
            WHEN LENGTH > 10 THEN LENGTH
            ELSE 10
        END AS LENGTH
    FROM FISH_INFO
) FISH;