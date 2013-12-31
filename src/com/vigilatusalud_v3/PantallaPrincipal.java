package com.vigilatusalud_v3;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;

import clases.ClassGenralidadesSIvigilaJSON;
import clases.EventoClassOldWEB;
import clases.EventosClassNewLocalWEB;
import clases.GeneralidadesSivigila;
import clases.ListarResultados;
import clases.MainActivity;

import com.vigilatusalud_v3.R;
import com.vigilatusalud_v3.R.id;
import com.vigilatusalud_v3.R.layout;
import com.vigilatusalud_v3.R.menu;

import conexiones_web.PeticionesWEB;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class PantallaPrincipal extends Activity {

	
	ImageView imAPP,imHelp;
	Dialog dialogo;
	AlertDialog.Builder dialogo1;
	Button btnrevision;
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId())
		{	
			case R.id.actualizarDBmenu:					
				
				if(isInternetAvailable(this))
				{					
					mostraMensajeActualizando();//Dialog
//					mostrarMSNACtualizacion();//ALert
					TimerTask timerTask = new TimerTask() 
				     { 
				         public void run()  
				         { 
				        	 Log.e("Actualizando","");
				        	 actualizar_baseDatos();			        	 
				         } 
				     };    
				     Timer timer = new Timer();
				     timer.schedule(timerTask, 900);
				}
				else
				{
					mostra_mensaje("Requiere de interner para realizar esta acción");
				}
				
				return true;	
				
			case R.id.aboutdt:	
				acerca_de();
				return true;	
		}		
		return super.onOptionsItemSelected(item);
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pantalla_principal);
		setTitle("");
		
//	    ActionBar actionBar = getSupportActionBar();
//	    actionBar.setDisplayHomeAsUpEnabled(true);		
		
		imAPP=(ImageView)findViewById(R.id.imageAPPrincipal);
		imHelp=(ImageView)findViewById(R.id.imageHELP);		
		
		copiar_dbV3();
		revisionObligatoria();		
	
		
		imAPP.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				lanzar_app();
			}
		});
		
		imHelp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				lanzar_ayuda_generalidadesSIV();
			}
		});
		
