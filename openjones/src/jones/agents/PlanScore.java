/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import java.util.Objects;

/**
 * Contains a Plan and a score
 * @author dimid <dimidd@gmail.com>
 */
public class PlanScore implements Comparable<PlanScore>{

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this._plan);
        hash = 83 * hash + (int) (Double.doubleToLongBits(this._score) ^ (Double.doubleToLongBits(this._score) >>> 32));
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
        final PlanScore other = (PlanScore) obj;
        if (!this._plan.equals(other._plan)) {
            return false;
        }
        if (this._score != other._score) {
            return false;
        }
        return true;
    }
    
    protected Plan _plan;
    protected double _score;

    public Plan getPlan() {
        return _plan;
    }

    public void setPlan(Plan _plan) {
        this._plan = _plan;
    }

    public double getScore() {
        return _score;
    }

    public void setScore(int _score) {
        this._score = _score;
    }
    
    public PlanScore (Plan plan, double score) {
        _plan = plan;
        _score = score;
    }

    @Override
    public int compareTo(PlanScore o) {
        return (int) (_score - o._score);
    }

    @Override
    public String toString() {
        return "PlanScore{" + "_plan=" + _plan + ", _score=" + _score + '}';
    }
    
    
}
