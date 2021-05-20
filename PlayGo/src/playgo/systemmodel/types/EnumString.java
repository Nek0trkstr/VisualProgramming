
package playgo.systemmodel.types;



/**
 * This file was automatically generated using PlayGo system-model.
 * This class shouldn't be changed. Any change to this class will be overridden!!!
 * 
 */
public enum EnumString {

    _tail ("tail"),
	_fetch ("fetch"),
	_empty ("empty"),
	_toy ("toy");

	private String displayString;

	EnumString(String displayString){
		this.displayString = displayString;
	}
	public String toString(){
		return this.displayString;
	}
	public boolean equals(String str) {
		return str.equals(this.toString());
	}

}
