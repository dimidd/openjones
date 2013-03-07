/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Experience is measured in Experience Units (EXPU). The experience model consists of a set of experiences at different jobs.
 * For each rank we sum the experience gained working in all the jobs of this rank.
 * The higher the rank, more experience can be gained. The time it takes to aquire an EXPU, depends on the specific job.
 * e.g. Working 10 Time Units (TIMU), as a manager in a factory would yield 2 EXPUs,
 * while Working 10 Time Units (TIMU), as a manager in a fast-food restaurant would yield only 1 EXPU.
 * This rate is defined by the Job field EXPERIENCE_UNITS_PER_1_TIME_UNIT_OF_WORK.
 * 
 * @author dimid
 */
class ExperienceManager {
    private HashMap<Integer, Experience> _exps;
    private static final int CAP_PER_RANK = 100;
    
    public ExperienceManager () {
        _exps = new HashMap<>();
    }

    ExperienceManager(ExperienceManager other) {         
        Set<Entry<Integer, Experience>> othersEntries = other._exps.entrySet();    
        for (Entry<Integer, Experience> e : othersEntries) {
            Integer rank = new Integer(e.getKey());
            Experience xp = new Experience(e.getValue());
            _exps.put(rank, xp);
        }
    }
    
    /**
     * Returns the highest experience value of all ranks
     * @return 
     */
    public int maxExperience() {
        int max = 0;
        for (Experience exp: _exps.values()) {
            max = Math.max(max, exp.getValue());
        }
        
        return max;
    }
    
    /**
     * Check if there are old experiences, if so reduce their value
     * @param player 
     */
    public void update (Player player) {
        for (Experience exp: _exps.values()) {
            exp.updateExperience(player);
        }
    }
    
    /**
     * Add a new experience
     * @param rank
     * @param cap
     * @return the new experience 
     */
    public Experience addExperience (int rank, int cap, int weeks) {
        return _exps.put(rank, new Experience(weeks,cap));
    }
     
    /**
     * Add a new experience with a default cap
     * @param rank
     * @return the new experience
     */
    public Experience addExperience (int rank, int weeks) {
           return _exps.put(rank, new Experience(getCapByRank(rank), weeks));
    }

    /**
     * Add all experiences upto maxRank, using default caps
     * @param maxRank
     *  
     */
    public void addAllExperiences (int maxRank, int weeks) {
        for(int i=1; i<=maxRank; ++i) {
            addExperience(i, weeks);
        }
    }


    /**
     * Return default cap for a given rank
     * @param rank
     * @return the rank's cap
     */
    public int getCapByRank(int rank) {
        return CAP_PER_RANK * rank;
    }
    
    
     
    /**
     * Gain experience to a rank from this turn 
     * @param addditionalEXPUs units of experience to add
     * @param rank the rank that gained the experience
     * @param player The player who gained it. (uses player`s current weeks)
     */
    public void gain (int rank, int addditionalEXPUs, Player player) {
        _exps.get(rank).gain(addditionalEXPUs, player);
    }

    
}
