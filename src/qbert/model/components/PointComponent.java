package qbert.model.components;

public class PointComponent {
    
    private int points;
    
    public PointComponent() {
        this.points = 0;
    }
    
    public void gain(int amount) {
        this.points += amount;
    }
    
    public int getPoints() {
        return this.points;
    }
}
