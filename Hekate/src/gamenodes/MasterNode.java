package gamenodes;

import java.util.List;

public abstract class MasterNode implements Comparable{
	
	public MasterNode pathParent;
	public float costFromStart;
	public float estimatedCostToGoal;
	
	public float getCost() {
		return this.costFromStart + this.estimatedCostToGoal;
	}
	
	public int compareTo(Object other) {
		float thisValue = this.getCost();
		float otherValue = ((MasterNode)other).getCost();
		
		float v = thisValue - otherValue;
		return (v>0)?1:(v<0)?-1:0;
	}
	
	public abstract float getCost(MasterNode node);
	
	public abstract float getEstimatedCost(MasterNode node);
	
	public abstract List getNeighbours();
}
