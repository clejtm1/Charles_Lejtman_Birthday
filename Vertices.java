
public class Vertices {
int distance;
boolean inSPT;
public Vertices()
{
	distance=100000;
	inSPT=false;
}
public void setDistance(int distance) 
{
	this.distance=distance;
}
public int getDistance() 
{
	return this.distance;
}
public void putInSPT() 
{
	inSPT=true;
}
public boolean getInSPT() 
{
	return inSPT;
}
}
