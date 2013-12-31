package com.vigilatusalud_v3;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONException;

import clases.ClassGenralidadesSIvigilaJSON;
import clases.EventoClassOldWEB;
import clases.EventosClassNewLocalWEB;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

public class BasedeDatos {
	
	

	public void actualizarBasedeDatos(String paqueteJSONfull)
	{		
		ArrayList<EventoClassOldWEB> listaEventos;
		try {
			listaEventos=InterpreteJson.generarListaOld_Eventos(paqueteJSONfull);

			for(int f=0;f<listaEventos.size();f++)
			{
				if(buscar_evento(listaEventos.get(f).getNomeven()))
				{actualizarElementosBD(listaEventos.get(f));}
				else
				{insertarElementosBD(listaEventos.get(f));}
			}					
			
		} catch (JSONException e) {
			Log.e("Conversion",""+e.toString());
			e.printStackTrace();
		}
	}	
	
	public boolean buscar_evento(String eventName)
	{
		boolean busqueda=false;
		SQLiteDatabase db=ConectarConDataBase();
		ArrayList<String> al = new ArrayList<String>();
//		+"like "+"'%"+nom+"%'"
		Cursor cursor = db.rawQuery("SELECT DISTINCT nombre_evento FROM reporte_sivigia WHERE nombre_evento" , null);
		 if (cursor.moveToFirst()) 
		 {do
	      { if(eventName.equals(cursor.getString(0)));
		 	{busqueda=true;}
	      } while (cursor.moveToNext());
	     }
		 
		return busqueda;
	}
	
	private void insertarElementosBD(EventoClassOldWEB event)
	{
		/***
		 * La idea es que cada evento de manera individual sera agregad
		 * Para tener en cuenta
		 */
		SQLiteDatabase db=ConectarConDataBase();
		ArrayList<String> campos=camposLocales();
		ContentValues ins=new ContentValues();

		ins.put(campos.get(14), event.getAcccolec());
		ins.put(campos.get(13), event.getAccind());
		ins.put(campos.get(11), event.getApolab());
		ins.put(campos.get(7), event.getCasconf());
		ins.put(campos.get(6), event.getCasprob());
		ins.put(campos.get(5), event.getCassosp());
		ins.put(campos.get(4), event.getDescrevent());
		ins.put(campos.get(10), event.getDiagdif());
		ins.put(campos.get(9), event.getFichnotif());
		ins.put(campos.get(15), event.getLinkurl());		
		ins.put(campos.get(3), event.getNomeven());
		ins.put(campos.get(1), event.getNomgrup());
		ins.put(campos.get(2), event.getNomsubgru());
		ins.put(campos.get(12), event.getOtrapoyo());
		ins.put(campos.get(8), event.getTiemnotif());
		
//		ins.put(campos.get(""), event.getNomdimen());
//		ins.put(campos.get(""), event.getPartitionkey());
//		ins.put(campos.get(""), event.getRowKey());
		
		try
		{
		db.insert("reporte_sivigia", null, ins);
		db.close();
		Log.e("INSERCION","Se hicieron una insercion del evento: "+event.getNomeven());
		}
		catch(Exception e)
		{
			Log.e("NO INSERT DB",e.toString());
		}
		finally{db.close();}
	}
	
	private void actualizarElementosBD(EventoClassOldWEB event)
	{
		/***
		 * La idea es que cada evento de manera individual sera agregad
		 * Para tener en cuenta
		 */
		SQLiteDatabase db=ConectarConDataBase();
		ArrayList<String> campos=camposLocales();
		ContentValues updat=new ContentValues();

		try
		{	
		
		updat.put(campos.get(14), event.getAcccolec());
		updat.put(campos.get(13), event.getAccind());
		updat.put(campos.get(11), event.getApolab());
		updat.put(campos.get(7), event.getCasconf());
		updat.put(campos.get(6), event.getCasprob());
		updat.put(campos.get(5), event.getCassosp());
		updat.put(campos.get(4), event.getDescrevent());
		updat.put(campos.get(10), event.getDiagdif());
		updat.put(campos.get(9), event.getFichnotif());
		updat.put(campos.get(15), event.getLinkurl());		
		updat.put(campos.get(3), event.getNomeven());
		updat.put(campos.get(1), event.getNomgrup());
		updat.put(campos.get(2), event.getNomsubgru());
		updat.put(campos.get(12), event.getOtrapoyo());
		updat.put(campos.get(8), event.getTiemnotif());
		
//		updat.put(campos.get(""), event.getNomdimen());
//		updat.put(campos.get(""), event.getPartitionkey());
//		updat.put(campos.get(""), event.getRowKey());
		
		db.update("Location_Table", 
				updat, campos.get(11)+"='"+event.getNomeven()+"'",
				null);
		db.close();
		Log.e("ACTUALIZACION","Se hicieron ajustes en los registro de los eventos");
		}
		catch(Exception e)
		{
			Log.e("NO UPDATE DB",e.toString());
		}
		finally{db.close();}
	}
	
