/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package external;




/**
 *
 * @author dimid
 */
public class ScoreNode<T> implements Comparable<ScoreNode<T>> {
 

    public double _score;

    public double getSearchScore() {
        return _score;
    }

    public void setScore(double _score) {
        this._score = _score;
    }

    public T getData() {
        return _data;
    }

    public void setData(T _data) {
        this._data = _data;
    }
    public T _data;

    public ScoreNode(double score, T data) {
        this._score = score;
        this._data = data;
    }

    @Override
    public int compareTo(ScoreNode<T> o) {
        if (_score < o._score) {
            return -1;
        } else if (_score > o._score) {
            return 1;
        }
        return 0;
    }

//    public boolean equals(external.FScoreNode o) {
//        return pos.equals(o.pos);
//    }

//    @Override
//    public int hashCode() {
//        return pos.hashCode();
//    }

//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final external.FScoreNode other = (external.FScoreNode) obj;
//        if (!pos.equals(other.pos)) {
//            return false;
//        }
//        return true;
//    }

//    @Override
//    public int compareTo(T o) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//}


}
