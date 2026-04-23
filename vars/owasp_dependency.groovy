def call() {
  // 1. Fetch the secret key from Jenkins and store it in the variable 'NVD_KEY'
  withCredentials([string(credentialsId: 'OWASP_Key', variable: 'NVD_KEY')]) {
    
    // 2. Pass the key as a manual argument in the additionalArguments string
    dependencyCheck(
      additionalArguments: "--scan ./ --nvdApiKey ${NVD_KEY}", 
      odcInstallation: 'OWASP'
    )
  }
  
  // 3. Publish the results
  dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
}