	public static SQLiteDatabase ConectarConDataBase()
	{
		String DATABASE_NAME = "saludvigia";		
		File sdcard = Environment.getExternalStorageDirectory();
		String dbfile = sdcard.getAbsolutePath() + File.separator+ "MiSalud2" + File.separator + DATABASE_NAME;
		SQLiteDatabase db=SQLiteDatabase.openDatabase(dbfile, null,SQLiteDatabase.OPEN_READWRITE);
		return db;
	}
	
	public ArrayList<String> camposLocales()
	{
		ArrayList<String> camposLocales=new ArrayList<String>();
		
		camposLocales.add("id");//0
		camposLocales.add("nom_grup");//1
		camposLocales.add("nom_subgrup");//2
		camposLocales.add("nom_even");//3
		camposLocales.add("descr_event");//4
		camposLocales.add("cas_sosp");//5
		camposLocales.add("cas_prob");//6
		camposLocales.add("cas_conf");//7
		camposLocales.add("tiem_notif");//8
		camposLocales.add("fich_notif");//9
		camposLocales.add("diag_dif");//10
		camposLocales.add("apo_lab");//11
		camposLocales.add("otr_apoyo");//12
		camposLocales.add("acc_ind");//13
		camposLocales.add("acc_colec");//14
		camposLocales.add("link_url");//15
		
	return camposLocales;
	}
	
	
	public ArrayList<String> consultar_nombres_subgrupos(String tipo_subgrupo)
    {   	
		ArrayList<String> nombre_subgrupo=new ArrayList<String>();
	 
	 	SQLiteDatabase db2=ConectarConDataBase();
    	Cursor c=db2.rawQuery("Select * from reporte_sivigia",null);
    	
    	if (c.moveToFirst()) 
    	{    		   
    		try
    		{
    	     do {
    	    	 if(tipo_subgrupo.equals(c.getString(1)))//(0)
     			{	 		    
    	    		 nombre_subgrupo.add(c.getString(2));//(1)
     			}
    	     } while(c.moveToNext());
    		}
    		catch (Exception e) {
    			Log.e(e.toString(),"se cayo la consulta");
			}
		    		    		
    	}
    	return nombre_subgrupo;
    }
	
	public ArrayList<String> consultar_nombres_enfermedades(String tipo_subgrupo)
    {  	
		ArrayList<String> nombre_enfermedad=new ArrayList<String>();
		SQLiteDatabase db2=ConectarConDataBase();
    	Cursor c=db2.rawQuery("Select * from reporte_sivigia",null);
    	
    	if (c.moveToFirst()) 
    	{    		    			
    	     do {
    	    	 if(tipo_subgrupo.equals(c.getString(2)))//(1)
     			{	 		    
    	    	 nombre_enfermedad.add(c.getString(3));//(2)
     			}
    	     } while(c.moveToNext());	    	
		    		    		
    	}
    	return nombre_enfermedad;
    }
	
