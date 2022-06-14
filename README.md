# 테크 스펙

작성자: 안지환  
마지막 수정일: 2022.06.13

### 배경 (Background)
점주분들이 공통 질문 사항들이 자주 올라 온다고 합니다.  
공통 질문 사항에 대해서 카테고리 별로 구분해서 질문을 공통화 했으면 좋겠다는 요청이 들어와서 관리자 페이지에 따로 FAQ 게시판을 만들게 되었습니다.
 

### 목표 (Goals)
공통화 된 질문은 FAQ 카테고리 게시판에 관리자가 등록 할 수 있습니다.  
 

### 목표가 아닌 것 (Non-goals)
사용자 질문 게시판과 FAQ 카테고리 게시판은 혼동 되어서는 안됩니다.  
한 가지 질문 사항에 FAQ 카테고리 게시판에 등록을 해서는 안됩니다.   
공통 질문 사항과 자주 묻는 질문 사항은 엄격하게 구분 해야 합니다. 


### 계획 (Plan) 

### 1. 가맹점 카테고리

**1.1 카테고리 목록**

| 카테고리명 | 최근수정일시 |
| --- | --- |
| storeFaqCategoryName | modifiedDate |

```java
@Getter
public FaqCategoryList {
	
	private FaqCategoryType storeFaqCategoryName;
	private LocalDateTime modifiedDate;
}
```

**1.2 카테고리 등록**

| FAQ카테고리명 |
| --- |
| storeFaqCategoryName |

```java
@Getter
public static class FaqCategoryListCreate {
	
	private FaqCategoryType storeFaqCategoryName;
}
```

**1.3 카테고리 수정**

| FAQ카테고리명 |
| --- |
| storeFaqCategoryName |

```java
@Getter
public static class FaqCategoryListUpdate {
	
	private FaqCategoryType storeFaqCategoryName;
}
```

## 2. 가맹점 목록

**2.1 가맹점 목록**

| 순번 | 카테고리 구분 | FAQ 제목 | 등록일시 |
| --- | --- | --- | --- |
| storeFaqId | storeFaqName | storeFaqTitle | createDate |

```java
@Getter
public static class StoreFaqList {
	
	private Long storeFaqId;

	private FaqCategoryType storeFaqCategoryName;
	
	private String storeFaqTitle;

	private LocalDateTime createDate;

	private Boolean expose;
	
}
```

**2.2 가맹점 FAQ 등록**

| 카테고리구분 | FAQ제목 | 사용자답변내용 |
| --- | --- | --- |
| storeFaqName | storeFaqTitle | storeFaqReplyContent |

```java
public static class StoreFaqCreate {

	private FaqCategoryType storeFaqCategoryName;

	private String storeFaqTitle;

	private String storeFaqReplyContent;
}
```

## 3. 사용자 카테고리 목록

**3.1 사용자카테고리 목록**

| 사용자FAQ카테고리명 | 최근수정일 |
| --- | --- |
| appUserFaqCategoryName | modifiedDate |

```java
public static class AppUserFaqList {
	
	private FaqCategoryType appUserFaqCategoryName;
	
	private LocalDateTime modifiedDate;
}
```

**3.2 사용자 카테고리 등록**

| FAQ카테고리명 |
| --- |
| appUserFaqCategoryName |

```java
public static class AppUserFaqCreate {
	
	private FaqCategoryType appUserFaqCategoryName;
}
```

**3.3 사용자 카테고리 수정**

| FAQ카테고리명 |
| --- |
| appUserFaqCategoryName |

```java
public static class AppUserFaqUpdate{
	
	private FaqCategoryType appUserFaqCategoryName;
}
```

## 4. 사용자  목록

4**.1 사용자 목록**

| 사용자순번 | 사용자FAQ카테고리 구분 | 사용자FAQ 제목 | 등록일시 |
| --- | --- | --- | --- |
| appUserFaqId | appUserFaqCategoryName | appUserFaqTitle | craeteDate |

```java
public static class AppUserList{

		private Long appUserFaqId;

		private FaqCategoryType appUserFaqCategoryName;

		private String appUserFaqTitle;

		private LocalDateTime createDate;
		
}
```

4**.2 사용자 FAQ 등록**

| 카테고리구분 | FAQ제목 | 사용자답변내용 |
| --- | --- | --- |
| appUserFaqName | appUserFaqTitle | appUserFaqReplyContent |

```java
public static class AppUserCreate {

		private FaqCategoryType appUserFaqCategoryName;
		private String appUserFaqTitle;
		private String appUserFaqReplyContent;
}
```

## Enum 타입으로 관리

같은 기능인데 enum 으로 다르게해서 정리하기

- Type  : 유형
- Status : 상태
- Order : 최근순, 옛날순, 가다라순

### Enum

- **FaqType**

```java

public enum FaqType implements EnumMapperType {
		
	STORE_FAQ("가맹점"),
	APPUSER_FAQ("사용자"),
	;

	private String title;

	public String getTitle() {
		return title;
	}
}
```

- **FaqCategoryGroup - domain**

```java
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public FaqCategoryGroup extends BaseEntity {

	@Id
	@GeneratedValue(stategy = GenerationType.IDENTITY)	
	private Long FaqCategoryGroupId; // id
	private String title; // 카테고리명
	private boolean expose = false; // 노출 여부

	@Enumerated(EnumType.STRING)
	private FaqType faqTypel; // FAQ 유형

	@OneToMany(mappedBy = "faqCategoryGroup", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FaqCategory> faqCateGorys = new ArrayList<>(); //카테고리 구분
	
}
```

- **FaqCategory - domain**

```java
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public FaqCategory extends BaseEntity {

	@Id
	@GeneratedValue(stategy = GenerationType.IDENTITY)	
	private Long FaqCategoryId; // id
	private String title; // 제목
	private String content; //내용
	private boolean expose = false; //노출 여부
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "faqCategoryGroup_id")
	private FaqCategoryGroup faqCategoryGroups;

}
```


### 이외 고려사항들 (Other Considerations)
검색 기능에 대해서 검토를 진행 했었으나, 아직 검색이 필요한 만큼 데이터가 충분 하지 않을꺼 같고 페이징으로 충분 한다고 판단이 되어 검색 기능은 추가 하지 않기로 함.

### 마일스톤 (Milestones)

~ 4/25: FAQ 카테고리 API 설계  
4/26 ~ 4/27: 개발 진행  
4/28 ~ 4/29: API 테스트 및 버그 수정
 
