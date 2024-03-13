// id가 addBtn인 버튼 요소 얻어오기
const addBtn = document.querySelector("#addBtn");

// id tbody인 요소 얻어오기
const tbody = document.querySelector("#tbody");


// addBtn이 클릭 되었을 때(이벤트 리스너) 추가
addBtn.addEventListener("click",() => {

  // 한 행을 나타내는 tr 요소 생성
  const tr = document.createElement("tr");

  // name 속성 값이 저장된 배열 생성
  const names = ['deptId', 'deptTitle', 'locationId'];
  
  // 배열 요소 순차 접근( 향상된 for 문 )
  for(let name of names){

    const td = document.createElement("td"); // td 요소 생성

    const input = document.createElement("input");// intput 요소 생성

    input.type = "text"; // type="text" 설정
    input.name = name; // 배열 요소를 name 속성 값으로 설정

    td.append(input);
    tr.append(td);
  }


  // 규칙성이 어긋난 th>button 따로 만들기
  const th = document.createElement("th");
  const button = document.createElement("button");

  button.type = "button";  // type="buoton" 설정
  button.classList.add("remove-btn"); // 클래스 추가
  button.innerText = "삭제"


  /* 
        함수명: 함수 코드(정의한 내용)를 현재 위치로 가져오기
        
        함수명() : 함수 호출
        (함수 내용 수행)
  */

  // 삭제 버튼 클릭 시 동작
  button.addEventListener("click", removeFn );

  th.append(button);
  tr.append(th);

  tbody.append(tr); // #tbody에 한 줄 추가
});



/* 삭제 버튼 클릭 시 해당 행 제거하는 함수 정의 */
function removeFn(e) {

    // e : 이벤트 객체
    // e.target : 이벤트가 발생한 요소 (== 삭제 버튼)

    const tr = e.target.parentElement.parentElement;
    
    tr.remove(); // tr 요소 제거
}


// 원래 존재하던 삭제 버튼에 removeFn 함수 동작 추가
document.querySelector(".remove-btn").addEventListener("click", removeFn);




