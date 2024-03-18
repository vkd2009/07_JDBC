const updateBtnList = document.querySelectorAll(".update-btn");
updateBtnList.forEach( (btn,index) => {

    btn.addEventListener('click', e => { 

     
        const tr = e.target.closest("tr");

     
        const todoNo = tr.children[1].innerText;

       
        location.href = "/todoList/update?todoNo=" + todoNo; 

    });


});