package daily_0202;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/* TODO: 도서 도메인 클래스에 대한 정렬을 해봅시다.
 * 
 * 1. 도서 도메인(Book)
 * 	- isbn:String
 * 	- title:String
 * 	- price:int
 * 
 * 2. 도서 도메인 정보
 * 		Book b1 = new Book("book-123", "Java의 정석", 50_000);
 * 		Book b2 = new Book("book-423", "Web Programming", 35_000);
 * 		Book b3 = new Book("book-111", "Data Base", 10_000);
 * 		Book b4 = new Book("book-222", "Web Programming", 15_000);
 * 		Book b5 = new Book("book-511", "Web Programming", 10_000);
 * 
 * 1. 고정 기본 정렬: isbn 올림차순
 * 	- 도서 기본 정렬 방식(도서번호 올림차순)
 * 	- 도메인 클래스에 기본 정렬 재정의
 * 	- implements Comparable#compareTo(<T>)
 * 
 * 2. 가변 정렬
 * 	- 필요시에 재정의 해서 사용
 * 	- implements Comparator#compare(<T>,<T>)
 * 	(1) 도서번호 내림차순
 * 	(2) 도서제목 올림차순
 * 	(3) 도서제목 내림차순
 * 	(4) 도서가격 올림차순
 *  (5) 도서가격 내림차순
 *  (6) 도서제목 올림차순, 단, 동일 제목이 있으면 가격이 낮은 순서데로
 */

public class ComparableAndComparatorSortTest {
	public static void main(String[] args) {
		// 초기 테스트 데이터
		Book b1 = new Book("book-123", "Java의 정석", 50_000);
		Book b2 = new Book("book-423", "Web Programming", 35_000);
		Book b3 = new Book("book-111", "Data Base", 10_000);
		Book b4 = new Book("book-222", "Web Programming", 15_000);
		Book b5 = new Book("book-511", "Web Programming", 10_000);
		
		Book[] array= {b1,b2,b3,b4,b5};
		arraySort(array);
		
		List<Book> list=Arrays.asList(array);
		//List<Book> list=Arrays.asList(b1,b2,b3,b4,b5)
		listSort(list);
	}

	public static void arraySort(Book[] array) {
		 System.out.println("\n1. 도서 정렬 전 원본 출력");
		 printBookArray(array); 
		 
		 System.out.println("\n2. 도서 기본 정렬 방식(도서번호 올림차순) 출력");
		 Arrays.sort(array); //원본을 바꿔준다
		 printBookArray(array); 
		 
		 System.out.println("\n3. 도서 동적 정렬 (도서번호 내림차순) 출력");
		 Arrays.sort(array,new Comparator<Book>() {
			 
			 //Interface 재정의
			 @Override
				public int compare(Book o1, Book o2) {
					return o2.getIsbn().compareTo(o1.getIsbn());
				}

		});
		 printBookArray(array);
		
		 
		 System.out.println("\n4. 도서 동적 정렬 (도서제목 올림차순) 출력");
//		 Arrays.sort(array,new Comparator<Book>() {
//			 
//			 @Override
//			public int compare(Book o1, Book o2) {
//				return o1.getTitle().compareTo(o2.getTitle());
//			}
//		});
//		 
		 // Lambda 표현식 변경
		 Arrays.sort(array, (o1,o2)->o1.getTitle().compareTo(o2.getTitle()));
		 printBookArray(array);
		 
		 System.out.println("\n5. 도서 동적 정렬 (도서제목 내림차순) 출력");
		 Arrays.sort(array, (o1,o2)->o2.getTitle().compareTo(o1.getTitle()));
		 printBookArray(array);
		 
		 System.out.println("\n6. 도서 동적 정렬 (도서가격 올림차순) 출력");
		 Arrays.sort(array, (o1,o2)->o1.getPrice()-o2.getPrice());
		 printBookArray(array);
		 
		 System.out.println("\n7. 도서 동적 정렬 (도서가격 내림차순) 출력");
		 Arrays.sort(array, (o1,o2)->o2.getPrice()-o1.getPrice());
		 printBookArray(array);
		 
		 System.out.println("\n8. 도서제목 올림차순, 단, 동일 제목이 있으면 가격이 낮은 순서데로 정렬 출력");
		 Arrays.sort(array,new Comparator<Book>() {
			 
			 public int compare(Book o1, Book o2) {
				 if(o1.getTitle().compareTo(o2.getTitle())==0) {
					 return o1.getPrice()-o2.getPrice();
				 }
				 return o1.getTitle().compareTo(o2.getTitle());
			 };
		});
		 
		 
		 	
	}

	public static void listSort(List<Book> list) {
		 System.out.println("\n1. 도서 정렬 전 원본 출력");
		 
		 System.out.println("\n2. 도서 기본 정렬 방식(도서번호 올림차순) 출력");
		 
		 System.out.println("\n3. 도서 동적 정렬 (도서번호 내림차순) 출력");
		 
		 System.out.println("\n4. 도서 동적 정렬 (도서제목 올림차순) 출력");
		 
		 System.out.println("\n5. 도서 동적 정렬 (도서제목 내림차순) 출력");
		 
		 System.out.println("\n6. 도서 동적 정렬 (도서가격 올림차순) 출력");
		 
		 System.out.println("\n7. 도서 동적 정렬 (도서가격 내림차순) 출력");
		 
		 System.out.println("\n8. 도서제목 올림차순, 단, 동일 제목이 있으면 가격이 낮은 순서데로 정렬 출력");
	}
	
	
	
	public static void printBookArray(Book[] array) {
//		for(Book b:array) {
//			 System.out.println(b);
//		 }
//		 
		 // 01. 02. 03. ... 99.
		 for(int i=0;i<array.length;i++) {
			 System.out.printf("%02d. %s\n", i+1,array[i]);
		 }
	}
	
	public static void printBookList(List<Book> list) {
//		for(Book b:array) {
//			 System.out.println(b);
//		 }
//		 
		 // 01. 02. 03. ... 99.
		 for(int i=0;i<list.size();i++) {
			 System.out.printf("%02d. %s\n", i+1,list.get(i));
		 }
	}
	
}

// 도서 기본 정렬: 도서번호 올림차순
// 올림차순: (고정)this.xx(other.xx) / (동적)o1.xxx(o2.xxx)
// 내림차순: (고정)other.xx(this.xx) / (동적)o2.xxx(o1.xxx)

class Book implements Comparable<Book>{
	private String isbn;
	private String title;
	private int price;
	
	
	public Book(String isbn, String title, int price) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.price = price;
	}
	

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(isbn);
		builder.append(", ");
		builder.append(title);
		builder.append(", ");
		builder.append(price);
		return builder.toString();
	}
	
	
	
	

	//도서 기본 정렬: 도서번호(String) 올림차순
	@Override
	public int compareTo(Book o) {
		return this.getIsbn().compareTo(o.getIsbn());
	}
	

	
}