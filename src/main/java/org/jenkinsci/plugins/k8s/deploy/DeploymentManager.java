package org.jenkinsci.plugins.k8s.deploy;

import hudson.Extension;
import hudson.model.RootAction;
/**
 * The recurring UI pattern in Jenkins is to have a bunch of navigable links and 
 * command links to the left of the page. The extension point Action was born as 
 * a means to contribute custom entries to the list.
 * 
 * Three methods defined on Action are for obtaining information needed to render 
 * actions (icon, name of the link, and where it takes the user to.)
 * 
 * RootAction is a stand-alone extension point of its own, that you'd put @Extension, 
 * and these gets automatically registered as transient actions of the root Jenkins 
 * object. There's a variant of this called UnprotectedRootAction that is made 
 * accessible even to anonymous users without the read access to Jenkins.
 * 
 * Jenkins defines extension points, which are interfaces or abstract classes that 
 * model an aspect of a build system. Those interfaces define contracts of what 
 * need to be implemented, and Jenkins allows plugins to contribute those 
 * implementations
 * 
 * @author bimal
 *
 */
@Extension
public class DeploymentManager implements RootAction{

	@Override
	public String getDisplayName() {
		return "Kubernetes deployment";
	}

	@Override
	public String getIconFileName() {
		return "clipboard.png";
	}

	/**
	 * Actions often want to define additional URL space into Jenkins to show 
	 * information. For example, "test reports" action adds a whole bunch of 
	 * subpages into Jenkins. To facilitate this common use case, Jenkins uses 
	 * the Action.getUrlName() method.
	 */
	@Override
	public String getUrlName() {
		return "k8s";
	}

}
