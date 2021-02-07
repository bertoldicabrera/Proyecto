package Utilitarios;

import java.util.*;
import com.mysql.jdbc.Driver;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearBase {
	
	static SystemProperties sp, sp2;
	static Connection con;
	
	public static void main(String[] args) {
		System.out.println("======================================");
		try {
			sp = new SystemProperties();
			sql_conectarBase();
			System.out.println("...abriendo conexion con el dbms:"+sp.getMysql_url());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sql_crearBaseYCargarla();
		
		sql_cerrarBase();
		System.out.println("=====================================================================");		
	}

	private static void sql_crearBaseYCargarla() {
		String sql_tempVarName, sql_temp;
		
		List<String> vars = new LinkedList<String>();
		vars.add("DROP DATABASE IF EXISTS WEB;");
		vars.add("CREATE DATABASE WEB;");
		vars.add("USE WEB;");
		//poner autoincremental el id
		vars.add("CREATE TABLE usuarios (id int(11) AUTO_INCREMENT PRIMARY KEY," +
											 "email varchar(100) NOT NULL," +
											 "password varchar(128)NOT NULL," +
											 "name varchar(100) NOT NULL );");
			
		
		//el hash es un sha-256 "password"
		vars.add("Insert into usuarios values (1,'test@test.com' ,'b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86','test');");
		vars.add("Insert into usuarios values (2,'test1@test.com' ,'b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86','test1');");
		
		
		
		
		
		Statement stmt = null;
		int i=1;
		for(String sql : vars) {            
			System.out.println("- - - - - - - - - - - - - - - - - - - -"); 
			sql_tempVarName = "sql_"+i;
			sql_temp		= sql;
			System.out.println("SQL::"+sql_tempVarName+"="+sql_temp);
			i++;
			try {
				stmt = con.createStatement();
				int cant = stmt.executeUpdate(sql_temp);
				stmt.close();
				System.out.println("Resultado de ==>" + sql_temp + "<==");
				System.out.println(cant + " filas afectadas");
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			
		}
	}

	public static void  sql_conectarBase()  {
		String driver = sp.getMysql_driver();
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = sp.getMysql_url();//+sp.getMysql_dbname();
		try {
			con = DriverManager.getConnection(url, sp.getMysql_user(), sp.getMysql_password());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void  sql_cerrarBase(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws IOException {
		sp2 = new SystemProperties();
		String driver = sp2.getMysql_driver();
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = sp2.getMysql_url();//+sp.getMysql_dbname();
		try {
			con = DriverManager.getConnection(url, sp2.getMysql_user(), sp2.getMysql_password());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
}
