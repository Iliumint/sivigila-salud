package clases;

import java.util.ArrayList;

public class EventosClassNewLocalWEB {

	    public String getNom_grup() {
			return nom_grup;
		}
		public void setNom_grup(String nom_grup) {
			this.nom_grup = nom_grup;
		}
		public String getNom_subgru() {
			return nom_subgru;
		}
		public void setNom_subgru(String nom_subgru) {
			this.nom_subgru = nom_subgru;
		}
		public String getNom_even() {
			return nom_even;
		}
		public void setNom_even(String nom_even) {
			this.nom_even = nom_even;
		}	
		public String getDescr_event() {
			return descr_event;
		}
		public void setDescr_event(String descr_event) {
			this.descr_event = descr_event;
		}
		public String getCas_sosp() {
			return cas_sosp;
		}
		public void setCas_sosp(String cas_sosp) {
			this.cas_sosp = cas_sosp;
		}
		public String getCas_prob() {
			return cas_prob;
		}
		public void setCas_prob(String cas_prob) {
			this.cas_prob = cas_prob;
		}
		public String getCas_conf() {
			return cas_conf;
		}
		public void setCas_conf(String cas_conf) {
			this.cas_conf = cas_conf;
		}
		public String getTiem_notif() {
			return tiem_notif;
		}
		public void setTiem_notif(String tiem_notif) {
			this.tiem_notif = tiem_notif;
		}
		public String getFich_notif() {
			return fich_notif;
		}
		public void setFich_notif(String fich_notif) {
			this.fich_notif = fich_notif;
		}
		public String getDiag_dif() {
			return diag_dif;
		}
		public void setDiag_dif(String diag_dif) {
			this.diag_dif = diag_dif;
		}
		public String getApo_lab() {
			return apo_lab;
		}
		public void setApo_lab(String apo_lab) {
			this.apo_lab = apo_lab;
		}
		public String getOtr_apoyo() {
			return otr_apoyo;
		}
		public void setOtr_apoyo(String otr_apoyo) {
			this.otr_apoyo = otr_apoyo;
		}
		public String getAcc_ind() {
			return acc_ind;
		}
		public void setAcc_ind(String acc_ind) {
			this.acc_ind = acc_ind;
		}
		public String getAcc_colec() {
			return acc_colec;
		}
		public void setAcc_colec(String acc_colec) {
			this.acc_colec = acc_colec;
		}
		public String getLink_url() {
			return link_url;
		}
		public void setLink_url(String link_url) {
			this.link_url = link_url;
		}
		
		 String nom_grup;
		 String nom_subgru;
		 String nom_even;
//		 String nom_dimen; // String d,
		 String descr_event;
		 String cas_sosp;
		 String cas_prob;
		 String cas_conf;
		 String tiem_notif;
		 String fich_notif;
		 String diag_dif;
		 String apo_lab;
		 String otr_apoyo;
		 String acc_ind;
		 String acc_colec;
		 String link_url;
		 
		 public EventosClassNewLocalWEB(String nom_grupa,String nom_subgrub,String nom_evenc, String descr_evente,String cas_sospf,
				 String cas_probg,String cas_confh,String tiem_notifi,String fich_notifj,String diag_difk,String apo_labl,String otr_apoyom,
				 String acc_indn,String acc_coleco,String link_urlp)
		 {
			  nom_grup=nom_grupa;
			  nom_subgru=nom_subgrub;
			  nom_even=nom_evenc;			  
			  descr_event=descr_evente;
			  cas_sosp=cas_sospf;
			  cas_prob=cas_probg;
			  cas_conf=cas_confh;
			  tiem_notif=tiem_notifi;
			  fich_notif=fich_notifj;
			  diag_dif=diag_difk;
			  apo_lab=apo_labl;
			  otr_apoyo=otr_apoyom;
			  acc_ind=acc_indn;
			  acc_colec=acc_coleco;
			  link_url=link_urlp;
		 }	
		 
		 
		public static ArrayList<String> camposLocalClass()
		{
			ArrayList<String> camposClass=new ArrayList<String>();
			camposClass.add("id");//pos-0
			camposClass.add("nom_grup");//pos-1
			camposClass.add("nom_subgru");//pos-2
			camposClass.add("nom_even");//pos-3
			camposClass.add("descr_event");//pos-4
			camposClass.add("cas_sosp");//pos-5
			camposClass.add("cas_prob");//pos-6
			camposClass.add("cas_conf");//pos-7
			camposClass.add("tiem_notif");//pos-8
			camposClass.add("fich_notif");//pos-9
			camposClass.add("diag_dif");//pos-10
			camposClass.add("apo_lab");//pos-11
			camposClass.add("otr_apoyo");//pos-12
			camposClass.add("acc_ind");//pos-13
			camposClass.add("acc_colec");//pos-14
			camposClass.add("link_url");//pos-15
			
			
			return camposClass;
		}
		
		public EventosClassNewLocalWEB()
		{}
}
