package conexiones_web;

import java.util.concurrent.TimeUnit;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;


import android.util.Log;

public class PeticionesWEB {

//	String respuesta="Sin respuesta";
	
	public static String peticionJSON(){	
				
		try {   
			String respuesta="Sin respuesta";
			JSONObject obj=new JSONObject();
			obj.put("id", "00");
		    StringEntity entity = new StringEntity(
		    					obj.toString()
		    					);
			Log.e("Pack.JSON",obj.toString());			
//		    String url = "http://servicedatosabiertoscolombia.cloudapp.net/v1/Ministerio_de_Salud/enosfinal?$format=json";
			String url = "http://servicedatosabiertoscolombia.cloudapp.net/v1/Ministerio_de_Salud/enosfinal?$format=json";
			ExecuteWS ex = new ExecuteWS();
			ex.setConsultar(true);
			ex.setUrl(url);
			ex.execute(entity);		    

			String resp="JSON Vacio";
			try
			{
//				long a =50000;
//				respuesta=ex.get(a, TimeUnit.MILLISECONDS).toString();				
				respuesta=ex.get().toString();
//				Log.e("Respueta.JSON",respuesta);
				
		/////**************************************
//				
//				try
//				{
//				JSONObject respond=new JSONObject(respuesta);
//				resp=respond.getString("d");//d, representa el encabezado de la respuesta
//				}
//				catch(Exception e)
//				{
//					Log.e("ERROR-JSON", "No se pudo formar el paquete JSON");
//				}
//				
		/////**************************************				
				
//				boolean resp=respond.getBoolean("ServicioResult");//				
				
				//Log.e("Conexion EXT",respuesta);//--------
				
//				Log.e("Boolean EXT",Boolean.toString(resp)+"");
//				return resp;
				return respuesta;
				
			}
			catch(Exception e)
			{
				Log.e("Peticion.JSON","No se  pudo optener la respuesta RTA "+e.toString());
//				return respuesta;
				return "Nada";
			}
			
			
			//Log.e("RESULT-WEB",ex.get().toString());
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			Log.e("ServicioRest","Error!"+ ex.toString(), ex);
			return "Sin respuesta";
		}
	}
	
	
	public static String peticionJSON_GEN(){	
		
		try {   
			String respuesta="Sin respuesta";
			JSONObject obj=new JSONObject();
			obj.put("id", "00");
		    StringEntity entity = new StringEntity(
		    					obj.toString()
		    					);
			Log.e("Pack.JSON",obj.toString());		
			String url = "http://servicedatosabiertoscolombia.cloudapp.net/v1/Ministerio_de_Salud/enosgeneralidades?$format=json";
			ExecuteWS ex = new ExecuteWS();
			ex.setConsultar(true);
			ex.setUrl(url);
			ex.execute(entity);		    
			long a =20000;
			String resp="JSON Vacio";
			try
			{
				respuesta=ex.get(a, TimeUnit.MILLISECONDS).toString();				
//				respuesta=ex.get().toString();
//				Log.e("Respueta.JSON",respuesta);
				
		/////**************************************
//				
//				try
//				{
//				JSONObject respond=new JSONObject(respuesta);
//				resp=respond.getString("d");//d, representa el encabezado de la respuesta
//				}
//				catch(Exception e)
//				{
//					Log.e("ERROR-JSON", "No se pudo formar el paquete JSON");
//				}
//				
		/////**************************************				
				
//				boolean resp=respond.getBoolean("ServicioResult");//				
				
				//Log.e("Conexion EXT",respuesta);//--------
				
//				Log.e("Boolean EXT",Boolean.toString(resp)+"");
//				return resp;
				return respuesta;
				
			}
			catch(Exception e)
			{
				Log.e("Peticion.JSON","No se  pudo optener la respuesta RTA "+e.toString());
//				return respuesta;
				return "Nada";
			}
			
			
			//Log.e("RESULT-WEB",ex.get().toString());
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			Log.e("ServicioRest","Error!"+ ex.toString(), ex);
			return "Sin respuesta";
		}
	}
	
}
