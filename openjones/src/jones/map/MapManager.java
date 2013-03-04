/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.map;

import jones.map.ApplianceStore;
import java.util.ArrayList;
import java.util.Iterator;
import jones.general.Position;

/**
 *
 * @author dimid
 */
public class MapManager {
    
    private House _lowestHousing;// = new Tent(); 
    
    private Grid<Location> _grid;
    private ArrayList <Building> _buildings;
    private ArrayList <Location> _nonBuildings;
    
   
   
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
        m.addDefaultBuildings();
        m.addDefaultLocations();
       
        m._grid = getDefaultGrid(m._buildings, m._nonBuildings);
        
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
        _buildings.add(new Villa(pos,"luxHousing"));
        
        pos.setXY(1,0);
        _buildings.add(new RentAgency(pos,"rent"));

        pos.setXY(2,0);
        _lowestHousing = new Tent(pos,"tent");
        _buildings.add(_lowestHousing);

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
        _buildings.add(new EmploymentAgency (pos,"employment"));  
         
        pos.setXY(0,3);
        _buildings.add(new Factory (pos,"factory"));
         
        pos.setXY(0,2);
        _buildings.add(new Bank (pos,"bank"));
         
        pos.setXY(0,1);
        _buildings.add(new SuperMarket (pos,"black market"));

    }

    private static Grid<Location> getDefaultGrid(ArrayList<Building> buildings, ArrayList<Location> nonBuildings) {
        Grid<Location> g = new Grid<>(3,5);
        
        for (Building b: buildings)
            g.set(b.getPosition(), b);
      
        for (Location l: nonBuildings)
            g.set(l.getPosition(), l);
        
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
        for (int i=1;i<=2;++i) {        
            for (int j=1;i<=3;++j) {
                pos.setXY(i,j);
                _nonBuildings.add(new Wall(pos));
            }
        }
        
        addDefaultBuildings();
        
    }

}
