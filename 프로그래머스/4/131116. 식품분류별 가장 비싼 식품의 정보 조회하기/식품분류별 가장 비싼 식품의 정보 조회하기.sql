SELECT FP.CATEGORY, FP.PRICE AS MAX_PRICE, FP.PRODUCT_NAME
FROM FOOD_PRODUCT FP
WHERE CATEGORY IN ('과자', '국', '김치', '식용유') AND PRICE IN (SELECT MAX(PRICE) FROM FOOD_PRODUCT GROUP BY CATEGORY)
ORDER BY FP.PRICE DESC;

# SELECT * FROM FOOD_PRODUCT;

# 식품분류별 가격이 제일 비싼 식품
#

# SELECT * FROM FOOD_PRODUCT;
# SELECT MAX(PRICE) FROM FOOD_PRODUCT GROUP BY CATEGORY;