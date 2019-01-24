package table;
public class TarifElectricite{
	Number MaxuniteElectricite1;
	Number MaxuniteElectricite2;
	Number MaxuniteElectricite3;
	Number PrixElectriciteTranche1;
	Number PrixElectriciteTranche2;
	Number PrixElectriciteTranche3;

	public TarifElectricite(Number mel1,Number mel2,Number mel3,Number pel1,Number pel2,Number pel3){
		this.MaxuniteElectricite1=mel1;
		this.MaxuniteElectricite2=mel2;
		this.MaxuniteElectricite3=mel3;
		this.PrixElectriciteTranche1=pel1;
		this.PrixElectriciteTranche2=pel2;
		this.PrixElectriciteTranche3=pel3;
}
	public Number getPelec1(){
		return this.PrixElectriciteTranche1;
	}
	public Number getPelec2(){
		return this.PrixElectriciteTranche2;
	}
	public Number getPelec3(){
		return this.PrixElectriciteTranche2;
	}
	public Number getMaxuniteElectricite1(){
		return this.MaxuniteElectricite1;
	}
	public Number getMaxuniteElectricite2(){
		return this.MaxuniteElectricite2;
	}
	public Number getMaxuniteElectricite3(){
		return this.MaxuniteElectricite1;
	}
}