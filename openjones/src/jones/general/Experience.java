/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

/**
 * Experience can be gained upto a CAP.
 * Old experience (i.e. experience gained at least
 * OLD_EXPERIENCE_AGE_THRESHOLD_IN_WEEKS weeks ago, can lose value.
 * The old experience loses OLD_EXPERIENCE_VALUE_LOSS_PER_WEEK each week.
 * @author dimid
 */
class Experience {
    
    /**
     * The maximum experience which can be gained
     */
    public final int _CAP;
    protected int _value;
    
    /**
     * The turn (number of weeks), when the last experience was gained
     */
    protected int _timestamp;
    
    /**
     * How many weeks it takes for an experience 
     */
    public static final int OLD_EXPERIENCE_AGE_THRESHOLD_IN_WEEKS = 24; //6 months
    
    
    /**
     * How many EXPUs are lost each week, because the experience is old
     */
    public static final int OLD_EXPERIENCE_VALUE_LOSS_PER_WEEK = 1; 
    
    /**
     * 
     * @param cap The maximum experience which can be gained
     */
    public Experience (int cap, int weeks) {
        _CAP = cap;
        _value = 0;
        _timestamp = weeks;
    }

    public Experience(Experience other) {
        _CAP = other._CAP;
        _value = other._value;
        _timestamp = other._timestamp;
        
    }

    /**
     * Gain experience
     * @param addditionalEXPUs units of experience to add
     * @param weeks The turn (number of weeks), when the experience was gained
     */
    public void gain (int addditionalEXPUs, int weeks) {
        _value += addditionalEXPUs;
        if (_value > _CAP) {
            _value = _CAP;
        }
        _timestamp = weeks;
    }
    
    /**
     * Gain experience from this turn 
     * @param addditionalEXPUs units of experience to add
     * @param player The player who gained it. (uses player`s current weeks)
     */
    public void gain (int addditionalEXPUs, Player player) {
        gain( addditionalEXPUs, player.getWeeks());
    }
   
    /**
     * Check if the experience is old, if so reduce its value
     * @param player 
     */
    public void updateExperience (Player player) {
        int curWeeks = player.getState().getWeeks();
        if (curWeeks - _timestamp >= OLD_EXPERIENCE_AGE_THRESHOLD_IN_WEEKS) {
            _value -= OLD_EXPERIENCE_VALUE_LOSS_PER_WEEK;
            if (_value < 0) {
                _value = 0;
            }
        }
        
    }
            
    
    /**
     * @return the _value
     */
    public int getValue() {
        return _value;
    }

    /**
     * @param value the _value to set
     */
    public void setValue(int value) {
        this._value = value;
    }

    /**
     * @return the _timestamp
     */
    public int getTimestamp() {
        return _timestamp;
    }

    /**
     * @param timestamp the _timestamp to set
     */
    public void setTimestamp(int timestamp) {
        this._timestamp = timestamp;
    }
            
    
}
