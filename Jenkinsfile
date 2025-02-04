pipeline {
    agent any
    tools{
        jdk ' jdk17'
        maven 'maven3'
    }
    
    environment {
        SCANNER_HOME= tool 'sonar-scanner'
        // DOCKER_PASSWORD = credentials('b2db1116-9a36-45f0-b566-333bd021cab2')
    }

    stages {
        stage('Git-Checkout') {
            steps {
                git changelog: false, credentialsId: 'd8bed3b5-241c-4e87-9889-b3d7757a0600', poll: false, url: 'https://github.com/tushargawande45/Ekart'
            }
        }
        stage('Compile') {
            steps {
                sh 'mvn clean compile -DskipTests=true'    
            }
        }
        stage('OWASP Scan') {
            steps {
                dependencyCheck additionalArguments: '--scan ./ ', odcInstallation: 'DP'
                dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
            }
        }
        stage('Sonarqube') {
            steps {
                withSonarQubeEnv('sonar-server'){
                    sh ''' $SCANNER_HOME/bin/sonar-scanner -Dsonar.projectName=Ekart \
                    -Dsonar.java.binaries=. \
                    -Dsonar.projectKey=Ekart'''
                }
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests=true'    
            }
        }
        stage('Docker Build, Tag, and Push') {
            steps {
                script {
                    sh "docker login -u tushargawande --password Tushar@123"
                    sh "docker build -t ekart -f docker/Dockerfile ."
                    sh "docker tag ekart tushargawande/ekart:latest"
                    sh "docker push tushargawande/ekart:latest"
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    sh "docker run -d --name shopping-shopping -p 8070:8070 tushargawande/ekart:latest"
                }
            }
        }
    }
}
