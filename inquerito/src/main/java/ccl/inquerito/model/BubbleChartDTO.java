package ccl.inquerito.model;

public class BubbleChartDTO {
    
	private String label;
    private double x;
    private double y;
    private int r;
    
    
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	public BubbleChartDTO(String label, double x, double y, int r) {
		this.label = label;
		this.x = x;
		this.y = y;
		this.r = r;
	}



}
