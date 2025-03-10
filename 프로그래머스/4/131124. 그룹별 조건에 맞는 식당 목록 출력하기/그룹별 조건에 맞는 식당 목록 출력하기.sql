-- 리뷰를 가장 많이 작성한 회원의 리뷰
-- 회원 이름, 리뷰 텍스트, 리뷰 작성일
-- 리뷰 작성일, 리뷰 텍스트
SELECT MEMBER_NAME, REVIEW_TEXT, DATE_FORMAT(REVIEW_DATE, '%Y-%m-%d') AS REVIEW_DATE
FROM (
    SELECT MEMBER_ID, REVIEW_TEXT, REVIEW_DATE
    FROM REST_REVIEW
    WHERE MEMBER_ID = (SELECT MEMBER_ID FROM REST_REVIEW GROUP BY MEMBER_ID ORDER BY COUNT(*) DESC LIMIT 1)
    ) REVIEW LEFT JOIN MEMBER_PROFILE ON REVIEW.MEMBER_ID = MEMBER_PROFILE.MEMBER_ID
ORDER BY 3, 2;