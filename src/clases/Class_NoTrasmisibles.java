package clases;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import com.vigilatusalud_v3.BasedeDatos;
import com.vigilatusalud_v3.PublicarInfoBusqueda;
import com.vigilatusalud_v3.R;
import com.vigilatusalud_v3.R.id;
import com.vigilatusalud_v3.R.layout;
import com.vigilatusalud_v3.R.menu;

import consultas.AccionesSalud;
import consultas.Apoyo;
import consultas.DiagnosticoDiferencial;
import consultas.Generalidades;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;



public class Class_NoTrasmisibles extends Activity {

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
//		finish();
	}

	ListView listaTramisibles;
	ArrayList<String> listademo=new ArrayList<String>();
	Context contextoM=this;	
	ArrayList<String> subgrupos;
	ArrayList<String> nombres;
	Spinner spinSubgrupo;
	BasedeDatos baseDatos=new BasedeDatos();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_class_trasmisibles);
		
		listaTramisibles=(ListView)findViewById(R.id.listTransmi);
		spinSubgrupo=(Spinner)findViewById(R.id.spinTrans);		
		
		llenar_spinner();
		
		spinSubgrupo.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override			
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				llenar_listaPorGrupo(subgrupos.get(arg2));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {				
			}
		});
		
		listaTramisibles.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				lanzar_opcionesVisualizacion(nombres.get(index));
			}
		});
		
		ImageView atras=(ImageView)findViewById(R.id.imgAtrasCuatro);
		atras.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				actividad_anterior();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.class__trasmisibles, menu);
		return true;
	}

	public void llenar_spinner()
	{
		try{
			subgrupos=baseDatos.consultar_nombres_subgrupos("NO TRANSMISIBLES");
		}
		catch(Exception e)
		{
			Log.e(e.toString(),"NO pudo conectarse a la BD vision");
		}
				
		HashSet hs = new HashSet();
		//Lo cargamos con los valores del array, esto hace quite los repetidos
		hs.addAll(subgrupos);
		//Limpiamos el array
		subgrupos.clear();
		//Agregamos los valores sin repetir
		subgrupos.addAll(hs);
		Collections.sort(subgrupos);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,subgrupos);
		spinSubgrupo.setAdapter(adapter);
	}
	
	public void llenar_listaPorGrupo(String subgrupoP)
	{
		nombres = baseDatos.consultar_nombres_enfermedades(subgrupoP);		
		//Creamos un objeto HashSet
		HashSet hs = new HashSet();
		//Lo cargamos con los valores del array, esto hace quite los repetidos
		hs.addAll(nombres);
		//Limpiamos el array
		nombres.clear();
		//Agregamos los valores sin repetir
		nombres.addAll(hs);
		Collections.sort(nombres);
		
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,nombres);
		listaTramisibles.setAdapter(adapter);
	}	

	
	public void lanzar_opcionesVisualizacion(final String titulo)
	 {
	    final Dialog dialogo=new Dialog(this);
		dialogo.setContentView(R.layout.comovisualizarinfo);
		dialogo.setTitle("Evento: " +titulo);
		
		ImageView FullInformation,Generalidades,AccionesCotrol,ApoyoDiag,Diagnostico,datras;
		FullInformation=(ImageView)dialogo.findViewById(R.id.imgFInforma);
		Generalidades=(ImageView)dialogo.findViewById(R.id.imgGeneralidades);
		AccionesCotrol=(ImageView)dialogo.findViewById(R.id.imgAccControl);
		ApoyoDiag=(ImageView)dialogo.findViewById(R.id.imgApoyoDiag);		
		Diagnostico=(ImageView)dialogo.findViewById(R.id.imgDiagnosticoDif);
		datras=(ImageView)dialogo.findViewById(R.id.imgAtrasCuatro);
		
		
		FullInformation.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				lanzar_informacion(titulo);
//				dialogo.dismiss();
			}
		});
		Generalidades.setOnClickListener(new OnClickListener() {					
			@Override
			public void onClick(View v) {
				lanzar_generalidades(titulo);
//				dialogo.dismiss();
			}
		});
		AccionesCotrol.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				lanzar_accionesControl(titulo);
//				dialogo.dismiss();
			}
		});
		ApoyoDiag.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				lanzar_apoyoDiag(titulo);
//				dialogo.dismiss();
			}
		});		
		Diagnostico.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				lanzar_diagnosticoDif(titulo);
//				dialogo.dismiss();
			}
		});		
		datras.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
			dialogo.dismiss();
				
			}
		});
		
		dialogo.show();
	 } 
	 
	  
	 public void lanzar_generalidades(String even)
	 {
		Intent i=new Intent(this, Generalidades.class);	
		i.putExtra("EVENTO", even);	
		startActivity(i);
	 }
	 public void lanzar_accionesControl(String even)
	 {
		Intent i=new Intent(this, AccionesSalud.class);	
		i.putExtra("EVENTO", even);	
		startActivity(i);
	 }
	 public void lanzar_apoyoDiag(String even)
	 {
		Intent i=new Intent(this, Apoyo.class);	
		i.putExtra("EVENTO", even);	
		startActivity(i);
	 }
	 public void lanzar_diagnosticoDif(String even)
	 {
		Intent i=new Intent(this, DiagnosticoDiferencial.class);	
		i.putExtra("EVENTO", even);	
		startActivity(i);
	 }
	 public void lanzar_informacion(String even)
	 {
		Intent i=new Intent(this, PublicarInfoBusqueda.class);	
		i.putExtra("EVENTO", even);	
		startActivity(i);
	 }
		
	public void actividad_anterior()
	{
//		Intent i=new Intent(this, MainActivity.class);
//		startActivity(i);
		finish();
	}
}
