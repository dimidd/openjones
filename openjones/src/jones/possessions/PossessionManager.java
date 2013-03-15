/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.possessions;

import java.util.ArrayList;
import jones.map.MapManager;

/**
 *
 * @author dimid
 */
public class PossessionManager {

	//	private RentPossession 		_rent;
	//private ArrayList<Possession>	_clothes;
	//	private FoodPossession		_food;
	private RentContract _rentContract;
	private ArrayList<Possession> _poss;
	private int _rentDebt;
        private int _nOutfits;
        //private int _clothesLevel;                
        private ConsumablePossession _bestClothesPossesion;
        /**
         * The number of WORs a player has at the beginning 
         */
        public static final int STRATING_WEEKS_OF_RENT = 4; 
        
	public PossessionManager (MapManager map) {
		_poss = new ArrayList<>();
                addDefaultPossessions(map);
	}

	public Possession find(Possession newPos) {

		for (Possession p: _poss) {
                    if (p.getCommodity().equals(newPos.getCommodity())) {				
                            return p;
                    }
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
            int sum = 0;                                   
            for (Possession p: _poss) {            
                sum += p.getRestHealthEffect();            
            }                
           
            return sum;
	}

	public int sumRestHappinessEffects() {             
            int sum = 0;                             
            for (Possession p: _poss) {
                    sum += p.getRestHappinessEffect();
                }
                               
            return sum;
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
                assert(null != possession);
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
        if (null == rentPoss 
                ||
           0 == rentPoss.getUnits()) {            
         
            _rentDebt += rentPoss.getCommodity().getUnitValue();
        }
        
        //TODO: food, clothes
        
        for (Possession p: _poss) {
            p.consume();
            if (p.getUnits() <= 0) {
                _poss.remove(p);
            }
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getClothesLevel() {
        
        Clothes bestOutfit = null;
        ConsumablePossession bestClothesPossesion = null;
        int max = 0;
        for (Possession p: _poss) {			
            Commodity comm = p.getCommodity();
            if (comm instanceof Clothes) {
                
                //First, we set all clothes to NOT autoconsume.
                //At the end the method, the best outfit is set to autoconsume.                
                // This is because only the best outfit is wared
                ((ConsumablePossession) p).setIsAutoConsmuptionOn(false); 
                
                Clothes outfit = (Clothes) comm;                
                if (outfit.getLevel() > max) {
                    max = outfit.getLevel();
                    bestOutfit = outfit;
                    bestClothesPossesion = (ConsumablePossession) p;
                }			                                		                
            }
        }       
       
        if (null == bestClothesPossesion) {
            return 0;
        } //naked       
        else {               
            bestClothesPossesion.setIsAutoConsmuptionOn(true);
            _bestClothesPossesion = bestClothesPossesion;
            return max;                
        }
    }

    
    public boolean isRentDue() {
        
        RentPossession rentPoss = getRentPossession();
        if (null == rentPoss) {
            return true;
        }
        else {
            return rentPoss.getUnits() <= 0;
        }

    }

    private void addDefaultPossessions(MapManager map) {
        
        //rent
        WeekOfRent wor = new WeekOfRent(map.getLowestHousing().pricePerWeek(), map.getLowestHousing());
        RentPossession defaultRent = new RentPossession(STRATING_WEEKS_OF_RENT, wor);
        _poss.add(defaultRent);
        _rentContract = new RentContract(defaultRent);
        
        //clothes
        Possession casualPoss = new Possession(1, new CasualClothes());
        _poss.add(casualPoss);
        ++_nOutfits;
        _bestClothesPossesion = (ConsumablePossession) casualPoss;
        
        
        //food TODO
    }

    
    /**
     * Clothes are about to ware iff: player has only one outfit, and next week there won't be any outfits 
     * @return 
     */
    public boolean areClothesAboutToWare() {
        if (_nOutfits != 1) {
            return false;
        } 
        else {
            //we check for 2 turns, since the check is BEFORE consuming
            return _bestClothesPossesion.isGoingToExhaustIn2Turns();
        }
     }


}
