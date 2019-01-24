package cn.davidma.runicarcanology.render.rune.animation.core;

import java.util.ArrayList;
import java.util.List;

import cn.davidma.runicarcanology.render.rune.Circle;

public class RuneAnimation {

	public List<CircleStats> circles;
	
	public RuneAnimation() {
		circles = new ArrayList<CircleStats>();
	}
	
	public void tick(double x, double y, double z, double time) {
		
		for(CircleStats i: circles) {
			
		}
	}
}
