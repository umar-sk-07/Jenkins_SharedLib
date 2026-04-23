def call() {
  // Use the credentials ID 'NVD_API_KEY' that you created in Jenkins
  dependencyCheck(
    nvdApiKey: credentials('OWASP_Key'), 
    additionalArguments: '--scan ./', 
    odcInstallation: 'OWASP'
  )
  dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
}
