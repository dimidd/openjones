package jones.possessions;

public class Commodity {

    @Override
    public Commodity clone()  {
        return new Commodity(_unitValue, _name);
    }
	
	protected int _unitValue;
	protected String _name;
	
	public Commodity (int value, String name) {
		_unitValue = value;
		_name = name;
	}
        

        public Commodity (Commodity other) {
		this(other._unitValue, other._name);
	}

        
	public int getUnitValue() {
		return _unitValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_name == null) ? 0 : _name.hashCode());
		//result = prime * result + _unitValue;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commodity other = (Commodity) obj;
		if (_name == null) {
			if (other._name != null)
				return false;
		} else if (!_name.equals(other._name))
			return false;
//		if (_unitValue != other._unitValue)
//			return false;
		return true;
	}

    /**
     * The effects of this commodity accumulate
     * e.g. The more plants you have in your home, the happier will be a rest
     * @return 
     */ 
    public boolean hasAccumulativeEffect() {
        return false;
    }

    public int getRestHealthEffect() {
        return 0;
    }

    public int getRestHappinessEffect() {
        return 0;
    }
	
      
    @Override
    public String toString () {
        return _name;
    }
    
}

    

