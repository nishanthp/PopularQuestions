package accpetedSubmissions;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;


public class SerializeDeserialize {

	public static void main(String[] args) {
		// Ran on leetcode.
		
	}
	
	 // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
         StringBuilder s = new StringBuilder();
        serializeTree(root, s);
        return s.toString();
    }
    
     public void serializeTree(TreeNode root, StringBuilder buffer){
        if(root == null) {
            buffer.append("null").append(",");
            return;
        }
        buffer.append(String.valueOf(root.val)).append(",");
        serializeTree(root.left, buffer);       
        serializeTree(root.right, buffer);
   }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        data = data.substring(0,data.length()-1);
        Deque<String> dended = new LinkedList<String>();
        dended.addAll(Arrays.asList(data.split(",")));
        return deserializeTree(dended);
    }
    
    public TreeNode deserializeTree(Deque<String> data) {
        String curretNode = data.remove();
        if(curretNode.equals("null")) {
            return null;
        }
            TreeNode root = new TreeNode(Integer.parseInt(curretNode));
            root.left = deserializeTree(data);
            root.right = deserializeTree(data);
            return root;
           
        
    }

}






