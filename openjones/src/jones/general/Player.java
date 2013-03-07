/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

import jones.map.House;
import jones.possessions.Possession;
import jones.possessions.RentContract;

/**
 *
 * @author dimid
 */
public class Player {
    
    private int _id;
    private String _name;
    private PlayerState _state;
    private PlayerGraphics _graphics;

    public Player (String name, PlayerGraphics graphics) {
    	 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    boolean hasWon() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setGoals(Goals goals) {
        _state.setGoals(goals);

    }
    
    void startTurn() {
        _state.advanceWeeks();
        House home = _state.getHouse();
        _state.setPos(home.getPosition(), false);
    }

    public int getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public PlayerState getState() {
        return _state;
    }

    public PlayerGraphics getGraphics() {
        return _graphics;
    }
    
    public int clothesLevel () {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    void move(int x, int y) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    void move(Movement move) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public Career getCareer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void affectCash(int cashEffect) {
        _state.affectCash(cashEffect);
    }

    public int getWeeks() {
        return _state.getWeeks();
    }

    public void affectTime(int timeEffect) {
        _state.affectTime(timeEffect);
    }

    public int getHour() {
        return _state.getHour();
    }

    public boolean hasTime() {
        return _state.hasTime();
    }

    public int timeLeft() {
         return _state.timeLeft();
    }

    public RentContract getRentContract() {
        return _state.getRentContract();
    }
 
    
    public void setRentContract(RentContract r) {
        _state.setRentContract(r);
    }

	public boolean isRentDue() {		
		return 0 == _state.getNumOfWeeksOfRent();
	}

	public boolean hasFoodSpoiled() {
		// TODO Auto-generated method stub

		return false;
	}

	public boolean hasAllFoodSpoiled() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean areClothesAboutToWare() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getSumOfRescueFromRelative() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void removeNonContractRentPossesions() {
		// TODO Auto-generated method stub
		
	}

	public void setRentPossession(Possession possession) {
		_state.getPossessions().setRentPossession(possession);
		
	}

    
    
    
            
}
