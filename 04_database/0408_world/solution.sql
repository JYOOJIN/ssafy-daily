use ssafydb;

show tables;

desc country;

# 1) 현재 날짜와 올해가 몇 일이 지났는지,100일 후는 몇일인지 출력하시오.(아래는 2020년 기준 예시) 
select current_date() 오늘, datediff(current_date(), concat(year(current_date()), '-01-01')) '올해 지난 날',
date_add(current_date(), interval 100 day )  '100일 후' ;

# 2) country에서 asia에 있는 나라 중 희망 수명이 있는 경우에 기대 수명이 80 초과면 장수국가, 60 초과면 일반국가, 그렇지 않으면 단명국가라고 표현하시오. 기대 수명으로 정렬한다.(51건)
select name, continent, LifeExpectancy,
	case
		when LifeExpectancy > 80 then '장수국가'
        when LifeExpectancy > 60 then '일반국가'
        else '단명국가'
	end as '구분'
from country 
where continent='Asia' and LifeExpectancy is not null
order by LifeExpectancy;

# 3) country에서 (gnp-gnpold)를 gnp 향상이라고 표현하시오. 단 gnpold가 없는 경우 신규라고 출력하고 name으로 정렬한다.(239건)
select name, gnp, gnpold,
	case
		when gnpold is null then '신규'
		else gnp-gnpold
    end as 'gnp 향상'
from country
order by name;


# 4) 2020년 어린이 날이 평일이면 행복, 토요일 또는 일요일이면 불행이라고 출력하시오.
select weekday('2020-05-05'),
	case
		when weekday('2020-05-05')=1 then '행복'
        else '불행'
	end as '행복여부';

# 5) country에서 전체 자료의 수와 독립 연도가 있는 자료의 수를 각각 출력하시오.
select count(*) 전체, count(IndepYear) '독립 연도 보유'
from country;

# 6)  country에서 기대 수명의 합계, 평균, 최대, 최소를 출력하시오. 평균은 소수점 2자리로 반올림한다.
select round(sum(LifeExpectancy),2) 합계, round(avg(LifeExpectancy),2) 평균, round(max(LifeExpectancy),2) 최대, round(min(LifeExpectancy),2) 최소
from country;

# 7) country에서 continent 별 국가의 개수와 인구의 합을 구하시오. 국가 수로 정렬 처리한다.(7건)
select Continent, count(Name) '국가 수', sum(Population) '인구 합'
from country
group by continent 
order by count(name) desc;

# 8) country에서 대륙별 국가 표면적의 합을 구하시오. 면적의 합으로 내림차순 정렬하고 상위 3건만 출력한다.
select continent, sum(SurfaceArea) '표면적 합'
from country
group by continent
order by sum(SurfaceArea) desc limit 3;

# 9) country에서 대륙별로 인구가 50,000,000이상인 나라의 gnp 총 합을 구하시오. 합으로 오름차순 정렬한다.(5건)
select continent, sum(gnp) 'gnp 합'
from country
where Population >= 50000000
group by continent
order by sum(gnp);


# 10) country에서 대륙별로 인구가 50,000,000이상인 나라의 gnp 총 합을 구하시오. 이때 gnp의 합이 5,000,000 이상인 것만 구하시오.
select continent, sum(gnp) 'gnp 합'
from country
where Population >= 50000000
group by continent
having sum(gnp) >= 5000000;

# 11) country에서 연도별로 10개 이상의 나라가 독립한 해는 언제인가?
select indepyear, count(*) '독립 국가 수'
from country
where IndepYear is not null
group by IndepYear
having count(*) >= 10;

# 12) country에서 국가별로 gnp와 함께 전세계 평균 gnp, 대륙 평균 gnp를 출력하시오.(239건)
select c.continent, c.name, c.gnp, 
(select avg(gnp) from country) '전세계 평균', 
(select avg(gnp) from country where continent=c.continent) '대륙 평균'
from country c
order by continent;

