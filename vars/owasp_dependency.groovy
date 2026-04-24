def call() {
  withCredentials([string(credentialsId: 'OWASP_Key', variable: 'NVD_KEY')]) {
    dependencyCheck(
      // --nvdValidForHours 24: Skips download if the last check was less than 24h ago (Massive speed boost)
      // --nvdApiKey: Prevents 503 errors and speeds up the download when it DOES happen (Once a day)
      additionalArguments: '--scan ./ --format XML --nvdApiKey ' + NVD_KEY + ' --nvdValidForHours 24', 
      odcInstallation: 'OWASP'
    )
  }
  dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
}
