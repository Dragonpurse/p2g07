package domein;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class Vraag implements MutableTreeNode{

	private List<Antwoord> antwoorden;
	private MutableTreeNode parent;

	@Override
	public Enumeration<Antwoord> children() {
		return Collections.enumeration(antwoorden);
	}
	@Override
	public boolean getAllowsChildren() {
		return true;
	}
	@Override
	public TreeNode getChildAt(int childIndex) {
		return antwoorden.get(childIndex);
	}
	@Override
	public int getChildCount() {
		return antwoorden.size();
	}
	@Override
	public int getIndex(TreeNode node) {
		return antwoorden.indexOf(node);
	}
	@Override
	public TreeNode getParent() {
		return this.parent;
	}
	@Override
	public boolean isLeaf() {
		return antwoorden.isEmpty();
	}
	@Override
	public void insert(MutableTreeNode child, int index) {
		antwoorden.add(index, (Antwoord) child);
		
	}
	@Override
	public void remove(int index) {
		antwoorden.remove(index);
		
	}
	@Override
	public void remove(MutableTreeNode node) {
		antwoorden.remove(node);
		
	}
	@Override
	public void removeFromParent() {
		((MutableTreeNode)getParent()).remove(this);
		setParent(null);
		
	}
	@Override
	public void setParent(MutableTreeNode newParent) {
		setParent(newParent);
		
	}
	@Override
	public void setUserObject(Object object) {
		// TODO Auto-generated method stub
		
	}
}
