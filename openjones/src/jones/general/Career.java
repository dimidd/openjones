/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

/**
 * The career score is determined by the maximum experience ever gained.
 * Even if the experience becomes old, and disappears, the career score remains. 
 * i.e. the career score is non-decreasing
 
 * 
 * @author dimid
 */
public class Career extends Measure {
    private ExperienceManager _exp;
    
    public Career (int maxJobRank, int weeks) {
        _exp = new ExperienceManager();
        _exp.addAllExperiences(maxJobRank, weeks);
    }

    public Career(Career other) {
        _score = other._score;
        _exp = new ExperienceManager(other._exp);
    }
    
       
    /**
     * Gain experience to a rank from this turn 
     * @param addditionalEXPUs units of experience to add
     * @param rank the rank that gained the experience
     * @param player The player who gained it. (uses player`s current weeks)
     */
    public void gain (int rank, int addditionalEXPUs, Player player) {
        _exp.gain(rank,addditionalEXPUs, player);
        computeScore();
    }

    private void computeScore() {
        _score = Math.max(_score, _exp.maxExperience());
    }
    
    public int getExperienceLevel (int rank) {
        return _exp.getExperienceLevel(rank);
    }

    public ExperienceManager getExp() {
        return _exp;
    }

    public void setExp(ExperienceManager _exp) {
        this._exp = _exp;
    }
    
    
}
