package com.tts.PresentationProject.BlogPost;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private Long id;
		private String userName;
		private String passWord;
		
		
		
		public User() {
			
		}
		
		public User(String userName, String passWord) {
			this.userName = userName;
			this.passWord = passWord;

		}
		
		
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassWord() {
			return passWord;
		}
		public void setPassWord(String passWord) {
			this.passWord = passWord;
		}
		@Override
		public String toString() {
			return "UserRepository [userName=" + userName + ", passWord=" + passWord + "]";
		}
		
}
