/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.possessions;

import java.util.ArrayList;

/**
 *
 * @author dimid
 */
public class PossessionManager {

	//	private RentPossession 		_rent;
	//	private ClothesPossession	_clothes;
	//	private FoodPossession		_food;
	private RentContract _rentContract;
	private ArrayList<Possession> _poss;
	private int _rentDebt;

	public PossessionManager () {
		_poss = new ArrayList<>();

	}

	public Possession find(Possession newPos) {

		for (Possession p: _poss)
			if (p.getCommodity().equals(newPos.getCommodity())) {				
				return p;
			}
		return null; //not found

	}

	
	public void remove(Possession ps) {
		_poss.remove(ps);
	}

	
	public void add(Possession newPos) {

		Possession foundPos = find(newPos);
		if (null != foundPos) {
			foundPos.addUnits(newPos.getUnits());
		}
		else {
			_poss.add(newPos); //not found
		}
	}
	

	public int sumRestHealthEffects() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public int sumRestHappinessEffects() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public RentPossession getRentPossession() {
		return (RentPossession) find(_rentContract.getPossession());
	}

	public RentContract getRentContract() {
		return _rentContract;
	}

	public void setRentContract(RentContract r) {
		_rentContract = r;
	}

	public void setRentPossession(Possession possession) {
		_poss.remove(getRentPossession());
		_poss.add(possession);
	}

        
    /**
     * Before consuming check rent:
     * if the player has 0 WOR (before consuming), the rent debt is increased by the value of
     * 1 WOR.
     */
        
    public void consume() {
        //check rent debt
        RentPossession rentPoss = getRentPossession();
        if (null != rentPoss && 0 == rentPoss.getUnits()) {
            _rentDebt += rentPoss.getCommodity().getUnitValue();
        }
        
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
