/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.map;

import jones.general.Player;
import jones.general.PlayerState;
import jones.general.Position;
import jones.jobs.Job;

/**
 *
 * @author dimid
 */
class Factory extends Building {

    public static final int JANITOR_BASE_WAGE = 6;
    public static final int ASSEMBLY_WORKER_BASE_WAGE = 7;
    public static final int SECRETARY_BASE_WAGE = 8;
    public static final int MACHINIST_HELPER_MANAGER_BASE_WAGE = 9;
    public static final int EXECUTIVE_SECRETARY_BASE_WAGE = 18;
    public static final int MACHINIST_BASE_WAGE = 19;
    public static final int DEPARTMENT_MANAGER_BASE_WAGE = 21;
    public static final int ENGINEER_BASE_WAGE = 23;
    public static final int GENERAL_MANAGER_BASE_WAGE = 25;

    public Factory(Position pos, String name) {
        super(pos, name);

    }

    @Override
    protected void buildActionsTree(PlayerState player) {
        //nothing
    }

    @Override
    protected void addJobs() {

        getJobs().add(new Job("Janitor", this, 1, JANITOR_BASE_WAGE, 1));
        getJobs().add(new Job("Assembly Worker", this, 1, ASSEMBLY_WORKER_BASE_WAGE, 1));
        getJobs().add(new Job("Secretary", this, 2, SECRETARY_BASE_WAGE, 2));
        getJobs().add(new Job("Machinist Helper", this, 3, MACHINIST_HELPER_MANAGER_BASE_WAGE, 1));
        getJobs().add(new Job("Executive Secretary", this, 4, EXECUTIVE_SECRETARY_BASE_WAGE, 3));
        getJobs().add(new Job("Machinist", this, 5, MACHINIST_BASE_WAGE, 1));
        getJobs().add(new Job("Department Manager", this, 6, DEPARTMENT_MANAGER_BASE_WAGE, 3));
        getJobs().add(new Job("Engineer", this, 7, ENGINEER_BASE_WAGE, 2));
        getJobs().add(new Job("General Manager", this, 8, GENERAL_MANAGER_BASE_WAGE, 3));


    }
}
