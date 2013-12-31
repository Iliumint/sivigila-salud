package clases;

import java.util.ArrayList;

import org.json.JSONException;

import com.vigilatusalud_v3.BasedeDatos;
import com.vigilatusalud_v3.InterpreteJson;
import com.vigilatusalud_v3.R;
import com.vigilatusalud_v3.R.id;
import com.vigilatusalud_v3.R.layout;
import com.vigilatusalud_v3.R.menu;

import conexiones_web.PeticionesWEB;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class ListarResultados extends Activity {

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId())
		{			
		// Para agregar una etiqueta		//
			case R.id.action_shared:
//				action_shared();
				shareSocialNetwork("Notificar Evento", "Notificación de Evento", "Direccion Archivo");
				return true;	
		}
		
		return super.onOptionsItemSelected(item);
		
	}
	
	@Override
	public void setTitle(CharSequence title) {
		// TODO Auto-generated method stub
		super.setTitle(title);
	}

	String eventoname;
	EventosClassNewLocalWEB claseEvento;
	
	TextView resultados;
	ListView listaEjemplo;
	Spinner NombresEventos;
	
	TextView Grupo, Subgrupo,Evento, Descripcion, CasosSospechosos, CasosProvados,
			CasosConfirmado, TiemposNotif, FichaNotif, DiagDiff, ApoyoLaboratorio,
			OtroApoyo, AccionesIndividuales, AccionesColectias, LinkUrl;
	
	ArrayList<String> eventos=new ArrayList<String>();
	ArrayList<EventoClassOldWEB> listaEventos;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listar_resultados_campos);

//		********** Para llenar la platilla lista resultado *******
//		resultados=(TextView)findViewById(R.id.textoResult);
//		resultados.setMovementMethod(new ScrollingMovementMethod());
		
				
		Intent i=getIntent();
//		String NombreEvento = i.getStringExtra("Resultados");		
		setTitle("Nombres de los Eventos");
		
		Grupo=(TextView)findViewById(R.id.PublicNomGrup);
		Subgrupo=(TextView)findViewById(R.id.PublicNomSubGrup);
		Evento=(TextView)findViewById(R.id.PublicNomEven);
		Descripcion=(TextView)findViewById(R.id.PublicDecripEven);
		CasosSospechosos=(TextView)findViewById(R.id.PublicCasSosp);
		CasosProvados=(TextView)findViewById(R.id.PublicCasProb);
		CasosConfirmado=(TextView)findViewById(R.id.PublicCasConf);
		TiemposNotif=(TextView)findViewById(R.id.PublicTimeNotif);
		FichaNotif=(TextView)findViewById(R.id.PublicFichNotif);
		DiagDiff=(TextView)findViewById(R.id.PublicDialogDiff);
		ApoyoLaboratorio=(TextView)findViewById(R.id.PublicApoyoLab);		
		OtroApoyo=(TextView)findViewById(R.id.PublicOtroApoyo);
		AccionesIndividuales=(TextView)findViewById(R.id.PublicAccInd);
		AccionesColectias=(TextView)findViewById(R.id.PublicAccColec);
		LinkUrl=(TextView)findViewById(R.id.PublicLinURl);
		
		NombresEventos=(Spinner)findViewById(R.id.spinNomEven);			
			
