/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.measures;

/**
 *
 * @author dimid
 */
public class Measure {

    protected int _score;
    
    public void add(int effect) {
        setScore(getScore() + effect);
    }

    /**
     * @return the _score
     */
    public int getScore() {
        return _score;
    }

    /**
     * @param score the _score to set
     */
    public void setScore(int score) {
        this._score = score;
    }
    
}
