package com.vigilatusalud_v3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import clases.ClassGenralidadesSIvigilaJSON;
import clases.EventoClassOldWEB;
import clases.EventosClassNewLocalWEB;


import android.util.Log;

public class InterpreteJson {
	
		
	public static ArrayList<EventoClassOldWEB> generarListaOld_Eventos(String cadenaJSON) throws JSONException{
	/***
	 * Este metodo genera UN ArrayList, EventoClass
	 * EventoClass contiene el esquema de la base de datos que se descargar de la web
	 * @param cadenaJSON Es un String con el JSON que viene de la WEB
	 */
			
	JSONObject jsonPartes;
	JSONArray jsonPartes2=null;
	
	try
	{
		jsonPartes=new JSONObject(cadenaJSON);
		jsonPartes2=new JSONArray(jsonPartes.getString("d"));//d, representa el encabezado del paquete json
	
//		Log.e("ObjetJson",jsonPartes.toString());
//		Log.e("ArrayJson",jsonPartes2.toString());
	}
	catch(Exception e)
	{
		Log.e("","No se pudo generar el Array-Objet/JSON");
	}

	
//	Comensaremos a Importar cada campo y lo agregaresmoa a la clase correspondiente	
	
	ArrayList<EventoClassOldWEB> listaEventos =new ArrayList<EventoClassOldWEB>();
	ArrayList<String> campos=EventoClassOldWEB.camposClass();
	
	if (!(jsonPartes2.equals(null))){
		JSONObject fila;
    	for (int index=0; index<jsonPartes2.length(); index++){
    		EventoClassOldWEB eventoTemp=new EventoClassOldWEB();
    		fila = jsonPartes2.getJSONObject(index);
    		
    		eventoTemp.setAcccolec(fila.getString(campos.get(0)));
    		eventoTemp.setAccind(fila.getString(campos.get(1)));
    		eventoTemp.setApolab(fila.getString(campos.get(2)));
    		eventoTemp.setCasconf(fila.getString(campos.get(3)));
    		eventoTemp.setCasprob(fila.getString(campos.get(4)));
    		eventoTemp.setCassosp(fila.getString(campos.get(5)));
    		eventoTemp.setDescrevent(fila.getString(campos.get(6)));
    		eventoTemp.setDiagdif(fila.getString(campos.get(7)));
    		eventoTemp.setFichnotif(fila.getString(campos.get(8)));    		
    		eventoTemp.setLinkurl(fila.getString(campos.get(9)));
//    		eventoTemp.setNomdimen(fila.getString(campos.get(10))); //Se solicitu la eliminacion de este campo
    		eventoTemp.setNomeven(fila.getString(campos.get(11)));
    		eventoTemp.setNomgrup(fila.getString(campos.get(12)));
    		eventoTemp.setNomsubgru(fila.getString(campos.get(13)));
    		eventoTemp.setOtrapoyo(fila.getString(campos.get(14)));
    		eventoTemp.setPartitionkey(fila.getString(campos.get(15)));
    		eventoTemp.setRowKey(fila.getString(campos.get(16)));
    		eventoTemp.setTiemnotif(fila.getString(campos.get(17)));

    		listaEventos.add(eventoTemp);
    	}
	}		
	
	return listaEventos;
	}
	
	
	public static ArrayList<EventosClassNewLocalWEB> generarListaNew_Event(String cadenaJSON) throws JSONException
	{
		JSONObject jsonPartes;
		JSONArray jsonPartes2=null;
		try
		{
			jsonPartes=new JSONObject(cadenaJSON);
			jsonPartes2=new JSONArray(jsonPartes.getString("d"));//d, representa el encabezado del paquete json
		
//			Log.e("ObjetJson",jsonPartes.toString());
//			Log.e("ArrayJson",jsonPartes2.toString());
		}
		catch(Exception e)
		{
			Log.e("","No se pudo generar el Array-Objet/JSON");
		}
		
		ArrayList<EventosClassNewLocalWEB> listaEventos =new ArrayList<EventosClassNewLocalWEB>();
		ArrayList<String> campos=EventosClassNewLocalWEB.camposLocalClass();
		
		if (!(jsonPartes2.equals(null))){
			JSONObject fila;
	    	for (int index=0; index<jsonPartes2.length(); index++){
	    		EventosClassNewLocalWEB eventoTemp=new EventosClassNewLocalWEB();
	    		fila = jsonPartes2.getJSONObject(index);
	    		
	    		Log.e("Error Posible", fila.getString(campos.get(1))+"");
	    		eventoTemp.setNom_grup(fila.getString(campos.get(1)));	    		
	    		eventoTemp.setNom_subgru(fila.getString(campos.get(2)));
	    		eventoTemp.setNom_even(fila.getString(campos.get(3)));
	    		eventoTemp.setDescr_event(fila.getString(campos.get(4)));
	    		eventoTemp.setCas_sosp(fila.getString(campos.get(5)));
	    		eventoTemp.setCas_prob(fila.getString(campos.get(6)));
	    		eventoTemp.setCas_conf(fila.getString(campos.get(7)));
	    		eventoTemp.setTiem_notif(fila.getString(campos.get(8)));
	    		eventoTemp.setFich_notif(fila.getString(campos.get(9)));
	    		eventoTemp.setDiag_dif(fila.getString(campos.get(10)));    		
	    		eventoTemp.setApo_lab(fila.getString(campos.get(11)));
	    		eventoTemp.setOtr_apoyo(fila.getString(campos.get(12)));
	    		eventoTemp.setAcc_ind(fila.getString(campos.get(13)));
	    		eventoTemp.setAcc_ind(fila.getString(campos.get(14)));
	    		eventoTemp.setLink_url(fila.getString(campos.get(15)));
	
	    		listaEventos.add(eventoTemp);
	    	}
		}
		return listaEventos;
	}
	
	
	public static ArrayList<ClassGenralidadesSIvigilaJSON> generarListaGENERALIDAES_Class(String cadenaJSON) throws JSONException
	{
		JSONObject jsonPartes;
		JSONArray jsonPartes2=null;
		
		try
		{
			jsonPartes=new JSONObject(cadenaJSON);
			jsonPartes2=new JSONArray(jsonPartes.getString("d"));//d, representa el encabezado del paquete json
		
//			Log.e("ObjetJson",jsonPartes.toString());
//			Log.e("ArrayJson",jsonPartes2.toString());
		}
		catch(Exception e)
		{
			Log.e("","No se pudo generar el Array-Objet/JSON");
		}
		
		ArrayList<ClassGenralidadesSIvigilaJSON> listaEventos =new ArrayList<ClassGenralidadesSIvigilaJSON>();
		ArrayList<String> campos=ClassGenralidadesSIvigilaJSON.camposLocalClass();
		
		if (!(jsonPartes2.equals(null))){
			JSONObject fila;
	    	for (int index=0; index<jsonPartes2.length(); index++){
	    		ClassGenralidadesSIvigilaJSON eventoTemp=new ClassGenralidadesSIvigilaJSON();
	    		fila = jsonPartes2.getJSONObject(index);
	    		
//	    		Log.e("Error Posible", fila.getString(campos.get(1))+"");
	    		
	    		eventoTemp.setDescrip(fila.getString(campos.get(0)));	    		
	    		eventoTemp.setDominf(fila.getString(campos.get(1)));
	    		eventoTemp.setSubtem(fila.getString(campos.get(2)));
	    		eventoTemp.setTem(fila.getString(campos.get(3)));
	
	    		listaEventos.add(eventoTemp);
	    	}
		}
		
		return listaEventos;
	}
	
	public static ArrayList<String> quitarElemetosRepetidos(ArrayList<String> element)
	{
		/***
		 * Quitamos los Elemento repetidos y aprece ser que tambien los ordena
		 */
		
		HashSet hs = new HashSet();
		//Lo cargamos con los valores del array, esto hace quite los repetidos
		hs.addAll(element);
		//Limpiamos el array
		element.clear();
		//Agregamos los valores sin repetir
		element.addAll(hs);
		Collections.sort(element);//Seguramente FALTA ver si los ordena de manera asscedente
		
		return element;
	}
	

}
