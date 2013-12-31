package clases;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.vigilatusalud_v3.Directorio;
import com.vigilatusalud_v3.PublicarInfoBusqueda;
import com.vigilatusalud_v3.R;
import com.vigilatusalud_v3.R.id;
import com.vigilatusalud_v3.R.layout;
import com.vigilatusalud_v3.R.menu;

import conexiones_web.PeticionesWEB;
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
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	
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

	ImageView btnTrasmisibles,btnExternas, atras, 
	btnNoTrasmisibles, btnBusquedaRapida, btnDirectorio;
	EditText texto;
	ListView  lista;
	ArrayList<String> resultado=new ArrayList<String>();
	PeticionesWEB peticionweb=new PeticionesWEB();
	private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
	ImageView porVoz;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ly_main);
		setTitle("");
		
//	WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		
	btnTrasmisibles=(ImageView)findViewById(R.id.imgTrasmisibles);
	btnExternas=(ImageView)findViewById(R.id.imgExternas);
	btnNoTrasmisibles=(ImageView)findViewById(R.id.imgNoTrasmisibles);
//	btnBusquedaRapida=(ImageView)findViewById(R.id.imgBusquedaRapida);
	btnDirectorio=(ImageView)findViewById(R.id.imgGeneralidades);	
	atras=(ImageView)findViewById(R.id.imgAtras);
	
//	copiar_dbV3();
	
	//VIsualizamos la peticion
//	mostrar_mensaje(peticionweb.peticionJSON());
	atras.setOnClickListener(new OnClickListener() {		
		@Override
		public void onClick(View v) {
			finish();
		}
	});
	
	
	btnTrasmisibles.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Lanzar_Trasmisibles();			
		}
	});
	
	btnExternas.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Lanzar_Externas();
		}
	});
	
	btnNoTrasmisibles.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Lanzar_NoTrasmisibles();
		}
	});
	
//	btnBusquedaRapida.setOnClickListener(new OnClickListener() {
//		
//		@Override
//		public void onClick(View v) {
////			mostrar_mensaje("Se lanza dialogo para realizar busqueda rapida");
//			lanzar_dialogBusqueda();
//		}
//	});
	
	btnDirectorio.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Lanzar_Directorio();

		}
	});
		
	}
	
	 public void lanzar_dialogBusqueda()
	 {
	    final Dialog dialogo=new Dialog(this);
		dialogo.setContentView(R.layout.activity_busqueda_rapida);
		dialogo.setTitle("Busqueda Rapida: ");
		
		texto=(EditText)dialogo.findViewById(R.id.txtBusqueda);
		Button buscar=(Button)dialogo.findViewById(R.id.btnBuscar);
		lista=(ListView)dialogo.findViewById(R.id.listBuscar);
		
		ImageView atras=(ImageView)dialogo.findViewById(R.id.imgBack3);
		porVoz=(ImageView)dialogo.findViewById(R.id.imgVoz);
		
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
				
				llenarLista_busqueda();
			}
		});
		
		
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
//				Lanzar_consulta(resultado.get(arg2));
				lanzar_opcionesVisualizacion(resultado.get(arg2));
				
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
	 
//	 public void Lanzar_consulta(String seleccion)
//	 {
//		 Intent i=new Intent(this, PublicarInfoBusqueda.class);	
//		 i.putExtra("EVENTO", seleccion);
//		 Log.e("Seleccion de Busqueda",seleccion);
//		 startActivity(i);
//	 }
	 public void Lanzar_Directorio()
	 {
		 Intent i=new Intent(this, Directorio.class);	
//			i.putExtra("P_SESION", p_sesion);	
			startActivity(i);
	 }
	 
//	 public void Lanzar_ListaResultados(String lista)
//	 {
//		 Intent i=new Intent(this, ListarResultados.class);	
//			i.putExtra("Resultados", lista);	
//			startActivity(i);
//	 }
		
	 public void llenarLista_busqueda()
	 {		 
		String nombre=texto.getText().toString();
		resultado = generar_busqeuda(nombre);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, resultado);
		lista.setAdapter(adapter);
	 }
	 
	 public SQLiteDatabase Conectar_baseDatos()
	 {
		String DATABASE_NAME = "saludvigia";		
		File sdcard = Environment.getExternalStorageDirectory();
		String dbfile = sdcard.getAbsolutePath() + File.separator+ "MiSalud2" + File.separator + DATABASE_NAME;
		SQLiteDatabase db=SQLiteDatabase.openDatabase(dbfile, null,SQLiteDatabase.OPEN_READWRITE);
		return db;
	 }	 
	
	 
	public ArrayList<String> generar_busqeuda(String nom)
	{
//	SQLiteDatabase database =Conectar_baseDatos();
	SQLiteDatabase database =Conectar_baseDatos();
	ArrayList<String> posiblesnombres = new ArrayList<String>();
	Cursor cursor = database.rawQuery("SELECT DISTINCT nom_even FROM reporte_sivigia WHERE nom_even like "+"'%"+nom+"%'", null);
	 if (cursor.moveToFirst()) {
    	  do{
    		  posiblesnombres.add(cursor.getString(0));
    	  } while (cursor.moveToNext());
    	  }
	 return posiblesnombres;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void Lanzar_Trasmisibles()
	{		
		Intent i=new Intent(this, Class_Trasmisibles.class);	
//		i.putExtra("P_SESION", p_sesion);	
		startActivity(i);
	}
	public void Lanzar_Externas()
	{		
		Intent i=new Intent(this, Class_Externa.class);	
//		i.putExtra("P_SESION", p_sesion);	
		startActivity(i);
	}
	public void Lanzar_NoTrasmisibles()
	{		
		Intent i=new Intent(this, Class_NoTrasmisibles.class);	
//		i.putExtra("P_SESION", p_sesion);	
		startActivity(i);
	}
	
	public void mostrar_mensaje(String mensaje)
	{
		Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
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
//	    File archivoDB = new File(ruta + archivo);
//	    Log.e("RUTA",ruta);
//	    Log.e("PARA entrar IF",Boolean.toString(archivoDB.exists()));
//	    if (!archivoDB.exists()) 
//	    {
//	    	Log.e("EXIST", "No exite, entonces entra");
//		    try 
//		    {
//		        InputStream IS = getApplicationContext().getAssets().open(archivo);
//		        OutputStream OS = new FileOutputStream(archivoDB);
//		        byte[] buffer = new byte[1024];
//		        int length = 0;
//		        while ((length = IS.read(buffer))>0){
//		            OS.write(buffer, 0, length);
//		        }
//		        OS.flush();
//		        OS.close();
//		        IS.close();
//		        Log.e("LISTO","Base de datos creada");
//		    } 
//		    catch (FileNotFoundException e) 
//		    {
//			        Log.e("ERROR", "Archivo no encontrado, " + e.toString());
//		    }
//		    catch (IOException e) 
//		    {
//			        Log.e("ERROR", "Error al copiar la Base de Datos, " + e.toString());
//			}
//	    }
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
	
	///////////////////////
	//Para Consultas po voz
	
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
	            
	            texto.setText(matches.get(0));  //siendo el primero el mas exacto me traigo el elemento 0.            	            
	        }
	        super.onActivityResult(requestCode, resultCode, data);
	    }

}
