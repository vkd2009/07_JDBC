package edu.kh.todoList.model.dto;

public class Todo {
	
	private int 	 	listNo;				
	private String 	listTitle;		
	private String 	listContent;
	private String 	complete;			
	private String 	regDate;
  
	
	public Todo () {}
	


	public Todo(int listNo, String listTitle, String listContent, String complete, String regDate) {
		super();
		this.listNo = listNo;
		this.listTitle = listTitle;
		this.listContent = listContent;
		this.complete = complete;
		this.regDate = regDate;
	}



	public Todo(int listNo, String listTitle, String complete, String regDate) {
		super();
		this.listNo = listNo;
		this.listTitle = listTitle;
		this.complete = complete;
		this.regDate = regDate;
	}


	public int getListNo() {
		return listNo;
	}
	public void setListNo(int listNo) {
		this.listNo = listNo;
	}
	public String getListTitle() {
		return listTitle;
	}
	public void setListTitle(String listTitle) {
		this.listTitle = listTitle;
	}
	public String getListContent() {
		return listContent;
	}
	public void setListContent(String listContent) {
		this.listContent = listContent;
	}
	public String getComplete() {
		return complete;
	}
	public void setComplete(String complete) {
		this.complete = complete;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}




	@Override
	public String toString() {
		return "Todo [listNo=" + listNo + ", listTitle=" + listTitle + ", listContent=" + listContent + ", complete="
				+ complete + ", regDate=" + regDate + " ]";
	}
	
	
	
	
	
	
	
}
