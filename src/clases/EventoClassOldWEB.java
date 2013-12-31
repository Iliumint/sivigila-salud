package clases;

import java.util.ArrayList;

public class EventoClassOldWEB {

	public String getAcccolec() {
		return acccolec;
	}

	public void setAcccolec(String acccolec) {
		this.acccolec = acccolec;
	}

	public String getAccind() {
		return accind;
	}

	public void setAccind(String accind) {
		this.accind = accind;
	}

	public String getApolab() {
		return apolab;
	}

	public void setApolab(String apolab) {
		this.apolab = apolab;
	}

	public String getCasconf() {
		return casconf;
	}

	public void setCasconf(String casconf) {
		this.casconf = casconf;
	}

	public String getCasprob() {
		return casprob;
	}

	public void setCasprob(String casprob) {
		this.casprob = casprob;
	}

	public String getCassosp() {
		return cassosp;
	}

	public void setCassosp(String cassosp) {
		this.cassosp = cassosp;
	}

	public String getDescrevent() {
		return descrevent;
	}

	public void setDescrevent(String descrevent) {
		this.descrevent = descrevent;
	}

	public String getDiagdif() {
		return diagdif;
	}

	public void setDiagdif(String diagdif) {
		this.diagdif = diagdif;
	}

	public String getFichnotif() {
		return fichnotif;
	}

	public void setFichnotif(String fichnotif) {
		this.fichnotif = fichnotif;
	}

	public String getLinkurl() {
		return linkurl;
	}

	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}

	public String getNomdimen() {
		return nomdimen;
	}

	public void setNomdimen(String nomdimen) {
		this.nomdimen = nomdimen;
	}

	public String getNomeven() {
		return nomeven;
	}

	public void setNomeven(String nomeven) {
		this.nomeven = nomeven;
	}

	public String getNomgrup() {
		return nomgrup;
	}

	public void setNomgrup(String nomgrup) {
		this.nomgrup = nomgrup;
	}

	public String getNomsubgru() {
		return nomsubgru;
	}

	public void setNomsubgru(String nomsubgru) {
		this.nomsubgru = nomsubgru;
	}

	public String getOtrapoyo() {
		return otrapoyo;
	}

	public void setOtrapoyo(String otrapoyo) {
		this.otrapoyo = otrapoyo;
	}

	public String getPartitionkey() {
		return PartitionKey;
	}

	public void setPartitionkey(String partitionkey) {
		PartitionKey = partitionkey;
	}

	public String getRowKey() {
		return RowKey;
	}

	public void setRowKey(String rowKey) {
		RowKey = rowKey;
	}

	public String getTiemnotif() {
		return tiemnotif;
	}

	public void setTiemnotif(String tiemnotif) {
		this.tiemnotif = tiemnotif;
	}

	String acccolec="";//1
	String accind="";//2
	String apolab="";//3
	String casconf="";//4
	String casprob="";//5
	String cassosp="";//6
	String descrevent="";//7
	String diagdif="";//8
	String fichnotif="";//9
	String linkurl="";//10
	String nomdimen="";//11
	String nomeven="";//12
	String nomgrup="";//13
	String nomsubgru="";//14
	String otrapoyo="";//15
	String PartitionKey="";//16
	String RowKey="";//17
	String tiemnotif="";//18
	
	
	
	public static ArrayList<String> camposClass()
	{
		ArrayList<String> camposClass=new ArrayList<String>();
		camposClass.add("acccolec");//pos-0
		camposClass.add("accind");//pos-1
		camposClass.add("apolab");//pos-2
		camposClass.add("casconf");//pos-3
		camposClass.add("casprob");//pos-4
		camposClass.add("cassosp");//pos-5
		camposClass.add("descrevent");//pos-6
		camposClass.add("diagdif");//pos-7
		camposClass.add("fichnotif");//pos-8
		camposClass.add("linkurl");//pos-9
		camposClass.add("nomdimen");//pos-10
		camposClass.add("nomeven");//pos-11
		camposClass.add("nomgrup");//pos-12
		camposClass.add("nomsubgru");//pos-13
		camposClass.add("otrapoyo");//pos-14
		camposClass.add("PartitionKey");//pos-15
		camposClass.add("RowKey");//pos-16
		camposClass.add("tiemnotif");//pos-17
		
		
		return camposClass;
	}
	
	public EventoClassOldWEB(
			String acccolec_,
			String accind_,
			String apolab_,
			String casconf_,
			String casprob_,
			String cassosp_,
			String descrevent_,
			String diagdif_,
			String fichnotif_,
			String linkurl_,
			String nomdimen_,
			String nomeven_,
			String nomgrup_,
			String nomsubgru_,
			String otrapoyo_,
			String Partitionkey_,
			String RowKey_,
			String tiemnotif_
			)
	{
		
		acccolec= acccolec_;
		accind= accind_;
		apolab= apolab_;
		casconf= casconf_;
		casprob= casprob_;
		cassosp= cassosp_;
		descrevent= descrevent_;
		diagdif= diagdif_;
		fichnotif= fichnotif_;
		linkurl= linkurl_;
		nomdimen= nomdimen_;
		nomeven= nomeven_;
		nomgrup= nomgrup_;
		nomsubgru= nomsubgru_;
		otrapoyo= otrapoyo_;
		PartitionKey= Partitionkey_;
		RowKey= RowKey_;
		tiemnotif= tiemnotif_;
		
	}
	
	public EventoClassOldWEB()
	{}
	
}
