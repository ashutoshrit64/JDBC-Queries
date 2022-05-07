package com.bridgelabz.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class JDBCConnection {
	

	public static void main(String[] args)  {
		
		
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?useSSL=false","root","password");

		if(con!=null) {
			System.out.println("connection is estabished");
//			Statement statement = con.createStatement();
//			String query="create table employeepayrolldb(id int PRIMARY KEY,username varchar(25),emaiid varchar(100),password varchar(100), age int(20),city varchar(40) ,salary int(40))";
//			statement.executeUpdate(query);
//			statement.close();
			int id1=fetchdata();
			System.out.println("id-->"+id1);
			addData(id1++);
//			updatedata(id1);
			
			
			
		}else {
			System.out.print("connection failed");
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	


	public static void addData(int id) throws SQLException {
		
		id=id+1;
		System.out.println(id);
		Scanner scan=new Scanner(System.in);
		System.out.println("please enter username:");
		String username=scan.next();
		System.out.println("please enter email:");
		String email=scan.next();
		System.out.println("please enter password:");
		String password=scan.next();
		System.out.println("please enter age:");
		int age=scan.nextInt();
		System.out.println("please enter city:");
		String city=scan.next();
		System.out.println("please enter salary:");
		int salary=scan.nextInt();
		
		//		id int PRIMARY KEY,username varchar(25),emaiid varchar(100),password varchar(100), age int(20),city varchar(40) ,salary int(40))";
		String query=String.format("insert into employeepayrolldb values(?,?,?,?,?,?,?)");
		
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?useSSL=false","root","password");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1,id);
		ps.setString(2, username);
		ps.setString(3, email);
		ps.setString(4, password);
		ps.setInt(5, age);
		ps.setString(6, city);
		ps.setInt(7, salary);
		// etc.
		ps.execute();

//		Statement statement;
//		try {
//			statement = con.createStatement();
//			statement.executeUpdate(query);
//			System.out.println("Data inserted into database");
//			statement.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}
	
	public static void updatedata(int id1) {
		Scanner scan=new Scanner(System.in);
		String query="update employeepayrolldb set username=? where id="+id1;
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?useSSL=false","root","password");
			PreparedStatement ps = con.prepareStatement(query);
			System.out.println("please enter username");
			String username=scan.next();
			ps.setString(1, username);
			ps.execute();
			System.out.println("Query Executed Succesfully!!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static int fetchdata() {
		int id=0;
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?useSSL=false","root","password");
			String query="select * from employeepayrolldb";
			Statement statement=con.createStatement();
			ResultSet rs=statement.executeQuery(query);
			while(rs.next()) {
				id=rs.getInt("id");
				System.out.println("******************");
				System.out.println("username->"+rs.getString("username"));
				System.out.println("email->"+rs.getString("emaiid"));
				System.out.println("password->"+rs.getString("password"));
				System.out.println("******************");
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
		
	}
	
	
}
