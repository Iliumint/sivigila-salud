package com.vigilatusalud_v3;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ResultBusquedaGeneralidades extends Activity {

	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) 
		{
		case R.id.atras_gen:
			finish();
			break;

		default:
			break;
		}		
		
		return super.onOptionsItemSelected(item);		
	}
	
	
	TextView Dominio, Tema, Subtema, Descripcion;
	TextView txDom, txTem, txSubtem, txDescrip; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_busqueda_generalidades);
		
		Dominio=(TextView)findViewById(R.id.TxtDominio);
		Tema=(TextView)findViewById(R.id.TxtTema);
		Subtema=(TextView)findViewById(R.id.TxtSubTema);
		Descripcion=(TextView)findViewById(R.id.TxtDescription);
		
		txDom=(TextView)findViewById(R.id.Texto_dom);
		txTem=(TextView)findViewById(R.id.Texto_tem);
		txSubtem=(TextView)findViewById(R.id.Texto_subtem);
		txDescrip=(TextView)findViewById(R.id.Texto_descrip);
		
		Intent in=getIntent();
		String dom=in.getStringExtra("DOMINIO");
		String tem=in.getStringExtra("TEMA");
		String subtem=in.getStringExtra("SUBTEMA");
		String descripty = in.getStringExtra("DES");
		
		llenar_textos(dom, tem, subtem, descripty);
	}
	
	
	public void llenar_textos(String dom, String tem, String subtem, String descrip)
	{
		Dominio.setText(dom);
		Tema.setText(tem);
		
		if(!subtem.equals("null"))
		{
			Subtema.setVisibility(View.VISIBLE);
			Subtema.setText(subtem);
		}
		else
		{
			txSubtem.setVisibility(View.INVISIBLE);
			Subtema.setVisibility(View.INVISIBLE);
		}

		if(descrip.equals("null"))
		{
			txDescrip.setVisibility(View.INVISIBLE);
			Descripcion.setVisibility(View.INVISIBLE);
//			Descripcion.setText("Descripción no disponible");
		}
		else
		{	
			txDescrip.setVisibility(View.VISIBLE);
			Descripcion.setVisibility(View.VISIBLE);
			Descripcion.setText(descrip);
		}
	}

	
//	private String buscar_descripcion(String tem, String subtem)
//	{
//		BasedeDatos databas=new BasedeDatos();
//		ArrayList<String> items=new ArrayList<String>();
//		items.addAll(databas.consultar_descripcion(subtem));
////		Log.e("IMTES",items.get(0)+"");		
////		items.addAll(BasedeDatos.busqeuda_genralidades_subtema(subtem));
//		if(items==null)
//		{	return "null";}
//		else
//		{
//			String des=items.get(0)+"";
//			Log.e("ITEM",des);
//			return des;
//		}
//	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result_busqueda_generalidades, menu);
		return true;
	}

}
