package com.vigilatusalud_v3;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ActoresResultados extends Activity {

	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) 
		{
		case R.id.atras_actores:
			finish();
			break;

		default:
			break;
		}		
		
		return super.onOptionsItemSelected(item);		
	}
	
	
	
	ImageView atras;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actores_resultados);
		
		atras=(ImageView)findViewById(R.id.btnAtras);
		
		atras.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actores_resultados, menu);
		return true;
	}

}
