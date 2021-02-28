package Utilitarios;

import java.util.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearBase {
	
	static SystemProperties sp, sp2;
	static Connection con;
	
	public static void main(String[] args) {
		System.out.println("===============holaMundo=========Practico4========================");
		try {
			sp = new SystemProperties();
			sql_conectarBase();
			System.out.println("...abriendo conexion con el dbms:"+sp.getMysql_url());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sql_crearBaseYCargarla();
		
		sql_cerrarBase();
		System.out.println("========================chauMundo=============================================");		
	}

	private static void sql_crearBaseYCargarla() {
		String sql_tempVarName, sql_temp;		
        List<String> vars= null;
        vars = new LinkedList<String>();
        String sqlText;

      

          ////DROP DATABASE IF EXISTS PROYECTO;
        sqlText = " DROP DATABASE IF EXISTS PROYECTO; ";
        vars.add(sqlText);

        ////CREATE DATABASE PROYECTO;
        sqlText = " CREATE DATABASE PROYECTO; ";
        vars.add(sqlText);

        ////USE PROYECTO;
        sqlText = " USE PROYECTO; ";
        vars.add(sqlText);


        ////CREATE TABLE JUGADORES (
        ////	PK_jugador_id INT NOT NULL AUTO_INCREMENT,
        ////	jugadorUserName VARCHAR(45),
        ////	jugadorPassword VARCHAR(45),
        ////	jugadorIsOnline BOOLEAN,
        ////	jugadorPuntajeAcumulado INT,
        ////	PRIMARY KEY (PK_jugador_id)
        ////);

        sqlText = " CREATE TABLE JUGADORES (";
        sqlText = sqlText+" PK_jugador_id INT NOT NULL AUTO_INCREMENT, ";
        sqlText = sqlText+" jugadorUserName VARCHAR(45), ";
        sqlText = sqlText+" jugadorPassword VARCHAR(45), ";
        sqlText = sqlText+" jugadorIsOnline BOOLEAN, ";
        sqlText = sqlText+" jugadorPuntajeAcumulado INT, ";
        sqlText = sqlText+" PRIMARY KEY (PK_jugador_id) ";
        sqlText = sqlText+" ); ";
        vars.add(sqlText);
        
        ////CREATE TABLE PARTIDAS (
        ////	PK_partida_id INT NOT NULL AUTO_INCREMENT,
        ////	partidaEstado VARCHAR(45),
        ////	partidaFechaUltimaActualizacion DATE,
        ////	partidaGuardada BOOLEAN,
        ////	partidaNombre VARCHAR(45),
        ////	partidaCantidadJugadores INT,
        ////	FK_partidaCreador_id INT,
        ////	partidaFechaCreada DATE,
        ////	partidaTermino  BOOLEAN,
        ////	
        ////	PRIMARY KEY (PK_partida_id),
        ////	FOREIGN KEY (FK_partidaCreador_id) REFERENCES JUGADORES(PK_jugador_id)
        ////);

        sqlText = " CREATE TABLE PARTIDAS ( ";
        sqlText = sqlText+" PK_partida_id INT NOT NULL AUTO_INCREMENT, ";
        sqlText = sqlText+" partidaEstado VARCHAR(45), ";
        sqlText = sqlText+" partidaFechaUltimaActualizacion DATE, ";
        sqlText = sqlText+" partidaGuardada BOOLEAN, ";
        sqlText = sqlText+" partidaNombre VARCHAR(45), ";
        sqlText = sqlText+" partidaCantidadJugadores INT, ";
        sqlText = sqlText+" FK_partidaCreador_id INT, ";
        sqlText = sqlText+" partidaFechaCreada DATE, ";
        sqlText = sqlText+" partidaTermino  BOOLEAN, ";
        sqlText = sqlText+" PRIMARY KEY (PK_partida_id), ";
        sqlText = sqlText+" FOREIGN KEY (FK_partidaCreador_id) REFERENCES JUGADORES(PK_jugador_id) ";
        sqlText = sqlText+" ); ";
        vars.add(sqlText);
        
        ////CREATE TABLE DEPOSITOS_DE_BOMBAS (
        ////	PK_depBombas_id INT NOT NULL AUTO_INCREMENT,
        ////	depBombasCoordX INT,
        ////	depBombasCoordY INT,
        ////	depBombasEstado BOOLEAN,
        ////	depBombasVida INT,
        ////	depBombasCantBombas INT,
        ////	depBombasEnUso BOOLEAN,
        ////	PRIMARY KEY (PK_depBombas_id)
        ////);

        sqlText = " CREATE TABLE DEPOSITOS_DE_BOMBAS ( ";
        sqlText = sqlText+" PK_depBombas_id INT NOT NULL AUTO_INCREMENT, ";
        sqlText = sqlText+" depBombasCoordX INT, ";
        sqlText = sqlText+" depBombasCoordY INT, ";
        sqlText = sqlText+" depBombasEstado BOOLEAN, ";
        sqlText = sqlText+" depBombasVida INT, ";
        sqlText = sqlText+" depBombasCantBombas INT, ";
        sqlText = sqlText+" depBombasEnUso BOOLEAN, ";
        sqlText = sqlText+" PRIMARY KEY (PK_depBombas_id) ";
        sqlText = sqlText+" ); ";
        vars.add(sqlText);
        
        
        ////CREATE TABLE DEPOSITO_DE_COMBUSTIBLE (
        ////	PK_depCombustible_id INT NOT NULL AUTO_INCREMENT,
        ////	depCombustibleCoordX INT,
        ////	depCombustibleCoordY INT,
        ////	depCombustibleEstado BOOLEAN,
        ////	depCombustibleVida INT,
        ////	depCombustibleCantCombustible INT,
        ////	depCombustibleEnUso BOOLEAN,
        ////	PRIMARY KEY (PK_depCombustible_id)	
        ////);
        
        sqlText = " CREATE TABLE DEPOSITO_DE_COMBUSTIBLE ( ";
        sqlText = sqlText+" PK_depCombustible_id INT NOT NULL AUTO_INCREMENT, ";
        sqlText = sqlText+" depCombustibleCoordX INT, ";
        sqlText = sqlText+" depCombustibleCoordY INT, ";
        sqlText = sqlText+" depCombustibleEstado BOOLEAN, ";
        sqlText = sqlText+" depCombustibleVida INT, ";
        sqlText = sqlText+" depCombustibleCantCombustible INT, ";
        sqlText = sqlText+" depCombustibleEnUso BOOLEAN, ";
        sqlText = sqlText+" PRIMARY KEY (PK_depCombustible_id) ";
        sqlText = sqlText+" ); ";
        vars.add(sqlText);
        
        
        ////CREATE TABLE TORRES_DE_CONTROL (
        ////	PK_torreControl_id INT NOT NULL AUTO_INCREMENT,
        ////	torreControlCoordX INT,
        ////	torreControlCoordY INT,
        ////	torreControlEstado BOOLEAN,
        ////	torreControlVida INT,
        ////	torreControlHayEnemigo BOOLEAN,
        ////	torreControlRangoVision INT,
        ////	PRIMARY KEY (PK_torreControl_id)		
        ////);
        
        sqlText = " CREATE TABLE TORRES_DE_CONTROL ( ";
        sqlText = sqlText+" PK_torreControl_id INT NOT NULL AUTO_INCREMENT, ";
        sqlText = sqlText+" torreControlCoordX INT, ";
        sqlText = sqlText+" torreControlCoordY INT, ";
        sqlText = sqlText+" torreControlEstado BOOLEAN, ";
        sqlText = sqlText+" torreControlVida INT, ";
        sqlText = sqlText+" torreControlHayEnemigo BOOLEAN, ";
        sqlText = sqlText+" torreControlRangoVision INT, ";
        sqlText = sqlText+" PRIMARY KEY (PK_torreControl_id) ";
        sqlText = sqlText+" ); ";
        vars.add(sqlText);
        
        ////CREATE TABLE BASES (
        ////	PK_base_id INT NOT NULL AUTO_INCREMENT,
        ////	FK_depBombas_id INT,
        ////	FK_depCombustible_id INT,
        ////	FK_torreControl_id INT,
        ////	PRIMARY KEY (PK_base_id),
        ////	FOREIGN KEY (FK_depBombas_id) REFERENCES DEPOSITOS_DE_BOMBAS(PK_depBombas_id),
        ////	FOREIGN KEY (FK_depCombustible_id) REFERENCES DEPOSITO_DE_COMBUSTIBLE(PK_depCombustible_id),
        ////	FOREIGN KEY (FK_torreControl_id) REFERENCES TORRES_DE_CONTROL(PK_torreControl_id)
        ////);
        
        sqlText = " CREATE TABLE BASES ( ";
        sqlText = sqlText+" PK_base_id INT NOT NULL AUTO_INCREMENT, ";
        sqlText = sqlText+" FK_depBombas_id INT, ";
        sqlText = sqlText+" FK_depCombustible_id INT, ";
        sqlText = sqlText+" FK_torreControl_id INT, ";
        sqlText = sqlText+" PRIMARY KEY (PK_base_id), ";
        sqlText = sqlText+" FOREIGN KEY (FK_depBombas_id) REFERENCES DEPOSITOS_DE_BOMBAS(PK_depBombas_id), ";
        sqlText = sqlText+" FOREIGN KEY (FK_depCombustible_id) REFERENCES DEPOSITO_DE_COMBUSTIBLE(PK_depCombustible_id), ";
        sqlText = sqlText+" FOREIGN KEY (FK_torreControl_id) REFERENCES TORRES_DE_CONTROL(PK_torreControl_id) ";
        sqlText = sqlText+" ); ";
        vars.add(sqlText);
        
        ////CREATE TABLE AVIONES (
        ////	PK_avion_id INT NOT NULL AUTO_INCREMENT,
        ////	avionCoordX INT,
        ////	avionCoordY INT,
        ////	avionCoordZ INT,
        ////	avionEstado BOOLEAN,
        ////	avionVida INT,
        ////	avionHayEnemigo BOOLEAN,
        ////	avionRangoVision INT,
        ////	avionAngulo INT,
        ////	avionTieneBomba BOOLEAN,
        ////	avionCantBombas INT,
        ////	avionCantCombustible INT,
        ////	avionEnCampoEnemigo BOOLEAN,
        ////	FK_base_id INT,
        ////	PRIMARY KEY (PK_avion_id),
        ////	FOREIGN KEY (FK_base_id) REFERENCES BASES(PK_base_id)
        ////);
        
        
        sqlText = " CREATE TABLE AVIONES ( ";
        sqlText = sqlText+" PK_avion_id INT NOT NULL AUTO_INCREMENT, ";
        sqlText = sqlText+" avionCoordX INT, ";
        sqlText = sqlText+" avionCoordY INT, ";
        sqlText = sqlText+" avionCoordZ INT, ";
        sqlText = sqlText+" avionEstado BOOLEAN, ";
        sqlText = sqlText+" avionVida INT, ";
        sqlText = sqlText+" avionHayEnemigo BOOLEAN, ";
        sqlText = sqlText+" avionRangoVision INT, ";
        sqlText = sqlText+" avionTieneBomba BOOLEAN, ";
        sqlText = sqlText+" avionCantBombas INT, ";
        sqlText = sqlText+" avionCantCombustible INT, ";
        sqlText = sqlText+" avionEnCampoEnemigo BOOLEAN, ";
        sqlText = sqlText+" FK_base_id INT, ";
        sqlText = sqlText+" PRIMARY KEY (PK_avion_id), ";
        sqlText = sqlText+" FOREIGN KEY (FK_base_id) REFERENCES BASES(PK_base_id) ";
        sqlText = sqlText+" ); ";
        vars.add(sqlText);
        
        ////CREATE TABLE ARTILLEROS (
        ////	PK_artillero_id INT NOT NULL AUTO_INCREMENT,
        ////	artilleroCoordX INT,
        ////	artilleroCoordY INT,
        ////	artilleroEstado BOOLEAN,
        ////	artilleroVida INT,
        ////	artilleroHayEnemigo BOOLEAN,
        ////	artilleroRangoVision INT,
        ////	artilleroAngulo INT,
        ////	FK_base_id INT,
        ////	PRIMARY KEY (PK_artillero_id),
        ////	FOREIGN KEY (FK_base_id) REFERENCES BASES(PK_base_id)
        ////);
        
        sqlText = " CREATE TABLE ARTILLEROS ( ";
        sqlText = sqlText+" PK_artillero_id INT NOT NULL AUTO_INCREMENT, ";
        sqlText = sqlText+" artilleroCoordX INT, ";
        sqlText = sqlText+" artilleroCoordY INT, ";
        sqlText = sqlText+" artilleroEstado BOOLEAN, ";
        sqlText = sqlText+" artilleroVida INT, ";
        sqlText = sqlText+" artilleroHayEnemigo BOOLEAN, ";
        sqlText = sqlText+" artilleroRangoVision INT, ";
        sqlText = sqlText+" artilleroAngulo INT, ";
        sqlText = sqlText+" FK_base_id INT, ";
        sqlText = sqlText+" PRIMARY KEY (PK_artillero_id), ";
        sqlText = sqlText+" FOREIGN KEY (FK_base_id) REFERENCES BASES(PK_base_id) ";
        sqlText = sqlText+" ); ";
        vars.add(sqlText);
        
        
        ////CREATE TABLE EQUIPOS (
        ////	PK_equipo_id INT NOT NULL AUTO_INCREMENT,
        ////	equipoBando VARCHAR(45),
        ////	FK_partida_id INT,
        ////	FK_base_id INT,
        ////	PRIMARY KEY (PK_equipo_id),
        ////	FOREIGN KEY (FK_partida_id) REFERENCES PARTIDAS(PK_partida_id),
        ////	FOREIGN KEY (FK_base_id) REFERENCES BASES(PK_base_id)
        ////);
        
        sqlText = " CREATE TABLE EQUIPOS ( ";
        sqlText = sqlText+" PK_equipo_id INT NOT NULL AUTO_INCREMENT, ";
        sqlText = sqlText+" equipoBando VARCHAR(45), ";
        sqlText = sqlText+" FK_partida_id INT, ";
        sqlText = sqlText+" FK_base_id INT, ";
        sqlText = sqlText+" PRIMARY KEY (PK_equipo_id), ";
        sqlText = sqlText+" FOREIGN KEY (FK_partida_id) REFERENCES PARTIDAS(PK_partida_id), ";
        sqlText = sqlText+" FOREIGN KEY (FK_base_id) REFERENCES BASES(PK_base_id) ";
        sqlText = sqlText+" ); ";
        vars.add(sqlText);
        
        ////CREATE TABLE EQUIPOS_JUGADORES (
        ////	PK_jugador_id INT NOT NULL,
        ////	PK_equipo_id INT NOT NULL,
        ////	PRIMARY KEY (PK_jugador_id, PK_equipo_id),
        ////	FOREIGN KEY (PK_jugador_id) REFERENCES JUGADORES(PK_jugador_id),
        ////	FOREIGN KEY (PK_equipo_id) REFERENCES EQUIPOS(PK_equipo_id)
        ////);
       
        
        sqlText = " CREATE TABLE EQUIPOS_JUGADORES ( ";
        sqlText = sqlText+" PK_jugador_id INT NOT NULL, ";
        sqlText = sqlText+" PK_equipo_id INT NOT NULL, ";
        sqlText = sqlText+" PRIMARY KEY (PK_jugador_id, PK_equipo_id), ";
        sqlText = sqlText+" FOREIGN KEY (PK_jugador_id) REFERENCES JUGADORES(PK_jugador_id), ";
        sqlText = sqlText+" FOREIGN KEY (PK_equipo_id) REFERENCES EQUIPOS(PK_equipo_id) ";
        sqlText = sqlText+" ); ";
        vars.add(sqlText);
        
        
        
		
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
				System.out.println(cant + "filas afectadas");
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
