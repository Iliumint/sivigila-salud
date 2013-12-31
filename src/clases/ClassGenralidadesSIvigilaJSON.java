package clases;

import java.util.ArrayList;

public class ClassGenralidadesSIvigilaJSON {

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public String getDominf() {
		return dominf;
	}

	public void setDominf(String dominf) {
		this.dominf = dominf;
	}

	public String getSubtem() {
		return subtem;
	}

	public void setSubtem(String subtem) {
		this.subtem = subtem;
	}

	public String getTem() {
		return tem;
	}

	public void setTem(String tem) {
		this.tem = tem;
	}

	String descrip;
	String dominf;
	String subtem;
	String tem;
	
	public ClassGenralidadesSIvigilaJSON(String descripcion, String dominio, String subtema, String tema)
	{
		descrip=descripcion;
		dominf=dominio;
		subtem=subtema;
		tem=tema;
	}
	
	public static ArrayList<String> camposLocalClass()
	{
		ArrayList<String> camposClass=new ArrayList<String>();
		camposClass.add("descrip");//pos-0
		camposClass.add("dominf");//pos-1
		camposClass.add("subtem");//pos-2
		camposClass.add("tem");//pos-3				
		
		return camposClass;
	}
	
	public static ArrayList<String> camposLocalClassAndroid()
	{
		ArrayList<String> camposClass=new ArrayList<String>();
		camposClass.add("descrip");//pos-0
		camposClass.add("dom_inf");//pos-1
		camposClass.add("sub_tem");//pos-2
		camposClass.add("tem");//pos-3				
		
		return camposClass;
	}
	
	public ClassGenralidadesSIvigilaJSON()
	{}
}
