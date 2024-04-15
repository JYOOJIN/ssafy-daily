use ssafydb;

desc movie;

select * from movie;

# 1) movie 테이블에 전채 몇개의 행이 있는지 조회하시오.
select count(*) '영화 수' from movie;

# 2) 전체 영화의 상영시간 평균을 조회하시오.
select avg(RunningTime) '평균' from movie;

# 3) 전체 영화의 상영시간 합을 조회하시오.
select sum(RunningTime) '총 합' from movie;

# 4) 제목에 '포터' 단어가 포함된 영화들의 상영시간 평균을 둘째짜리 까지 반올림 하여 조회하시오.
select  round(avg(RunningTime),2) '포터 평균' 
from movie
where  Title like '%포터%' ;

# 5) cinemacode 로 그룹핑 했을때 각 그룹에 몇개의 영화가 포함되어 있는지 조회하시오.
select cinemacode, count(*) '수'
from movie
group by cinemacode;

# 6) cinemacode 로 그룹핑 했을때 각 그룹의 제일 먼저 개봉한 영화의 날짜를 조회하시오.
select cinemacode, min(ReleaseDate) 날짜
from movie
group by cinemacode;

# 7) cinemacode 로 그룹핑 했을때 각 그룹의 제일 긴 상영시간이 몇분인지 조회하시오.
select cinemacode, max(RunningTime) 최대
from movie
group by cinemacode;

# 8) cinemacode 로 그룹핑 했을때 상영시간의 평균이 150분 이상인 그룹의 cinemacode 만 조회하시오.
select cinemacode, avg(RunningTime) 평균
from movie
group by cinemacode
having avg(RunningTime)>=150;

# 9) cinemacode로 그룹핑 했을때 상영시간의 합이 300분 이상인 그룹의 cinemacode 만 조회하시오.
select cinemacode, sum(RunningTime) 합
from movie
group by cinemacode
having sum(RunningTime)>=300;

# 10) cinemacode 로 그룹핑 했을때 상영시간의 평균이 150분 이상인 그룹들 중 상영시간의 총 합이 300 이상인 그룹의 cinemacode를 조회하시오.
select cinemacode, avg(RunningTime), sum(RunningTime)
from movie
group by cinemacode
having avg(RunningTime)>=150 and sum(RunningTime)>=300;




