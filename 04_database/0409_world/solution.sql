use ssafydb;

select * from city;
select * from country;
select * from countrylanguage;


# 1. 도시명 kabul이 속한 국가의 이름은?
select c.code, c.name from country c join city on city.countrycode = c.code where city.name like '%kabul%';

# 2. 국가의 공식 언어 사용율이 100%인 국가의 정보를 출력하시오. 국가 이름으로 오름차순 정렬한다.(8건) !!!!
select c.name, cl.language, cl.percentage from country c 
join countrylanguage cl on c.code=cl.countrycode 
where cl.percentage=100 and cl.IsOfficial =true
order by c.name;

# 3. 도시명 amsterdam에서 사용되는 주요 언어와 amsterdam이 속한 국가는?
select city.name, cl.language, c.name from city 
join countrylanguage cl on cl.CountryCode=city.CountryCode
join country c on city.CountryCode=c.code
where city.name='amsterdam'
and cl.isofficial = true;

# 4. 국가명이 united로 시작하는 국가의 정보와 수도의 이름, 인구를 함께 출력하시오. 단 수도 정보가 없다면 출력하지 않는다. (3건)
select c.name, c.capital, city.name 수도, city.population 수도인구
from city 
join country c on c.capital=city.id
where c.name like 'united%';

# 5. 국가명이 united로 시작하는 국가의 정보와 수도의 이름, 인구를 함께 출력하시오. 단 수도 정보가 없다면 수도 없음이라고 출력한다. (4건)
select c.name,
		ifnull(c.capital, null),
		ifnull(city.name, '수도 없음') '수도 이름', 
        ifnull(city.population, '수도 없음') '수도 인구'
from country c
left join city on c.capital = city.id
where c.name like 'united%';


# 6. 국가 코드 che의 공식 언어 중 가장 사용률이 높은 언어보다 사용율이 높은 공식언어를 사용하는 국가는 몇 곳인가?
-- (1) 국가 코드 che의 공식 언어 중 가장 사용률이 높은 언어
select * from countrylanguage where countrycode='che' and IsOfficial=true
and percentage >= (
	select max(percentage) from countrylanguage where countrycode='che' 
);

-- (2) 최종
select count(*) from country c
join countrylanguage cl on cl.CountryCode=c.code
where cl.Percentage > (
	select percentage from countrylanguage where countrycode='che' and IsOfficial=true
	and percentage >= (
		select max(percentage) from countrylanguage where countrycode='che' 
	)
)
and cl.IsOfficial=true;


# 7. 국가명 south korea의 공식 언어는?
select cl.language from countrylanguage cl
join country c on c.code=cl.CountryCode 
where c.name='south korea' and cl.IsOfficial=true;

# 8. 국가 이름이 bo로 시작하는 국가에 속한 도시의 개수를 출력하시오. (3건)


# 9. 국가 이름이 bo로 시작하는 국가에 속한 도시의 개수를 출력하시오. 도시가 없을 경우는 단독 이라고 표시한다.(4건)


# 10. 인구가 가장 많은 도시는 어디인가?
select city.countrycode, city.name, city.population from city
where city.population = (select max(population) from city);

# 11. 가장 인구가 적은 도시의 이름, 인구수, 국가를 출력하시오.
select c.name,c.code, city.name, city.Population from city
join country c on c.Capital=city.ID
where city.population = (select min(population) from city);

# 12. KOR의 seoul보다 인구가 많은 도시들을 출력하시오.
select city.countrycode, city.name, city.population
from city
where city.Population > (select population from city where name='seoul');

# 13. San Miguel 이라는 도시에 사는 사람들이 사용하는 공식 언어는?
select city.countrycode, cl.language from city
join countrylanguage cl on cl.CountryCode=city.CountryCode
where city.name='san miguel' and cl.IsOfficial=true;

# 14. 국가 코드와 해당 국가의 최대 인구수를 출력하시오. 국가 코드로 정렬한다.(232건)
select CountryCode, max(population) max_pop from city
group by countrycode
order by countrycode;

# 15. 국가별로 가장 인구가 많은 도시의 정보를 출력하시오. 국가 코드로 정렬한다.(232건)
select c.CountryCode, city.name, c.max_pop population
from (
	select CountryCode, max(population) max_pop from city
	group by countrycode
) as c
join city on c.CountryCode = city.CountryCode and c.max_pop = city.Population
order by c.CountryCode;

# 16. 국가 이름과 함께 국가별로 가장 인구가 많은 도시의 정보를 출력하시오.(239건)
select c.CountryCode, country.name, city.name, c.max_pop population
from (
	select CountryCode, max(population) max_pop from city
	group by countrycode
) as c
join city on c.CountryCode = city.CountryCode and c.max_pop = city.Population
join country on country.Code=c.CountryCode
order by c.CountryCode;

# 17. 위 쿼리의 내용이 자주 사용된다. 재사용을 위해 위 쿼리의 내용을 summary라는 이름의 view로 생성하시오.
create view summary as
select c.CountryCode, country.name '국가이름', city.name '도시이름', c.max_pop population
from (
	select CountryCode, max(population) max_pop from city
	group by countrycode
) as c
join city on c.CountryCode = city.CountryCode and c.max_pop = city.Population
join country on country.Code=c.CountryCode
order by c.CountryCode;

# 18. summary에서 code가 KOR인 국가의 대표도시를 조회하시오.
select * from summary where countrycode='KOR';
