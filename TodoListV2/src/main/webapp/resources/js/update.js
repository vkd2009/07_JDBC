const updateBtnList = document.querySelectorAll(".update-btn");
updateBtnList.forEach( (btn,index) => {

    btn.addEventListener('click', e => { 

     
        const tr = e.target.closest("tr");

     
        const todoTitle = tr.children[1].innerText;

       
        location.href = "/todoTitle/update?todoTitle=" + todoTitle; 

    });


});