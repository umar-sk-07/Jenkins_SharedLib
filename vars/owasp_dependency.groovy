def call() {
  withCredentials([string(credentialsId: 'OWASP_Key', variable: 'NVD_KEY')]) {
    dependencyCheck(
      // Adding --noupdate tells it to use the local data and stop hitting the 503 error
      additionalArguments: '--scan ./ --nvdApiKey ' + NVD_KEY + ' --noupdate', 
      odcInstallation: 'OWASP'
    )
  }
  dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
}
