**API 명세서**

|기능|HTTP 메서드|API PATH|REQUEST|RESPONSE|
| :- | :- | :- | :- | :- |
|-일정-|||||
|일정 등록|POST|/api/todo|<p>@RequestBody</p><p>"userId" : 유저 Id,</p><p>"todoTitle" : "일정 제목",</p><p>"todoContents" : "일정 내용"</p>|<p>{</p><p>"id": 일정 id,</p><p>"todoTitle": "일정 제목",</p><p>"todoContents": "일정 내용",</p><p>"createdAt": "최초 생성 날짜/시간",</p><p>"modifiedAt": "최종 수정 날짜/시간"</p><p>}</p>|
|일정 단건 조회|GET|/api/todo/id|<p>@RequestParam</p><p>id = 일정Id</p>|<p>{</p><p>"todoResponseDto": {</p><p>"id": 일정 Id,</p><p>"todoTitle": "일정 제목",</p><p>"todoContents": "일정 내용",</p><p>"createdAt": “최초 생성 날짜/시간",</p><p>"modifiedAt": “최종 수정 날짜/시간"</p><p>},</p><p>"userWithoutDateResponseDtoList": [</p><p>{</p><p>"id": 유저Id,</p><p>"username": "유저1",</p><p>"email": "유저 이메일"</p><p>} ] }</p>|
|일정 전체 조회|GET|/api/todo|<p>@RequestParam </p><p>size = 페이지 사이즈(기본값 : 10)</p><p>page = 페이지 번호(기본값: 0)</p>|<p>{</p><p>"content": [</p><p>{</p><p>"id": 일정 ID,</p><p>"todoTitle": “일정 제목",</p><p>"todoContents": "내용 수정 테스트",</p><p>"createdAt": "최초 생성 날짜/시간",</p><p>"modifiedAt": "최종 수정 날짜/시간"</p><p>}</p><p>],</p><p>"pageable": {</p><p>"pageNumber": 0,</p><p>"pageSize": 10,</p><p>"sort": {</p><p>"empty": false,</p><p>"sorted": true,</p><p>"unsorted": false</p><p>},</p><p>"offset": 0,</p><p>"paged": true,</p><p>"unpaged": false</p><p>},</p><p>"last": true,</p><p>"totalPages": 총 페이지 수,</p><p>"totalElements": 총 일정 갯수,</p><p>"first": true,</p><p>"numberOfElements": 페이지에 표시된 일정 갯수,</p><p>"size": 10,</p><p>"number": 0,</p><p>"sort": {</p><p>"empty": false,</p><p>"sorted": true,</p><p>"unsorted": false</p><p>},</p><p>"empty": false</p><p>}</p>|
|일정 담당 유저 추가|PUT|/api/todo/add|<p>@RequestParam</p><p>todoId = 일정 Id</p><p>userId = 유저 Id</p>|(따로 응답 없음)|
|일정 수정(admin전용)|PUT|/api/todo/admin|<p>@RequestParam</p><p>id = 일정 Id</p><p></p><p>@RequestBody</p><p>"todoTitle" : “수정할 일정 제목",</p><p>"todoContents" : “수정할 일정 내용"</p>|<p>{</p><p>"id": 일정id,</p><p>"todoTitle": "일정 제목",</p><p>"todoContents": "일정 내용",</p><p>"createdAt": "최초 생성 날짜/시간",</p><p>"modifiedAt": "최종 수정 날짜/시간"</p><p>}</p>|
|일정 삭제(admin 전용)|DELETE|/api/todo/admin|<p>@requestParam</p><p>id = 일정 id</p>|<p>{</p><p>"id": 일정 id,</p><p>"todoTitle": "일정 제목",</p><p>"todoContents": "일정 내용",</p><p>"createdAt": "최초 생성 날짜/시간",</p><p>"modifiedAt": "최종 수정 날짜/시간"</p><p>}</p>|
|-댓글-|||||
|댓글 작성 |POST|/api/comment|<p>@RequestParam</p><p>id = 일정 Id</p><p></p><p>@RequestBody</p><p>{</p><p>"username" : "작성자명",</p><p>"commentContents" : "작성 내용"</p><p>}</p>|<p>{</p><p>"username": "작성자명",</p><p>"commentContents": "작성 내용",</p><p>"createdAt": "최초 생성 날짜/시간",</p><p>"modifiedAt": "최종 수정 날짜/시간",</p><p>"message": "등록 완료"</p><p>}</p>|
|댓글 단건 조회|GET|/api/comment/readById|<p>@RequestParam</p><p>commentId = 댓글 Id</p><p>todoId = 일정 Id</p>|<p>{</p><p>"username": "작성자명",</p><p>"commentContents": "작성 내용",</p><p>"createdAt": "최초 생성 날짜/시간",</p><p>"modifiedAt": "최종 수정 날짜/시간",</p><p>"message": "조회 성공"</p><p>}</p>|
|댓글 전체 조회|GET|/api/comment|<p>@RequestParam</p><p>todoId = 일정 Id</p>|<p>[</p><p>{</p><p>"username": "작성자명",</p><p>"commentContents": "작성 내용",</p><p>"createdAt": "최초 생성 날짜/시간",</p><p>"modifiedAt": "최종 수정 날짜/시간",</p><p>"message": "조회 성공"</p><p>}, (위 양식 반복)</p><p>]</p>|
|댓글 수정|PUT|/api/comment|<p>@RequestParam</p><p>commentId = 댓글 Id</p><p>todoId = 일정 Id</p>|<p>{</p><p>"username": "작성자명",</p><p>"commentContents": "작성 내용",</p><p>"createdAt": "최초 생성 날짜/시간",</p><p>"modifiedAt": "최종 수정 날짜/시간",</p><p>"message": "수정 완료"</p><p>}</p>|
|댓글 삭제|DELETE|/api/comment|<p>@RequestParam</p><p>commentId = 댓글 Id</p><p>todoId = 일정 Id</p>|<p>{</p><p>"username": "작성자명",</p><p>"commentContents": "작성 내용",</p><p>"createdAt": "최초 생성 날짜/시간",</p><p>"modifiedAt": "최종 수정 날짜/시간",</p><p>"message": "삭제 완료"</p><p>}</p>|
|-유저-|||||
|유저 단건 조회|GET|/api/user/id|<p>@RequestParam</p><p>id = 유저 id</p>|<p>{</p><p>"id": 유저 Id,</p><p>"username": "유저명",</p><p>"email": "유저 이메일",</p><p>"createdAt": "최초 생성 날짜/시간",</p><p>"modifiedAt": "최종 수정 날짜/시간",</p><p>"role": “권한",</p><p>"message": "조회 성공"</p><p>}</p>|
|유저 전체 조회|GET|/api/user||<p>[</p><p>{</p><p>"id": 유저 Id,</p><p>"username": "유저명",</p><p>"email": "유저 이메일",</p><p>"createdAt": "최초 생성 날짜/시간",</p><p>"modifiedAt": "최종 수정 날짜/시간",</p><p>"role": “권한",</p><p>"message": "조회 성공"</p><p>}, (위 양식 반복)</p><p>]</p>|
|유저 삭제|DELETE|/api/user|<p>@RequestParam</p><p>id = 유저 id</p>|<p>{</p><p>"id": 유저 Id,</p><p>"username": "유저명",</p><p>"email": "유저 이메일",</p><p>"createdAt": "최초 생성 날짜/시간",</p><p>"modifiedAt": "최종 수정 날짜/시간",</p><p>"role": “권한",</p><p>"message": "삭제 완료"</p><p>}</p>|
|-회원가입/로그인-|||||
|유저 등록|POST|/api/auth/regi|<p>@RequestBody</p><p>{</p><p>"username" : "유저명",</p><p>"password" : "비밀번호",</p><p>"email" : "이메일",</p><p>"role" : "권한" (USER, ADMIN)</p><p>}</p>|<p>@ResponseBody</p><p>{</p><p>"id": 유저 Id,</p><p>"username": "유저명",</p><p>"email": "유저 이메일",</p><p>"createdAt": "최초 생성 날짜/시간",</p><p>"modifiedAt": "최종 수정 날짜/시간",</p><p>"role": “권한",</p><p>"message": “등록 완료"</p><p>}    </p><p>@Cookie</p><p>Name : Authorization</p><p>Value : Bearer%20(JWT)</p>|
|로그인|GET|/api/auth/login|<p>@RequestBody</p><p>{</p><p>"email" : "이메일",</p><p>"password" : "비밀번호"</p><p>}</p>|로그인 성공. JWT 토큰 : Bearer (JWT)|


**ERD**
https://github.com/user-attachments/assets/068b66f6-3ccb-4d1b-8444-cc19f5db63d5
