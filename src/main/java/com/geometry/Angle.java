package com.geometry;

public class Angle extends GeoItem{
	
	public Point pointOne;
    public Point pointThree;
    public Point middle;
    public Line directionLeft;
    public Line directionRight;
    public int num;

    public Angle(Line directionLeft,Line directionRight) {
        
        super(GeoType.ANGLE);
        if(directionLeft.endPoints[0].sameItem(directionRight.endPoints[0])){
            pointOne = directionLeft.endPoints[1];
            middle = directionLeft.endPoints[0];
            pointThree = directionRight.endPoints[1];
        }
        else if(directionLeft.endPoints[0].sameItem(directionRight.endPoints[1])){
            pointOne = directionLeft.endPoints[1];
            middle = directionLeft.endPoints[0];
            pointThree = directionRight.endPoints[0];
        }
        else{
            pointOne = directionLeft.endPoints[0];
            middle = directionLeft.endPoints[1];
            if(directionLeft.endPoints[1].sameItem(directionRight.endPoints[0])){
                pointThree = directionRight.endPoints[1];
            }else{
                pointThree = directionRight.endPoints[0];
            }
            
        }
        
        num = 1;
        this.directionLeft=directionLeft;
        this.directionRight=directionRight;
        
        name = getName();
        
    }
    
    public Angle(String angleName) {
        super(GeoType.ANGLE);
        char[] arr = angleName.toCharArray();
		pointOne = new Point(arr[0]);
		middle = new Point(arr[1]);
		pointThree = new Point(arr[2]);
        directionLeft = new Line(pointOne,middle);
        directionRight = new Line(middle,pointThree);
        
        num = 1;
        name = getName();
        
    }
    
    public Angle(double angle){
    	super(GeoType.ANGLE);
    	this.value=angle;
    } 
    
    public boolean isMiddlePoint(char pointName){
        return middle.name == pointName;
    }
    
    public boolean isACornerPoint(char pointName){
        return ( pointOne.name == pointName || pointThree.name == pointName );
    }


	@Override
	public Boolean sameItem(GeoItem item) {
		
		if ( item.type != GeoType.ANGLE) return false;
		
		//if ( item == null ) return false;

		Point itemMiddle = ((Angle)item).middle;
		Point itemOne = ((Angle)item).pointOne;
		Point itemThree = ((Angle)item).pointThree;

		if ( !itemMiddle.sameItem(middle) )
			{ 
			//System.out.println("in here" + itemMiddle.getName() + middle.getName());
			return false;
			
			}
		
		
		if ( ( ( pointOne.sameItem(itemOne)) || (pointOne.sameItem(itemThree)) )
			&& ( ( pointThree.sameItem(itemOne)) || (pointThree.sameItem(itemThree)) ) 
			){ return true;}
		else{
			//System.out.println("2 "+ ( ( pointThree.sameItem(itemOne)) || (pointThree.sameItem(itemThree)) ) );
		}
		
		return false;

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		if( ( pointOne.getName().compareTo(pointThree.getName()) ) > 0 )
		{
			return String.valueOf(pointThree.getName()) + String.valueOf(middle.getName()) + String.valueOf(pointOne.getName());
			
		}
		return String.valueOf(pointOne.getName()) + String.valueOf(middle.getName()) + String.valueOf(pointThree.getName());
	}

	public Point getMiddlePoint(){
		
		return middle;
	}
	
	public boolean isAPoint(Point A){
		if(pointOne.sameItem(A)){
			return true;
		}
		if(middle.sameItem(A)){
			return true;
		}
		if(pointThree.sameItem(A)){
			return true;
		}
		return false;
		
	}
	public boolean isALine(Line AB){
		
		if(directionLeft.sameItem(AB)){
			return true;
		}
		if(directionRight.sameItem(AB)){
			return true;
		}
		
		return false;
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Angle_"+pointOne+""+middle+""+pointThree;
	}
	
}
