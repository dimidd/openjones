/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.map;

import jones.actions.Action;
import jones.actions.PurchaseAction;
import jones.actions.PurchaseClothesAction;
import jones.general.Player;
import jones.general.PlayerState;
import jones.general.Position;
import jones.jobs.Job;
import jones.possessions.BusinessSuit;
import jones.possessions.CasualClothes;
import jones.possessions.ConsumablePossession;
import jones.possessions.DressClothes;
import net.vivin.GenericTree;
import net.vivin.GenericTreeNode;

/**
 *
 * @author dimid
 */
public class ClothesStore extends Building {
    
     
    public static final int SALESPERSON_BASE_WAGE = 6;
    public static final int ASSISTANT_MANAGER_BASE_WAGE = 9;
    public static final int MANAGER_BASE_WAGE = 11;

    public static final int MAX_CLOTHES_LEVEL = 3;
    
    private final ConsumablePossession _casualClothesPoss;
    private final ConsumablePossession _dresslClothesPoss;
    private final ConsumablePossession _busSuitPos;

    public ClothesStore(Position pos, String name) {
        super(pos,name);
        CasualClothes casualClothes = new CasualClothes();
        _casualClothesPoss = new ConsumablePossession(1, casualClothes, 1.0 / casualClothes.getLifeSpanWeeks() );
        DressClothes dressClothes = new DressClothes();
        _dresslClothesPoss = new ConsumablePossession(1, dressClothes, 1.0 / dressClothes.getLifeSpanWeeks() );
        BusinessSuit busSuit = new BusinessSuit();
        _busSuitPos = new ConsumablePossession(1, busSuit, 1.0 / busSuit.getLifeSpanWeeks() );
        
        
    }

	@Override
	protected void buildActionsTree(PlayerState player, GenericTree<Action> actionsTree) {
		   
            GenericTreeNode<Action> root = actionsTree.getRoot();
            
            Action casualPurchase = new PurchaseClothesAction(_casualClothesPoss);
            GenericTreeNode<Action> casualPurchaseNode = new GenericTreeNode<>(casualPurchase);
            root.addChild(casualPurchaseNode);
 
            Action dressPurchase = new PurchaseClothesAction(_dresslClothesPoss);
            GenericTreeNode<Action> dressPurchaseNode = new GenericTreeNode<>(dressPurchase);
            root.addChild(dressPurchaseNode);

            Action suitPurchase = new PurchaseClothesAction(_busSuitPos);
            GenericTreeNode<Action> suitPurchaseNode = new GenericTreeNode<>(suitPurchase);
            root.addChild(suitPurchaseNode);
		
	}

	@Override
	protected void addJobs() {
	
            getJobs().add(new Job("Salesperson", this, 2, SALESPERSON_BASE_WAGE,1));           
            getJobs().add(new Job("Assistant Manager", this, 3, ASSISTANT_MANAGER_BASE_WAGE, 2));      		          
            getJobs().add(new Job("Manager", this, 4, MANAGER_BASE_WAGE,3));      		

		
	}
    
}