	public ArrayList<EventosClassNewLocalWEB> consultar_FullEventos()
    {  	
		ArrayList<EventosClassNewLocalWEB> lista_Eventos=new ArrayList<EventosClassNewLocalWEB>();
		SQLiteDatabase db=ConectarConDataBase();
    	Cursor c=db.rawQuery("Select * from reporte_sivigia",null);
    	
    	if (c.moveToFirst()) 
    	{    		    			
    	     do {
    	    	lista_Eventos.add(new EventosClassNewLocalWEB(
    	    			c.getString(1),//nom_gru
    	    			c.getString(2),//nom_subgru
    	    			c.getString(3),//nom_even
    	    			c.getString(4),//descrip
    	    			c.getString(5),//cas sosp
    	    			c.getString(6),//caso proba
    	    			c.getString(7),//caso confr
    	    			c.getString(8),//tiempo notif
    	    			c.getString(9),//ficha notif
    	    			c.getString(10),//diag dif
    	    			c.getString(11),//apoyo lab
    	    			c.getString(12),//otro apoyo
    	    			c.getString(13),//acc ind
    	    			c.getString(14),//acc colec
    	    			c.getString(15) //link
    	    			));
    	    	
    	     } while(c.moveToNext());	    	
		    		    		
    	}
    	return lista_Eventos;
    }
	
	public EventosClassNewLocalWEB consultar_Evento_unico(String evento)
    {  			
		SQLiteDatabase db=ConectarConDataBase();
    	Cursor c=db.rawQuery("Select * from reporte_sivigia WHERE nom_even='"+evento+"'",null);
    	EventosClassNewLocalWEB EventoA=new EventosClassNewLocalWEB();
    	
    	if (c.moveToFirst()) 
    	{    	EventosClassNewLocalWEB EventoP;	    			
    	     do {
    	    	 EventoP=new EventosClassNewLocalWEB(
    	    			c.getString(1),//nom_gru
    	    			c.getString(2),//nom_subgru
    	    			c.getString(3),//nom_even
    	    			c.getString(4),//descrip
    	    			c.getString(5),//cas sosp
    	    			c.getString(6),//caso proba
    	    			c.getString(7),//caso confr
    	    			c.getString(8),//tiempo notif
    	    			c.getString(9),//ficha notif
    	    			c.getString(10),//diag dif
    	    			c.getString(11),//apoyo lab
    	    			c.getString(12),//otro apoyo
    	    			c.getString(13),//acc ind
    	    			c.getString(14),//acc colec
    	    			c.getString(15) //link
    	    			 );
    	    	
    	     } while(c.moveToNext());	    	
		    		  EventoA=EventoP;  		
    	}
    	return EventoA;
    }
	
	
	
	
	//////////////////////////////////////////////
	//Tabla de Generalidades Sivigila
	
	
	public ArrayList<String> consultar_DomInfo()
    {   	
		ArrayList<String> DomInfo=new ArrayList<String>();
	 
	 	SQLiteDatabase db2=ConectarConDataBase();
    	Cursor c=db2.rawQuery("Select Distinct * from informacion_sivigila",null);
    	
    	if (c.moveToFirst()) 
    	{    		   
    		try
    		{
    	     do {   	    			    
    	    		 DomInfo.add(c.getString(1));//(1)     			
    	     } while(c.moveToNext());
    		}
    		catch (Exception e) {
    			Log.e(e.toString(),"se cayo la consulta informaicon Sivigila");
			}
		    		    		
    	}
    	return DomInfo;
    }
	
	ArrayList<String> descripcion;
	ArrayList<String> subtema;
	
	public ArrayList<String> consultar_Tema(String gener)
    {   	
		ArrayList<String> DomTema=new ArrayList<String>();
		descripcion=new ArrayList<String>();
		subtema=new ArrayList<String>();
	 	SQLiteDatabase db2=ConectarConDataBase();
    	Cursor c=db2.rawQuery("Select Distinct * from informacion_sivigila where dom_inf='"+gener+"'",null);
    	
    	if (c.moveToFirst()) 
    	{    		   
    		try
    		{
    	     do {   	
    	    	 if(!(c.getString(2)+"").equals("null"))
    	    	 DomTema.add(c.getString(2)+"");
//    	    	 subtema.add(c.getString(3)+"");
//    	    	 descripcion.add(c.getString(4)+"");    	    	 
//    	    	 Log.e("Tema",c.getString(0)+"");
    	     } while(c.moveToNext());
    		}
    		catch (Exception e) {
    			Log.e(e.toString(),"se cayo la consulta informaicon Sivigila por tema");
			}		    		    		
    	}
       	Log.e("TAMAÑO",DomTema.size()+"");
    	if(DomTema.size()==0)
    	{
    		DomTema.add("null");
    		Log.e("TAMAÑO-2",DomTema.size()+"");
    	}
    	return DomTema;
    }
	
