/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.map;

import java.util.ArrayList;
import java.util.Iterator;
import jones.general.Position;

/**
 *
 * @author dimid
 */
public class MapManager {
    
    private House _lowestHousing;// = new LowCostHousing(); 
    
    private Grid<Location> _grid;
    private ArrayList <Building> _buildings;
    private ArrayList <Wall> _walls;   
    private ArrayList <Location> _nonBuildings;
    
    public static final int BASE_RENT_LOWCOST_HOUSING = 305;
    public static final int BASE_RENT_SECURITY_HOUSING = 445;
  
    public MapManager (House lowestHousing) {
        if (null != lowestHousing) {
            _lowestHousing = lowestHousing;
        }
        else
        {
            throw new NullPointerException("Null House provided");
        }
            
    }

    public MapManager () {
           
    }
      
    
    public GridTile getTile (Position pos) {
        return _grid.get(pos);
    }
    
    
    
    public static MapManager getDefaultMap() {
        
        MapManager m  = new MapManager();
        m._buildings = new ArrayList<>();
        m._nonBuildings = new ArrayList<>();
        m._walls = new ArrayList<>();
        m.addDefaultBuildings();
        m.addDefaultLocations();
       
        m._grid = getDefaultGrid(m._buildings, m._nonBuildings, m._walls);
        
        return m;

      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public House getLowestHousing() {
        return _lowestHousing;
    }
    
    
    
    /**
     * Adds the default buildings and sets the lowest housing
     */
    private void addDefaultBuildings() {
        //(0,0) is top left corner
        Position pos = new Position (0,0);
        House secHousing = new SecurityHousing(pos,"Security Apartment",BASE_RENT_SECURITY_HOUSING);
        _buildings.add(secHousing);
        
        pos.setXY(2,0);
        _lowestHousing = new LowCostHousing(pos,"Low-Cost Apartment",BASE_RENT_LOWCOST_HOUSING);
        _buildings.add(_lowestHousing);
      
        pos.setXY(1,0);
        ArrayList<House> houses = new ArrayList<>();
        houses.add(_lowestHousing);
        houses.add(secHousing);
        _buildings.add(new RentAgency(pos,"rent", houses));

        pos.setXY(3,0);
        _buildings.add(new PawnShop(pos,"pawn"));
     
        pos.setXY(4,0);
        _buildings.add(new DepartmentStore (pos,"zmarket"));

        pos.setXY(4,1);
        _buildings.add(new Restaurant (pos,"Monolith Burgers"));

        pos.setXY(4,2);
        _buildings.add(new ClothesStore (pos,"QT clothing"));
        
        pos.setXY(4,3);
        _buildings.add(new ApplianceStore (pos,"SocketCity"));
                 
        pos.setXY(3,3);
        _buildings.add(new College (pos,"HI-TECH U"));
               
//        pos.setXY(2,3);
//        _buildings.add(new Spot (pos));        
      
        pos.setXY(1,3);
        _buildings.add(new EmploymentAgency (pos,"employment",this));  
         
        pos.setXY(0,3);
        _buildings.add(new Factory (pos,"factory"));
         
        pos.setXY(0,2);
        _buildings.add(new Bank (pos,"bank"));
         
        pos.setXY(0,1);
        _buildings.add(new SuperMarket (pos,"black market"));

    }

    private static Grid<Location> getDefaultGrid(ArrayList<Building> buildings, ArrayList<Location> nonBuildings, ArrayList<Wall> walls) {
        Grid<Location> g = new Grid<>(5,4);
        
        for (Building b: buildings) {
            g.set(b.getPosition(), b);
        }
      
        for (Location l: nonBuildings) {
            g.set(l.getPosition(), l);
        }
      
        for (Wall w: walls) {
            g.set(w.getPosition(), w);
        }
       
        
        return g;
        
    }

    public Iterator<Building> buildingsIterator() {
        return _buildings.iterator();
    }

    /**
     * Add all default Locations 
     */
    private void addDefaultLocations() {
        Position pos = new Position (2,3);
        _nonBuildings.add(new Spot (pos));
        
        //add internal rectangle of walls
        for (int col=1;col<=3;++col) {        
            for (int row=1;row<=2;++row) {
                pos.setXY(col,row);
                _walls.add(new Wall(pos));
            }
        }
        
        //addDefaultBuildings();
        
    }

    public Grid<Location> getGrid() {
        return _grid;
    }

    public void setGrid(Grid<Location> grid) {
        this._grid = grid;
    }

    public ArrayList <Building> getBuildings() {
        return _buildings;
    }

    public void setBuildings(ArrayList <Building> buildings) {
        this._buildings = buildings;
    }

    public ArrayList <Wall> getWalls() {
        return _walls;
    }

    public void setWalls(ArrayList <Wall> walls) {
        this._walls = walls;
    }

    public ArrayList <Location> getNonBuildings() {
        return _nonBuildings;
    }

    public void setNonBuildings(ArrayList <Location> nonBuildings) {
        this._nonBuildings = nonBuildings;
    }

    public Position getBuildingPositionByName(String name) {
     
        return getBuildingByName(name).getPosition();
    }

      
    public  Building getBuildingByName(String name) {
        for (Building b: _buildings) {
            if (b.getName().equals(name)) {
                return b;
            }
        }
        
        return null;
    }

}
