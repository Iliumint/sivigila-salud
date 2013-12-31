package com.vigilatusalud_v3;

import java.util.ArrayList;
import java.util.List;

import consultas.Generalidades;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import clases.GeneralidadesSivigila;

public class GeneralidadesCuatroBotones extends Activity {

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) 
		{
		case R.id.buscar:
			lanzar_dialogBusqueda();
			break;

		default:
			break;
		}		
		
		return super.onOptionsItemSelected(item);		
	}
	
	ImageView generalidades, actores, flujoinfo, conceptosclaves;
	ImageView atras;
	private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
	ArrayList<String> resultado=new ArrayList<String>();
	EditText textoBusqueda;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ly_generalidades_cuatro_botones);
		
		generalidades=(ImageView)findViewById(R.id.subGeneralidades);
		actores=(ImageView)findViewById(R.id.subActores);
		flujoinfo=(ImageView)findViewById(R.id.subFlujoInfo);
		conceptosclaves=(ImageView)findViewById(R.id.subConceptos);
		atras=(ImageView)findViewById(R.id.imgAtrasCuatro);
		
		generalidades.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				lanzar_resultados("GENERALIDADES");
			}
		});
		actores.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				lanzar_resultadosActores("ACTORES Y RESPONSABLES");
			}
		});
		flujoinfo.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				lanzar_resultados("FLUJO DE INFORMACIÓN");
			}
		});
		conceptosclaves.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				lanzar_resultados("CONCEPTOS CLAVES");
			}
		});
		
		atras.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		
	}
	
	
	public void lanzar_dialogBusqueda()
	 {
	    final Dialog dialogo=new Dialog(this);
		dialogo.setContentView(R.layout.activity_busqueda_rapida);
		dialogo.setTitle("Busqueda Rapida: ");
		
		final EditText texto=(EditText)dialogo.findViewById(R.id.txtBusqueda);
		final Button buscar=(Button)dialogo.findViewById(R.id.btnBuscar);
		final ListView lista=(ListView)dialogo.findViewById(R.id.listBuscar);
		textoBusqueda=texto;
		ImageView atras=(ImageView)dialogo.findViewById(R.id.imgBack3);
		ImageView porVoz=(ImageView)dialogo.findViewById(R.id.imgVoz);
		
		texto.setFocusable(true);
		texto.setFocusableInTouchMode(true);
		texto.setClickable(true);
		
		//comprueba que el sistema este apto para reconcimiento de voz, necesario
		 PackageManager pm = getPackageManager();
	        List<ResolveInfo> activities = pm.queryIntentActivities(
	                new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
	        if (activities.size() != 0) {
	            porVoz.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						startVoiceRecognitionActivity();
						
					}
				});
	        } else {
	        	porVoz.setEnabled(false);
//	        	porVoz.setText("Google Voice no esta instalado, por favor descargarlo de la tienda Google Play");	        	
	        	try {
					porVoz.setImageResource(R.drawable.icono_acceso);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        
		buscar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Log.e("Buscar","Se toco el boton buscar");
				llenarLista_busqueda(texto, lista);				
			}
		});
		
		
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				String[] aux=resultado.get(index).split("-");
				String[] result=aux[0].split(" ");
				
				String descripcionCompat="";
				for (int j=1; j<aux.length;j++) {		
					
					descripcionCompat=descripcionCompat+aux[j];
				}
//				Log.e("DESCRIPCIOPN",descripcionCompat);
//				int i=0;
//				for (String palabra : result) {					
//					Log.e(i+"",palabra);
//					i++;
//				}
//				Se van a tomar el 1, 3, 5, 7,
				
				mostrar_resultadoBusqeuda(result[1], result[3], result[5], descripcionCompat);
			}
		});
		
		atras.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			dialogo.dismiss();				
			}
		});		
	
		
		dialogo.show();
	 }//Cierrra el dialogo de busqueda
	
	
	public void mostrar_resultadoBusqeuda(String dom, String tem, String subtem, String descripty)
	{
		Intent i=new Intent(this, ResultBusquedaGeneralidades.class);	
		i.putExtra("DOMINIO", dom);
		i.putExtra("TEMA", tem);
		i.putExtra("SUBTEMA", subtem);
		i.putExtra("DES", descripty);
		startActivity(i);
		Log.e("Lanzar", "Mustra el resultado");
	}
	
	
	 public void llenarLista_busqueda(EditText texto, ListView lista)
	 {		 
		String nombre=texto.getText().toString();		
		resultado = generar_busqeuda(nombre);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, resultado);
		lista.setAdapter(adapter);
	 }
	 
		public ArrayList<String> generar_busqeuda(String nom)
		{
			Log.e("Texto",nom);
			ArrayList<String> items=new ArrayList<String>();
			items.addAll(BasedeDatos.busqeuda_genralidades_tema(nom));
			items.addAll(BasedeDatos.busqeuda_genralidades_subtema(nom));
			
			return items;
		}
	 
	 private void startVoiceRecognitionActivity() {
	        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

	        // Specify the calling package to identify your application
	        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());

	        // Display an hint to the user about what he should say.
	        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Diga el nombre del evento a consultar");

	        // Given an hint to the recognizer about what the user is going to say
	        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
	                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

	        // Specify how many results you want to receive. The results will be sorted
	        // where the first result is the one with higher confidence.
	        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);

	        // Specify the recognition language. This parameter has to be specified only if the
	        // recognition has to be done in a specific language and not the default one (i.e., the
	        // system locale). Most of the applications do not have to set this parameter.	       

	        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
	    }

	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {
	            // Fill the list view with the strings the recognizer thought it could have heard
	            ArrayList<String> matches = data.getStringArrayListExtra(
	                    RecognizerIntent.EXTRA_RESULTS);	            
	            //aqui implementa lo que desea. En el ArrayList matches van los strings de voz, siendo el primero el mas exacto.	            
//	            EditText cajaTexto = (EditText) findViewById(R.id.);
	            
	            textoBusqueda.setText(matches.get(0));  //siendo el primero el mas exacto me traigo el elemento 0.            	            
	        }
	        super.onActivityResult(requestCode, resultCode, data);
	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.generalidades_cuatro_botones, menu);
		return true;
	}
	
	public void lanzar_resultadosActores(String tipoItem)
	{
		Intent i=new Intent(this, ActoresResultados.class);		
		startActivity(i);
	}
	
	public void lanzar_resultados(String tipoItem)
	{
		Intent i=new Intent(this, GeneralidadesSivigila.class);
		i.putExtra("TIPO-ITEM", tipoItem);
		startActivity(i);
	}

}
