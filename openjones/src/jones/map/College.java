/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.map;

import jones.actions.Action;
import jones.actions.StudyAction;
import jones.general.Player;
import jones.general.PlayerState;
import jones.general.Position;
import jones.jobs.Job;
import net.vivin.GenericTreeNode;

/**
 *
 * @author dimid
 */
class College extends Building {

    public static final int JANITOR_BASE_WAGE = 6;
    public static final int TEACHER_BASE_WAGE = 12;
    public static final int PROFESSOR_BASE_WAGE = 27;

    public College(Position pos, String name) {

        super(pos, name);

    }

    @Override
    protected void buildActionsTree(PlayerState player) {
        Action study = new StudyAction();
        GenericTreeNode<Action> studyNode = new GenericTreeNode<>(study);
        _actionsTree.getRoot().addChild(studyNode);

    }

    @Override
    protected void addJobs() {
        getJobs().add(new Job("Janitor", this, 1, JANITOR_BASE_WAGE, 1));
        getJobs().add(new Job("Teacher", this, 4, TEACHER_BASE_WAGE, 2));
        getJobs().add(new Job("Proffesor", this, 9, PROFESSOR_BASE_WAGE, 3));

    }
}
