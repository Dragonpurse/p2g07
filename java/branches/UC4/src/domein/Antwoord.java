package domein;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class Antwoord implements MutableTreeNode {

	private List<MutableTreeNode> casusKnopen;
	private MutableTreeNode parent;
	private String antwoord;
	private String feedback;
	
	@Override
	public Enumeration<MutableTreeNode> children() {
		return Collections.enumeration(casusKnopen);
	}
	@Override
	public boolean getAllowsChildren() {
		return true;
	}
	@Override
	public TreeNode getChildAt(int childIndex) {
		return casusKnopen.get(childIndex);
	}
	@Override
	public int getChildCount() {
		return casusKnopen.size();
	}
	@Override
	public int getIndex(TreeNode node) {
		return casusKnopen.indexOf(node);
	}
	@Override
	public TreeNode getParent() {
		return this.parent;
	}
	@Override
	public boolean isLeaf() {
		return casusKnopen.isEmpty();
	}
	@Override
	public void insert(MutableTreeNode child, int index) {
		casusKnopen.add(index, child);
		
	}
	@Override
	public void remove(int index) {
		casusKnopen.remove(index);
		
	}
	@Override
	public void remove(MutableTreeNode node) {
		casusKnopen.remove(node);
		
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
