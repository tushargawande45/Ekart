def call(String url, String credentialsId) {
    checkout([
        $class: 'GitSCM',
        branches: [[name: '*/master']],
        userRemoteConfigs: [[url: url, credentialsId: credentialsId]]
    ])
}

