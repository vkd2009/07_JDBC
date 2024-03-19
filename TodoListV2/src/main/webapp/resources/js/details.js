const deleteBtn = document.querySelector("#deleteBtn");

deleteBtn.addEventListener("click", e => {

  if(confirm("삭제 하시겠습니까?")){
    location.href = `/todo/delete?todoNo=${e.target.dataset.todoNo}`; 
  }



});


const goToList = document.querySelector("#goToList");

goToList.addEventListener("click", () => {
  location.href = "/"; 
});

