package table;
public class TarifEau{
	Number MaxuniteEau1;
	Number PrixEauTranche1;
	Number MaxuniteEau2;
	Number PrixEauTranche2;
	

	public TarifEau(Number m,Number pe1,Number m2,Number pe2){
		this.MaxuniteEau1=m;
		this.PrixEauTranche1=pe1;
		this.MaxuniteEau2=m2;
		this.PrixEauTranche2=pe2;
	}
	public Number getPet1(){
		return this.PrixEauTranche1;
	}
	public Number getPet2(){
		return this.PrixEauTranche2;
	}

	public Number getMaxuniteEau1(){
		return  this.MaxuniteEau1;
	}
	
	public Number getMaxuniteEau2(){
		return  this.MaxuniteEau2;
	}
}