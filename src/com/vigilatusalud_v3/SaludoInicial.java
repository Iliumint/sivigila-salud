package com.vigilatusalud_v3;

import java.util.Timer;
import java.util.TimerTask;

import clases.Class_NoTrasmisibles;
import clases.MainActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.widget.Toast;

public class SaludoInicial extends Activity {

	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	Context contexto=this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_saludo_inicial);
	
		// Clase en la que está el código a ejecutar 
	     TimerTask timerTask = new TimerTask() 
	     { 
	         public void run()  
	         { 
	             // Aquí el código que queremos ejecutar. 
	        	 Log.e("APLication","Lanzar Aplication: ");
	        	 Lanzar_Inicio();
	        	 Log.e("APLication","LANZADO ");
	         } 
	     };	     

	      // Aquí se pone en marcha el timer cada segundo. 
	     Timer timer = new Timer(); 
	     // Dentro de 0 milisegundos avísame cada 1000 milisegundos
	     timer.schedule(timerTask, 2700);
//	     timer.scheduleAtFixedRate(timerTask, 0, 3000);
		
		
	}

	public void Lanzar_Inicio()
	{		
//		Log.e("APLication","Lanzar Inicio ");
		Intent i=new Intent(this, PantallaPrincipal.class);	
//		i.putExtra("P_SESION", p_sesion);	
		startActivity(i);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.saludo_inicial, menu);
		return true;
	}

}
