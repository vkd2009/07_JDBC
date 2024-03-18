<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>버킷리스트 작성</title>
</head>
<body>
    <h1>버킷리스트 작성</h1>
    <hr>

    <form action="/list/add" method="POST">
      <fieldset>
        <legend>버킷리스트</legend>
         <div>
            제목 : <input type="text" name="listTitle">
         </div>
         <br>
         <div>
            <textarea name="listContent" 
         cols="50" rows="5" placeholder="상세 내용"></textarea>
         </div>
      </fieldset>
      <button>등록하기</button>
    </form>

    <br>
    <h3>
      전체 Todo 개수 : ${fn:length(todoList)}개 
      / 
      완료된 Todo 개수 : ${completeCount}개
    </h3>

    <table border="1" style="border-collapse: collapse;">
      <thead>
        <th>순서</th>
        <th>할 일 제목</th>
        <th>등록 날짜</th>  
        <th>수정</th> 
      </thead>

      
      <tbody>
        <c:forEach items="${todoList}" var="todo">
      
          <tr>
            <td>
              <a href="/todo/detail?todoNo=${todo.listNo}">${todo.listNo}</a>
            </td>
            <td>
              ${todo.listTitle}
            </td>
 
        
            <td>${todo.regDate}</td>
            <th>
               <button type="button" class="update-btn">수정</button>
            </th>    
          </tr>
        
      </c:forEach>
      
      

      </tbody>
    </table>
    <c:if test="${not empty message}" >
    <script>
      alert("${message}");
    </script>

    <c:remove var="message" />
  </c:if>

  <script src="/resources/js/update.js"></script>
</body>
</html>