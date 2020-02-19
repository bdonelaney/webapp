pipeline {
    agent {
        kubernetes {
            label 'testagent1'
            defaultContainer 'jnlp-agent'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
    }
}