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
    
    protected Measure (int score) {
        _score = score;
    }
    
    protected Measure () {
        _score = 0;
    }
    
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
    
    public String toString() {
        return getClass().getSimpleName()+":"+_score;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this._score;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Measure other = (Measure) obj;
        if (this._score != other._score) {
            return false;
        }
        return true;
    }
    
    
}