//		String resultadoJSON=PeticionesWEB.peticionJSON();
//		try {
//			eventos.addAll(capturarNombresEventos(resultadoJSON));		
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			Log.e("eventos","Genera el valor null");
//		}		
		
		BasedeDatos database=new BasedeDatos();
		final ArrayList<EventosClassNewLocalWEB> EVENTOS=database.consultar_FullEventos();
		for(int index=0;index<EVENTOS.size();index++)
		{
			eventos.add(EVENTOS.get(index).getNom_even());
			Log.e("EVENTO",EVENTOS.get(index).getNom_even());
		}
		
		//Llenamos el Spinner con los datos Nombre de Eventos generados
		llenar_spinner_nombresEventos(eventos);	
		
		NombresEventos.setOnItemSelectedListener(new OnItemSelectedListener() {
			
				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int index, long arg3) {
					String evento=eventos.get(index);
					setTitle(evento);	
					claseEvento=EVENTOS.get(index);
					publicarInformacion(EVENTOS.get(index));
//					publicarOldInformacion(listaEventos.get(index));					
				}
				@Override
				public void onNothingSelected(AdapterView<?> arg0) {					
				}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_compartir, menu);
		return true;
	}
	
	public void publicarOldInformacion(EventoClassOldWEB eventoSelect)
	{
		Grupo.setText(eventoSelect.getNomgrup());
		Subgrupo.setText(eventoSelect.getNomsubgru());
		Evento.setText(eventoSelect.getNomeven());
		Descripcion.setText(eventoSelect.getDescrevent());
		CasosSospechosos.setText(eventoSelect.getCassosp());
		CasosProvados.setText(eventoSelect.getCasprob());
		CasosConfirmado.setText(eventoSelect.getCasconf());
		TiemposNotif.setText(eventoSelect.getTiemnotif());
		FichaNotif.setText(eventoSelect.getFichnotif());
		DiagDiff.setText(eventoSelect.getDiagdif());
		ApoyoLaboratorio.setText(eventoSelect.getApolab());
		OtroApoyo.setText(eventoSelect.getOtrapoyo());
		AccionesIndividuales.setText(eventoSelect.getAccind());
		AccionesColectias.setText(eventoSelect.getAcccolec());				
		try {
			mostrar_linkINFO(eventoSelect.getLinkurl());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}						
//		LinkUrl.setText(eventoSelect.getLinkurl());		
	}
	
	public void publicarInformacion(EventosClassNewLocalWEB eventoSelect)
	{
		Grupo.setText(eventoSelect.getNom_grup());
		Subgrupo.setText(eventoSelect.getNom_subgru());
		Evento.setText(eventoSelect.getNom_even());
		Descripcion.setText(eventoSelect.getDescr_event());
		CasosSospechosos.setText(eventoSelect.getCas_sosp());
		CasosProvados.setText(eventoSelect.getCas_prob());
		CasosConfirmado.setText(eventoSelect.getCas_conf());
		TiemposNotif.setText(eventoSelect.getTiem_notif());
		FichaNotif.setText(eventoSelect.getFich_notif());
		DiagDiff.setText(eventoSelect.getDiag_dif());
		ApoyoLaboratorio.setText(eventoSelect.getApo_lab());
		OtroApoyo.setText(eventoSelect.getOtr_apoyo());
		AccionesIndividuales.setText(eventoSelect.getAcc_ind());
		AccionesColectias.setText(eventoSelect.getAcc_colec());
		try
		{
			if(!(eventoSelect.getLink_url().equals("")))
			{
				Log.e("Vacio","Es diferente a vacio");
				if(!(eventoSelect.getLink_url().equals(null)))					
				{Log.e("Vacio","Es diferente a null");
				mostrar_linkINFO(eventoSelect.getLink_url());
				}
				else
				{LinkUrl.setText("");}
			}
			else
			{LinkUrl.setText("");}
		}
		catch(Exception e)
		{
			Log.e("Error-Link","No se pudo formar el link "+e.toString());
			LinkUrl.setText("");
		}
//		LinkUrl.setText(eventoSelect.getLinkurl());		
	}
	
	public void llenar_spinner_nombresEventos(ArrayList<String> eventosP)
	{
		ArrayAdapter<String> adaptador;
		try {
			adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, eventosP);
			NombresEventos.setAdapter(adaptador);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public ArrayList<String> capturarNombresEventos(String codigoJson)
	{
		ArrayList<String> evento=new ArrayList<String>();
		if(!codigoJson.equals("Nada"))
		{
		try {
			listaEventos=InterpreteJson.generarListaOld_Eventos(codigoJson);
			for(int f=0;f<listaEventos.size();f++)
			{
				evento.add(listaEventos.get(f).getNomeven());
			}
		} catch (JSONException e) {
			Log.e("Conversion",""+e.toString());
			e.printStackTrace();
		}}
		else{evento=null;return evento;}
		
		return evento;
	}
	
	 public void mostrar_linkINFO(String url_link)
	 {
		//Agrega el formato link
		SpannableString myString  = new SpannableString(url_link);
		myString.setSpan(new UnderlineSpan(), 0, myString.length(), 0);
		LinkUrl.setTextSize(12);
		LinkUrl.setText(myString);
		
		
		//Agrega el Enlace
		if((url_link!=null)||(!url_link.equals("null")))
		{
		LinkUrl.setText(Html.fromHtml(
				" "
				+ "<a href=" 
				+ url_link 
				+ ">" 
				+url_link 
				+"</a>"
				));
		LinkUrl.setMovementMethod(LinkMovementMethod.getInstance());
		}
	 }

	 public void shareSocialNetwork(String title, String extraTitle,String filename) 
	 {
		 // "claseEvento" contiene todos los valores de la consulta del evento
	        Intent share = new Intent(Intent.ACTION_SEND);
	        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//	        share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filename)));
	        share.putExtra(Intent.EXTRA_TITLE, extraTitle);
	        share.putExtra(Intent.EXTRA_SUBJECT, extraTitle);
	        String msg= "Nombre del Evento: \n"+claseEvento.getNom_even()+"\n\n" +
	        		"Descripción:\n "+claseEvento.getDescr_event()+
	        		"\n\nColombia SiVigila la Salud Pública APP";						
			share.putExtra(Intent.EXTRA_TEXT,msg);
	        share.setType("image/png");
	        startActivity(Intent.createChooser(share, title));
	 }
}
