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

 
  <form action="update" method="POST">
    <div>
      순서 :
      <input type="text" name="deptId" value="${todo.listNo}" readolny>
     
    </div>
  
    <div>
      할 일 제목:
      <input type="text" name="todoTitle"
       value= "${todo.lilstTitle}" >
    </div>

    <div>
      등록 날짜 : 
      <input type="text" name="todoContent"
       value="${todo.listcontent}">
    </div>
    
   <button>수정</button>
  </form>
    
</body>
</html>