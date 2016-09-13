/**
 * 
 */
package org.jenkinsci.plugins.k8s.deploy;

import java.io.IOException;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractProject;
import hudson.model.Build;
import hudson.model.BuildListener;
import hudson.model.FreeStyleProject;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import hudson.util.FormValidation;

/**
 * @author bimal
 *
 */
public class DeploymentBuilder extends Builder {
	private long time;
	
	@DataBoundConstructor
	public DeploymentBuilder(long time){
		this.time = time;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}	
	
	@Override
	public boolean perform(Build<?, ?> build, Launcher launcher, BuildListener listener)
			throws InterruptedException, IOException {
		listener.getLogger().print("Sleeping for " + this.time + " ms.");
		Thread.sleep(time);
		return true;
	}
	
	/***
	 * 
	 * @author bimal	 *
	 */
	@Extension
	public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

		@Override
		public boolean isApplicable(Class<? extends AbstractProject> jobType) {
			return jobType == FreeStyleProject.class;
		}

		@Override
		public String getDisplayName() {
			return "Deployment Builder";
		}	
		
		public FormValidation doCheckTime(@QueryParameter String time){
			try {
				// Check for a valid positive number.
				if(Long.valueOf(time) < 0){
					return FormValidation.error("Please enter a positive number.");
				} else {
					return FormValidation.ok();
				}				
			} catch (NumberFormatException e) {
				return FormValidation.error("Please enter a number.");
			}			
		}
	}
}
