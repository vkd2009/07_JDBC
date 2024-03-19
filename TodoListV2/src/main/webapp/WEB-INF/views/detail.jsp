<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>${todo.listTitle}</title>

 <style>
    table{
      border-collapse : collapse;
    }
  
    .todo-content{
      /* pre태그 처럼 처리 == HTML에 작성된 모양 그대로 화면에 출력*/
      white-space : pre-wrap;
    }
  </style>

</head>
<body>
  
  <h3>${todo.listTitle}</h3>

  <table border="1">
    <tr>
      <th>순서</th>
      <td>${todo.listNo}</td>
    </tr>

    <tr>
      <th>등록 날짜</th>
      <td>${todo.regDate}</td>
    </tr>

    <tr>
      <th>내용</th>
      <td class="todo-content">${todo.listContent}</td>
    </tr>
  </table>

  <button id="goToList">목록으로</button>
 
  <button id="deleteBtn" data-todo-no="${todo.listNo}">삭제</button>

  <script src = "/resources/js/details.js"></script>

  <c:if test="${not empty message}" >
    <script>
      alert("${message}");
    </script>

    <c:remove var="message" />
  </c:if>

</body>
</html>