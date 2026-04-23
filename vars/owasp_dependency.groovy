def call() {
  withCredentials([string(credentialsId: 'OWASP_Key', variable: 'NVD_KEY')]) {
    dependencyCheck(
      // Using single quotes (') prevents the "interpolation" warning
      additionalArguments: '--scan ./ --nvdApiKey ' + NVD_KEY, 
      odcInstallation: 'OWASP'
    )
  }
  dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
}