//		lanzar_timer(); // Ejecuta la app de saludo inicial
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pantalla_principal, menu);
		return true;
	}
	
	public void revisionObligatoria()
	{		
		final Dialog dialogoObligatorio=new Dialog(this);	
		dialogoObligatorio.setContentView(R.layout.obligatorio);
		dialogoObligatorio.setTitle("Revisión Acerca de SiVigila");
		dialogoObligatorio.setCancelable(false);
		
		Button omitir=(Button)dialogoObligatorio.findViewById(R.id.btnOmitir);
		
		omitir.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {			
				dialogoObligatorio.dismiss();
			}
		});					
		dialogoObligatorio.show();
	}
	
	private void actualizar_baseDatos()
	{
		
		//*********Para SIVIGILA**********
		String cadenaJson=PeticionesWEB.peticionJSON();
		Log.e("cadenaJson", "Desde el Boton ACT: "+cadenaJson);
		try {
			ArrayList<EventoClassOldWEB> DatosWEB=		
			InterpreteJson.generarListaOld_Eventos(cadenaJson);
			controlador_OLD_databaseSIV(DatosWEB);
			
//********Las siguientes 3 lienas de codigo comentareadas con para el casi de usar 
//********los nuevos nombres de los campos desde el JSON
			
//			ArrayList<EventosClassNewLocalWEB> DatosWEB=		
//			InterpreteJson.generarListaNew_Event(cadenaJson);
//			controlador_databaseSIV(DatosWEB);
			
		} catch (JSONException e) {
			Log.e("Error actualizando la BD desde SIVIGILA",e.toString()+"\n\n");
			e.printStackTrace();
			mostra_mensaje("No se pudo actualizar la tabla de EVENTOS SIVIGILA");
		}
		
		//*********Para GENERALIDADES**********
		String cadenaJsonGEN=PeticionesWEB.peticionJSON_GEN();
		Log.e("cadenaJson", "Desde el Boton ACT: "+cadenaJsonGEN);
		try {
			ArrayList<ClassGenralidadesSIvigilaJSON> DatosWEBGEN=		
			InterpreteJson.generarListaGENERALIDAES_Class(cadenaJsonGEN);
			controlador_databaseGEN(DatosWEBGEN);
		} catch (JSONException e) {
			Log.e("Error actualizando la BD desde GENERALIDADES",e.toString());
			e.printStackTrace();
			mostra_mensaje("No se pudo actualizar la tabla de GENERALIDADES SIVIGILA");
		}
		
		dialogo.dismiss();		 
		 
	}
	
	
	private void mostraMensajeActualizando()
	{		
		dialogo=new Dialog(this);	
		dialogo.setContentView(R.layout.ly_actualizando);
		dialogo.setTitle("Actualizando...");
		dialogo.setCancelable(false);
		
		dialogo.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss(DialogInterface arg0) {
				mostra_mensaje("Actualización Exitosa");
			}
		});
		dialogo.show();
	}
	
	private void mostrarMSNACtualizacion()
	{
		dialogo1 = new AlertDialog.Builder(this);  
        dialogo1.setTitle("Actualizando...");  
        dialogo1.setMessage("Un momento mientras se actualiza la Base de Datos");            
        dialogo1.setCancelable(false); 
        
        dialogo1.setPositiveButton("Finalizar", new DialogInterface.OnClickListener() {  
	           public void onClick(DialogInterface dialogo1, int id) {  
	               mostra_mensaje("Actualización Completada");
	           }  
	       });  
//        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {  
//            public void onClick(DialogInterface dialogo1, int id) {  
//                dialogo1.dismiss();
//            }  
//        });

        dialogo1.show();   
        
	}

	private void controlador_OLD_databaseSIV(ArrayList<EventoClassOldWEB> event)
	{		
		//Limpia tabla SIVIGILA OLD
			BasedeDatos.limpiar_databaseTable_SIV();
			for (EventoClassOldWEB eventosClass : event) {
				BasedeDatos.insertar_OLD_SIVIGILA(eventosClass);
			}			
	}
	
	private void controlador_databaseSIV(ArrayList<EventosClassNewLocalWEB> event)
	{		
		//Limpia tabla SIVIGILA NEW
			BasedeDatos.limpiar_databaseTable_SIV();
			for (EventosClassNewLocalWEB eventosClass : event) {
				BasedeDatos.insertar_SIVIGILA(eventosClass);
			}			
	}
	
	private void controlador_databaseGEN(ArrayList<ClassGenralidadesSIvigilaJSON> event)
	{		
		//Limpia tabla GENERALIDADES
		
			BasedeDatos.limpiar_databaseTable_GEN();
			
			for (ClassGenralidadesSIvigilaJSON classGenralidadesSIvigilaJSON : event) {
				BasedeDatos.insertar_GENERALIDADES(classGenralidadesSIvigilaJSON);
			}
					
	}
	
	private void lanzar_app()
	{
		Intent i=new Intent(this, MainActivity.class);	
//		i.pxutExtra("P_SESION", p_sesion);	
		startActivity(i);
	}
	
	private void lanzar_ayuda_generalidadesSIV()
	{
//		Intent i=new Intent(this, GeneralidadesSivigila.class);	
		Intent i=new Intent(this, GeneralidadesCuatroBotones.class);
		startActivity(i);
	}
	
	public void acerca_de()
	{
		final Dialog dialogo=new Dialog(this);
		dialogo.setContentView(R.layout.ly_about);
		dialogo.setTitle("Acerca de");	
		
		ImageView atras=(ImageView)dialogo.findViewById(R.id.imgAtrasAcerca);
		btnrevision=(Button)dialogo.findViewById(R.id.btnrevision);
//		TextView moviltxt=(TextView)dialogo.findViewById(R.id.textAcercaDe);
//		moviltxt.setMovementMethod(new ScrollingMovementMethod());
//		moviltxt.setMaxLines(9);
		
		btnrevision.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
//			Log.e("Button","Boton de revision activado");
			revisionObligatoria();
			}
		});
		
		atras.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {			
				dialogo.dismiss();
			}
		});
		
		dialogo.show();
		
	}
	
	
	public void copiar_dbV3()
	{	
//	    String ruta =rutfile.toString();
		String ruta = Environment.getExternalStorageDirectory()+ File.separator +  "MiSalud2/";
		File rutfile= new File (ruta);
		 if(!rutfile.exists())
		 {rutfile.mkdirs();}
	    String archivo = "saludvigia"; //nOMBRE DE LA bASE DE dATOS	    
	    String directorio="directorio";
	    copiar_fichero(ruta, archivo);
	    copiar_fichero(ruta, directorio);
	}
	
	
	private void copiar_fichero(String ruta, String archivo)
	{
		 File archivoDB = new File(ruta + archivo);
//		    Log.e("RUTA",ruta);
//		    Log.e("PARA entrar IF",Boolean.toString(archivoDB.exists()));
		    if (!archivoDB.exists()) 
		    {
		    	Log.e("EXIST", "No exite, entonces entra");
			    try 
			    {
			        InputStream IS = getApplicationContext().getAssets().open(archivo);
			        OutputStream OS = new FileOutputStream(archivoDB);
			        byte[] buffer = new byte[1024];
			        int length = 0;
			        while ((length = IS.read(buffer))>0){
			            OS.write(buffer, 0, length);
			        }
			        OS.flush();
			        OS.close();
			        IS.close();
			        Log.e("LISTO","Base de datos creada");
			    } 
			    catch (FileNotFoundException e) 
			    {
				        Log.e("ERROR", "Archivo no encontrado, " + e.toString());
			    }
			    catch (IOException e) 
			    {
				        Log.e("ERROR", "Error al copiar la Base de Datos, " + e.toString());
				}
		    }
	}	
	
	private void mostra_mensaje(String msn)
	{
		Toast.makeText(this, msn, Toast.LENGTH_LONG).show();
	}
	
	 public static boolean isInternetAvailable(Context context) {
		    boolean haveConnectedWifi = false;
		    boolean haveConnectedMobile = false;
		    boolean connectionavailable = false;
		    ConnectivityManager cm = (ConnectivityManager) context
		            .getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		    NetworkInfo informationabtnet = cm.getActiveNetworkInfo();
		    for (NetworkInfo ni : netInfo) {
		        try {
		            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
		                if (ni.isConnected()) haveConnectedWifi = true; //Log.e("Type-Conection",ni.getTypeName());
		            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
		                if (ni.isConnected()) haveConnectedMobile = true;//Log.e("Type-Conection",ni.getTypeName());
		            if (informationabtnet.isAvailable()
		                && informationabtnet.isConnected())
		                connectionavailable = true;//Log.e("Internet","Esta Conectado a Internet");
		            Log.i("ConnectionAvailable", "" + connectionavailable);
		        } catch (Exception ex) {
//		           Log.e("Catch Internet","Inside utils catch clause , exception is" + ex.toString());
		            ex.printStackTrace();
		        }
		    }
		    return haveConnectedWifi || haveConnectedMobile;
		}

}
