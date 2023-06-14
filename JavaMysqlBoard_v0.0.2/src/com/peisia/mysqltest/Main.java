package com.peisia.mysqltest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class Main {
	public static void main(String[] args) {
		ProcBoard procBoard=new ProcBoard();
		procBoard.run();
	}
	
	

}