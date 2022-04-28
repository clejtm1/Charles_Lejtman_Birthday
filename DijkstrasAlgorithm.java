import java.util.*; 
import java.lang.*; 
import java.io.*; 
  
public class DijkstrasAlgorithm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
           int graph[][] = new int[][] {
		  //we just changed the vertex[0][7] from 6 to 100
        	{ 0, 4, 0, 0, 0, 0, 0, 100, 0 }, 
            { 4, 0, 8, 0, 0, 0, 0, 11, 0 }, 
            { 0, 8, 0, 7, 0, 4, 0, 0, 2 }, 
            { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, 
            { 0, 0, 0, 9, 0, 10, 0, 0, 0 }, 
            { 0, 0, 4, 14, 10, 0, 2, 0, 0 }, 
            { 0, 0, 0, 0, 0, 2, 0, 1, 6 }, 
            { 8, 11, 0, 0, 0, 0, 1, 0, 7 }, 
            { 0, 0, 2, 0, 0, 0, 6, 7, 0 } }; 
           
       //Let us first create a distance array for each vertex we will the distance automaticly be initialized to  100000 and inSPT as false
           Vertices[] distanceVertices=new Vertices[graph.length];
           for(int i=0;i<distanceVertices.length;i++) 
           	{
        	  distanceVertices[i]=new Vertices();
        	  System.out.println(distanceVertices[i].getInSPT());
           	}
      //First make distance vertex 0 have a distance of 0 and put in spt i will hold the index of the row since vertex zero is the source initialize this to zero
           distanceVertices[0].setDistance(0);
           distanceVertices[0].putInSPT();
           int i=0;
           //int currentMin=10000000;
           int currentMinIndex=0;
           int length=graph[0].length-1;
      //next go through the graph in row 0 any column that is greater than zero change the distance get the minimum column above zero and put it in the SPT
           while(length>0) {
           int currentMin=10000000;
           for(int j=0; j< graph[i].length;j++)
            {
        	   if(graph[i][j]>0 && distanceVertices[i].getDistance()+ graph[i][j]<distanceVertices[j].getDistance() && distanceVertices[j].getInSPT()==false) 
        	   {
        		   distanceVertices[j].setDistance(distanceVertices[i].getDistance()+graph[i][j]);
        		   System.out.println("Vertex"+j+" distance has been updated to"+ distanceVertices[j].getDistance());
        		   
        	   }
            }
           for(int k=0;k<distanceVertices.length;k++)
           {
        	   if(distanceVertices[k].getDistance()<currentMin && distanceVertices[k].getInSPT()==false)
        	   {
        		   currentMin=distanceVertices[k].getDistance();
        		   currentMinIndex=k;
        	   }
           }
           System.out.println("Put vertex "+currentMinIndex+"in the SPT with a distance from the source of "+currentMin);
           distanceVertices[currentMinIndex].putInSPT();
           i=currentMinIndex;
           length--;
           //checks if all vertices are in spt if all are in the spt the while will stop
           }
           for(int l=0;l<distanceVertices.length;l++)
           {
        	   System.out.println("Vertex"+l+"has a distance from the source of: "+distanceVertices[l].getDistance());
           }
	}

}
