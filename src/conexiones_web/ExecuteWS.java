package conexiones_web;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class ExecuteWS extends AsyncTask<HttpEntity, HttpEntity, String> {
	
	public String Url;
	private boolean consultar;

	private Context context;
    ProgressDialog dialog;

	
    protected boolean doInBackground(String... uri) {
    	
    	 HttpGet httpGet = new HttpGet(uri[0]);
 		 HttpClient client = new DefaultHttpClient();
 		 HttpResponse response;
 		 Log.e("DOIN","Corriendo inBackgroud");
 		try {
 			response = client.execute(httpGet);
 			  StatusLine statusLine = response.getStatusLine();
 		        int statusCode = statusLine.getStatusCode();
 		      if( statusLine.getStatusCode() == HttpStatus.SC_OK){	return true;}
 		 	return false;
 		} catch (ClientProtocolException e) {
 			// TODO Auto-generated catch block
 			
 			return false;
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			return false;
 		}
    }


	@Override
	protected String doInBackground(HttpEntity... entity) {
		
		InputStream is = null;
		
		HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        if(!consultar){
        HttpPost httppost =new HttpPost(Url);//new HttpPost("http://192.168.85.65/ServicioEmergenciaSantander/Servicios.svc/InsertarHogar/"); //new HttpPost(Url);
        httppost.setHeader("content-type", "application/json");       
        //httppost.setHeader("content-type", "text/xml"); 
        String responseString = null;
        try {        
        	
                httppost.setEntity(entity[0]);
               
                response = httpclient.execute(httppost);
                Log.i("Resultado", response.getParams().toString());
                Log.e("POST", "Se llamo el metodo POST");
                //responseString = response.getStatusLine().toString();
                
////////////////////////////////////////////////////////////////////
//                
//                StatusLine statusLine = response.getStatusLine();
//                int statusCode = statusLine.getStatusCode();
//                if (statusCode == 200) {
//                    HttpEntity httpEntity = response.getEntity();
//                    is = httpEntity.getContent();
//                } else {
//                    Log.e("Log", "Failed to download result..");
//                }
//                
//////////////////////////////////////////////////////////////////////
                responseString = EntityUtils.toString(response.getEntity());
            
        } catch (ClientProtocolException e) {
        	Log.e("Error", "Catch1 POST", e);
        } catch (IOException e) {
        	Log.e("Error", "Catch2 POST", e);
        }catch(Exception ex){
        	Log.e("ServicioRest","Catch3 POST", ex);
        }
        
        return responseString;
        }
        else{
       
            HttpGet httpget =new HttpGet(Url);//new HttpPost("http://192.168.85.65/ServicioEmergenciaSantander/Servicios.svc/InsertarHogar/"); //new HttpPost(Url);
            httpget.setHeader("content-type", "application/json");       
            //httppost.setHeader("content-type", "text/xml"); 
            String responseString = null;
            try {       	
            		Log.e("GET", "Se llamo el metodo GET");         	               
                    response = httpclient.execute(httpget);
                    Log.i("Resultado", response.getParams().toString()); 
                            
                    //responseString = response.getStatusLine().toString();
                    
                    
////////////////////////////////////////////////////////////////////
//                    
//					StatusLine statusLine = response.getStatusLine();
//					int statusCode = statusLine.getStatusCode();
//					if (statusCode == 200) {
//					HttpEntity httpEntity = response.getEntity();
//					is = httpEntity.getContent();
//					} else {
//					Log.e("Log", "Failed to download result..");
//					}
//
//////////////////////////////////////////////////////////////////////

                    responseString = EntityUtils.toString(response.getEntity());
                
            } catch (ClientProtocolException e) {
            	Log.e("Error", "Catch1 GET "+e.toString(), e);
            } catch (IOException e) {
            	Log.e("Error", "Catch2 GET "+e.toString(), e);
            } catch(Exception ex){
            	Log.e("ServicioRest","Catch3 GET "+ex.toString(), ex);
            }
            
         //   dialog.dismiss();
            
            return responseString;
        	
        	
        }
        
	}

  
    protected void onPostExecute(Void unused) {
        dialog.dismiss();
    }

	
	public  void setUrl(String url) {		
		this.Url=url;
		
	}


	public void setConsultar(boolean b) {
		// TODO Auto-generated method stub
		this.consultar=b;
	}

}
