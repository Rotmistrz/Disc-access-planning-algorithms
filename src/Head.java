public class Head {
    private int totalPath;
    private int currentSection;
    private int maxSection;

    public Head(int currentSection) {
        this.totalPath = 0;
        this.currentSection = currentSection;
        this.maxSection = 1000;
    }

    public Head move(Request request) {
        totalPath += Math.abs(currentSection - request.discSection);
        currentSection = request.discSection;
        
        return this;
    }
    
    public Head systemMove(Request request) {
    	currentSection = request.discSection;
    	
    	return this;
    }

    public int getTotalPath() {
        return totalPath;
    }

    public int getCurrentSection() {
        return currentSection;
    }

    public int getMaxSection() {
        return maxSection;
    }

    public Head setTotalPath(int totalPath) {

        this.totalPath = totalPath;

        return this;
    }

    public Head setCurrentSection(int currentSection) {
        this.currentSection = currentSection;

        return this;
    }

}