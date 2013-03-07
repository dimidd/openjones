package jones.possessions;

public class ModifiableCommodity extends Commodity {



    public ModifiableCommodity(int value, String name) {
		super(value, name);
		// TODO Auto-generated constructor stub
	}

	/**
     * @param unitValue the _unitValue to set
     */
    protected void setUnitValue(int unitValue) {
        this._unitValue = unitValue;
    }

}
