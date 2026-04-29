def call() {
  withCredentials([string(credentialsId: 'OWASP_Key', variable: 'NVD_KEY')]) {
    dependencyCheck(
      /* 
         --nvdValidForHours 168: Trusts the AMI data for 7 days (prevents the 31k record sync).
         --autoUpdate true: Keeps updates enabled, but the 168h window will skip them for now.
         --data: Points directly to the path we found on your Amazon Linux instance.
      */
      additionalArguments: """
        --scan ./ 
        --format XML 
        --nvdApiKey ${NVD_KEY} 
        --nvdValidForHours 168 
        --data /var/lib/jenkins/tools/org.jenkinsci.plugins.DependencyCheck.tools.DependencyCheckInstallation/OWASP/data
      """, 
      odcInstallation: 'OWASP'
    )
  }
  // This publishes the report to the Jenkins UI
  dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
}