	public ArrayList<String> consultar_subTema(String gener)
    {   	
		ArrayList<String> DomTema=new ArrayList<String>();
		descripcion=new ArrayList<String>();
		subtema=new ArrayList<String>();
	 	SQLiteDatabase db2=ConectarConDataBase();
    	Cursor c=db2.rawQuery("Select Distinct * from informacion_sivigila where tem='"+gener+"'",null);
    	
    	if (c.moveToFirst()) 
    	{    		   
    		try
    		{
    	     do {   
    	    	 if(!(c.getString(3)+"").equals("null"))
    	    	 DomTema.add(c.getString(3)+"");
//    	    	 subtema.add(c.getString(3)+"");
//    	    	 descripcion.add(c.getString(4)+"");    	    	 
//    	    	 Log.e("Tema",c.getString(0)+"");
    	     } while(c.moveToNext());
    		}
    		catch (Exception e) {
    			Log.e(e.toString(),"se cayo la consulta informaicon Sivigila por tema");
			}		    		    		
    	}
    	Log.e("TAMAÑO",DomTema.size()+"");
    	if(DomTema.size()==0)
    	{
    		DomTema.add("null");
    		Log.e("TAMAÑO-2",DomTema.size()+"");
    	}
    	return DomTema;
    }
	
	public ArrayList<String> consultar_descripcion(String gener)
    {   	
		ArrayList<String> DomTema=new ArrayList<String>();
		descripcion=new ArrayList<String>();
		subtema=new ArrayList<String>();
	 	SQLiteDatabase db2=ConectarConDataBase();
//	 	try
//	 	{
    	Cursor c=db2.rawQuery("Select Distinct * from informacion_sivigila where sub_tem='"+gener+"'",null);
    	
    	if (c.moveToFirst()) 
    	{    		   
    		try
    		{
    	     do {   	    			    
    	    	 DomTema.add(c.getString(4)+"");
//    	    	 subtema.add(c.getString(3)+"");
//    	    	 descripcion.add(c.getString(4)+"");    	    	 
//    	    	 Log.e("Tema",c.getString(0)+"");
    	     } while(c.moveToNext());
    		}
    		catch (Exception e) {
    			Log.e(e.toString(),"se cayo la consulta informaicon Sivigila por subtema");
			}		    		    		
    	}
    	return DomTema;
//	 	}
//	 	catch (Exception e) {
//	 		Log.e("NULOS Consultas",e.toString());
//		}
//	 	finally
//	 	{return DomTema;}
    }
	
	public ArrayList<String> consultar_descripcion_por_tema(String gener, String dominio)
    {   	
		ArrayList<String> DomTema=new ArrayList<String>();
		descripcion=new ArrayList<String>();
		subtema=new ArrayList<String>();
	 	SQLiteDatabase db2=ConectarConDataBase();
    	Cursor c=db2.rawQuery("Select Distinct * from informacion_sivigila where tem='"+gener+"' and dom_inf='"+dominio+"'",null);
    	
    	if (c.moveToFirst()) 
    	{    		   
    		try
    		{
    	     do {   	    			    
    	    	 DomTema.add(c.getString(4)+"");
//    	    	 subtema.add(c.getString(3)+"");
//    	    	 descripcion.add(c.getString(4)+"");    	    	 
//    	    	 Log.e("Tema",c.getString(0)+"");
    	     } while(c.moveToNext());
    		}
    		catch (Exception e) {
    			Log.e(e.toString(),"se cayo la consulta informaicon Sivigila por tema");
			}		    		    		
    	}
    	return DomTema;
    }
	
	
	static public ArrayList<String> busqeuda_genralidades_tema(String gener)
    {   	
		ArrayList<String> DomTema=new ArrayList<String>();		
	 	SQLiteDatabase db2=ConectarConDataBase();
    	Cursor c=db2.rawQuery("Select Distinct * from informacion_sivigila where tem like "+"'%"+gener+"%'",null);//+"' or dom_inf like "+"'%"+gener+"%'"
    	Log.e("Consulta","Entro en la BD");
    	if (c.moveToFirst()) 
    	{    		   
    		try
    		{
    	     do { 
    	    	 DomTema.add(
    	    			 "*Dom: "+c.getString(1)+" \n"+
    	    			 "*Tem: "+c.getString(2)+" \n"+
    	    			 "*SubTem: "+c.getString(3)+" \n-"+
    	    			 c.getString(4)+"\n"
    	    			 );
    	    	 
//    	    	 DomTema.add(c.getString(1)+"");//Dominio
//    	    	 DomTema.add(c.getString(2)+"");//Tema
//    	    	 DomTema.add(c.getString(3)+"");//Subtema
//    	    	 DomTema.add(c.getString(4)+"");//descripcion
    	    	 
//    	    	 Log.e("Tema",c.getString(4)+"");
    	     } while(c.moveToNext());
    		}
    		catch (Exception e) {
    			Log.e(e.toString(),"se cayo la consulta informaicon Sivigila por tema");
			}		    		    		
    	}
    	return DomTema;
    }
	
