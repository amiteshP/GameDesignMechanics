package algorithms;

import java.util.LinkedList;
import java.util.List;

import gamenodes.MasterNode;

public class AStarSearch {
	
	public static class PriorityList extends LinkedList {
		
		public void add(Comparable object) {
			for(int i=0; i<size(); i++) {
				if(object.compareTo(get(i))<=0) {
					add(i, object);
					return;
				}
			}
			addLast(object);
		}
		
	}
	
	protected List constructPath(MasterNode node) {
		LinkedList path = new LinkedList();
		while(node.pathParent != null) {
			path.addFirst(node);
			node = node.pathParent;
		}
		return path;
	}
	
	public List findPath(MasterNode startNode, MasterNode goalNode) {
		
		PriorityList openList = new PriorityList();
		LinkedList closedList = new LinkedList();
		
		startNode.costFromStart = 0;
		startNode.estimatedCostToGoal = startNode.getEstimatedCost(goalNode);
		startNode.pathParent = null;
		
		openList.add(startNode);
		
		while(!openList.isEmpty()) {
			MasterNode node = (MasterNode)openList.removeFirst();
			if(node == goalNode)
				return constructPath(goalNode);
			
			List neighbours = node.getNeighbours();
			
			for(int i=0; i<neighbours.size(); i++) {
				MasterNode neighbourNode = (MasterNode)neighbours.get(i);
				boolean isOpen = openList.contains(neighbourNode);
				boolean isClosed = closedList.contains(neighbourNode);
				
				float costFromStart = node.costFromStart + node.getCost(neighbourNode);
				
				if((!isOpen && !isClosed) || costFromStart<neighbourNode.costFromStart) {
					neighbourNode.pathParent = node;
					neighbourNode.costFromStart = costFromStart;
					neighbourNode.estimatedCostToGoal = neighbourNode.getEstimatedCost(goalNode);
					if(isClosed) {
						closedList.remove(neighbourNode);
					}
					if(!isOpen) {
						openList.add(neighbourNode);
					}
				}
			}
			closedList.add(node);
		}
		
		return null;
		
	}
}
