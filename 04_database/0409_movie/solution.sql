use ssafydb;

select * from movie;
select * from cinema;

# 1. movie 테이블과 cinema 테이블을 full outer 조인하면 몇개의 행이 조회되는시 출력하시오.
select count(*) 
from movie, cinema;

# 2. 모든 영화가 어떤 cinema에서 상영중인지 알 수 있도록 영화 정보와 시네마 정보를 모두 출력하시오.
select * from movie m join cinema c on m.CinemaCode=c.CinemaCode;

# 3. 서울에 있는 시네마에서 상영하는 영화들을 조회하시오.
select * from movie m join cinema c on m.CinemaCode=c.CinemaCode where c.Location='서울';

# 4. 광주에 있는 시네마에서 상영하는 영화가 몇개인지 조회하시오.
select count(*) '상영 중', c.Location from movie m join cinema c on m.CinemaCode=c.CinemaCode where c.Location='광주';

# 5. cinema 이름으로 그룹핑 하여 각 시네마 마다 몇개의 영화가 상영중인지 조회하시오.
select c.CinemaName, count(*) '상영 중' from movie m join cinema c on m.CinemaCode=c.CinemaCode group by c.CinemaName;

# 6. cinema 이름으로 그룹핑 하여 각 시네마 마다 몇개의 영화가 상영중인지 출력하는데 상영중인 영화가 0개인 시네마도 조회하시오.
select c.CinemaName, ifnull(count(m.CinemaCode),0) '상영 중' from cinema c left join movie m on m.CinemaCode=c.CinemaCode group by c.CinemaName;

# 7. cinema 지역으로 그룹핑 하는데 상영하는 영화의 개수가 1개인 시네마의 지역들을 조회하시오. 
select count(*) '상영 중', location 지역
from cinema c join movie m on c.cinemacode = m.cinemacode
group by location
having count(ID) = 1;

# 8. 영화 이름이 '이터널스' 인 영화의 상영 시간 이상인 영화 이름과 상영 시간을 출력하시오.
-- (1) 영화 이름이 '이터널스' 인 영화의 상영 시간
select runningtime from movie where title='이터널스';

-- (2) subquery 이용
select title, runningtime from movie where runningtime >= (select runningtime from movie where title='이터널스');

# 9. 상영 시간이 156분 이상인 영화들을 상영중인 cinema 이름을 출력하시오.
-- (1) 상영 시간이 156분 이상인 영화들
select * from movie where runningtime>=156;

-- (2) 위 영화들을 상영중인 cinema 이름
select c.cinemaname from cinema c join movie m on m.CinemaCode=c.CinemaCode where m.runningtime>=156;

# 10. 상영 시간이 156분 이상인 영화들 중 제목에 '해리포터' 라는 단어를 포함하는 영화 제목과 상영시간을 출력하시오.
select title, runningtime from movie  where title like '%해리포터%' and runningtime>=156;


