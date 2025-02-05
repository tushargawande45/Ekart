def call(String projectName, String projectKey, String sonarServer) {
    withSonarQubeEnv(sonarServer) {
        sh """
            ${tool 'sonar-scanner'}/bin/sonar-scanner \
            -Dsonar.projectName=${projectName} \
            -Dsonar.projectKey=${projectKey} \
            -Dsonar.java.binaries=.
        """
    }
}

