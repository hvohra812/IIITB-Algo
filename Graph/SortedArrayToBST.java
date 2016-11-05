import java.util.*;

//Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

class TreeNode 
{
 	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

public class SortedArrayToBST {
    public static void main(String arg[])
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int nums[] = new int[n];
        for(int i=0;i<n;i++)
            nums[i] = sc.nextInt();
        TreeNode root = sortedArrayToBST(nums);
        preOrder(root);
    }
    static TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = sortIt(nums,0,nums.length-1);
        //preOrder(root);
        return root;
    }
    
    static TreeNode sortIt(int nums[], int s, int e)
    {
        if(s==e)
        {
            return new TreeNode(nums[s]);
        }
        if(s<e)
        {
            int mid = (s+e)/2;
            TreeNode t = new TreeNode(nums[mid]);
            t.left = sortIt(nums,s,mid-1);
            t.right = sortIt(nums,mid+1,e);
            return t;
        }
        else
            return null;
    }
    
    static void preOrder(TreeNode node)
    {
        if(node != null)
        {
            pr( node.val + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
    static void pr(String s)
    {
        System.out.print(s);
    }
}
