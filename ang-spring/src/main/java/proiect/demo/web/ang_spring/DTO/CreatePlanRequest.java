package proiect.demo.web.ang_spring.DTO;

public class CreatePlanRequest {
	
    private String type;
    private Integer targetWeight;
    private Integer duration;
    
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getTargetWeight() {
		return targetWeight;
	}
	public void setTargetWeight(Integer targetWeight) {
		this.targetWeight = targetWeight;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
    
}
