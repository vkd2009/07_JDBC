<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${todo.listNo} 수정 페이지</title>
</head>
<body>
  <h1>${todo.listNo} 수정 페이지</h1>

  <%-- 현재 주소 : /department/update (GET) --%>
  <form action="update" method="POST">
    <div>
      순서 :
      <input type="text" name="deptId" value="${todo.listNo}" readolny>
      <%-- readonly 속성 : 읽기 전용(input태그 값 수정 불가) --%>
    </div>
  
    <div>
      할 일 제목:
      <input type="text" name="deptTitle"
       value= "${todo.lilstTitle}" >
    </div>

    <div>
      등록 날짜 : 
      <input type="text" name="locationId"
       value="${todo.content}">
    </div>
    
   <button>수정</button>
  </form>
    
</body>
</html>