	static public ArrayList<String> busqeuda_genralidades_subtema(String gener)
    {   	
		ArrayList<String> DomTema=new ArrayList<String>();		
	 	SQLiteDatabase db2=ConectarConDataBase();
    	Cursor c=db2.rawQuery("Select Distinct * from informacion_sivigila where sub_tem like "+"'%"+gener+"%'",null); 
    	Log.e("Consulta","Entro en la BD");
    	if (c.moveToFirst()) 
    	{    		   
    		try
    		{
    	     do {   	    			    
    	    	 DomTema.add(
    	    			 "*Dom: "+c.getString(1)+" \n"+
    	    			 "*Tem: "+c.getString(2)+" \n"+
    	    			 "*SubTem: "+c.getString(3)+" \n-"+
    	    			 c.getString(4)+"\n"
    	    			 );
    	    	 
    	    	 
//    	    	 DomTema.add(c.getString(1)+"");//Dominio
//    	    	 DomTema.add(c.getString(2)+"");//Tema
//    	    	 DomTema.add(c.getString(3)+"");//Subtema
//    	    	 DomTema.add(c.getString(4)+"");//descripcion
    	    	 
//    	    	 Log.e("Tema",c.getString(4)+"");
    	     } while(c.moveToNext());
    		}
    		catch (Exception e) {
    			Log.e(e.toString(),"se cayo la consulta informaicon Sivigila por tema");
			}		    		    		
    	}
    	return DomTema;
    }
	
	public ArrayList<String> getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(ArrayList<String> descripcion) {
		this.descripcion = descripcion;
	}
	public ArrayList<String> getSubtema() {
		return subtema;
	}
	public void setSubtema(ArrayList<String> subtema) {
		this.subtema = subtema;
	}
	
	
	public static void limpiar_databaseTable_SIV()
	{
		SQLiteDatabase db2=ConectarConDataBase();
    	Cursor c=db2.rawQuery("Delete from reporte_sivigia",null);
    	if (c.moveToFirst()) 
    	{    		   
    		try
    		{
    	     do {   	    			    
    	    	
    	     } while(c.moveToNext());
    		}
    		catch (Exception e) {
    			Log.e("Error Limpiando DataBase",e.toString());
			}		    		    		
    	}
	}
	
	public static void limpiar_databaseTable_GEN()
	{
		SQLiteDatabase db2=ConectarConDataBase();
    	Cursor c=db2.rawQuery("Delete from informacion_sivigila",null);
    	if (c.moveToFirst()) 
    	{    		   
    		try
    		{
    	     do {   	    			    
    	    	
    	     } while(c.moveToNext());
    		}
    		catch (Exception e) {
    			Log.e("Error Limpiando DataBase",e.toString());
			}	    		
    	}
	}
	
