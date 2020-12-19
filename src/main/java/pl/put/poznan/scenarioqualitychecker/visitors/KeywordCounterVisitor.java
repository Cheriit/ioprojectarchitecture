package pl.put.poznan.scenarioqualitychecker.visitors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.put.poznan.scenarioqualitychecker.logic.models.Step;

/**
 * Counts number of scenario steps with a particular keyword
 */
public class KeywordCounterVisitor implements Visitor {
	
	private Integer count = 0;
	private String keyword;
    Logger logger = LoggerFactory.getLogger(ScenarioStepCounterVisitor.class);
    
    public KeywordCounterVisitor(String keyword) {
    	this.keyword = keyword;
    }
    
    /**
     * Visit action on a specific step
     * @param step step to visit
     */
	@Override
	public void visit(Step step) {
		if(step.getContent().startsWith(keyword))
			count++;
	}
	
	public Integer getCount() { return count; }
	
	public String getKeyword() { return keyword; }
	
	public void setKeyword(String keyword) { this.keyword = keyword; }
	
	/**
	 * Method that resets counter
	 */
	public void reset() { count = 0; }
}
