package com.vigilatusalud_v3;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;


import clases.MainActivity;


import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class Directorio extends Activity {

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) 
		{
		case R.id.atras_direc:
			finish();
			break;

		default:
			break;
		}		
		
		return super.onOptionsItemSelected(item);		
	}
	
	
	GridView tabla;
	ArrayList<String> campos=new ArrayList<String>();
	ArrayList<String> lugares=new ArrayList<String>();
	TextView mail, tel, dire, enti, ciudad;
	Spinner departamentos;
	ImageView atras;
	TextView departamento,entidad,direccion, city, email, telefono;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_directorio);
		setContentView(R.layout.tabla);
		
		atras=(ImageView)findViewById(R.id.imgatras);
		departamentos=(Spinner)findViewById(R.id.spinDirectorio);
		mail=(TextView)findViewById(R.id.txtmail22);
		tel=(TextView)findViewById(R.id.txtTel);
		dire=(TextView)findViewById(R.id.txtDir2);
		ciudad=(TextView)findViewById(R.id.textoCiudad);
		enti=(TextView)findViewById(R.id.textoEntidad);
		
		
//		departamento=(TextView)findViewById(R.id.textoDep);
		entidad=(TextView)findViewById(R.id.textoEnt);
		direccion=(TextView)findViewById(R.id.textoDir);
		city=(TextView)findViewById(R.id.textoCiu);
		email=(TextView)findViewById(R.id.textoMail);
		telefono=(TextView)findViewById(R.id.textoTel);
		
//	tabla=(GridView)findViewById(R.id.gridDirectorio);	
//	consultar_directorio(Conectar_baseDatos());
		
		
		llenar_Spinner();
		
		departamentos.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				llenar_Textos(lugares.get(arg2));
				Log.e("Lugar",lugares.get(arg2));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
//	llenarLista_Directorio();
			
		atras.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				actividad_anterior();
			}
		});
	}
	
	public void actividad_anterior()
	{
//		Intent i=new Intent(this, MainActivity.class);
//		startActivity(i);
		finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.directorio, menu);
		return true;
	}

	public void llenar_Spinner()
	{	
		lugares=busqueda_departamento();
		HashSet hs = new HashSet();
		hs.addAll(lugares);
		lugares.clear();
		lugares.addAll(hs);
		Collections.sort(lugares);
		
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lugares);
		departamentos.setAdapter(adapter);//		
	}
	
	public void llenar_Textos(String dep)
	{
		try
		{
		ArrayList<DirectorioClass>datos =consultar_directorio(Conectar_baseDatos(),dep);
//		if(!datos.get(0).getEmail().equals(""))
		mail.setText(datos.get(0).getEmail());
//		else
//		{email.setVisibility(View.INVISIBLE);}
//		if(!datos.get(0).getTele().equals(""))
		tel.setText(datos.get(0).getTele());
//		else
//		{telefono.setVisibility(View.INVISIBLE);}
//		if(!datos.get(0).getDireccion().equals(""))
		dire.setText(datos.get(0).getDireccion());
//		else
//		{direccion.setVisibility(View.INVISIBLE);}
//		if(!datos.get(0).getEntidad().equals(""))
		enti.setText(datos.get(0).getEntidad());
//		else
//		{entidad.setVisibility(View.INVISIBLE);}
//		if(!datos.get(0).getCiudad().equals(""))
		ciudad.setText(datos.get(0).getCiudad());
//		else
//		{city.setVisibility(View.INVISIBLE);}
		}
		catch(Exception e)
		{}
	}
	public void llenarLista_Directorio()
	 { 		
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, campos);
		tabla.setAdapter(adapter);
	 }
	 public SQLiteDatabase Conectar_baseDatos()
	 {
		String DATABASE_NAME = "saludvigia";		
		File sdcard = Environment.getExternalStorageDirectory();
		String dbfile = sdcard.getAbsolutePath() + File.separator+ "MiSalud2" + File.separator + DATABASE_NAME;
		SQLiteDatabase db=SQLiteDatabase.openDatabase(dbfile, null,SQLiteDatabase.OPEN_READWRITE);
		return db;
	 }
	 
	 public ArrayList<DirectorioClass> consultar_directorio(SQLiteDatabase db2, String dep)
	    {  			
		 	ArrayList<DirectorioClass> directorio=new ArrayList<DirectorioClass>();
		 	campos.clear();
	    	Cursor c=db2.rawQuery("Select * from directorio_entidades where departamento='"+dep+"'",null);
//	    	(String dep, String mail, String tel, String dir, String entiti, String city)
	    	if (c.moveToFirst()) {	    		    			
	    	     do {	 	    	    			
	     			directorio.add(new DirectorioClass(
	     					c.getString(1), c.getString(5), 
	     					c.getString(6), c.getString(3),
	     					c.getString(2),c.getString(4)));
	     			
	     			Log.e("Departamento",c.getString(1));
	     			
	    	     } while(c.moveToNext());    				    		    		
	    	}
	    	return directorio;
	    }
	 
	 public ArrayList<String> busqueda_departamento()
		{
		SQLiteDatabase database =Conectar_baseDatos();
		ArrayList<String> al = new ArrayList<String>();
		Cursor cursor = database.rawQuery("SELECT DISTINCT departamento from directorio_entidades", null);
		 if (cursor.moveToFirst()) {
	    	  do{
	    		  al.add(cursor.getString(0));
	    	  } while (cursor.moveToNext());
	    	  }
		 return al;
		}
	 
	 public class DirectorioClass
	 {
		 public String getCiudad() {
			return ciudad;
		}

		public void setCiudad(String ciudad) {
			this.ciudad = ciudad;
		}

		public String getEntidad() {
			return entidad;
		}

		public void setEntidad(String entidad) {
			this.entidad = entidad;
		}

		public String getDepar() {
			return depar;
		}

		public void setDepar(String depar) {
			this.depar = depar;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getTele() {
			return tele;
		}

		public void setTele(String tele) {
			this.tele = tele;
		}

		public String getDireccion() {
			return direccion;
		}

		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}

		 String depar;
		 String email;
		 String tele;
		 String direccion; 
		 String ciudad;
		 String entidad;
		 
		 public DirectorioClass(String dep, String mail, String tel, String dir, String entiti, String city)
		 {
			depar=dep;
			email=mail;
			tele=tel;
			direccion=dir;
			ciudad=city;
			entidad=entiti;
		 }
	 }
	 //id
	 //departamento
	 //email
	 //telefono
	 //direccion
}