	public static void insertar_OLD_SIVIGILA(EventoClassOldWEB event)
	{
		/***
		 * La idea es que cada evento de manera individual sera agregad
		 * Para tener en cuenta
		 */
		SQLiteDatabase db=ConectarConDataBase();
		ArrayList<String> campos=EventosClassNewLocalWEB.camposLocalClass();
		ContentValues ins=new ContentValues();

		
		ins.put(campos.get(1), event.getNomgrup());
		ins.put(campos.get(2), event.getNomsubgru());
		ins.put(campos.get(3), event.getNomeven());
		ins.put(campos.get(4), event.getDescrevent());
		ins.put(campos.get(5), event.getCassosp());
		ins.put(campos.get(6), event.getCasprob());
		ins.put(campos.get(7), event.getCasconf());
		ins.put(campos.get(8), event.getTiemnotif());
		ins.put(campos.get(9), event.getFichnotif());
		ins.put(campos.get(10), event.getDiagdif());		
		ins.put(campos.get(11), event.getApolab());
		ins.put(campos.get(12), event.getOtrapoyo());
		ins.put(campos.get(13), event.getAccind());
		ins.put(campos.get(14), event.getAcccolec());
		ins.put(campos.get(15), event.getLinkurl());
		
//		ins.put(campos.get(""), event.getNomdimen());
//		ins.put(campos.get(""), event.getPartitionkey());
//		ins.put(campos.get(""), event.getRowKey());
		
		try
		{
		db.insert("reporte_sivigia", null, ins);
		db.close();
		Log.e("INSERCION","Se hicieron una insercion del evento: "+event.getNomeven());
		}
		catch(Exception e)
		{
			Log.e("NO INSERT DB SIV-OLD",e.toString());
		}
		finally{db.close();}
	}
	
	public static void insertar_SIVIGILA(EventosClassNewLocalWEB event)
	{
		/***
		 * La idea es que cada evento de manera individual sera agregad
		 * Para tener en cuenta
		 */
		SQLiteDatabase db=ConectarConDataBase();
		ArrayList<String> campos=EventosClassNewLocalWEB.camposLocalClass();
		ContentValues ins=new ContentValues();

		ins.put(campos.get(1), event.getNom_grup());
		ins.put(campos.get(2), event.getNom_subgru());
		ins.put(campos.get(3), event.getNom_even());
		ins.put(campos.get(4), event.getDescr_event());
		ins.put(campos.get(5), event.getCas_sosp());
		ins.put(campos.get(6), event.getCas_prob());
		ins.put(campos.get(7), event.getCas_conf());
		ins.put(campos.get(8), event.getTiem_notif());
		ins.put(campos.get(9), event.getFich_notif());
		ins.put(campos.get(10), event.getDiag_dif());		
		ins.put(campos.get(11), event.getApo_lab());
		ins.put(campos.get(12), event.getOtr_apoyo());
		ins.put(campos.get(13), event.getAcc_ind());
		ins.put(campos.get(14), event.getAcc_colec());
		ins.put(campos.get(15), event.getLink_url());
		
//		ins.put(campos.get(""), event.getNomdimen());
//		ins.put(campos.get(""), event.getPartitionkey());
//		ins.put(campos.get(""), event.getRowKey());
		
		try
		{
		db.insert("reporte_sivigia", null, ins);
		db.close();
		Log.e("INSERCION","Se hicieron una insercion del evento: "+event.getNom_even());
		}
		catch(Exception e)
		{
			Log.e("NO INSERT DB SIV",e.toString());
		}
		finally{db.close();}
	}
	
	public static void insertar_GENERALIDADES(ClassGenralidadesSIvigilaJSON event)
	{
		/***
		 * La idea es que cada evento de manera individual sera agregad
		 * Para tener en cuenta
		 */
		SQLiteDatabase db=ConectarConDataBase();
		ArrayList<String> campos=ClassGenralidadesSIvigilaJSON.camposLocalClassAndroid();
		ContentValues ins=new ContentValues();

		ins.put(campos.get(0), event.getDescrip());
		ins.put(campos.get(1), event.getDominf());
		ins.put(campos.get(2), event.getSubtem());
		ins.put(campos.get(3), event.getTem());			
		
//		ins.put(campos.get(""), event.getNomdimen());
//		ins.put(campos.get(""), event.getPartitionkey());
//		ins.put(campos.get(""), event.getRowKey());
		
		try
		{
		db.insert("informacion_sivigila", null, ins);
		db.close();
		Log.e("INSERCION",""+event.getDominf()+"--"+event.getSubtem());
		}
		catch(Exception e)
		{
			Log.e("NO INSERT DB GEN",e.toString());
		}
		finally{db.close();}
	}
	

}
