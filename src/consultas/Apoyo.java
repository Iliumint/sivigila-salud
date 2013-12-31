package consultas;

import clases.EventosClassNewLocalWEB;

import com.vigilatusalud_v3.BasedeDatos;
import com.vigilatusalud_v3.R;
import com.vigilatusalud_v3.R.layout;
import com.vigilatusalud_v3.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Apoyo extends Activity {

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId())
		{			
		// Para agregar una etiqueta		//
			case R.id.action_shared:
//				action_shared();
				shareSocialNetwork("Notificar Evento", "Información Evento de Vigilancia de Salud Publica en Colombia:", "Direccion Archivo");
				return true;	
			case R.id.atras_gen:
				finish();
				break;
		}
		
		return super.onOptionsItemSelected(item);
		
	}
	
	String evento;
	TextView Grupo, Subgrupo,Evento, Descripcion, CasosSospechosos, CasosProvados,
	CasosConfirmado, TiemposNotif, FichaNotif, DiagDiff, ApoyoLaboratorio,
	OtroApoyo, AccionesIndividuales, AccionesColectias, LinkUrl;
	
	TextView apoyodiag, otroApo;
	EventosClassNewLocalWEB claseEvento;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plantilla_apoyo);
		
		Intent i=getIntent();
		evento=i.getStringExtra("EVENTO");
		
//		Grupo=(TextView)findViewById(R.id.PublicNomGrup);
//		Subgrupo=(TextView)findViewById(R.id.PublicNomSubGrup);
		Evento=(TextView)findViewById(R.id.lynomevent);
//		Descripcion=(TextView)findViewById(R.id.PublicDecripEven);
//		CasosSospechosos=(TextView)findViewById(R.id.PublicCasSosp);
//		CasosProvados=(TextView)findViewById(R.id.PublicCasProb);
//		CasosConfirmado=(TextView)findViewById(R.id.PublicCasConf);
//		TiemposNotif=(TextView)findViewById(R.id.PublicTimeNotif);
//		FichaNotif=(TextView)findViewById(R.id.PublicFichNotif);
//		DiagDiff=(TextView)findViewById(R.id.PublicDialogDiff);
		ApoyoLaboratorio=(TextView)findViewById(R.id.lyApoyo);		
		OtroApoyo=(TextView)findViewById(R.id.lyOtroAp);
		apoyodiag=(TextView)findViewById(R.id.tx_accIndivi);
		otroApo=(TextView)findViewById(R.id.tx_accColect);
//		AccionesIndividuales=(TextView)findViewById(R.id.PublicAccInd);
//		AccionesColectias=(TextView)findViewById(R.id.PublicAccColec);
//		LinkUrl=(TextView)findViewById(R.id.PublicLinURl);
		
		BasedeDatos database=new BasedeDatos();
		EventosClassNewLocalWEB EventoSelected=database.consultar_Evento_unico(evento);
		claseEvento=EventoSelected;
		Log.e("Parametro Evento",evento);
		publicarInformacion(EventoSelected);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_compartir, menu);
		return true;
	}
	
	public void publicarInformacion(EventosClassNewLocalWEB eventoSelect)
	{
//		Grupo.setText(eventoSelect.getNom_grup());
//		Subgrupo.setText(eventoSelect.getNom_subgru());
		Evento.setText(eventoSelect.getNom_even());
//		Descripcion.setText(eventoSelect.getDescr_event());
//		CasosSospechosos.setText(eventoSelect.getCas_sosp());
//		CasosProvados.setText(eventoSelect.getCas_prob());
//		CasosConfirmado.setText(eventoSelect.getCas_conf());
//		TiemposNotif.setText(eventoSelect.getTiem_notif());
//		FichaNotif.setText(eventoSelect.getFich_notif());
//		DiagDiff.setText(eventoSelect.getDiag_dif());
		if(!eventoSelect.getApo_lab().equals(""))//||eventoSelect.getApo_lab()!=null
		ApoyoLaboratorio.setText(eventoSelect.getApo_lab());
		else
		{ApoyoLaboratorio.setVisibility(View.INVISIBLE);
		apoyodiag.setVisibility(View.INVISIBLE);}
		if(!eventoSelect.getOtr_apoyo().equals(""))//||eventoSelect.getOtr_apoyo()!=null
		OtroApoyo.setText(eventoSelect.getOtr_apoyo());
		else
		{OtroApoyo.setVisibility(View.INVISIBLE);
		otroApo.setVisibility(View.INVISIBLE);}
//		AccionesIndividuales.setText(eventoSelect.getAcc_ind());
//		AccionesColectias.setText(eventoSelect.getAcc_colec());
//		try
//		{
//			if(!(eventoSelect.getLink_url().equals("")))
//			{
//				Log.e("Vacio","Es diferente a vacio");
//				if(!(eventoSelect.getLink_url().equals(null)))					
//				{Log.e("Vacio","Es diferente a null");
//				mostrar_linkINFO(eventoSelect.getLink_url());
//				}
//				else
//				{LinkUrl.setText("");}
//			}
//			else
//			{LinkUrl.setText("");}
//		}
//		catch(Exception e)
//		{
//			Log.e("Error-Link","No se pudo formar el link "+e.toString());
//			LinkUrl.setText("");
//		}
		
//		LinkUrl.setText(eventoSelect.getLinkurl());		
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
	        share.setType("text/plain");
	        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//	        share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filename)));
	        share.putExtra(Intent.EXTRA_TITLE, extraTitle);
	        share.putExtra(Intent.EXTRA_SUBJECT, extraTitle);
	        String msg= "Nombre del Evento: \n"+evento+"\n\n" +
	        		"Descripción:\n "+claseEvento.getDescr_event()+"\n\n" +
	    	        		"Colombia SiVigila";											
			share.putExtra(Intent.EXTRA_TEXT,msg);
//	        share.setType("image/png");
	        startActivity(Intent.createChooser(share, title));
	 }

}
