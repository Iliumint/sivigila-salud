package consultas;

import java.io.File;
import java.util.List;

import clases.EventosClassNewLocalWEB;

import com.vigilatusalud_v3.BasedeDatos;
import com.vigilatusalud_v3.R;
import com.vigilatusalud_v3.R.layout;
import com.vigilatusalud_v3.R.menu;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class Generalidades extends FragmentActivity {

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId())
		{			
		// Para agregar una etiqueta		//
			case R.id.action_shared:
//				action_shared();
				shareSocialNetwork("Notificar Evento", "Información Evento de Vigilancia de Salud Publica en Colombia:", "Direccion Archivo");
//				shareSocialNetwork2("Notificar Evento", "Información Evento de Vigilancia de Salud Publica en Colombia:", "Direccion Archivo");
//				compartirFacebbok(findViewById(android.R.id.content).getRootView());
				return true;	
			case R.id.atras_gen:
				finish();
				break;
		}
		
		return super.onOptionsItemSelected(item);
		
	}

	String evento;
	EventosClassNewLocalWEB claseEvento;
	TextView Grupo, Subgrupo,Evento, Descripcion, CasosSospechosos, CasosProvados,
	CasosConfirmado, TiemposNotif, FichaNotif, DiagDiff, ApoyoLaboratorio,
	OtroApoyo, AccionesIndividuales, AccionesColectias, LinkUrl;
	
	TextView casosPro,casosSosp, casosConfir, fichaNotifa, timeNotifa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plantilla_generalidades);
		
		Intent i=getIntent();
		evento=i.getStringExtra("EVENTO");
		
//		Grupo=(TextView)findViewById(R.id.PublicNomGrup);
//		Subgrupo=(TextView)findViewById(R.id.PublicNomSubGrup);
		Evento=(TextView)findViewById(R.id.lynomevent);
		Descripcion=(TextView)findViewById(R.id.lyDescrip);
		CasosSospechosos=(TextView)findViewById(R.id.lyCasSosp);
		CasosProvados=(TextView)findViewById(R.id.lyCasProb);
		CasosConfirmado=(TextView)findViewById(R.id.lyCasConf);
		TiemposNotif=(TextView)findViewById(R.id.lyTime);
		FichaNotif=(TextView)findViewById(R.id.lyFicha);
		
		casosConfir=(TextView)findViewById(R.id.tx_casosConfir);
		casosPro=(TextView)findViewById(R.id.tx_enlaces);
		casosSosp=(TextView)findViewById(R.id.tx_casosSospe);
		fichaNotifa=(TextView)findViewById(R.id.tx_fichaNotif);
		timeNotifa=(TextView)findViewById(R.id.tx_timenotif);
//		DiagDiff=(TextView)findViewById(R.id.PublicDialogDiff);
//		ApoyoLaboratorio=(TextView)findViewById(R.id.PublicApoyoLab);		
//		OtroApoyo=(TextView)findViewById(R.id.PublicOtroApoyo);
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
		if(!eventoSelect.getDescr_event().equals(""))//eventoSelect.getDescr_event()!=null||
		Descripcion.setText(eventoSelect.getDescr_event());
		else
		{Descripcion.setVisibility(View.INVISIBLE);}
		if((!eventoSelect.getCas_sosp().equals("")))//(eventoSelect.getCas_sosp()!=null)||
		{
//			Log.e("Sospechoso","Diferente de nulo:"+eventoSelect.getCas_sosp()+"ParaPRobelmas");
			CasosSospechosos.setText(eventoSelect.getCas_sosp());
		}
		else
		{CasosSospechosos.setVisibility(View.INVISIBLE);
		casosSosp.setVisibility(View.INVISIBLE);}
		if(!eventoSelect.getCas_prob().equals(""))//eventoSelect.getCas_prob()!=null||
		CasosProvados.setText(eventoSelect.getCas_prob());
		else
		{CasosProvados.setVisibility(View.INVISIBLE);
		casosPro.setVisibility(View.INVISIBLE);}
		if(!eventoSelect.getCas_conf().equals(""))//eventoSelect.getCas_conf()!=null||
		CasosConfirmado.setText(eventoSelect.getCas_conf());
		else
		{CasosConfirmado.setVisibility(View.INVISIBLE);
		casosConfir.setVisibility(View.INVISIBLE);}
		if(!eventoSelect.getTiem_notif().equals(""))//eventoSelect.getTiem_notif()!=null||
		TiemposNotif.setText(eventoSelect.getTiem_notif());
		else
		{TiemposNotif.setVisibility(View.INVISIBLE);
		timeNotifa.setVisibility(View.INVISIBLE);}
		if(!eventoSelect.getFich_notif().equals(""))//eventoSelect.getFich_notif()!=null||
		FichaNotif.setText(eventoSelect.getFich_notif());
		else
		{FichaNotif.setVisibility(View.INVISIBLE);
		fichaNotifa.setVisibility(View.INVISIBLE);}
		
//		DiagDiff.setText(eventoSelect.getDiag_dif());
//		ApoyoLaboratorio.setText(eventoSelect.getApo_lab());
//		OtroApoyo.setText(eventoSelect.getOtr_apoyo());
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
	
	 public void action_shared()
		{
		 //Para compartir en redes sociales
			Intent share =new Intent();
			share.setAction(Intent.ACTION_SEND);
			String msg= "El usuario '";						
			share.putExtra(Intent.EXTRA_TEXT,msg);	
			startActivity(Intent.createChooser(share, "Compartir"));			
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
	 
	 
	 public void shareSocialNetwork2(String title, String extraTitle,String filename) 
	 {
		 // "claseEvento" contiene todos los valores de la consulta del evento
	        Intent share = new Intent(Intent.ACTION_SEND);
	        share.setType("text/plain");
//	        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//			share.addCategory(Intent.CATEGORY_LAUNCHER);
			share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
//			share.setComponent(name);
//	        share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filename)));
	        share.putExtra(Intent.EXTRA_TITLE, extraTitle);
	        share.putExtra(Intent.EXTRA_SUBJECT, extraTitle);
	        String msg= "Nombre del Evento: \n"+evento+"\n\n" +
	        		"Descripción:\n "+claseEvento.getDescr_event()+"\n\n" +
	    	        		"Colombia SiVigila";											
			share.putExtra(Intent.EXTRA_TEXT,msg);						

	        startActivity(Intent.createChooser(share, title));
	 }
	 
	 
	 public void compartirFacebbok(View v)
	 {
	 Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
	 shareIntent.setType("text/plain");
	 shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, 
//			 (String) v.getTag(R.string.app_name))
			 "Subtema")
			 ;
	 shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, 
//			 (String) v.getTag(R.drawable.ic_launcher))
			 "texto que conttiene este asusnto")
			 ;
	 PackageManager pm = v.getContext().getPackageManager();
	 List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
	 for (final ResolveInfo app :activityList) {
	 if ((app.activityInfo.name).contains("facebook")) 
	 {
		 final ActivityInfo activity = app.activityInfo;
		 final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
		 shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		 shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
		 shareIntent.setComponent(name);
		 v.getContext().startActivity(shareIntent);
		 break;
	 }
	 }
	 }
	 

}
