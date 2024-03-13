package model.dao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import model.AlunoServices;

public class DB {
	private XStream xStream = new XStream( new DomDriver( "UTF-8" ));
	
	
	public DB() {
		xStream.addPermission(AnyTypePermission.ANY);
	}
	
	public void salvarDados(AlunoServices central) {
		
		try {
			File arquivo = new File("db.xml");
			arquivo.createNewFile();
			PrintWriter pw = new PrintWriter(arquivo);
			String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n";
			xml += xStream.toXML(central);
			pw.print(xml);
			pw.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public AlunoServices recuperarDados() {
		File arquivo = new File("db.xml");
		
		if(arquivo.exists()) {
			try {
				FileInputStream fis = new FileInputStream(arquivo);
				
				return (AlunoServices) xStream.fromXML(fis);
			} catch (FileNotFoundException e) {
				e.getMessage();
			}
		}
		return new AlunoServices();	
	}
}