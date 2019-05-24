package ini;


import java.io.*;
import java.sql.*;


class javaFirebird {
	public static void main(String[] args) {
		try {
			Class.forName("org.firebirdsql.jdbc.FBDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:firebirdsql:localhost/44680:E:\\Sistema\\MK4\\DBMKEY.FDB?encoding=UTF8", "SYSDBA", "Office25");
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select pr_codigo, pr_codbarra, pr_nome, pr_precovend from produtos");
			File ArquivoSQL = new File("C:\\Users\\user\\Desktop","ArquivoSQL.sql");     
			if (ArquivoSQL.exists()) {
				ArquivoSQL.delete();
			} 
			
			FileWriter file = new FileWriter(ArquivoSQL); 
			BufferedWriter conexao = new BufferedWriter(file);
			while (rs.next() == true) {
			
			conexao.write("UPDATE OR INSERT INTO PRODUTOS (pr_codigo, pr_codbarra, pr_nome, pr_precovend) VALUES (" + rs.getInt(1) + "," + rs.getString(2) + ",'" + rs.getString(3) + "','" + rs.getFloat(4) + "')" + " MATCHING (PR_CODIGO);");
			conexao.newLine();
			}
			conexao.close();
			con.close();
			System.out.println("[SUCESSO] SCRIPT SQL CRIADO COM SUCESSO!");						
		} catch (Exception e) {
			System.out.println("[ERRO] ERRO AO CRIAR O SCRIPT SQL!" + e.getMessage());
		}
	}
}
